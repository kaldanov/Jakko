package com.Jakko.command.impl;

import com.Jakko.command.Command;
import com.Jakko.enums.WaitingType;
import com.Jakko.model.custom.Menu;
import com.Jakko.model.custom.Repair;
import com.Jakko.util.ButtonsLeaf;
import com.Jakko.util.Const;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class id014_Operator extends Command {

    private int deleteId;
    private int wrongDeleteId;
    private List<String> names = new ArrayList<>();
    private List<String> ids = new ArrayList<>();
    private String data;
    private Repair repair;

    @Override
    public boolean execute() throws TelegramApiException, SQLException, IOException {
        switch (waitingType) {
            case START:
                delete();
                if (isButton(31)) {
                    getExecutedList();
                } else if (isButton(32)) {
                    getInProcessList();
                } else if (isButton(33)) {
                    return EXIT;
                } else wrongData();
                return COMEBACK;
            case CHOOSE_OPTION:
                delete();
                if (hasCallbackQuery()) {
                    try {
                        Repair repair = repairRepo.findById(Integer.parseInt(updateMessageText));
                        if (repair != null) {
                            String s = "<b>Заявка №" + repair.getId() + "</b>" + next + next + next + "<b>Оборудование:</b> " + repair.getName() + next +
                                    next + "<b>Текст:</b> " + repair.getTextRepair();
                            toDeleteKeyboard(bot.execute(new SendPhoto().setChatId(chatId).setPhoto(repair.getPhoto()).setCaption(s).setParseMode("html").setReplyMarkup(keyboardMarkUpService.select(17))).getMessageId());
                        }
                    } catch (Exception e) {
                        getExecutedList();
                    }
                } else wrongData();
                return COMEBACK;

            case CHOOSE_FUNCTION:
                delete();
                if (hasCallbackQuery()) {
                    Repair repair = repairRepo.findById(Integer.parseInt(updateMessageText));
                    if (repair != null) {
                        String s = "<b>Заявка №" + repair.getId() + "</b>" + next + next + next + "<b>Оборудование:</b> " + repair.getName() + next +
                                next + "<b>Текст:</b> " + repair.getTextRepair();
                        List<String> names = new ArrayList<>();
                        names.add("Исполнено");
                        names.add("Назад");
                        names.add(buttonRepo.findById(33).getName());
                        List<String> data = new ArrayList<>();
                        data.add(String.valueOf(repair.getId()));
                        data.add("Назад");
                        data.add(buttonRepo.findById(33).getName());
                        ButtonsLeaf buttonsLeaf = new ButtonsLeaf(names, data);
                        toDeleteKeyboard(bot.execute(new SendPhoto().setChatId(chatId).setParseMode("html").setPhoto(repair.getPhoto()).setCaption(s).setReplyMarkup(buttonsLeaf.getListButtonWithDataList())).getMessageId());
                        waitingType = WaitingType.IN_PROCESS;
                    }
                } else wrongData();
                return COMEBACK;
            case EXECUTED:
                delete();
                if (hasCallbackQuery()) {
                    if (updateMessageText.equals("Назад")) {
                        getExecutedList();
                    }
                } else wrongData();
                return COMEBACK;
            case IN_PROCESS:
                delete();
                if (hasCallbackQuery()) {
                    try {
                        Repair repair = repairRepo.findById(Integer.parseInt(updateMessageText));
                        if (repair != null) {
                            repair.setExecuted(true);
                            repairRepo.save(repair);
                            sendMessage("Исполнено");
                            String s = "Здравствуйте, " + usersRepo.findByChatId(repair.getSenderChatId()).getFullName() + ".\n" +
                                    "На Ваше обращение под №" + repair.getId() + " от " + new SimpleDateFormat("dd.MM.yyyy").format(repair.getDate()) +
                                    " года, время " + new SimpleDateFormat("HH:mm").format(repair.getDate()) + " сообщаем следующее: Неполадки устранены.";
                            sendMessage(s, repair.getSenderChatId());
                        }
                    } catch (Exception e) {
                        getInProcessList();
                    }

                } else wrongData();
                return COMEBACK;
        }
        return EXIT;
    }

    private void getInProcessList() throws TelegramApiException {
        List<String> names = new ArrayList<>();
        List<String> data = new ArrayList<>();
        List<Repair> repairs = repairRepo.findAllByExecutedIsFalse();
        if (repairs != null && repairs.size() != 0) {
            for (Repair repair : repairs) {
                names.add(repair.getName());
                data.add(String.valueOf(repair.getId()));
            }
            ButtonsLeaf buttonsLeaf = new ButtonsLeaf(names, data);
            toDeleteKeyboard(sendMessageWithKeyboard(getText(49), buttonsLeaf.getListButtonWithDataList()));
            waitingType = WaitingType.CHOOSE_FUNCTION;
        } else
            deleteId = sendMessage(48);
    }

    private void getExecutedList() throws TelegramApiException {
        List<String> names = new ArrayList<>();
        List<String> data = new ArrayList<>();
        List<Repair> repairs = repairRepo.findAllByExecutedIsTrue();
        if (repairs != null && repairs.size() != 0) {
            for (Repair repair : repairs) {
                names.add(repair.getName());
                data.add(String.valueOf(repair.getId()));
            }
            ButtonsLeaf buttonsLeaf = new ButtonsLeaf(names, data);
            toDeleteKeyboard(sendMessageWithKeyboard(getText(49), buttonsLeaf.getListButtonWithDataList()));
            waitingType = WaitingType.CHOOSE_OPTION;
        } else
            deleteId = sendMessage(48);
    }


    private void wrongData() throws TelegramApiException {
        wrongDeleteId = sendMessage(Const.WRONG_DATA_TEXT);
    }

    private void delete() {
        delete(updateMessageId);
        delete(deleteId);
        delete(wrongDeleteId);
    }

}
