package com.Jakko.command;

import com.Jakko.exceptions.ButtonNotFoundException;
import com.Jakko.exceptions.CommandNotFoundException;
import com.Jakko.exceptions.KeyboardNotFoundException;
import com.Jakko.exceptions.MessageNotFoundException;
import com.Jakko.repository.*;
import com.Jakko.service.KeyboardMarkUpService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.Jakko.enums.WaitingType;
import com.Jakko.model.standart.Button;
import com.Jakko.util.BotUtil;
import com.Jakko.util.SetDeleteMessages;
import com.Jakko.util.UpdateUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@NoArgsConstructor
public abstract class Command {


    @Getter
    @Setter
    protected long id;
    protected Long chatId;
    protected Update update;
    @Getter
    @Setter
    protected long messageId;
    protected String markChange;
    protected int updateMessageId;
    protected DefaultAbsSender bot;
    protected int lastSentMessageID;
    protected static BotUtil botUtils;
    protected String updateMessageText;
    protected String updateMessagePhoto;
    protected String updateMessageVideo;
    protected String updateMessagePhone;
    protected WaitingType waitingType = WaitingType.START;
    protected String editableTextOfMessage;
    protected final static String linkEdit = "/linkId";
    protected static final String next = "\n";
    protected static final String space = " ";
    protected final static boolean EXIT = true;
    protected final static boolean COMEBACK = false;
    protected Message updateMessage;

    protected KeyboardMarkUpService keyboardMarkUpService = new KeyboardMarkUpService();
    protected UsersRepo usersRepo = TelegramBotRepositoryProvider.getUsersRepo();
    protected MessageRepo messageRepo = TelegramBotRepositoryProvider.getMessageRepo();
    protected ButtonRepo buttonRepo = TelegramBotRepositoryProvider.getButtonRepo();
    protected AdminRepo adminRepo = TelegramBotRepositoryProvider.getAdminRepo();
    protected KeyboardRepo keyboardRepo = TelegramBotRepositoryProvider.getKeyboardRepo();
    protected PropertiesRepo propertiesRepo = TelegramBotRepositoryProvider.getPropertiesRepo();
    protected ReportRepo reportRepo = TelegramBotRepositoryProvider.getReportRepo();
    protected RepairRepo repairRepo = TelegramBotRepositoryProvider.getRepairRepo();
    protected MenuRepo menuRepo = TelegramBotRepositoryProvider.getMenuRepo();
    protected RejectRepo rejectRepo = TelegramBotRepositoryProvider.getRejectRepo();
    protected RejectReasonRepo rejectReasonRepo = TelegramBotRepositoryProvider.getRejectReasonRepo();
    protected TempUsersRepo tempUsersRepo = TelegramBotRepositoryProvider.getTempUsersRepo();
    protected MasterRepo masterRepo = TelegramBotRepositoryProvider.getMasterRepo();
    protected ZakupshikRepo zakupshikRepo = TelegramBotRepositoryProvider.getZakupshikRepo();
    protected RequestZakupRepo requestZakupRepo = TelegramBotRepositoryProvider.getRequestZakupRepo();
    //------------------------------------------------------------------------


    public abstract boolean execute() throws TelegramApiException, IOException, SQLException, FileNotFoundException, MessageNotFoundException, KeyboardNotFoundException, ButtonNotFoundException, CommandNotFoundException;

    public void editMessageWithKeyboard(String text, long chatId, int messageId, InlineKeyboardMarkup replyKeyboard) throws TelegramApiException {
        EditMessageText new_message = new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(replyKeyboard)
                .setParseMode("html");
        try {
            bot.execute(new_message);
        } catch (TelegramApiException e) {
            if (e.toString().contains("Bad Request: can't parse entities")) {
                new_message.setParseMode(null);
                bot.execute(new_message);
            } else e.printStackTrace();
        }

    }

    public void editMessage(String text, long chatId, int messageId) throws TelegramApiException {
        EditMessageText new_message = new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text);
        try {
            bot.execute(new_message);
        } catch (TelegramApiException e) {
            if (e.toString().contains("Bad Request: can't parse entities")) {
                new_message.setParseMode(null);
                bot.execute(new_message);
            }
            e.printStackTrace();
        }
    }

    protected int sendMessage(long messageId) throws TelegramApiException {
        return sendMessage(messageId, chatId);
    }

    protected int sendMessage(long messageId, long chatId) throws TelegramApiException {
        return sendMessage(messageId, chatId, null);
    }

    protected int sendMessage(long messageId, long chatId, Contact contact) throws TelegramApiException {
        return sendMessage(messageId, chatId, contact, null);
    }

    protected int sendMessage(long messageId, long chatId, Contact contact, String photo) throws TelegramApiException {
        lastSentMessageID = botUtils.sendMessage(messageId, chatId, contact, photo);
        return lastSentMessageID;
    }

    protected int sendMessage(String text) throws TelegramApiException {
        return sendMessage(text, chatId);
    }

    protected int sendMessage(String text, long chatId) throws TelegramApiException {
        return sendMessage(text, chatId, null);
    }

    protected int sendMessage(String text, long chatId, Contact contact) throws TelegramApiException {
        lastSentMessageID = botUtils.sendMessage(text, chatId);
        if (contact != null) {
            botUtils.sendContact(chatId, contact);
        }
        return lastSentMessageID;
    }

    protected void deleteMessage() {
        deleteMessage(chatId, lastSentMessageID);
    }

    protected void deleteMessage(int messageId) {
        if (messageId != 0)
            deleteMessage(chatId, messageId);
    }

    protected void deleteMessage(long chatId, int messageId) {
        if (messageId != 0)
            botUtils.deleteMessage(chatId, messageId);
    }

    protected String getText(int messageIdFromBD) {
        return messageRepo.findById(messageIdFromBD).getName();
    }

    protected int sendDocument(String doc, long chatId) throws TelegramApiException, NullPointerException {

        return botUtils.sendDocument(doc, chatId);
    }

    protected void sendMediaGroup(List<InputMedia> files, long chatId) throws TelegramApiException, NullPointerException {
        SendMediaGroup sendMediaGroup = new SendMediaGroup();
        sendMediaGroup.setChatId(chatId).setMedia(files);
        bot.execute(sendMediaGroup);
    }


    protected Optional<String> getTextOptional(int messageIdFromDb) {
        return messageRepo.getName(messageIdFromDb);
    }

    public void clear() {
        update = null;
        bot = null;
    }

    protected boolean isButton(int buttonId) {
        Button button = buttonRepo.findById(buttonId);
        return updateMessageText.equals(button.getName());
    }

    public boolean isInitNormal(Update update, DefaultAbsSender bot) {
        if (botUtils == null) botUtils = new BotUtil(bot);
        this.update = update;
        this.bot = bot;
        chatId = UpdateUtil.getChatId(update);
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            updateMessage = callbackQuery.getMessage();
            updateMessageText = callbackQuery.getData();
            updateMessageId = updateMessage.getMessageId();
            editableTextOfMessage = callbackQuery.getMessage().getText();
        } else if (update.hasMessage()) {
            updateMessage = update.getMessage();
            updateMessageId = updateMessage.getMessageId();
            if (updateMessage.hasText()) updateMessageText = updateMessage.getText();
            if (updateMessage.hasPhoto()) {
                int size = update.getMessage().getPhoto().size();
                updateMessagePhoto = update.getMessage().getPhoto().get(size - 1).getFileId();
            } else {
                updateMessagePhoto = null;
            }
            if (updateMessage.hasVideo()) {
                updateMessageVideo = update.getMessage().getVideo().getFileId();
            } else {
                updateMessageVideo = null;
            }
        }
        if (hasContact()) updateMessagePhone = update.getMessage().getContact().getPhoneNumber();
//        if (markChange == null) markChange      = getText(Const.EDIT_BUTTON_ICON);
        return COMEBACK;
    }

    protected boolean isUser(long chatId) {
        int count = usersRepo.countUserByChatId(chatId);
        if (count > 0) return EXIT;
        return COMEBACK;
    }

    protected void sendMessageWithAddition() throws TelegramApiException {
        deleteMessage(updateMessageId);
        com.Jakko.model.standart.Message message = messageRepo.findById((int) messageId);
        if (message != null) {
            try {
                if (message.getFile() != null || message.getFileType() != null) {
                    switch (message.getFileType()) {
                        case "photo":
                            bot.execute(new SendPhoto().setPhoto(message.getFile()).setChatId(chatId));
                            break;
                        case "audio":
                            bot.execute(new SendAudio().setAudio(message.getFile()).setChatId(chatId));
                            break;
                        case "video":
                            bot.execute(new SendVideo().setVideo(message.getFile()).setChatId(chatId));
                            break;
                        case "document":
                            bot.execute(new SendDocument().setChatId(chatId).setDocument(message.getFile()));
                            break;

                    }
                }
                sendMessage(messageId, chatId, null, message.getPhoto());
            } catch (TelegramApiException e) {
                log.error("Exception by send file for message " + messageId, e);
            }
        }
    }


    protected boolean isAdmin() {
        int count = adminRepo.countByChatId(chatId);
        if (count > 0) return EXIT;
        return COMEBACK;
    }

    protected boolean isOperator() {
        int count = menuRepo.countByChatId(chatId);
        if (count > 0) return EXIT;
        return COMEBACK;
    }

    protected boolean isZakupshik() {
        return zakupshikRepo.existsByUser(usersRepo.findByChatId(chatId));

    }

    protected boolean isAdmin(Long chat) {
        int count = adminRepo.countByChatId(chat);
        if (count > 0) return EXIT;
        return COMEBACK;
    }

    protected boolean isButtonExist(String name) {

        return buttonRepo.countByName(name) > 0;
    }


    protected boolean isAdmin(long chatId) {
        int count = adminRepo.countByChatId(chatId);
        if (count > 0) return EXIT;
        return COMEBACK;
    }

    protected String getLinkForUser(long chatId, String userName) {
        return String.format("<a href = \"tg://user?id=%s\">%s</a>", chatId, userName);
    }

    protected int toDeleteMessage(int messageDeleteId) {
        SetDeleteMessages.addKeyboard(chatId, messageDeleteId);
        return messageDeleteId;
    }

    protected int toDeleteKeyboard(int messageDeleteId) {
        SetDeleteMessages.addKeyboard(chatId, messageDeleteId);
        return messageDeleteId;
    }

    protected int sendMessageWithKeyboard(int messageId, ReplyKeyboard keyboard) throws TelegramApiException {
        return sendMessageWithKeyboard(getText(messageId), keyboard);
    }

    protected int sendMessageWithKeyboard(String text, int keyboardId) throws TelegramApiException {
        return sendMessageWithKeyboard(text, keyboardMarkUpService.select(keyboardId));
    }

    protected int sendMessageWithKeyboard(String text, ReplyKeyboard keyboard) throws TelegramApiException {
        lastSentMessageID = sendMessageWithKeyboard(text, keyboard, chatId);
        return lastSentMessageID;
    }

    protected int sendMessageWithKeyboard(String text, ReplyKeyboard keyboard, long chatId) throws TelegramApiException {
        return botUtils.sendMessageWithKeyboard(text, keyboard, chatId);
    }

    protected boolean isExist(String buttonName) {
        return buttonRepo.countByName(buttonName) > 0;
    }

    protected void delete(int updateMessageId, int deleteMesId) {
        deleteMessage(updateMessageId);
        deleteMessage(deleteMesId);
        deleteMessage(lastSentMessageID);
    }

    protected void delete(int updateMessageId) {
        deleteMessage(updateMessageId);
        deleteMessage(lastSentMessageID);
    }

    protected String uploadFile(String fileId) {
        Objects.requireNonNull(fileId);
        GetFile getFile = new GetFile();
        getFile.setFileId(fileId);
        try {
            org.telegram.telegrambots.meta.api.objects.File file = bot.execute(getFile);
            return file.getFilePath();
        } catch (TelegramApiException e) {
            throw new IllegalStateException(e);
        }
    }

    protected boolean hasContact() {
        return update.hasMessage() && update.getMessage().getContact() != null;
    }

    protected boolean isRegistered() {
        return usersRepo.countUserByChatId(chatId) > 0;
    }

    protected boolean hasCallbackQuery() {
        return update.hasCallbackQuery();
    }

    protected boolean hasPhoto() {
        return update.hasMessage() && update.getMessage().hasPhoto();
    }

    protected boolean hasDocument() {
        return update.hasMessage() && update.getMessage().hasDocument();
    }

    protected boolean hasAudio() {
        return update.hasMessage() && update.getMessage().getAudio() != null;
    }

    protected boolean hasVideo() {
        return update.hasMessage() && update.getMessage().getVideo() != null;
    }

    protected boolean hasMessageText() {
        return update.hasMessage() && update.getMessage().hasText();
    }
}
