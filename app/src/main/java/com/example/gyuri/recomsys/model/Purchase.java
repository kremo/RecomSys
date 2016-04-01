package com.example.gyuri.recomsys.model;

import java.util.Date;

/**
 * Created by Gyuri on 2016. 03. 31..
 */
public class Purchase {
    public Book book;
    public User user;
    public Date date;


    public Purchase(Book book, User user, Date date) {
        this.book = book;
        this.user = user;
        this.date = date;
    }
}
