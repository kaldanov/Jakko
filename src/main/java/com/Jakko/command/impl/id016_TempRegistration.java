package com.Jakko.command.impl;

import com.Jakko.command.Command;
import com.Jakko.model.custom.Master;
import com.Jakko.model.standart.Admin;
import com.Jakko.model.standart.TempUser;
import com.Jakko.model.standart.User;
import com.Jakko.service.RegistrationService;
import com.Jakko.util.ButtonsLeaf;
import com.Jakko.util.Const;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

public class id016_TempRegistration extends Command {

    @Override

    public boolean execute() throws TelegramApiException {
        deleteMessage(updateMessageId);
        if (isAdmin()) {
            if (isButton(35)) {

                TempUser tempUser = tempUsersRepo.findById(Integer.parseInt(update.getCallbackQuery().getMessage().getText().split("№")[1].split("\n")[0]));
                Master master = masterRepo.findByPhone(tempUser.getPhone());
                if (master != null) {
                    tempUsersRepo.delete(tempUser);
                    master.setUser(usersRepo.save(new User(tempUser)));
                    masterRepo.save(master);
                    sendMessageWithKeyboard(getText(56),keyboardMarkUpService.select(1), tempUser.getChatId());
                    sendMessage("Пользователь успешно зарегистрировался как мастер");
                }
                else {
                    tempUsersRepo.delete(tempUser);
                    usersRepo.save(new User(tempUser));
                    sendMessageWithKeyboard(getText(56),keyboardMarkUpService.select(1), tempUser.getChatId());
                    sendMessage("Пользователь успешно зарегистрировался как пользователь");
                }
            } else if (isButton(36)) {
                TempUser tempUser = tempUsersRepo.findById(Integer.parseInt(update.getCallbackQuery().getData()));
                tempUsersRepo.delete(tempUser);
                sendMessage(getText(57), tempUser.getChatId());
            }


        } else {
            sendMessage(getText(10));
        }
        return EXIT;
    }
}
