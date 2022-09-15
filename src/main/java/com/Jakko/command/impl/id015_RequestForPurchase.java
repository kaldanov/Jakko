package com.Jakko.command.impl;

import com.Jakko.command.Command;
import com.Jakko.enums.WaitingType;
import com.Jakko.model.custom.*;
import com.Jakko.util.ButtonsLeaf;
import com.Jakko.util.Const;
import com.Jakko.util.DateKeyboard;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class id015_RequestForPurchase extends Command {

    private int deleteId;
    private int wrongDeleteId;
    private List<String> names = new ArrayList<>();
    private List<String> ids = new ArrayList<>();
    private List<String> unitNames = new ArrayList<>();
    private List<String> unitCodes = new ArrayList<>();
    private String data;
    private String nameData;
    private DateKeyboard dateKeyboard;
    private Date start;
    private String unitCode;
    private String nomenName;
    private String description;
    private String date;
    private int count;
    private int priority;
    private int type;
    private Master master;
    private RequestZakup zakup;

    @Override
    public boolean execute() throws TelegramApiException, SQLException, IOException {
        if (isMaster()) {
            delete(updateMessageId);
            sendMessage(getText(64));
            return EXIT;
        }
        switch (waitingType) {
            case START:
                delete();
                getExtruder();
                return COMEBACK;
            case CHOOSE_EXTRUDER:
                if (update.hasCallbackQuery()) {
                    delete();
                    if (isButton(9)) {
                        deleteId = sendMessageWithKeyboard(getText(1), 1);
                    } else if (ids.contains(updateMessageText)) {
                        zakup = new RequestZakup();
                        if (updateMessageText.equals(buttonRepo.findById(27).getName())) {
                            data = "";
                        } else
                            data = updateMessageText;
                        nameData = getNameByData(updateMessageText);
                        zakup.setEquipmentName(nameData);
                        String stringBuilder = "Текущее оборудование: " + nameData +
                                next + next + getText(62);
                        deleteId = sendMessageWithKeyboard(stringBuilder, 20);
                        waitingType = WaitingType.NOMENCLATURES;
                    }
                } else wrongData();
                return COMEBACK;
            case NOMENCLATURES:
                delete();
                if (hasMessageText()) {
                    getExtruderNames(updateMessageText);
                } else if (isButton(9)) {
                    getExtruder();
                } else if (isButton(27)) {
                    deleteId = sendMessageWithKeyboard(getText(41), 11);
                    waitingType = WaitingType.OWN_VERSION;
                } else wrongData();
                return COMEBACK;
            case CHOOSE_NOMENCLATURES:
                if (isButton(9)) {
                    delete();
                    String stringBuilder = "Текущее оборудование: " + nameData +
                            next + next + getText(62);
                    deleteId = sendMessageWithKeyboard(stringBuilder, 20);
                    waitingType = WaitingType.NOMENCLATURES;
                } else if (hasCallbackQuery()) {
                    nomenName = updateMessageText;
                    zakup.setNomenclatureName(getNameByData(nomenName));
                    editMessageWithKeyboard(getNameByData(updateMessageText), chatId, deleteId, (InlineKeyboardMarkup) keyboardMarkUpService.select(12));
                    waitingType = WaitingType.CONFIRM;
                } else wrongData();
                return COMEBACK;
            case CONFIRM:
                delete();
                if (hasCallbackQuery()) {
                    if (isButton(9)) {
                        String stringBuilder = "Текущее оборудование: " + nameData +
                                next + next + getText(62);
                        deleteId = sendMessageWithKeyboard(stringBuilder, 20);
                        waitingType = WaitingType.NOMENCLATURES;
                    } else if (isButton(15)) {
                        type = 0;
                        dateKeyboard = new DateKeyboard();
                        sendStartDate();
                        waitingType = WaitingType.START_DATE;
                        return COMEBACK;
                    }
                } else wrongData();
                return COMEBACK;
            case OWN_VERSION:
                delete();
                if (hasMessageText()) {
                    nomenName = updateMessageText;
                    zakup.setNomenclatureName(nomenName);
                    type = 1;
                    dateKeyboard = new DateKeyboard();
                    sendStartDate();
                    waitingType = WaitingType.START_DATE;
                } else if (isButton(9)) {
                    getExtruder();
                } else wrongData();
                return COMEBACK;
            case START_DATE:
                deleteMessage(updateMessageId);
                if (hasCallbackQuery()) {
                    if (dateKeyboard.isNext(updateMessageText)) {
                        sendStartDate();
                        return COMEBACK;
                    }
                    start = dateKeyboard.getDateDate(updateMessageText);
                    zakup.setZakupDate(start);
                    start.setHours(0);
                    start.setMinutes(0);
                    start.setMinutes(0);
                    deleteId = sendMessageWithKeyboard(getText(65), 11);
                    waitingType = WaitingType.DESCRIPTION;
                }
                return COMEBACK;
            case DESCRIPTION:
                delete();
                if (hasMessageText()) {
                    description = updateMessageText;
                    zakup.setDescription(description);
                    deleteId = sendMessageWithKeyboard(getText(67), 21);
                    waitingType = WaitingType.SET_PHOTO;
                } else if (isButton(9)) {
                    dateKeyboard = new DateKeyboard();
                    sendStartDate();
                    waitingType = WaitingType.START_DATE;
                } else wrongData();
                return COMEBACK;
            case SET_PHOTO:
                delete();
                if (hasPhoto()) {
                    zakup.setFile(updateMessagePhoto);
                    deleteId = sendMessageWithKeyboard(getText(63), 11);
                    waitingType = WaitingType.COUNT;
                } else if (hasVideo()) {
                    zakup.setFile(updateMessageVideo);
                    deleteId = sendMessageWithKeyboard(getText(63), 11);
                    waitingType = WaitingType.COUNT;
                } else if (isButton(38)) {
                    deleteId = sendMessageWithKeyboard(getText(63), 11);
                    waitingType = WaitingType.COUNT;
                } else if (isButton(9)) {
                    deleteId = sendMessageWithKeyboard(getText(65), 11);
                    waitingType = WaitingType.DESCRIPTION;
                } else wrongData();
                return COMEBACK;
            case COUNT:
                delete();
                if (hasMessageText()) {
                    try {
                        count = Integer.parseInt(updateMessageText);
                        zakup.setCount(count);
                        getUnitCode();
                    } catch (Exception e) {
                        wrongData();
                    }
                } else if (isButton(9)) {
                    dateKeyboard = new DateKeyboard();
                    sendStartDate();
                    waitingType = WaitingType.START_DATE;
                } else wrongData();
                return COMEBACK;
            case CHOOSE_UNIT:
                if (update.hasCallbackQuery()) {
                    delete();
                    if (isButton(9)) {
                        deleteId = sendMessageWithKeyboard(getText(63), 11);
                        waitingType = WaitingType.COUNT;
                    } else if (unitCodes.contains(updateMessageText)) {
                        unitCode = updateMessageText;
                        zakup.setUnit(getNameByData1(unitCode));
                        deleteId = sendMessageWithKeyboard(getText(73), 24);
                        waitingType = WaitingType.CHOOSE_PRIORITY;
                    }
                } else wrongData();
                return COMEBACK;
            case CHOOSE_PRIORITY:
                if (update.hasCallbackQuery()) {
                    delete();
                    if (isButton(9)) {
                        getUnitCode();
                    } else if (isButton(48) || isButton(49) || isButton(50) || isButton(51)) {
                        zakup.setDate(new Date());
                        zakup.setExecuted(false);
                        zakup.setUser(usersRepo.findByChatId(chatId));
                        if (isButton(48)) {
                            priority = 0;
                            zakup.setPriority(0);
                        } else if (isButton(49)) {
                            priority = 1;
                            zakup.setPriority(1);
                        } else if (isButton(50)) {
                            priority = 2;
                            zakup.setPriority(2);
                        } else if (isButton(51)) {
                            priority = 3;
                            zakup.setPriority(3);
                        }
                        zakup = requestZakupRepo.save(zakup);
                        date = new SimpleDateFormat("dd.MM.yyyy").format(start);
                        save();
                        for (Zakupshik z : zakupshikRepo.findAll()) {
                            sendMessage(getText(68), z.getUser().getChatId());
                        }
                        sendMessage("Ваша заявка №" + zakup.getId() + " успешно отправлено!");
                    }
                } else wrongData();
                return COMEBACK;
        }
        return EXIT;
    }

    private void save() {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            client.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("webuser", "!48597586!"));
            HttpPost httpPost = new HttpPost("http://178.89.184.2:5142/UTP_JAKKOKZ/hs/rooturl/jaktempl/inout/");
            StringEntity params;
            String list =
                    "{" +
                            "\"item\":1" +
                            ",\"nomentype\":" + type +
                            ",\"nomencode\":" + "\"" + nomenName + "\"" +
                            ",\"description\":" + "\"" + description + "\"" +
                            ",\"nomenvalue\":" + count +
                            ",\"unytcode\":" + "\"" + unitCode.trim() + "\"" +
                            ",\"extlinecode\":" + "\"" + data + "\"" +
                            ",\"deliverydate\":\"" + date + "\"" + "}";

            params = new StringEntity(
                    "{\"success\":1" +
                            ",\"what\":\"savepurchasedoc\"" +
                            ",\"masterid\":" + master.getId2() +
                            ",\"priority\":" + priority +
                            ",\"date\":\"" + date + "\"" +
                            ",\"list\":[" + list + "]}", "UTF-8");


            list = "{\"success\":1" +
                    ",\"what\":\"savepurchasedoc\"" +
                    ",\"masterid\":" + master.getId2() +
                    ",\"date\":\"" + date + "\"" +
                    ",\"list\":[" + list + "]}";


            System.out.println(list);


            httpPost.setEntity(params);
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
                System.out.println(jsonObject);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendStartDate() throws TelegramApiException {
        toDeleteKeyboard(sendMessageWithKeyboard(getText(52), dateKeyboard.getCalendarKeyboard()));
    }

    private void getUnitCode() {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            client.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("WSUser", "Ti9Na5ju@*"));

            HttpPost httpPost = new HttpPost("http://178.89.184.2:5142/UTP_JAKKOKZ/hs/rooturl/jaktempl/inout/");
            StringEntity params;
            params = new StringEntity("{\"success\":1,\"what\":\"unitslist\"}");
            httpPost.setEntity(params);
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
                System.out.println(jsonObject);
                JSONArray lang = (JSONArray) jsonObject.get("data");
                for (Object o : lang) {
                    JSONObject innerObj = (JSONObject) o;
                    unitNames.add(innerObj.get("unytname").toString());
                    unitCodes.add(innerObj.get("unytcode").toString());
                }
                unitNames.add(buttonRepo.findById(9).getName());
                unitCodes.add(buttonRepo.findById(9).getName());
                ButtonsLeaf buttonsLeaf = new ButtonsLeaf(unitNames, unitCodes);

                deleteId = sendMessageWithKeyboard(getText(66), buttonsLeaf.getListButtonWithDataList());
                waitingType = WaitingType.CHOOSE_UNIT;
            }

        } catch (IOException | TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void getExtruder() {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            client.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("WSUser", "Ti9Na5ju@*"));

            HttpPost httpPost = new HttpPost("http://178.89.184.2:5142/UTP_JAKKOKZ/hs/rooturl/jaktempl/inout/");
            StringEntity params;
            if (master.isType())
                params = new StringEntity("{\"success\":1,\"what\":\"extline\"}");
            else
                params = new StringEntity("{\"success\":1,\"what\":\"injline\"}");
            httpPost.setEntity(params);
            names = new ArrayList<>();
            ids = new ArrayList<>();
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
                JSONArray lang = (JSONArray) jsonObject.get("data");
                for (Object o : lang) {
                    JSONObject innerObj = (JSONObject) o;
                    if (master.isType()) {
                        names.add(innerObj.get("extline").toString());
                        ids.add(innerObj.get("extlinecode").toString());
                    } else {
                        names.add(innerObj.get("line").toString());
                        ids.add(innerObj.get("linecode").toString());
                    }
                }
                names.add(buttonRepo.findById(27).getName());
                ids.add(buttonRepo.findById(27).getName());
                names.add(buttonRepo.findById(9).getName());
                ids.add(buttonRepo.findById(9).getName());
                ButtonsLeaf buttonsLeaf = new ButtonsLeaf(names, ids);

                deleteId = sendMessageWithKeyboard(getText(49), buttonsLeaf.getListButtonWithDataList());
                waitingType = WaitingType.CHOOSE_EXTRUDER;
            }

        } catch (IOException | TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void getExtruderNames(String nomenclature) {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            client.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("WSUser", "Ti9Na5ju@*"));

            HttpPost httpPost = new HttpPost("http://178.89.184.2:5142/UTP_JAKKOKZ/hs/rooturl/jaktempl/inout/");
            StringEntity params = new StringEntity("{\"success\":1,\"what\":\"nomenlist\"}");
            httpPost.setEntity(params);
            names = new ArrayList<>();
            ids = new ArrayList<>();
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
                JSONArray lang = (JSONArray) jsonObject.get("data");
                for (Object o : lang) {
                    JSONObject innerObj = (JSONObject) o;
                    names.add(innerObj.get("nomemname").toString());
                    ids.add(innerObj.get("nomencode").toString());
                }

                List<String> names1 = new ArrayList<>();
                List<String> ids1 = new ArrayList<>();
                boolean isFind = false;
                for (int i = 0; i < names.size(); i++) {
                    if (names.get(i).toLowerCase().contains(nomenclature.toLowerCase())) {
                        names1.add(names.get(i));
                        ids1.add(ids.get(i));
                        isFind = true;
                    }
                }
                if (isFind) {
                    if (names1.size() > 90) {
                        deleteId = sendMessageWithKeyboard(getText(33), 11);
                    } else {
                        names = names1;
                        ids = ids1;
                        names.add(buttonRepo.findById(9).getName());
                        ids.add(buttonRepo.findById(9).getName());
                        ButtonsLeaf buttonsLeaf = new ButtonsLeaf(names, ids);
                        deleteId = sendMessageWithKeyboard(getText(32), buttonsLeaf.getListButtonWithDataList());
                        waitingType = WaitingType.CHOOSE_NOMENCLATURES;
                    }
                } else {
                    deleteId = sendMessageWithKeyboard(getText(33), 11);
                }
            }

        } catch (IOException | TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String getNameByData(String equipmentName) {
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i).equals(equipmentName)) {
                return names.get(i);
            }
        }
        return null;
    }

    private String getNameByData1(String equipmentName) {
        for (int i = 0; i < unitCodes.size(); i++) {
            if (unitCodes.get(i).equals(equipmentName)) {
                return unitNames.get(i);
            }
        }
        return null;
    }

    private void wrongData() throws TelegramApiException {
        wrongDeleteId = sendMessage(Const.WRONG_DATA_TEXT);
    }

    private boolean isMaster() {
        master = masterRepo.findByUser(usersRepo.findByChatId(chatId));
        return master == null;
    }

    private void delete() {
        delete(updateMessageId);
        delete(deleteId);
        delete(wrongDeleteId);
    }

}
