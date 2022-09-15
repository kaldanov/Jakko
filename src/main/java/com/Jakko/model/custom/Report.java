package com.Jakko.model.custom;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int     id;

    private boolean day;

    private String equipmentName;

    private String nomenclatureId;

    private String nomenclatureName;

    private String type;

    private int eye;

    private int cycle;

    private int count;

    private int time;

    private Date date;

    private Date regDate;

    @OneToOne(fetch = FetchType.EAGER)
    private Master master;

    @OneToMany(fetch = FetchType.EAGER)
    private List<RejectReason> rejectReasons;



}
