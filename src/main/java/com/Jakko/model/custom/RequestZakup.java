package com.Jakko.model.custom;

import com.Jakko.model.standart.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class RequestZakup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String equipmentName;

    private String nomenclatureName;

    private String file;

    private String description;

    private String unit;

    private int count;

    private Integer priority;

    private boolean executed;  // true = Исполнено

    @OneToOne
    private User user;

    private Date zakupDate;

    private Date date;


}
