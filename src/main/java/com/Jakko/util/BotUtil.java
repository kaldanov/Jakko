package com.Jakko.util;

import com.Jakko.enums.Language;
import com.Jakko.enums.ParseMode;
import com.Jakko.model.standart.Message;
import com.Jakko.repository.MessageRepo;
import com.Jakko.service.KeyboardMarkUpService;
import com.Jakko.repository.TelegramBotRepositoryProvider;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.Objects;

@Slf4j
public class BotUtil {

    private DefaultAbsSender bot;
    private MessageRepo messageRepo = TelegramBotRepositoryProvider.getMessageRepo();
    private long chatId;
    private KeyboardMarkUpService keyboardMarkUpService;
//    private static DaoFactory       factory = DaoFactory.getInstance();

    public BotUtil(DefaultAbsSender bot) {
        this.bot = bot;
    }

    public int sendMessage(SendMessage sendMessage) throws TelegramApiException {
        try {
            return bot.execute(sendMessage).getMessageId();
        } catch (TelegramApiRequestException e) {
            if (e.getApiResponse().contains("Bad Request: can't parse entities")) {
                sendMessage.setParseMode(null);
                sendMessage.setText(sendMessage.getText() + "\nBad tags");
                return bot.execute(sendMessage).getMessageId();
            } else throw e;
        }
    }

    public int sendMessage(String text, long chatId) throws TelegramApiException {
        return sendMessage(text, chatId, ParseMode.html);
    }

    public int sendMessage(String text, long chatId, ParseMode parseMode) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage().setChatId(chatId).setText(text);
        if (parseMode == ParseMode.WITHOUT) {
            sendMessage.setParseMode(null);
        } else {
            sendMessage.setParseMode(parseMode.name());
        }
        return sendMessage(sendMessage);
    }

    public int sendMessage(long messageId, long chatId) throws TelegramApiException {
        return sendMessage(messageId, chatId, null, null);
    }

    public int sendMessage(long messageId, long chatId, Contact contact, String photo) throws TelegramApiException {
        int result = 0;
        this.chatId = chatId;
//        Message message                     = factory.getMessageDao().getMessage(messageId);
        Message message = messageRepo.findById((int) messageId);
        SendMessage sendMessage = new SendMessage().setText(message.getName()).setChatId(chatId).setParseMode(ParseMode.html.name());
        keyboardMarkUpService = new KeyboardMarkUpService();
//        KeyboardMarkUpDao keyboardMarkUpDao = factory.getKeyboardMarkUpDao();
//        if (keyboardMarkUp.select())
        if (message.getKeyboardId() != null) {
            if (keyboardMarkUpService.select(message.getKeyboardId()) != null)
                sendMessage.setReplyMarkup(keyboardMarkUpService.select(message.getKeyboardId()));
        } else {
            if (keyboardMarkUpService.select(0) != null)
                sendMessage.setReplyMarkup(keyboardMarkUpService.select(message.getKeyboardId()));
        }
        boolean isCaption = false;
        if (photo != null) {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setPhoto(photo);
            if (message.getName().length() < 200) {
                sendPhoto.setCaption(message.getName());
                isCaption = true;
            }
            try {
                result = bot.execute(sendPhoto).getMessageId();
            } catch (TelegramApiException e) {
                log.debug("Can't send photo", e);
                isCaption = false;
            }
        }
        if (!isCaption) result = bot.execute(sendMessage).getMessageId();
        if (contact != null) sendContact(chatId, contact);
        return result;
    }

    public int sendDocument(String document, long chatId) throws TelegramApiException {

        SendDocument sendDocument = new SendDocument()
                .setParseMode("html")
                .setDocument(document)
                .setChatId(chatId);

        return bot.execute(sendDocument).getMessageId();

    }

    public int sendMessageWithKeyboard(String text, ReplyKeyboard keyboard, long chatId) throws TelegramApiException {
        return sendMessageWithKeyboard(text, keyboard, chatId, 0);
    }

    private int sendMessageWithKeyboard(String text, ReplyKeyboard keyboard, long chatId, int replyMessageId) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage().setParseMode(ParseMode.html.name()).setChatId(chatId).setText(text).setReplyMarkup(keyboard);
        if (replyMessageId != 0) sendMessage.setReplyToMessageId(replyMessageId);
        return sendMessage(sendMessage);
    }

    public int sendContact(long chatId, Contact contact) throws TelegramApiException {
        return bot.execute(new SendContact().setChatId(chatId).setFirstName(contact.getFirstName()).setLastName(contact.getLastName()).setPhoneNumber(contact.getPhoneNumber())).getMessageId();
    }

    public void deleteMessage(long chatId, int messageId) {
        try {
            bot.execute(new DeleteMessage(chatId, messageId));
        } catch (TelegramApiException ignored) {
        }
    }

    public boolean hasContactOwner(Update update) {
        return (update.hasMessage() && update.getMessage().hasContact()) && Objects.equals(update.getMessage().getFrom().getId(), update.getMessage().getContact().getUserID());
    }


}
