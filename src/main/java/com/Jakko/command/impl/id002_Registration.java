package com.Jakko.command.impl;

import com.Jakko.model.custom.Master;
import com.Jakko.model.standart.Admin;
import com.Jakko.model.standart.TempUser;
import com.Jakko.service.RegistrationService;
import com.Jakko.util.ButtonsLeaf;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.Jakko.command.Command;

import java.util.ArrayList;
import java.util.List;

public class id002_Registration extends Command {

    private RegistrationService registrationService = new RegistrationService();

    @Override
    public boolean execute() throws TelegramApiException {
        deleteMessage(updateMessageId);
        if (!isRegistered()) {
            if (!registrationService.isRegistration(update, botUtils)) {
                return COMEBACK;
            } else {
                TempUser tempUser = tempUsersRepo.save(new TempUser(registrationService.getUser()));
                StringBuilder s = new StringBuilder();
                for (Admin admin : adminRepo.findAll()) {
                    s.append("Запрос №").append(tempUser.getId()).append(next);
                    s.append("ФИО: ").append(tempUser.getFullName()).append(next).append("Номер телефона: ").
                            append(tempUser.getPhone());
                    toDeleteMessage(sendMessageWithKeyboard(s.toString(), keyboardMarkUpService.select(19), admin.getChatId()));
                    s = new StringBuilder();
                }
                sendMessage("Ваша заявка отправлено на проверку");
            }
        } else {
            sendMessageWithAddition();
        }
        return EXIT;
    }
}
