package com.Jakko.command.impl;

import com.Jakko.command.Command;
import com.Jakko.enums.WaitingType;
import com.Jakko.model.custom.Master;
import com.Jakko.model.custom.Reject;
import com.Jakko.model.custom.RejectReason;
import com.Jakko.model.custom.Report;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class id005_Extruder extends Command {

    private int deleteId;
    private int wrongDeleteId;
    private boolean isDay;  // true - day, false - night
    private List<String> names = new ArrayList<>();
    private List<String> ids = new ArrayList<>();
    private List<String> idsData = new ArrayList<>();
    private List<String> namesData = new ArrayList<>();
    private String data;
    private Report report;
    private boolean isEdit = false;
    private int sizeOfExtruder = 0;
    private Reject reject = new Reject();
    private Master master = new Master();
    private DateKeyboard dateKeyboard;
    private Date start;
    private Map<String, Integer> mapForMessage = new HashMap<>();
    private Map<String, WaitingType> mapForWaitingType = new HashMap<>();
    private int indexReport;
    private int rejectReasonId;
    private boolean isAddReject = false;

    @Override
    public boolean execute() throws TelegramApiException, SQLException, IOException {
        if (!isMaster()) {
            delete(updateMessageId);
            sendMessage(getText(10));
            return EXIT;
        }
        switch (waitingType) {
            case START:
                List<Report> reports = reportRepo.findAll();
                if (reports.size() == 0) {
                    delete(updateMessageId);
                    deleteId = sendMessageWithKeyboard(getText(5), 4);
                    waitingType = WaitingType.CHOOSE_DATE;
                } else {
                    isDay = reports.get(0).isDay();
                    start = reports.get(0).getDate();
                    getExtruder();
                }
                return COMEBACK;
            case CHOOSE_DATE:
                if (update.hasCallbackQuery()) {
                    delete();
                    if (isButton(11)) {
                        dateKeyboard = new DateKeyboard();
                        deleteId = sendStartDate();
                        isDay = true;
                        waitingType = WaitingType.SET_DATE;
                    } else if (isButton(12)) {
                        dateKeyboard = new DateKeyboard();
                        deleteId = sendStartDate();
                        isDay = false;
                        waitingType = WaitingType.SET_DATE;
                    } else if (isButton(9)) {
                        sendMessageWithKeyboard(getText(6), 3);
                        return EXIT;
                    }
                    return COMEBACK;

                } else wrongData();
            case SET_DATE:
                deleteMessage(updateMessageId);
                if (hasCallbackQuery()) {
                    if (dateKeyboard.isNext(updateMessageText)) {
                        sendStartDate();
                        return COMEBACK;
                    }
                    start = dateKeyboard.getDateDate(updateMessageText);
                    start.setHours(0);
                    start.setMinutes(0);
                    start.setSeconds(0);
                    getExtruder();
                } else wrongData();
                return COMEBACK;
            case CHOOSE_EXTRUDER:
                if (update.hasCallbackQuery()) {
                    delete();
                    if (isButton(9)) {
                        deleteId = sendMessageWithKeyboard(getText(6), 3);
                        waitingType = WaitingType.CHOOSE_DATE;
                    } else if (ids.contains(updateMessageText)) {
                        data = updateMessageText;
                        reports = reportRepo.findAllByEquipmentName(data);
                        if (reports != null && reports.size() > 0) {
                            deleteId = sendMessageWithKeyboard(getText(59), getInfo(reports));
                        } else {
                            data = updateMessageText;
                            String stringBuilder = "<b>Текущее оборудование:</b> " + getNameByDataOf(data) +
                                    next + next + getText(31);
                            deleteId = sendMessageWithKeyboard(stringBuilder, 11);
                            waitingType = WaitingType.NOMENCLATURES;
                        }
                    }
                    return COMEBACK;

                } else wrongData();
            case NOMENCLATURES:
                delete();
                if (hasMessageText()) {
                    isEdit = false;
                    isAddReject = false;
                    report = new Report();
                    report.setEquipmentName(data);
                    getExtruderNames(data, updateMessageText);
                } else if (isButton(9)) {
                    getExtruder();
                } else wrongData();
                return COMEBACK;
            case CHOOSE_NOMENCLATURES:
                if (hasCallbackQuery()) {
                    report.setNomenclatureId(updateMessageText);
                    report.setNomenclatureName(getNameByData(updateMessageText));
                    editMessageWithKeyboard(getNameByData(updateMessageText), chatId, deleteId, (InlineKeyboardMarkup) keyboardMarkUpService.select(12));
                    waitingType = WaitingType.CONFIRM;
                } else if (isButton(9)) {
                    deleteId = sendMessageWithKeyboard(getText(31), 11);
                    waitingType = WaitingType.NOMENCLATURES;
                } else wrongData();
                return COMEBACK;
            case CONFIRM:
                delete();
                if (hasCallbackQuery()) {
                    if (isButton(9)) {
                        deleteId = sendMessageWithKeyboard(getText(31), 11);
                        waitingType = WaitingType.NOMENCLATURES;
                    } else if (isButton(15)) {
                        deleteId = sendMessageWithKeyboard(getText(34), 11);
                        waitingType = WaitingType.EYES;
                    }
                } else wrongData();
                return COMEBACK;
            case EYES:
                delete();
                if (hasMessageText()) {
                    try {
                        report.setEye(Integer.parseInt(updateMessageText));
                        if (!isEdit) {
                            deleteId = sendMessageWithKeyboard(getText(35), 11);
                            waitingType = WaitingType.CIRCLE;
                        } else {
                            reportRepo.save(report);
                            deleteId = getInformation();
                        }
                    } catch (Exception e) {
                        wrongData();
                        deleteId = sendMessageWithKeyboard(getText(34), 11);
                    }
                } else if (isButton(9)) {
                    if (!isEdit) {
                        deleteId = sendMessageWithKeyboard(getText(31), 11);
                        waitingType = WaitingType.NOMENCLATURES;
                    } else {
                        deleteId = getInformation();
                    }
                } else wrongData();
                return COMEBACK;
            case CIRCLE:
                delete();
                if (hasMessageText()) {
                    try {
                        report.setCycle(Integer.parseInt(updateMessageText));
                        if (!isEdit) {
                            deleteId = sendMessageWithKeyboard(getText(38), 11);
                            waitingType = WaitingType.COUNT;
                        } else {
                            reportRepo.save(report);
                            deleteId = getInformation();
                        }
                    } catch (Exception e) {
                        wrongData();
                        deleteId = sendMessageWithKeyboard(getText(35), 11);
                    }
                } else if (isButton(9)) {
                    if (!isEdit) {
                        deleteId = sendMessageWithKeyboard(getText(34), 11);
                        waitingType = WaitingType.EYES;
                    } else {
                        deleteId = getInformation();
                    }
                } else wrongData();
                return COMEBACK;
            case COUNT:
                delete();
                if (isButton(9)) {
                    if (!isEdit) {
                        deleteId = sendMessageWithKeyboard(getText(35), 11);
                        waitingType = WaitingType.CIRCLE;
                    } else deleteId = getInformation();
                } else if (hasMessageText()) {
                    try {
                        report.setCount(Integer.parseInt(updateMessageText));
                        if (isEdit) {
                            reportRepo.save(report);
                            deleteId = getInformation();
                        } else {
                            deleteId = sendMessageWithKeyboard(getText(36), 11);
                            waitingType = WaitingType.TIME;
                        }
                    } catch (Exception e) {
                        wrongData();
                        deleteId = sendMessageWithKeyboard(getText(38), 11);
                    }
                } else wrongData();
                return COMEBACK;
            case TIME:
                delete();
                if (isButton(9)) {
                    if (isEdit) {
                        deleteId = getInformation();
                    } else {
                        deleteId = sendMessageWithKeyboard(getText(38), 11);
                        waitingType = WaitingType.COUNT;
                    }
                } else if (hasMessageText()) {
                    try {
                        if (isEdit) {
                            report.setTime(Integer.parseInt(updateMessageText));
                            reportRepo.save(report);
                            deleteId = getInformation();
                        } else {
                            Calendar calendar0 = Calendar.getInstance();
                            calendar0.set(Calendar.HOUR_OF_DAY, Integer.parseInt(updateMessageText.split(":")[0]));
                            calendar0.set(Calendar.MINUTE, Integer.parseInt(updateMessageText.split(":")[1]));
                            calendar0.set(Calendar.SECOND, 0);
                            report.setRegDate(calendar0.getTime());
                            report.setTime(getTime(updateMessageText));
                            report.setType("Ext");
                            report.setDay(isDay);
                            report.setMaster(master);
                            report.setDate(start);
                            reportRepo.save(report);
                            deleteId = getInfoWithKeyboard();
                        }

                    } catch (Exception e) {
                        wrongData();
                        deleteId = sendMessageWithKeyboard(getText(36), 11);
                    }
                } else wrongData();
                return COMEBACK;
            case CHOOSE_OPTION:
                delete();
                if (isButton(25)) {
                    deleteId = sendMessageWithKeyboard(getText(31), 11);
                    waitingType = WaitingType.NOMENCLATURES;
                } else if (isButton(26)) {
                    getExtruder();
                } else {
                    try {
                        if (hasCallbackQuery()) {
                            indexReport = Integer.parseInt(updateMessageText);
                            deleteId = getInformation();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        wrongData();
                    }
                }
                return COMEBACK;
            case OPTION:
                delete();
                if (isButton(25)) {
                    isEdit = false;
                    isAddReject = true;
                    getReject();
                } else if (isButton(26)) {
                    reports = reportRepo.findAllByEquipmentName(data);
                    if (reports != null && reports.size() > 0) {
                        deleteId = sendMessageWithKeyboard(getText(59), getInfo(reports));
                    } else getExtruder();
                } else if (updateMessageText.contains("/edit")) {
                    isEdit = true;
                    String[] s = updateMessageText.split("_");
                    if (s.length <= 2) {
                        deleteId = sendMessageWithKeyboard(getText(mapForMessage.get(s[1])), 11);
                        waitingType = mapForWaitingType.get(s[1]);
                    } else {
                        rejectReasonId = Integer.parseInt(s[2]);
                        if (s[1].equals("reason")) {
                            getReject();
                        } else {
                            deleteId = sendMessageWithKeyboard(getText(54), 11);
                            waitingType = WaitingType.REJECT_TIME;
                        }
                    }
                } else wrongData();
                return COMEBACK;
            case CHOOSE_FUNCTION:
                delete();
                if (hasCallbackQuery()) {
                    if (isButton(22)) {
                        String stringBuilder = "<b>Текущее оборудование:</b> " + getNameByDataOf(data) +
                                next + next + getText(31);
                        isEdit = false;
                        deleteId = sendMessageWithKeyboard(stringBuilder, 11);
                        waitingType = WaitingType.NOMENCLATURES;
                    } else if (isButton(23)) {
                        isEdit = false;
                        getReject();
                    } else if (isButton(24)) {
                        if (sizeOfExtruder != idsData.indexOf(data) + 1) {
                            isEdit = false;
                            data = idsData.get(idsData.indexOf(data) + 1);
                            String stringBuilder = "<b>Текущее оборудование:</b> " + getNameByDataOf(data) +
                                    next + next + getText(31);
                            deleteId = sendMessageWithKeyboard(stringBuilder, 11);
                            waitingType = WaitingType.NOMENCLATURES;
                        } else {
                            getExtruder();
                        }
                    } else if (isButton(37)) {
                        reports = reportRepo.findAllByEquipmentName(data);
                        if (reports != null && reports.size() > 0) {
                            deleteId = sendMessageWithKeyboard(getInfo(reports).toString(), 15);
                            waitingType = WaitingType.CHOOSE_OPTION;
                        }
                    }
                } else wrongData();
                return COMEBACK;
            case REJECT_REASON:
                delete();
                if (isButton(9)) {
                    if (isEdit) {
                        deleteId = getInformation();
                    } else {
                        deleteId = getInfoWithKeyboard();
                    }
                } else if (ids.contains(updateMessageText)) {
                    try {
                        if (!isEdit) {
                            reject = rejectRepo.findById(Integer.parseInt(updateMessageText));
                            deleteId = sendMessageWithKeyboard(getText(54), 11);
                            waitingType = WaitingType.REJECT_TIME;
                        } else {
                            reject = rejectRepo.findById(Integer.parseInt(updateMessageText));
                            RejectReason rejectReason = rejectReasonRepo.findById(rejectReasonId);
                            rejectReason.setReject(reject);
                            rejectReasonRepo.save(rejectReason);
                            deleteId = getInformation();
                        }

                    } catch (Exception e) {
                        wrongData();
                        getReject();
                    }
                } else {
                    wrongData();
                    getReject();
                }
                return COMEBACK;
            case REJECT_TIME:
                delete();
                if (hasMessageText()) {
                    try {
                        int time = Integer.parseInt(updateMessageText);
                        if (!isEdit) {
                            report = reportRepo.findById(report.getId());
                            if (report.getRejectReasons().size() == 0) {
                                RejectReason rejectReason = new RejectReason(time, reject);
                                rejectReason = rejectReasonRepo.save(rejectReason);
                                List<RejectReason> rejectReasons = new ArrayList<>();
                                rejectReasons.add(rejectReason);
                                report.setRejectReasons(rejectReasons);
                            } else {
                                List<RejectReason> rejectReasons = report.getRejectReasons();
                                RejectReason rejectReason = new RejectReason(time, reject);
                                rejectReason = rejectReasonRepo.save(rejectReason);
                                rejectReasons.add(rejectReason);
                                report.setRejectReasons(rejectReasons);
                            }
                            report.setTime(report.getTime() - time);
                            reportRepo.save(report);
                            if (!isAddReject)
                                deleteId = getInfoWithKeyboard();
                            else deleteId = getInformation();
                        } else {
                            RejectReason rejectReason = rejectReasonRepo.findById(rejectReasonId);
                            rejectReason.setTime(time);
                            rejectReasonRepo.save(rejectReason);
                            deleteId = getInformation();
                        }
                    } catch (Exception e) {
                        wrongData();
                        deleteId = sendMessageWithKeyboard(getText(54), 11);
                    }
                } else if (isButton(9)) {
                    if (isEdit) {
                        deleteId = getInformation();
                    } else {
                        getReject();
                    }
                } else wrongData();
                return COMEBACK;
        }
        return EXIT;
    }

    private int getInfoWithKeyboard() throws TelegramApiException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>").append(report.getNomenclatureName()).append("</b>").append(next).
                append("<b>Количество глаз:</b> ").append(report.getEye()).append(next).
                append("<b>Выпущенный цикл:</b> ").append(report.getCycle()).append(next).
                append("<b>Выпущенное количество:</b> ").append(report.getCount()).append(next).
                append("<b>Время работы оборудования:</b>").append(report.getTime()).append(next).
                append(next);

        if (report.getRejectReasons() != null && report.getRejectReasons().size() != 0) {
            stringBuilder.append("<b>Причина остановки</b> ").append(next);
            for (RejectReason rejectReason : report.getRejectReasons()) {
                stringBuilder.
                        append("<b>Причина: </b> ").append(rejectReason.getReject().getText()).append(next).
                        append("<b>Время:</b> ").append(rejectReason.getTime()).append(next).append(next);
            }
        }
        stringBuilder.append(next);
        waitingType = WaitingType.CHOOSE_FUNCTION;

        return sendMessageWithKeyboard(stringBuilder.toString(), 13);

    }

    private int getInformation() throws TelegramApiException {
        report = reportRepo.findById(indexReport);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>").append(report.getNomenclatureName()).append("</b>").append(next).
                append("<b>Количество глаз:</b> ").append(report.getEye()).append(" /edit_").append("eye").append(next).
                append("<b>Выпущенный цикл:</b> ").append(report.getCycle()).append(" /edit_").append("cicle").append(next).
                append("<b>Выпущенное количество:</b> ").append(report.getCount()).append(" /edit_").append("count").append(next).
                append("<b>Время работы оборудования:</b>").append(report.getTime()).append(" /edit_").append("time").append(next).
                append(next);

        if (report.getRejectReasons() != null && report.getRejectReasons().size() != 0) {
            stringBuilder.append("<b>Причина остановки</b> ").append(next);
            for (RejectReason rejectReason : report.getRejectReasons()) {
                stringBuilder.
                        append("<b>Причина: </b> ").append(rejectReason.getReject().getText()).append(" /edit_").append("reason_").append(rejectReason.getId()).append(next).
                        append("<b>Время:</b> ").append(rejectReason.getTime()).append(" /edit_").append("rejecttime_").append(rejectReason.getId()).
                        append(next);
            }
        }
        stringBuilder.append(next);
        stringBuilder.append(getText(58));
        waitingType = WaitingType.OPTION;

        mapForMessage.put("eye", 34);
        mapForMessage.put("cicle", 35);
        mapForMessage.put("count", 38);
        mapForMessage.put("time", 60);

        mapForWaitingType.put("eye", WaitingType.EYES);
        mapForWaitingType.put("cicle", WaitingType.CIRCLE);
        mapForWaitingType.put("count", WaitingType.COUNT);
        mapForWaitingType.put("time", WaitingType.TIME);

        return sendMessageWithKeyboard(stringBuilder.toString(), 15);

    }

    private int getTime(String time) {
        List<Report> reports = reportRepo.findAllByEquipmentNameOrderByRegDateDesc(report.getEquipmentName());
        if (reports != null && reports.size() != 0) {
            Calendar calendar0 = Calendar.getInstance();
            calendar0.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.split(":")[0]));
            calendar0.set(Calendar.MINUTE, Integer.parseInt(time.split(":")[1]));
            calendar0.set(Calendar.SECOND, 0);
            Date date0 = calendar0.getTime();
            long diff = date0.getTime() - reports.get(0).getRegDate().getTime();
            return (int) TimeUnit.MILLISECONDS.toMinutes(diff);
        } else {
            if (isDay) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 8);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Calendar calendar0 = Calendar.getInstance();
                calendar0.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.split(":")[0]));
                calendar0.set(Calendar.MINUTE, Integer.parseInt(time.split(":")[1]));
                calendar0.set(Calendar.SECOND, 0);
                Date date = calendar.getTime();
                Date date0 = calendar0.getTime();
                long diff = date0.getTime() - date.getTime();
                return (int) TimeUnit.MILLISECONDS.toMinutes(diff);
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 20);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Calendar calendar0 = Calendar.getInstance();
                calendar0.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.split(":")[0]));
                calendar0.set(Calendar.MINUTE, Integer.parseInt(time.split(":")[1]));
                calendar0.set(Calendar.SECOND, 0);
                Date date = calendar.getTime();
                Date date0 = calendar0.getTime();
                long diff = date0.getTime() - date.getTime();
                return (int) TimeUnit.MILLISECONDS.toMinutes(diff);
            }
        }
    }

    private ReplyKeyboard getInfo(List<Report> reports) {
        List<String> namesNomen = new ArrayList<>();
        List<String> idsNomen = new ArrayList<>();

        for (Report report : reports) {
            namesNomen.add(report.getNomenclatureName());
            idsNomen.add(String.valueOf(report.getId()));
        }

        ButtonsLeaf buttonsLeaf = new ButtonsLeaf(namesNomen, idsNomen, buttonRepo.findById(25).getName(), buttonRepo.findById(26).getName());
        waitingType = WaitingType.CHOOSE_OPTION;
        return buttonsLeaf.getListButtonWithDataList();
    }

    private boolean isMaster() {
        master = masterRepo.findByUser(usersRepo.findByChatId(chatId));
        return master != null && master.isType();
    }

    private String getNameByData(String equipmentName) {
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i).equals(equipmentName)) {
                return names.get(i);
            }
        }
        return null;
    }

    private String getNameByDataOf(String equipmentName) {
        for (int i = 0; i < idsData.size(); i++) {
            if (idsData.get(i).equals(equipmentName)) {
                return namesData.get(i);
            }
        }
        return null;
    }

    private void getReject() throws TelegramApiException {

        List<Reject> rejects = rejectRepo.findAllByTypeIsFalse();

        names = new ArrayList<>();
        ids = new ArrayList<>();

        for (Reject reject : rejects) {
            names.add(reject.getText());
            ids.add(String.valueOf(reject.getId()));
        }

        names.add(buttonRepo.findById(9).getName());
        ids.add(buttonRepo.findById(9).getName());
        ButtonsLeaf buttonsLeaf = new ButtonsLeaf(names, ids);

        deleteId = sendMessageWithKeyboard(getText(53), buttonsLeaf.getListButtonWithDataList());
        waitingType = WaitingType.REJECT_REASON;
    }

    private void getExtruder() {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            client.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("webuser", "!48597586!"));

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

                sizeOfExtruder = names.size();
                idsData = new ArrayList<>(ids);
                namesData = new ArrayList<>(names);
                names.add(buttonRepo.findById(9).getName());
                ids.add(buttonRepo.findById(9).getName());
                ButtonsLeaf buttonsLeaf = new ButtonsLeaf(names, ids);

                deleteId = sendMessageWithKeyboard(getText(30), buttonsLeaf.getListButtonWithDataList());
                waitingType = WaitingType.CHOOSE_EXTRUDER;
            }

        } catch (IOException | TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void getExtruderNames(String extruder, String nomenclature) {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            client.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("webuser", "!48597586!"));

            HttpPost httpPost = new HttpPost("http://178.89.184.2:8282/UTP_JAKKOKZ/hs/rooturl/jaktempl/inout");
            StringEntity params = new StringEntity("{\"success\":1,\"what\":\"nomenforext\",\"extline\":\"" + extruder + "\"}");
            httpPost.setEntity(params);
            names = new ArrayList<>();
            ids = new ArrayList<>();
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
                JSONArray lang = (JSONArray) jsonObject.get("data");
                for (Object o : lang) {
                    JSONObject innerObj = (JSONObject) o;
                    names.add(innerObj.get("nomenname").toString());
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

    private void wrongData() throws TelegramApiException {
        wrongDeleteId = sendMessage(Const.WRONG_DATA_TEXT);
    }

    private void delete() {
        delete(updateMessageId);
        delete(deleteId);
        delete(wrongDeleteId);
    }

    private int sendStartDate() throws TelegramApiException {
        return toDeleteKeyboard(sendMessageWithKeyboard("<b>Пожалуйста выберите дату смены⬇️</b>", dateKeyboard.getCalendarKeyboard()));
    }

}
