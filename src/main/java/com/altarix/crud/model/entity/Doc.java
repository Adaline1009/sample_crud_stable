package com.altarix.crud.model.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Вячеслав on 20.10.2017.
 */
@Component
public class Doc {
    private long cardId;
    private int code;
    private Date date;
    private String name;
    private String kind;

    public Doc() {}

    public Doc(int code, Date date, String name) {
        this.code = code;
        this.date = date;
        this.name = name;
    }

    public Doc(int code, Date date, String name, String kind) {
        this.code = code;
        this.date = date;
        this.name = name;
        this.kind = kind;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
