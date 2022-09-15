package com.Jakko.util;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;

import java.util.List;
import java.util.ListIterator;

public class ParserMessageEntity {

    private String result;
    private String text;
    private String end;
    private String start;

    public  String  getTextWithEntity(Message message) {
        if (message.hasText() && message.hasEntities()) {
            result                                      = message.getText();
            List<MessageEntity> entities                = message.getEntities();
            ListIterator<MessageEntity> listIterator    = entities.listIterator(entities.size());
            while (listIterator.hasPrevious()) {
                MessageEntity previous  = listIterator.previous();
                parseEntity(previous);
                magic(previous);
                result                  = start + text + end;
            }
        } else if (message.hasText()) return message.getText();
        return result;
    }

    private void    magic(MessageEntity messageEntity) {
        if (messageEntity.getType().equalsIgnoreCase("bold")) {
            formatText("<b>%s</b>");
            return;
        }
        if (messageEntity.getType().equalsIgnoreCase("italic")) {
            formatText("<i>%s</i>");
            return;
        }
        if (messageEntity.getType().equalsIgnoreCase("code")) {
            formatText("<code>%s</code>");
            return;
        }
        if (messageEntity.getType().equalsIgnoreCase("text_link")) {
            formatText("<a href=\"" + messageEntity.getUrl() + "\">%s</a>");
            return;
        }
    }

    private String  formatText(String format) {
        return text = String.format(format, text);
    }

    private void    parseEntity(MessageEntity entity) {
        start   = result.substring(0, entity.getOffset());
        text    = result.substring(entity.getOffset(), entity.getOffset() + entity.getLength());
        end     = result.substring(entity.getOffset() + entity.getLength());
    }

    public  String  parseEntityToStringTag(Message message) {
        if (message.hasText() && message.hasEntities()) {
            result                          = message.getText();
            List<MessageEntity> entities    = message.getEntities();
            ListIterator<MessageEntity> li  = entities.listIterator(entities.size());
            while (li.hasPrevious()) {
                MessageEntity previous  = li.previous();
                parseEntity(previous);
                magic(previous);
                result                  = start + text + end;
            }
        } else if (message.hasText()) { return message.getText(); }
        return result;
    }
}
