package com.Jakko.model.standart;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private long chatId;
    private String phone;
    private String fullName;

    @Column(length = 500)
    private String username;

    public User() {
    }

    public User(TempUser user) {
        this.chatId = user.getChatId();
        this.fullName = user.getFullName();
        this.username = user.getUsername();
        this.phone = user.getPhone();

    }
}