package com.Jakko;


import com.Jakko.model.standart.TimerTaskControl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import com.Jakko.configuration.Bot;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class JakkoApplication implements CommandLineRunner {
	public static void main(String[] args) {
		System.out.println("HEllo");
		SpringApplication.run(JakkoApplication.class, args);
	}

	@Autowired
	private TimerTaskControl timerTaskControl = new TimerTaskControl();

	@Override
	public void run(String... args){
		ApiContextInitializer.init();
		log.info("ApiContextInitializer.InitNormal()");
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		Bot bot = new Bot();
		try {
			telegramBotsApi.registerBot(bot);
			timerTaskControl.init(bot);
			log.info("Bot was registered: " + bot.getBotUsername());
		} catch (TelegramApiRequestException e) {
			log.error("Error in main class", e);
		}
	}

}
