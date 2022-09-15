package com.Jakko.model.custom;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RejectReason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int     id;

    private int time;

    @OneToOne(fetch = FetchType.EAGER)
    private Reject reject;


    public RejectReason(int time, Reject reject){
        this.reject = reject;
        this.time = time;
    }


    public RejectReason() {

    }
}
