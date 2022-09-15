package com.Jakko.model.standart;

import com.Jakko.repository.TempUsersRepo;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TempUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private long chatId;
    private String phone;
    private String fullName;

    @Column(length = 500)
    private String username;
    public TempUser() {
    }

    public TempUser(User user) {
        this.chatId = user.getChatId();
        this.fullName = user.getFullName();
        this.username = user.getUsername();
        this.phone = user.getPhone();
    }
}