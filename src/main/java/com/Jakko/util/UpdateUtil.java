package com.Jakko.util;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public class UpdateUtil {

    public  static long     getChatId(Update update) {
        if (update.hasMessage())            return update.getMessage().getChatId();
        if (update.hasEditedMessage())      return update.getEditedMessage().getChatId();
        if (update.hasCallbackQuery())      return update.getCallbackQuery().getMessage().getChatId();
        if (update.hasInlineQuery())        return update.getInlineQuery().getFrom().getId();
        if (update.hasChosenInlineQuery())  return update.getChosenInlineQuery().getFrom().getId();
        if (update.hasChannelPost())        return update.getChannelPost().getChatId();
        if (update.hasEditedChannelPost())  return update.getEditedChannelPost().getChatId();
        if (update.hasPreCheckoutQuery())   return update.getPreCheckoutQuery().getFrom().getId();
        if (update.hasShippingQuery())      return update.getShippingQuery().getFrom().getId();
        return update.getMessage().getChatId();
    }

    public  static String   toString(Update update) { return convertString(update, true); }

    private static String   convertString(Update update, boolean isShort) {
        String text             = update.toString();
        int countTab            = 0;
        String[] split          = text.split(",");
        StringBuilder result    = new StringBuilder();
        result.append("\n");
        String concat           = ",";
        for (String s : split) {
            if (isShort) {
                if (!(s.contains("=null") || s.contains("='null'"))) result.append(s).append(concat);
            } else {
                result.append(s).append(concat);
            }
            if (s.contains("{")) {
                countTab++;
            } else if (s.contains("}")) countTab--;
        }
        return result.toString();
    }


    public static String getFrom(User user) {
        String userName = null;
        if (user != null) {
            userName = user.getUserName();
            if (userName == null) {
                userName = user.getFirstName() != null ? user.getFirstName() + " " : "";
                userName += user.getLastName() != null ? user.getLastName() : "";
            }
        }
        return userName;
    }
    public  static String   getFrom(Update update) {
        String userName = null;
        User user       = getUser(update);
        if (user != null) {
            userName    = user.getUserName();
            if (userName == null) {
                userName = user.getFirstName() != null ? user.getFirstName() + " " : "";
                userName += user.getLastName() != null ? user.getLastName() : "";
            }
        }
        return userName;
    }

    public static Chat getChat(Update update) {
        Message message = getMessage(update);
        if (message != null) {
            System.out.println(message.getChat());
            return message.getChat();

        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChat();
        } else {
            return null;
        }
    }

    public static Message getMessage(Update update) {
        if (update.hasMessage()) {
            return update.getMessage();
        } else if (update.hasEditedMessage()) {
            return update.getEditedMessage();
        } else if (update.hasEditedChannelPost()) {
            return update.getEditedChannelPost();
        } else if (update.hasChannelPost()) {
            return update.getChannelPost();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage();
        } else {
            return null;
        }
    }

    public  static User     getUser(Update update) {
        if (update.hasMessage())                return update.getMessage().getFrom();
        if (update.hasEditedMessage())          return update.getEditedMessage().getFrom();
        if (update.hasCallbackQuery())          return update.getCallbackQuery().getFrom();
        if (update.hasInlineQuery())            return update.getInlineQuery().getFrom();
        if (update.hasShippingQuery())          return update.getShippingQuery().getFrom();
        if (update.hasPreCheckoutQuery())       return update.getPreCheckoutQuery().getFrom();
        if (update.hasChannelPost())            return update.getChannelPost().getFrom();
        if ((update.hasEditedChannelPost()))    return update.getEditedChannelPost().getFrom();
        if (update.hasChosenInlineQuery())      return update.getChosenInlineQuery().getFrom();
        return null;
    }
}
