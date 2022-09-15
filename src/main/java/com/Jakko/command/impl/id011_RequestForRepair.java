package com.Jakko.command.impl;

import com.Jakko.command.Command;
import com.Jakko.enums.WaitingType;
import com.Jakko.model.custom.Master;
import com.Jakko.model.custom.Menu;
import com.Jakko.model.custom.Repair;
import com.Jakko.model.custom.Report;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class id011_RequestForRepair extends Command {

    private int deleteId;
    private int wrongDeleteId;
    private List<String> names = new ArrayList<>();
    private List<String> ids = new ArrayList<>();
    private String data;
    private Repair repair;
    private Master master = new Master();


    @Override
    public boolean execute() throws TelegramApiException, SQLException, IOException {
        if (!isMaster()) {
            delete(updateMessageId);
            sendMessage(getText(10));
            return EXIT;
        }
        switch (waitingType) {
            case START:
                delete(updateMessageId);
                if (master.isType())
                    getExtruder();
                else getInjection();
                return COMEBACK;
            case CHOOSE_EXTRUDER:
                if (update.hasCallbackQuery()) {
                    delete();
                    repair = new Repair();
                    if (isButton(9)) {
                        deleteId = sendMessageWithKeyboard(getText(1), 1);
                    } else if (isButton(27)) {
                        deleteId = sendMessageWithKeyboard(getText(41), 11);
                        waitingType = WaitingType.OWN_VERSION;
                    } else if (ids.contains(updateMessageText)) {
                        data = updateMessageText;
                        repair.setName(getNameByData(data));
                        String stringBuilder = "Текущее оборудование: " + getNameByData(data) +
                                next + next + getText(39);
                        deleteId = sendMessageWithKeyboard(stringBuilder, 11);
                        waitingType = WaitingType.CHOOSE_OPTION;
                    }
                } else wrongData();
                return COMEBACK;
            case OWN_VERSION:
                delete();
                if (hasMessageText()) {
                    repair.setName(updateMessageText);
                    deleteId = sendMessageWithKeyboard(getText(39), 11);
                    waitingType = WaitingType.CHOOSE_OPTION;
                } else if (isButton(9)) {
                    if (master.isType())
                        getExtruder();
                    else getInjection();
                } else wrongData();
                return COMEBACK;
            case CHOOSE_OPTION:
                delete();
                if (hasMessageText()) {
                    repair.setTextRepair(updateMessageText);
                    deleteId = sendMessageWithKeyboard(getText(40), 11);
                    waitingType = WaitingType.SET_PHOTO;
                } else if (isButton(9)) {
                    getExtruder();
                } else wrongData();
                return COMEBACK;
            case SET_PHOTO:
                delete();
                if (hasPhoto()) {
                    repair.setPhoto(updateMessagePhoto);
                    repair.setSenderChatId(chatId);
                    repair.setDate(new Date());
                    repair = repairRepo.save(repair);
                    List<Menu> menus = menuRepo.findAll();
                    for (Menu menu : menus) {
                        sendMessage(getText(43), menu.getChatId());
                    }
                    deleteId = sendMessage(getText(42));
                } else if (isButton(9)) {
                    deleteId = sendMessageWithKeyboard(getText(39), 11);
                    waitingType = WaitingType.CHOOSE_OPTION;
                } else wrongData();
                return COMEBACK;
        }
        return EXIT;
    }

    private String getNameByData(String equipmentName) {
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i).equals(equipmentName)) {
                return names.get(i);
            }
        }
        return null;
    }

    private void getExtruder() {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            client.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("WSUser", "Ti9Na5ju@*"));

            HttpPost httpPost = new HttpPost("http://178.89.184.2:5142/UTP_JAKKOKZ/hs/rooturl/jaktempl/inout/");
            StringEntity params = new StringEntity("{\"success\":1,\"what\":\"extline\"}");
            httpPost.setEntity(params);
            names = new ArrayList<>();
            ids = new ArrayList<>();
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
                JSONArray lang = (JSONArray) jsonObject.get("data");
                for (Object o : lang) {
                    JSONObject innerObj = (JSONObject) o;
                    names.add(innerObj.get("extline").toString());
                    ids.add(innerObj.get("extlinecode").toString());
                }

                for (int i = 0; i < ids.size() - 1; i++) {
                    for (int j = 0; j < ids.size() - i - 1; j++) {
                        if (Integer.parseInt(ids.get(j).split("_")[1]) > Integer.parseInt(ids.get(j + 1).split("_")[1])) {
                            String temp = ids.get(j);
                            ids.set(j, ids.get(j + 1));
                            ids.set(j + 1, temp);
                            String temp2 = names.get(j);
                            names.set(j, names.get(j + 1));
                            names.set(j + 1, temp2);
                        }
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

    private void getInjection() {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            client.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("WSUser", "Ti9Na5ju@*"));

            HttpPost httpPost = new HttpPost("http://178.89.184.2:5142/UTP_JAKKOKZ/hs/rooturl/jaktempl/inout/");
            StringEntity params = new StringEntity("{\"success\":1,\"what\":\"injline\"}");
            httpPost.setEntity(params);
            names = new ArrayList<>();
            ids = new ArrayList<>();
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
                JSONArray lang = (JSONArray) jsonObject.get("data");
                for (Object o : lang) {
                    JSONObject innerObj = (JSONObject) o;
                    names.add(innerObj.get("line").toString());
                    ids.add(innerObj.get("linecode").toString());
                }

                for (int i = 0; i < ids.size() - 1; i++) {
                    for (int j = 0; j < ids.size() - i - 1; j++) {
                        if (Integer.parseInt(ids.get(j).split("_")[1]) > Integer.parseInt(ids.get(j + 1).split("_")[1])) {
                            String temp = ids.get(j);
                            ids.set(j, ids.get(j + 1));
                            ids.set(j + 1, temp);
                            String temp2 = names.get(j);
                            names.set(j, names.get(j + 1));
                            names.set(j + 1, temp2);
                        }
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

    private boolean isMaster() {
        master = masterRepo.findByUser(usersRepo.findByChatId(chatId));
        return master != null;
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
