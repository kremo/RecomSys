package com.example.gyuri.recomsys.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Gyuri on 2016. 03. 29..
 */
public class User {
    private final String userStringSeparator = "++!!++";


    private String name;
    private long id;
    private String nickName;
    private int age;

    public User(String name, long id, String nickName, int age) {
        this.name = name;
        this.id = id;
        this.nickName = nickName;
        this.age = age;
    }
    public  User(String s){
        this.readFromString(s);
    }

    public String writeToString() {
        String str = new String();
        str = str.concat(name + userStringSeparator);
        str = str.concat(id + userStringSeparator);
        str = str.concat(nickName + userStringSeparator);
        str = str.concat(age + userStringSeparator);
        return str;
    }

    public void readFromString(String str) {
        String[] strings = str.split(userStringSeparator);
        name = strings[0];
        id = Long.parseLong(strings[1]);
        nickName = strings[2];
        age = Integer.parseInt(strings[3]);
    }
}
