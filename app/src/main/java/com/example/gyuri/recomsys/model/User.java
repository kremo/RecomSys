package com.example.gyuri.recomsys.model;

import android.util.Log;

import java.util.regex.Pattern;

/**
 * Created by Gyuri on 2016. 03. 29..
 */
public class User {
    public static final String userStringSeparator = "-!U!-";

    private long id;
    private String nickName;
    private int age;
    private String name;

    public User(String name, long id, String nickName, int age) {
        this.name = name;
        this.id = id;
        this.nickName = nickName;
        this.age = age;
    }

    public User(String s) {
        this.readFromString(s);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
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
        Log.d("HIBA ELŐTT", str);
        String[] strings = str.split(Pattern.quote(userStringSeparator));
        name = strings[0];
        id = Long.parseLong(strings[1]);
        nickName = strings[2];
        age = Integer.parseInt(strings[3]);
    }

    @Override
    public boolean equals(Object u) {
        if (this.getName().equals(((User)u).getName()) &
                this.getId() == ((User)u).getId() &
                this.getAge() == ((User)u).getAge() &
                this.getId() == ((User)u).getId() &
                this.getNickName().equals(((User)u).getNickName()))
            return true;
        else
            return false;

    }
}
