package com.Jakko.model.standart;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;

import java.util.*;

@Component
public class TimerTaskControl {
    private static Map<Long, Timer> timersPayDorms = new HashMap<>();

    public void init(DefaultAbsSender bot) {
        addTimer(bot);

    }


    public static void addTimer(DefaultAbsSender bot) {
        Timer timer = new Timer(true);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        timer.scheduleAtFixedRate(new SaveDocument(bot), new Date(),  1000 * 60 *2); // 1000 = 1 seconds  1000 * 60 * 60 * 12
    }


}