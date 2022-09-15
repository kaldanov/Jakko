package com.Jakko.command.impl;

import com.Jakko.command.Command;
import com.Jakko.enums.WaitingType;
import com.Jakko.model.standart.Button;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class id009_EditingMenuButton extends Command {
    private int inlineMessId;
    private int wrongMessId;
    private int infoMessId;
    private int notFoundMess;
    private Button currentButton;
    private List<Button> searchResultButtons;

    @Override
    public boolean execute() throws TelegramApiException {
        if (!isRegistered()) {
            sendMessageWithKeyboard(getText(11), 5);
            return EXIT;
        }
        if (!isAdmin()) {
            sendMessageWithKeyboard(getText(10), 1);
            return EXIT;
        }
        switch (waitingType) {
            case START:
                deleteUpdateMess();
                if (isButton(20)) { // editing button
                    infoMessId = sendMessage(22);
                    waitingType = WaitingType.SEARCH_BUTTON;
                } else {
                    sendMessageWithKeyboard(getText(23), 10);
                }
                return COMEBACK;
            case SEARCH_BUTTON:
                deleteUpdateMess();
                if (hasMessageText()) {
                    searchResultButtons = buttonRepo.findAllByNameContainingOrderById(updateMessageText);
                    if (searchResultButtons.size() != 0) {
                        deleteMessage(notFoundMess);
                        deleteMessage(infoMessId);
                        inlineMessId = sendMessage(getInfoButtons(searchResultButtons));
                        waitingType = WaitingType.CHOOSE_OPTION;
                    } else {
                        sendNotFound();
                    }
                } else {
                    deleteUpdateMess();
                    sendWrongData();
                }
                return COMEBACK;
            case CHOOSE_OPTION:
                deleteUpdateMess();
                if (updateMessageText.contains("/editName")) { //edit name
                    currentButton = buttonRepo.findById(getInt(updateMessageText.substring(9)));
                    if (currentButton == null) {
                        sendWrongData();
                        return COMEBACK;
                    }
                    deleteMessage(inlineMessId);
                    inlineMessId = sendMessage(getInfoForEdit(currentButton));
                    waitingType = WaitingType.SET_TEXT;
                } else if (updateMessageText.contains("/back")) { // back
                    deleteMessage(infoMessId);
                    deleteMessage(inlineMessId);
                    infoMessId = sendMessage(22);
                    waitingType = WaitingType.SEARCH_BUTTON;
                }
                return COMEBACK;
            case SET_TEXT:
                deleteUpdateMess();
                if (hasMessageText() && updateMessageText.length() < 100) {
                    if (updateMessageText.equals("/cancel")) {
                        deleteMessage(infoMessId);
                        deleteMessage(inlineMessId);
                        inlineMessId = sendMessage(getInfoButtons(searchResultButtons));
                        waitingType = WaitingType.CHOOSE_OPTION;
                        return COMEBACK;
                    } else if (buttonRepo.findByName(updateMessageText) != null || buttonRepo.findByName(updateMessageText) != null || updateMessageText.equals("/back") || updateMessageText.contains("/editName")) {
                        deleteMessage(infoMessId);
                        infoMessId = sendMessage(24);
                        return COMEBACK;
                    } else {
                        deleteWrongMess();
                        buttonRepo.update(updateMessageText, currentButton.getId());
                        deleteMessage(inlineMessId);
                        deleteMessage(infoMessId);

                        searchResultButtons = updateButtons(searchResultButtons);
                        currentButton = buttonRepo.findById(currentButton.getId());
                        inlineMessId = sendMessage(getInfoButtons(searchResultButtons));
                        waitingType = WaitingType.CHOOSE_OPTION;
                    }
                } else {
                    sendWrongData();
                }
                return COMEBACK;
        }
        return EXIT;
    }

    private List<Button> updateButtons(List<Button> searchResultButtons) {
        List<Button> newSearchRes = new ArrayList<>();
        for (Button button : searchResultButtons) {
            newSearchRes.add(buttonRepo.findById(button.getId()));
        }
        return newSearchRes;
    }

    private String getInfoForEdit(Button currentButton) {
        return getText(25) + currentButton.getName() + next +
                getText(26);
    }

    private String getInfoButtons(List<Button> searchResultButtons) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getText(27)).append(next).append(next);
        for (Button button : searchResultButtons) {
            stringBuilder.append(button.getName()).append(" \uD83D\uDD8A /editName").append(button.getId()).append(next).append(next);
        }
        stringBuilder.append(next).append(next);

        stringBuilder.append("/back").append(" ").append(getText(28));

        return stringBuilder.toString();
    }

    private void sendNotFound() throws TelegramApiException {
        deleteMessage(updateMessageId);
        deleteMessage(notFoundMess);
        notFoundMess = sendMessage(29, chatId);
    }


    private int getInt(String updateMessageText) {
        try {
            return Integer.parseInt(updateMessageText);
        } catch (Exception e) {
            return -1;
        }
    }

    private void deleteUpdateMess() {
        deleteMessage(updateMessageId);
    }

    private void deleteWrongMess() {
        deleteMessage(wrongMessId);
    }

    private void sendWrongData() throws TelegramApiException {
        deleteMessage(updateMessageId);
        deleteMessage(wrongMessId);
        wrongMessId = sendMessage(4, chatId);
    }
}