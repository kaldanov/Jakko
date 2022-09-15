package com.Jakko.command.impl;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.Jakko.command.Command;

public class id001_ShowInfo extends Command {

    @Override
    public boolean execute() throws TelegramApiException {
        deleteMessage(updateMessageId);

        sendMessageWithAddition();

        return EXIT;
    }
}
