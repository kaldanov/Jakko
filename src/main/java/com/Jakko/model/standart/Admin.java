package com.Jakko.model.standart;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int     id;

    private long    chatId;

    @Column(length = 500)
    private String  fullName;

    public Admin(long chatId, String fullName) {
        this.chatId = chatId;
        this.fullName = fullName;
    }

    public Admin() {

    }

    public Admin setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

}
