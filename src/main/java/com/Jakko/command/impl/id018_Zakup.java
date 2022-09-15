package com.Jakko.command.impl;

import com.Jakko.command.Command;
import com.Jakko.enums.WaitingType;
import com.Jakko.model.custom.Repair;
import com.Jakko.model.custom.RequestZakup;
import com.Jakko.util.ButtonsLeaf;
import com.Jakko.util.Const;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class id018_Zakup extends Command {

    private int deleteId;
    private int wrongDeleteId;
    private List<String> names = new ArrayList<>();
    private List<String> ids = new ArrayList<>();

    @Override
    public boolean execute() throws TelegramApiException, SQLException, IOException {
        switch (waitingType) {
            case START:
                delete();
                if (isButton(40)) {
                    getExecutedList();
                } else if (isButton(41)) {
                    getInProcessList();
                } else if (isButton(42)) {
                    return EXIT;
                } else wrongData();
                return COMEBACK;
            case CHOOSE_OPTION:
                delete();
                if (hasCallbackQuery()) {
                    try {
                        RequestZakup repair = requestZakupRepo.findById(Integer.parseInt(updateMessageText));
                        if (repair != null) {
                            String s =
                                    "<b>Заявка №" + repair.getId() + "</b>" +
                                            next + next + next +
                                            "<b>Оборудование:</b> " + repair.getEquipmentName() +
                                            next + next +
                                            "<b>Номенклатура:</b> " + repair.getNomenclatureName() +
                                            next + next +
                                            "<b>Количество:</b> " + repair.getCount() + " " + repair.getUnit() +
                                            next + next +
                                            "<b>Описание:</b> " + repair.getDescription() +
                                            next + next +
                                            "<b>Дата поступления:</b> " + new SimpleDateFormat("dd.MM.yyyy").format(repair.getDate()) +
                                            next + next +
                                            "<b>Дата поставки заявки:</b> " + new SimpleDateFormat("dd.MM.yyyy").format(repair.getZakupDate());
                            try {
                                toDeleteKeyboard(bot.execute(new SendPhoto().setChatId(chatId).setPhoto(repair.getFile()).setCaption(s).setParseMode("html").setReplyMarkup(keyboardMarkUpService.select(23))).getMessageId());
                            } catch (Exception e) {
                                try {
                                    toDeleteKeyboard(bot.execute(new SendVideo().setChatId(chatId).setVideo(repair.getFile()).setCaption(s).setParseMode("html").setReplyMarkup(keyboardMarkUpService.select(23))).getMessageId());
                                } catch (Exception exception) {
                                    toDeleteKeyboard(sendMessageWithKeyboard(s, 23));
                                }
                            }
                        }
                    } catch (Exception e) {
                        getExecutedList();
                    }
                } else wrongData();
                return COMEBACK;

            case CHOOSE_FUNCTION:
                delete();
                if (hasCallbackQuery()) {
                    RequestZakup repair = requestZakupRepo.findById(Integer.parseInt(updateMessageText));
                    if (repair != null) {
                        String s =
                                "<b>Заявка №" + repair.getId() + "</b>" +
                                        next + next + next +
                                        "<b>Оборудование:</b> " + repair.getEquipmentName() +
                                        next + next +
                                        "<b>Номенклатура:</b> " + repair.getNomenclatureName() +
                                        next + next +
                                        "<b>Количество:</b> " + repair.getCount() + " " + repair.getUnit() +
                                        next + next +
                                        "<b>Описание:</b> " + repair.getDescription() +
                                        next + next +
                                        "<b>Дата поступления:</b> " + new SimpleDateFormat("dd.MM.yyyy").format(repair.getDate()) +
                                        next + next +
                                        "<b>Дата поставки заявки:</b> " + new SimpleDateFormat("dd.MM.yyyy").format(repair.getZakupDate());
                        List<String> names = new ArrayList<>();
                        names.add("Исполнено");
                        names.add("Назад");
                        names.add(buttonRepo.findById(33).getName());
                        List<String> data = new ArrayList<>();
                        data.add(String.valueOf(repair.getId()));
                        data.add("Назад");
                        data.add(buttonRepo.findById(33).getName());
                        ButtonsLeaf buttonsLeaf = new ButtonsLeaf(names, data);
                        try {
                            toDeleteKeyboard(bot.execute(new SendPhoto().setChatId(chatId).setPhoto(repair.getFile()).setCaption(s).setParseMode("html").setReplyMarkup(buttonsLeaf.getListButtonWithDataList())).getMessageId());
                        } catch (Exception e) {
                            try {
                                toDeleteKeyboard(bot.execute(new SendVideo().setChatId(chatId).setVideo(repair.getFile()).setCaption(s).setParseMode("html").setReplyMarkup(buttonsLeaf.getListButtonWithDataList())).getMessageId());
                            } catch (Exception exception) {
                                toDeleteKeyboard(sendMessageWithKeyboard(s, buttonsLeaf.getListButtonWithDataList()));
                            }
                        }
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
                        RequestZakup repair = requestZakupRepo.findById(Integer.parseInt(updateMessageText));
                        if (repair != null) {
                            repair.setExecuted(true);
                            requestZakupRepo.save(repair);
                            sendMessage("Исполнено");
                            String s = "Здравствуйте, " + repair.getUser().getFullName() + ".\n" +
                                    "На Ваше обращение под №" + repair.getId() + " от " + new SimpleDateFormat("dd.MM.yyyy").format(repair.getDate()) +
                                    " года, время " + new SimpleDateFormat("HH:mm").format(repair.getDate()) + " сообщаем следующее: Исполнено.";
                            sendMessage(s, repair.getUser().getChatId());
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
        List<RequestZakup> requestZakups = requestZakupRepo.findAllByExecutedIsFalse();
        if (requestZakups != null && requestZakups.size() != 0) {
            for (RequestZakup repair : requestZakups) {
                names.add(repair.getNomenclatureName());
                data.add(String.valueOf(repair.getId()));
            }
            ButtonsLeaf buttonsLeaf = new ButtonsLeaf(names, data);
            toDeleteKeyboard(sendMessageWithKeyboard(getText(69), buttonsLeaf.getListButtonWithDataList()));
            waitingType = WaitingType.CHOOSE_FUNCTION;
        } else
            deleteId = sendMessage(48);
    }

    private void getExecutedList() throws TelegramApiException {
        List<String> names = new ArrayList<>();
        List<String> data = new ArrayList<>();
        List<RequestZakup> requestZakups = requestZakupRepo.findAllByExecutedIsTrue();
        if (requestZakups != null && requestZakups.size() != 0) {
            for (RequestZakup repair : requestZakups) {
                names.add(repair.getNomenclatureName());
                data.add(String.valueOf(repair.getId()));
            }
            ButtonsLeaf buttonsLeaf = new ButtonsLeaf(names, data);
            toDeleteKeyboard(sendMessageWithKeyboard(getText(69), buttonsLeaf.getListButtonWithDataList()));
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
