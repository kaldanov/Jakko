package com.Jakko.command.impl;

import com.Jakko.command.Command;
import com.Jakko.model.standart.Admin;
import com.Jakko.model.standart.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class id006_EditAdmin extends Command {
    User user;

    private int messId;
    private int notRegisteredMessId;
    private int alreadyAdminMessId;


    @Override
    public boolean execute() throws TelegramApiException {
        deleteUpdateMess();
        deleteNotRegisteredMessId();
        deleteAlreadyAdminMessId();
        user = usersRepo.findByChatId(chatId);
        if (!isRegistered()) {
            sendMessageWithKeyboard(getText(11), 5);
            return EXIT;
        }
        if (!isAdmin()) {
            sendMessageWithKeyboard(getText(10), 1);
            return EXIT;
        }
        if (hasContact()) {
            String phone = update.getMessage().getContact().getPhoneNumber();

            if (phone.charAt(0) == '8') {
                phone = phone.replaceFirst("8", "+7");
            } else if (phone.charAt(0) == '7') {
                phone = phone.replaceFirst("7", "+7");
            }

            return saveAdmin(phone);
        } else if (hasMessageText() && isPhoneNumber(updateMessageText)) {
            String phone = updateMessageText;

            if (phone.charAt(0) == '8') {
                phone = phone.replaceFirst("8", "+7");
            } else if (phone.charAt(0) == '7') {
                phone = phone.replaceFirst("7", "+7");
            }

            return saveAdmin(phone);

        } else if (hasMessageText() && updateMessageText.contains("/del")) {
            if (adminRepo.findAll().size() == 1) {
                return COMEBACK;
            }
            int delAdminId = getDelAdminId(updateMessageText);
            if (adminRepo.findById(delAdminId) != null) {
                adminRepo.delete(adminRepo.findById(delAdminId));
                editMessage(getListAdmins(), chatId, messId);
                return COMEBACK;
            }
        } else {
            if (messId == 0) {
                messId = sendMessage(getListAdmins());
            }
            return COMEBACK;
        }
        return COMEBACK;
    }

    private void deleteAlreadyAdminMessId() {
        if (alreadyAdminMessId != 0)
            deleteMessage(alreadyAdminMessId);
    }

    private void deleteNotRegisteredMessId() {
        if (notRegisteredMessId != 0)
            deleteMessage(notRegisteredMessId);
    }

    private int getDelAdminId(String updateMessageText) {
        try {
            return Integer.parseInt(updateMessageText.substring(4));
        } catch (Exception e) {
            return -1;
        }
    }

    private boolean isPhoneNumber(String phone) {

        if (phone.charAt(0) == '8') {
            phone = phone.replaceFirst("8", "+7");
        } else if (phone.charAt(0) == '7') {
            phone = phone.replaceFirst("7", "+7");
        }
        return phone.charAt(0) == '+' && phone.charAt(1) == '7' && phone.substring(2).length() == 10 && isLong(phone.substring(2));
    }

    private boolean saveAdmin(String phone) throws TelegramApiException {
        User newAdmin = usersRepo.findByPhone(phone);
        if (newAdmin == null) {
            deleteMessage(notRegisteredMessId);
            notRegisteredMessId = sendMessage(14);
            return COMEBACK;
        }
        if (adminRepo.findByChatId(newAdmin.getChatId()) != null) {
            deleteAlreadyAdminMessId();
            alreadyAdminMessId = sendMessage(13);
            return COMEBACK;
        }
        Admin admin = new Admin(usersRepo.findByPhone(phone).getChatId(), newAdmin.getFullName());
        adminRepo.save(admin);
        editMessage(getListAdmins(), chatId, messId);
        return COMEBACK;
    }

    private void deleteUpdateMess() {
        deleteMessage(updateMessageId);
    }


    protected boolean isLong(String mess) {
        try {
            Long.parseLong(mess);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String getListAdmins() {
        StringBuilder admins = new StringBuilder();
        admins.append(getText(12)).append(next).append(next);
        List<Admin> adminList = adminRepo.findAll();
        for (Admin admin : adminList) {
            try {
                admins.append(admin.getFullName()).append(" ");
                if (adminList.size() > 1)
                    admins.append("??? /del").append(admin.getId()).append(next);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        admins.append(next).append(next).append(getText(15));
        adminList.clear();
        return admins.toString();
    }
}
