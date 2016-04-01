package com.example.gyuri.recomsys.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Gyuri on 2016. 03. 29..
 */
public class User {
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
}
