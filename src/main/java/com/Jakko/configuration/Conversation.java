package com.Jakko.configuration;

import com.Jakko.command.Command;
import com.Jakko.enums.Language;
import com.Jakko.exceptions.ButtonNotFoundException;
import com.Jakko.exceptions.CommandNotFoundException;
import com.Jakko.exceptions.KeyboardNotFoundException;
import com.Jakko.exceptions.MessageNotFoundException;
import com.Jakko.model.standart.Message;
import com.Jakko.repository.MessageRepo;
import com.Jakko.service.CommandService;
import com.Jakko.service.KeyboardMarkUpService;
import com.Jakko.util.DateUtil;
import com.Jakko.util.SetDeleteMessages;
import com.Jakko.util.UpdateUtil;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.Jakko.repository.TelegramBotRepositoryProvider;
import com.Jakko.repository.UsersRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@Slf4j
public class Conversation {

    private Long chatId;

    private CommandService commandService = new CommandService();
    private MessageRepo messageRepo = TelegramBotRepositoryProvider.getMessageRepo();
    private KeyboardMarkUpService keyboardMarkUpService = new KeyboardMarkUpService();
    private UsersRepo usersRepo = TelegramBotRepositoryProvider.getUsersRepo();
    private static long currentChatId;
    private Command command;

    public void handleUpdate(Update update, DefaultAbsSender bot) throws TelegramApiException, SQLException, IOException, KeyboardNotFoundException, ButtonNotFoundException, MessageNotFoundException, CommandNotFoundException {
        printUpdate(update);
        chatId = UpdateUtil.getChatId(update);
        currentChatId = chatId;
        try {
            command = commandService.getCommand(update);
            if (command != null) {
                SetDeleteMessages.deleteKeyboard(chatId, bot);
                SetDeleteMessages.deleteMessage(chatId, bot);
            }
        } catch (CommandNotFoundException e) {

            if (chatId < 0) {
//                command = new id038_GroupManager();
            }

            if (command == null) {
                ReplyKeyboard replyKeyboard;
                Message message;
                if (usersRepo.findByChatId(chatId) != null) {
                    replyKeyboard = keyboardMarkUpService.select(1);
                    message = messageRepo.findById(1);
                } else {
                    replyKeyboard = keyboardMarkUpService.select(5);
                    message = messageRepo.findById(1);
                }
                bot.execute(new SendMessage().setChatId(chatId).setText(message.getName()).setReplyMarkup(replyKeyboard));
            }
        }
        if (command != null) {
            if (command.isInitNormal(update, bot)) {
                clear();
                return;
            }
            boolean commandFinished = command.execute();
            if (commandFinished) clear();
        }
    }

    private void printUpdate(Update update) {
        String dataMessage = "";
        if (update.hasMessage())
            dataMessage = DateUtil.getDbMmYyyyHhMmSs(new Date((long) update.getMessage().getDate() * 1000));
        log.info("New update get {} -> send response {}", dataMessage, DateUtil.getDbMmYyyyHhMmSs(new Date()));
        log.info(UpdateUtil.toString(update));
    }

    public static long getCurrentChatId() {
        return currentChatId;
    }


    private void clear() {
        command.clear();
        command = null;
    }
}
