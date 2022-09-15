package com.Jakko.model.standart;

import com.Jakko.enums.ParseMode;
import com.Jakko.model.custom.RejectReason;
import com.Jakko.model.custom.Report;
import com.Jakko.repository.RejectReasonRepo;
import com.Jakko.repository.ReportRepo;
import com.Jakko.repository.TelegramBotRepositoryProvider;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SaveDocument extends TimerTask {
    DefaultAbsSender bot;

    private ReportRepo reportRepo = TelegramBotRepositoryProvider.getReportRepo();
    private RejectReasonRepo rejectReasonRepo = TelegramBotRepositoryProvider.getRejectReasonRepo();

    public SaveDocument(DefaultAbsSender bot) {
        this.bot = bot;
    }

    @Override
    public void run() {

        try {
            List<Report> reports = reportRepo.findAll();
            for (Report report : reports) {
                save(report);
            }
            List<RejectReason> rejectReasons = rejectReasonRepo.findAll();
            rejectReasonRepo.deleteAll();
            reportRepo.deleteAll();

            Set<Long> chatIds = new HashSet<>();

            for (Report report : reports) {
                chatIds.add(report.getMaster().getUser().getChatId());
            }

            for (Long l : chatIds) {
                bot.execute(new SendMessage().setParseMode(ParseMode.html.name()).setChatId(l).setText("Ваш смен окончен!\nВаши отчеты отправлены в 1С"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void save(Report report) {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            client.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("webuser", "!48597586!"));
            HttpPost httpPost = new HttpPost("http://178.89.184.2:5142/UTP_JAKKOKZ/hs/rooturl/jaktempl/inout/");

            StringBuilder rejectList = new StringBuilder("[");
            int item = 1;
            int counter = 0;
            if (report.getRejectReasons().size() != 0) {
                for (RejectReason rejectReason : report.getRejectReasons()) {
                    if (item < 4) {
                        if (counter != 0) {
                            rejectList.append(",");
                        }
                        rejectList.append("{\"item\":").append(item).append(",\"rejectid\":").
                                append(rejectReason.getReject().getId1()).append(",\"timeinterval\":").append(rejectReason.getTime()).append("}");
                    }
                    item++;
                    counter++;
                }
            }
            rejectList.append("]");

            String list =
                    "{" +
                            "\"item\":1" +
                            ",\"extlinecode\":" + "\"" + report.getEquipmentName() + "\"" +
                            ",\"nomencode\":" + "\"" + report.getNomenclatureId() + "\"" +
                            ",\"nomencycle\":" + report.getCycle() +
                            ",\"nomeneye\":" + report.getEye() +
                            ",\"makedvalue\":" + report.getCount() +
                            ",\"normvalue\":0" +
                            ",\"secondval1\":0" +
                            ",\"secondval2\":0" +
                            ",\"brokenval\":0" +
                            ",\"standartbroken\":0" +
                            ",\"timeinterval\":" + report.getTime() +
                            ",\"rejectlist\":" + rejectList + "}";
            StringEntity params = new StringEntity(
                    "{\"success\":1" +
                            ",\"what\":\"savedoc\"" +
                            ",\"linetype\":\"" + report.getType() + "\"" +
                            ",\"day\":" + report.isDay() +
                            ",\"masterid\":" + report.getMaster().getId2() +
                            ",\"date\":\"" + new SimpleDateFormat("dd.MM.yyyy").format(report.getDate()) + "\"" +
                            ",\"list\":[" + list + "]}");
            System.out.println(params);
            httpPost.setEntity(params);
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
                System.out.println(jsonObject);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
