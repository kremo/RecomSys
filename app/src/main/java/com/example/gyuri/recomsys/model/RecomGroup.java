package com.example.gyuri.recomsys.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Gyuri on 2016. 03. 29..
 */
public class RecomGroup {
    String name;
    LinkedHashMap<Book, Integer> books = new LinkedHashMap<>();
    private ArrayList<User> users = new ArrayList<>();

    public RecomGroup(String m){
        name = m;
    }


    public String getName() {
        return name;
    }

    public void addUser(User user){
        users.add(user);
    }

    public void addBook(Book book, int i) {
        books.put(book,i);
    }
}
