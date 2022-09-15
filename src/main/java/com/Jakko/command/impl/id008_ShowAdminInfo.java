package com.Jakko.command.impl;

import com.Jakko.command.Command;
import com.Jakko.model.standart.Message;
import com.Jakko.util.Const;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;



public class id008_ShowAdminInfo extends Command {

    @Override
    public boolean execute() throws TelegramApiException {
        if (!isAdmin()) {
            sendMessage(Const.NO_ACCESS);
            return EXIT;
        }
        deleteMessage(updateMessageId);
        Message message = messageRepo.findById((int)messageId);
        sendMessage(messageId, chatId, null, message.getPhoto());
        if (message.getFile() != null) {
            switch (message.getFileType()) {
                case "audio":
                    bot.execute(new SendAudio().setAudio(message.getFile()).setChatId(chatId));
                case "video":
                    bot.execute(new SendVideo().setVideo(message.getFile()).setChatId(chatId));
                case "document":
                    bot.execute(new SendDocument().setChatId(chatId).setDocument(message.getFile()));
            }
        }
        return EXIT;
    }
}
