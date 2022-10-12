package com.rest_api.service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //Tabelle Erstellen im Datenbank
public class Todo {
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY ) //ID wird automatisch um 1 erhöht
    private Integer id;

    private Integer userId;
    private String beschreibung;
    private boolean stimmt;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setBeschreibung(String desc){
        this.beschreibung = desc;
    }
    public String getBeschreibung(){
        return this.beschreibung;
    }
    public void setStimmt(boolean stimmt){
        this.stimmt = stimmt;
    }

    public boolean getStimmt() {
        return this.stimmt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
