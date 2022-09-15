package com.Jakko.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.Jakko.enums.WaitingType;
import com.Jakko.model.standart.User;
import com.Jakko.util.BotUtil;
import com.Jakko.util.Const;
import com.Jakko.util.UpdateUtil;

@Service
public class RegistrationService {

    private User user;
    private long chatId;
    private BotUtil botUtil;
    private WaitingType waitingType = WaitingType.START;
    private boolean COMEBACK = false;
    private boolean EXIT = true;
    private int deleteId;
    private int wrongDeleteId;

    public boolean isRegistration(Update update, BotUtil botUtil) throws TelegramApiException {
        if (botUtil == null || chatId == 0) {
            chatId = UpdateUtil.getChatId(update);
            this.botUtil = botUtil;
        }
        switch (waitingType) {
            case START:
                user = new User();
                user.setChatId(chatId);
                deleteId = getName();
                waitingType = WaitingType.SET_FULL_NAME;
                return COMEBACK;
            case SET_FULL_NAME:
                delete();
                if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().length() <= 50) {
                    user.setFullName(update.getMessage().getText());
                    deleteId = getPhone();
                    waitingType = WaitingType.SET_PHONE_NUMBER;
                } else {
                    wrongDeleteId =wrongData();
                    deleteId = getName();
                }
                return COMEBACK;
            case SET_PHONE_NUMBER:
                delete();
                if (botUtil.hasContactOwner(update)) {
                    String phone = update.getMessage().getContact().getPhoneNumber();
                    if (update.getMessage().getContact().getPhoneNumber().startsWith("8")) {
                        phone = update.getMessage().getContact().getPhoneNumber().replaceFirst("8", "+7");
                    }
                    if (update.getMessage().getContact().getPhoneNumber().startsWith("7")) {
                        phone = update.getMessage().getContact().getPhoneNumber().replaceFirst("7", "+7");
                    }
                    user.setPhone(phone);
                    user.setUsername(UpdateUtil.getFrom(update));
                    return EXIT;
                } else {
                    wrongDeleteId = wrongData();
                    deleteId = getPhone();
                    return COMEBACK;
                }
        }
        return EXIT;
    }

    private void delete() throws TelegramApiException {
        botUtil.deleteMessage(chatId, deleteId);
        botUtil.deleteMessage(chatId, wrongDeleteId);
    }

    private int wrongData() throws TelegramApiException {
        return botUtil.sendMessage(Const.WRONG_DATA_TEXT, chatId);
    }

    private int getName() throws TelegramApiException {
        return botUtil.sendMessage(Const.SET_FULL_NAME_MESSAGE, chatId);
    }

    private int getPhone() throws TelegramApiException {
        return botUtil.sendMessage(Const.SEND_CONTACT_MESSAGE, chatId);
    }

    public User getUser() {
        return user;
    }
}
