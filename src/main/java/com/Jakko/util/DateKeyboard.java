package com.Jakko.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.text.SimpleDateFormat;
import java.util.*;

public class DateKeyboard {

    private static SimpleDateFormat formatCallBack  = new SimpleDateFormat("dd.MM.yyyy");
    private static SimpleDateFormat formatYear      = new SimpleDateFormat("yyyy");
    private static SimpleDateFormat formatMonth     = new SimpleDateFormat("MMMMM");
    private        Calendar         startDay        = Calendar.getInstance(new Locale("en", "UK"));
    private        String           buttonBackMonth = "◄";
    private        String           buttonNextMonth = "►";
    private        String           buttonBackYear  = "◄◄";
    private        String           buttonNextYear  = "►►";
    private        String           emptyValue      = "-";


    public  boolean                 isNext(String updateText) {
        if (updateText.equals(buttonNextMonth)) {
            startDay.add(Calendar.MONTH, 1);
            return true;
        }
        if (updateText.equals(buttonBackMonth)) {
            startDay.add(Calendar.MONTH, -1);
            return true;
        }
        if (updateText.equals(buttonNextYear)) {
            startDay.add(Calendar.YEAR, 1);
            return true;
        }
        if (updateText.equals(buttonBackYear)) {
            startDay.add(Calendar.YEAR, -1);
            return true;
        }
        if (updateText.equals(emptyValue) || updateText.equals(formatYear.format(startDay.getTime())) || updateText.equals(formatMonth.format(startDay.getTime()))) return true;
        return false;
    }

    public  String                  getDate(String updateText) {
        try {
            if (updateText.length() > 2) return null;
            int day = Integer.parseInt(updateText);
            startDay.set(Calendar.DAY_OF_MONTH, day);
            return formatCallBack.format(startDay.getTime());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public  Calendar                getDate() { return startDay; }

    public  Calendar                getCalendarDate(String updateText) {
        if (getDate(updateText) == null) return null;
        return startDay;
    }

    public  Date                    getDateDate(String updateText) { return getCalendarDate(updateText).getTime(); }

    public  InlineKeyboardMarkup    getCalendarKeyboard() {
        SimpleDateFormat simpleDateFormat   = new SimpleDateFormat("dd");
        ArrayList<String> listButtons       = new ArrayList<>();
        startDay.set(Calendar.DAY_OF_MONTH, 1);
        int currentMonth                    = startDay.get(Calendar.MONTH);
        listButtons.add(buttonBackYear + "," + formatYear.format(startDay.getTime()) + "," + buttonNextYear);
        listButtons.add(buttonBackMonth + "," + formatMonth.format(startDay.getTime()) + "," + buttonNextMonth);
        for (int i = 0; i < 5; i++) {
            String row = "";
            for (int t = 0; t < 7; t++) {
                if (startDay.get(Calendar.MONTH) != currentMonth) {
                    row += emptyValue + ",";
                    continue;
                }
                row += (simpleDateFormat.format(startDay.getTime())) + ",";
                startDay.add(Calendar.DATE, 1);
            }
            listButtons.add(row);
            if (startDay.get(Calendar.MONTH) != currentMonth) break;
        }
        startDay.add(Calendar.MONTH, -1);
        return getInlineKeyboard(listButtons.toArray(new String[listButtons.size()]));
    }

    private InlineKeyboardMarkup    getInlineKeyboard(String[] namesButton) {
        InlineKeyboardMarkup keyboard                   = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsKeyboard   = new ArrayList<>();
        String buttonIdsString;
        for (int i = 0; i < namesButton.length; i++) {
            buttonIdsString                         = namesButton[i];
            List<InlineKeyboardButton> rowButton    = new ArrayList<>();
            String[] buttonIds = buttonIdsString.split(",");
            for (String buttonId : buttonIds) {
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText(buttonId);
                button.setCallbackData(buttonId);
                rowButton.add(button);
            }
            rowsKeyboard.add(rowButton);
        }
        keyboard.setKeyboard(rowsKeyboard);
        return keyboard;
    }

    public  InlineKeyboardMarkup    getWeekCalendarKeyboard() {
        SimpleDateFormat simpleDateFormat   = new SimpleDateFormat("dd");
        ArrayList<String> listButtons       = new ArrayList<>();
        startDay.set(Calendar.DAY_OF_MONTH, 1);
        int currMonth                       = startDay.get(Calendar.MONTH);
        listButtons.add(buttonBackYear + "," + formatYear.format(startDay.getTime()) + "," + buttonNextYear);
        listButtons.add(buttonBackMonth + "," + formatMonth.format(startDay.getTime()) + "," + buttonNextMonth);
        boolean isDayFinned                 = false;
        for (int i = 0; i < 6; i++) {
            String row = "";
            for (int t = 0; t < 7; t++) {
                if (startDay.get(Calendar.MONTH) != currMonth) {
                    row += emptyValue + ",";
                    continue;
                }
                if (!isDayFinned) {
                    int dayOfWeek = startDay.get(Calendar.DAY_OF_WEEK) - 1;
                    if (dayOfWeek == 0) dayOfWeek = 7;
                    if (dayOfWeek != (t + 1)) {
                        row += emptyValue + ",";
                        continue;
                    } else {
                        isDayFinned = true;
                    }
                }
                row += (simpleDateFormat.format(startDay.getTime())) + ",";
                startDay.add(Calendar.DATE, 1);
            }
            listButtons.add(row);
            if (startDay.get(Calendar.MONTH) != currMonth) break;
        }
        startDay.add(Calendar.MONTH, -1);
        return getInlineKeyboard(listButtons.toArray(new String[listButtons.size()]));
    }
}
