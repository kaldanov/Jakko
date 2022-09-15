package com.Jakko.util;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SetDeleteMessages {

    private static Map<Long, Set<Integer>> messagesWithKeyboardIds = new HashMap<>();
    private static Map<Long, Set<Integer>> messagesIds             = new HashMap<>();

    public  static void deleteKeyboard(long chatId, DefaultAbsSender bot) {
        try {
            if (messagesWithKeyboardIds.get(chatId) != null) {
                for (Integer integer : messagesWithKeyboardIds.get(chatId)) {
                    try {
                        bot.execute(new EditMessageReplyMarkup().setChatId(chatId).setMessageId(integer).setReplyMarkup(null));
                    } catch (TelegramApiException e){}
                }
            }
        } catch (Exception e) {}
        messagesWithKeyboardIds.put(chatId, new HashSet<>());
    }

    public  static void deleteMessage(long chatId, DefaultAbsSender bot) {
        try {
            if (messagesIds.get(chatId) != null) {
                for (Integer integer : messagesIds.get(chatId)) {
                    try {
                        bot.execute(new DeleteMessage(chatId, integer));
                    } catch (TelegramApiException e) {}
                }
            }
        } catch (Exception e) {}
        messagesWithKeyboardIds.put(chatId, new HashSet<>());
    }

    public  static void addKeyboard(long chatId, int messageId) { add(messagesWithKeyboardIds, chatId, messageId); }

    private static void add(Map<Long, Set<Integer>> map, long chatId, int messageId) {
        if (map.get(chatId) == null) {
            Set<Integer> set = new HashSet<>();
            set.add(messageId);
            map.put(chatId, set);
        } else {
            map.get(chatId).add(messageId);
        }
    }
}
