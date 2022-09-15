package com.Jakko.model.custom;

import com.Jakko.model.standart.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int     id;

    private int     id2;

    private boolean type;

    private String phone;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;


}
