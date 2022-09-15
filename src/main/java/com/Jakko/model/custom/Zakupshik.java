package com.Jakko.model.custom;

import com.Jakko.model.standart.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Zakupshik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int     id;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;


}
