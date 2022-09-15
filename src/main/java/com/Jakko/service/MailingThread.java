package com.Jakko.service;

import com.Jakko.model.standart.User;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;

import java.util.List;

public class MailingThread extends Thread {

    private DefaultAbsSender bot;
    private List<User> users;
    List<String> documents;
    List<InputMedia> photoVideos;
    String text;
    int i = 0;


    public MailingThread( DefaultAbsSender bot, List<User> users, List<String> documents, List<InputMedia> photoVideos, String text) {
        this.bot = bot;
        this.users = users;
        this.documents = documents;
        this.photoVideos = photoVideos;
        this.text = text;
    }

    @Override
    public void run() {

        for(User user : users){
            if (documents != null && documents.size() > 0){
                for (String document : documents){
                    SendDocument sendDocument = new SendDocument();
                    sendDocument.setDocument(document).setChatId(user.getChatId());
                    try {
                        bot.execute(sendDocument);
                        sleepp();
                    } catch (Exception e) {e.printStackTrace(); }
                }
            }
            if (photoVideos != null && photoVideos.size()>0){
                SendMediaGroup sendMediaGroup = new SendMediaGroup();
                sendMediaGroup.setChatId(user.getChatId()).setMedia(photoVideos);
                try {
                    bot.execute(sendMediaGroup);
                    sleepp();
                } catch (Exception e) {e.printStackTrace(); }
            }
            SendMessage sendMessage = new SendMessage();
            sendMessage.setParseMode("html").setText(text).setChatId(user.getChatId());
            try {
                bot.execute(sendMessage);
                sleepp();
            } catch (Exception e) {e.printStackTrace(); }
        }

    }

    private void sleepp() {
        i++;
        if (i >= 10){
            try {
                sleep(1500);
                i=0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}