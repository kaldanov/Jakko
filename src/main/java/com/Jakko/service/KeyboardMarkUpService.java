package com.Jakko.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import com.Jakko.configuration.Conversation;
import com.Jakko.enums.Language;
import com.Jakko.model.standart.Button;
import com.Jakko.model.standart.Keyboard;
import com.Jakko.repository.ButtonRepo;
import com.Jakko.repository.KeyboardRepo;
import com.Jakko.repository.TelegramBotRepositoryProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class KeyboardMarkUpService {

    private ButtonRepo buttonRepo = TelegramBotRepositoryProvider.getButtonRepo();
    private KeyboardRepo keyboardRepo = TelegramBotRepositoryProvider.getKeyboardRepo();

    public ReplyKeyboard select(long keyboardMarkUpId) {
        if (keyboardMarkUpId < 0) {
            ReplyKeyboardRemove keyboard = new ReplyKeyboardRemove();
            return keyboard;
        }
        if (keyboardMarkUpId == 0) return null;
        return getKeyboard(keyboardRepo.findById((int) keyboardMarkUpId).get());
    }

    public ReplyKeyboard selectForEdition(long keyboardMarkUpId) {
        if (keyboardMarkUpId < 0) {
            ReplyKeyboardRemove keyboard = new ReplyKeyboardRemove();
            return keyboard;
        }
        if (keyboardMarkUpId == 0) return null;
        return getKeyboardForEdition(keyboardRepo.findById((int) keyboardMarkUpId).get());
    }

    private ReplyKeyboard getKeyboard(Keyboard keyboard) {
        String buttonIds = keyboard.getButtonIds();
        if (buttonIds == null) return null;
        String[] rows = buttonIds.split(";");
        if (keyboard.isInline()) {
            return getInlineKeyboard(rows);
        } else {
            return getReplyKeyboard(rows);
        }
    }

    private ReplyKeyboard getKeyboardForEdition(Keyboard keyboard) {
        String buttonIds = keyboard.getButtonIds();
        if (buttonIds == null) return null;
        String[] rows = buttonIds.split(";");
        return getInlineKeyboardForEdition(rows);
    }

    private InlineKeyboardMarkup getInlineKeyboard(String[] rowIds) {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        for (String buttonIdsString : rowIds) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            String[] buttonIds = buttonIdsString.split(",");
            for (String buttonId : buttonIds) {
//                Button buttonFromDb = buttonDao.getButton(Integer.parseInt(buttonId));
                Button buttonFromDb = buttonRepo.findById(Integer.parseInt(buttonId));
                InlineKeyboardButton button = new InlineKeyboardButton();
                String buttonText = buttonFromDb.getName();
                button.setText(buttonText);
                String url = buttonFromDb.getUrl();
                if (url != null) {
                    button.setUrl(url);
                } else {
                    buttonText = buttonText.length() < 64 ? buttonText : buttonText.substring(0, 64);
                    button.setCallbackData(buttonText);
                }
                row.add(button);
            }
            rows.add(row);
        }
        keyboard.setKeyboard(rows);
        return keyboard;
    }

    private InlineKeyboardMarkup getInlineKeyboardForEdition(String[] rowIds) {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        for (String buttonIdsString : rowIds) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            String[] buttonIds = buttonIdsString.split(",");
            for (String buttonId : buttonIds) {
                Button buttonFromDb = buttonRepo.findById(Integer.parseInt(buttonId));
                InlineKeyboardButton button = new InlineKeyboardButton();
                String buttonText = buttonFromDb.getName();
                button.setText(buttonText);
                String url = buttonFromDb.getUrl();
                if (url != null) {
                    button.setUrl(url);
                } else {
                    button.setCallbackData(buttonId);
                }
                row.add(button);
            }
            rows.add(row);
        }
        keyboard.setKeyboard(rows);
        return keyboard;
    }

    private ReplyKeyboard getReplyKeyboard(String[] rows) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        boolean isRequestContact = false;
        for (String buttonIdsString : rows) {
            KeyboardRow keyboardRow = new KeyboardRow();
            String[] buttonIds = buttonIdsString.split(",");
            for (String buttonId : buttonIds) {
//                Button buttonFromDb = buttonDao.getButton(Integer.parseInt(buttonId));
                Button buttonFromDb = buttonRepo.findById(Integer.parseInt(buttonId));
                KeyboardButton button = new KeyboardButton();
                String buttonText = buttonFromDb.getName();
                button.setText(buttonText);
                button.setRequestContact(buttonFromDb.isRequestContact());
                if (buttonFromDb.isRequestContact()) isRequestContact = true;
                keyboardRow.add(button);
            }
            keyboardRowList.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        replyKeyboardMarkup.setOneTimeKeyboard(isRequestContact);
        return replyKeyboardMarkup;
    }


    public List<Button> getListForEdit(int keyId) {
        List<Button> list = new ArrayList<>();
        for (String x : Arrays.asList(getButtonString(keyId).split(";"))) {
            list.add(buttonRepo.findById(Integer.parseInt(x)));
        }
        return list;
    }

    public boolean isInline(int keyboardMarkUpId) {
        return keyboardRepo.isInline(keyboardMarkUpId);
    }

    public String getButtonString(int id) {
        return keyboardRepo.findById(id).get().getButtonIds();
    }
}
