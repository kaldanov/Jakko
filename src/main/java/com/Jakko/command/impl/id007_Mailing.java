package com.Jakko.command.impl;

import com.Jakko.command.Command;
import com.Jakko.enums.WaitingType;
import com.Jakko.model.standart.User;
import com.Jakko.service.MailingThread;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaVideo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class id007_Mailing extends Command {

    private int deleteMessageId;
    private int acceptMessId;
    private String text;

    private List<InputMedia> photoVideos;
    private List<String> documents;
    User user;


    @Override
    public boolean execute() throws TelegramApiException {
        user = usersRepo.findByChatId(chatId);
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
                deleteMessage(updateMessageId);
                deleteMessageId = sendMessageWithKeyboard(getText(16), 6);
                waitingType = WaitingType.SET_TEXT;
                return COMEBACK;
            case SET_TEXT:
                deleteMessage(updateMessageId);
                deleteMessage(deleteMessageId);
                if (hasMessageText() && isButton(13)) { // cancel
                    deleteMessage(updateMessageId);
                    deleteMessage(deleteMessageId);
                    sendMessageWithKeyboard(getText(17), 7);
                    return EXIT;
                } else if (hasMessageText()) {
                    text = updateMessageText;
                    deleteMessageId = sendMessageWithKeyboard(getText(18), 8);
                    waitingType = WaitingType.CHOOSE_OPTION;
                    photoVideos = new ArrayList<>();
                    documents = new ArrayList<>();
                }
                return COMEBACK;
            case CHOOSE_OPTION:
                deleteMessage(updateMessageId);
                if (hasDocument()) {
                    documents.add(update.getMessage().getDocument().getFileId());
                    deleteMessage(acceptMessId);
                    acceptMessId = sendMessage(19);
                } else if (hasPhoto()) {
                    InputMediaPhoto photo = new InputMediaPhoto()
                            .setMedia(update.getMessage().getPhoto().get(0).getFileId());
                    photoVideos.add(photo);
                    deleteMessage(acceptMessId);
                    acceptMessId = sendMessage(19);

                } else if (hasVideo()) {
                    InputMediaVideo video = new InputMediaVideo()
                            .setMedia(update.getMessage().getVideo().getFileId());
                    photoVideos.add(video);
                    deleteMessage(acceptMessId);
                    acceptMessId = sendMessage(19);

                } else if (isButton(9)) { // back
                    deleteMessage(updateMessageId);
                    deleteMessage(deleteMessageId);
                    deleteMessageId = sendMessageWithKeyboard(getText(16), 6);
                    waitingType = WaitingType.SET_TEXT;
                } else if (isButton(14)) { // next
                    deleteMessage(acceptMessId);
                    deleteMessage(deleteMessageId);
                    if (documents != null && documents.size() > 0) {
                        for (String document : documents) {
                            sendDocument(document, chatId);
                        }
                    }
                    if (photoVideos != null && photoVideos.size() > 0) {
                        sendMediaGroup(photoVideos, chatId);
                    }

                    deleteMessageId = sendMessageWithKeyboard(text, 9);
                    sendMessage(20);
                    waitingType = WaitingType.CONFIRM;

                } else if (isButton(13)) { // cancel
                    deleteMessage(updateMessageId);
                    deleteMessage(deleteMessageId);
                    deleteMessage(acceptMessId);
                    sendMessageWithKeyboard(getText(17), 7);
                    return EXIT;
                }
                return COMEBACK;
            case CONFIRM:
                deleteMessage(updateMessageId);
                if (isButton(15)) {
                    List<User> users = usersRepo.findAll();
                    sendMessageWithKeyboard(getText(21), 7);

                    MailingThread mailingThread = new MailingThread(bot, users, documents, photoVideos, text);
                    mailingThread.start();

                    return EXIT;
                } else if (isButton(9)) {
                    deleteMessageId = sendMessageWithKeyboard(getText(18), 8);
                    waitingType = WaitingType.CHOOSE_OPTION;
                } else if (isButton(13)) {
                    deleteMessage(updateMessageId);
                    sendMessageWithKeyboard(getText(17), 7);
                    return EXIT;
                } else {
                    deleteMessage(deleteMessageId);
                    deleteMessage(updateMessageId);
                    deleteMessageId = sendMessageWithKeyboard(text, 9);
                    waitingType = WaitingType.CONFIRM;
                }
                return COMEBACK;
        }
        return EXIT;
    }
}
