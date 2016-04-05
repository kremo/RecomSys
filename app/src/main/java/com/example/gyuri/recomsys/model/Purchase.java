package com.example.gyuri.recomsys.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Gyuri on 2016. 03. 31..
 */
public class Purchase {
    String purchaseObjectSeparator = "|-_-|";

    public Book book;
    public User user;
    public Date date;


    public Purchase(Book book, User user, Date date) {
        this.book = book;
        this.user = user;
        this.date = date;
    }

    public Purchase(String loadedStr){
        readFromString(loadedStr);
    }

    public String writeToString(){
        String str = new String();
        str = str.concat(book.writeToString()+purchaseObjectSeparator);
        str = str.concat(user.writeToString()+purchaseObjectSeparator);

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String purchaseDate = df.format(date);

        str = str.concat(purchaseDate+purchaseObjectSeparator);

        return str;
    }

    public void readFromString(String str){
        String[] strings = str.split(purchaseObjectSeparator);
        user = new User(strings[0]);
        book = new Book(strings[1]);
        String dateString = strings[2];
        SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        try {
            date = (Date)formatter1.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
