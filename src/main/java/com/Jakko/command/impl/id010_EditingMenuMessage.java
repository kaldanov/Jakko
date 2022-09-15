package com.Jakko.command.impl;

import com.Jakko.command.Command;
import com.Jakko.enums.WaitingType;
import com.Jakko.model.standart.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class id010_EditingMenuMessage extends Command {
    private int inlineMessId;
    private int wrongMessId;
    private int infoMessId;
    private int notFoundMess;

    private Message currentMessage;

    private List<Message> searchResultMessage;

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
                if (isButton(21)) { // editing mess
                    infoMessId = sendMessage(22);
                    waitingType = WaitingType.SEARCH_BUTTON;
                } else {
                    sendMessageWithKeyboard(getText(23), 10);
                }
                return COMEBACK;
            case SEARCH_BUTTON:
                deleteUpdateMess();
                deleteNotFoundMess();
                if (hasMessageText()) {
                    searchResultMessage = messageRepo.findAllByNameContainingOrderById(updateMessageText);
                    if (searchResultMessage.size() != 0) {
                        deleteMessage(infoMessId);
                        inlineMessId = sendMessage(getInfoMessages(searchResultMessage));
                        waitingType = WaitingType.CHOOSE_OPTION;
                    } else {
                        sendNotFound();
                    }
                } else {
                    sendNotFound();
                }
                return COMEBACK;

            case CHOOSE_OPTION:
                deleteUpdateMess();
                deleteWrongMess();
                if (updateMessageText.contains("/editName")) { //edit name
                    currentMessage = messageRepo.findById(getLong(updateMessageText.substring(9)));
                    if (currentMessage == null) {
                        sendWrongData();
                        return COMEBACK;
                    }

                    deleteMessage(inlineMessId);
                    inlineMessId = sendMessage(getInfoForEdit(currentMessage));
//                    editMessage(getInfoMessage(currentMessage), inlineMessId);
//                    infoMessId = sendMessage(57);
                    waitingType = WaitingType.SET_TEXT;
                } else if (updateMessageText.contains("/back")) { // back
                    deleteMessage(infoMessId);
                    deleteMessage(inlineMessId);
                    infoMessId = sendMessage(22);
                    waitingType = WaitingType.SEARCH_BUTTON;
                } else {
                    sendWrongData();
                }
                return COMEBACK;

            case SET_TEXT:
                deleteUpdateMess();
                deleteWrongMess();
                if (hasMessageText()) {
                    if (updateMessageText.equals("/cancel")) {
                        deleteMessage(infoMessId);
                        deleteMessage(inlineMessId);
                        inlineMessId = sendMessage(getInfoMessages(searchResultMessage));
                        waitingType = WaitingType.CHOOSE_OPTION;
                        return COMEBACK;
                    } else {
                        messageRepo.update(updateMessageText, currentMessage.getId());
                        deleteMessage(inlineMessId);
                        deleteMessage(infoMessId);
                        currentMessage = messageRepo.findById(currentMessage.getId()).get();
                        searchResultMessage = updateMessages(searchResultMessage);

                        inlineMessId = sendMessage(getInfoMessages(searchResultMessage));
                        waitingType = WaitingType.CHOOSE_OPTION;
                    }
                } else {
                    sendWrongData();
                }
                return COMEBACK;
        }
        return EXIT;
    }

    private List<Message> updateMessages(List<Message> searchResultMessage) {
        List<Message> newSearchRes = new ArrayList<>();
        for (Message message : searchResultMessage) {
            newSearchRes.add(messageRepo.findById(message.getId()).get());
        }

        return newSearchRes;
    }

    private String getInfoForEdit(Message currentMessage) {
        return getText(25) + currentMessage.getName() + next +
                getText(26);
    }

    private void deleteNotFoundMess() {
        if (notFoundMess != 0) {
            deleteMessage(notFoundMess);
        }
    }

    private String getInfoMessages(List<Message> searchResultMessages) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getText(27)).append(next).append(next);

        for (Message message : searchResultMessages) {
            stringBuilder.append(message.getName()).append(" \uD83D\uDD8A /editName").append(message.getId()).append(next).append(next);
        }

        stringBuilder.append(next).append(next)
                .append("/back").append(" ").append(getText(28));

        return stringBuilder.toString();
    }

    private void sendNotFound() throws TelegramApiException {
        deleteMessage(updateMessageId);
        deleteNotFoundMess();
        notFoundMess = sendMessage(29, chatId);
    }


    private int getLong(String updateMessageText) {
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
        if (wrongMessId != 0)
            deleteMessage(wrongMessId);
    }

    private void sendWrongData() throws TelegramApiException {
        deleteMessage(updateMessageId);
        deleteWrongMess();
        wrongMessId = sendMessage(4, chatId);

    }
}
