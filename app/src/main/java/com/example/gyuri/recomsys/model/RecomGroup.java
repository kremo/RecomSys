package com.example.gyuri.recomsys.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Gyuri on 2016. 03. 29..
 */
public class RecomGroup {
    String recomGObjectSeparator = "-+-||-+-";
    String recomGArraySeparator = "-+-|A|-+-";


    String name;
    LinkedHashMap<Book, Integer> books = new LinkedHashMap<>();
    private ArrayList<User> users = new ArrayList<>();

    public RecomGroup(String m) {
        name = m;
    }


    public String getName() {
        return name;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addBook(Book book, int i) {
        books.put(book, i);
    }

    public String writeToString() {
        String str = new String();
        str = str.concat(name + recomGObjectSeparator);

        for (Book book : books.keySet()) {
            Integer rank = books.get(book);
            str = str.concat(book.writeToString() + ":" + Integer.toString(rank) + recomGArraySeparator);
        }
        str = str.concat(recomGObjectSeparator);

        for(User u : users){
            str = str.concat(u.writeToString() + recomGArraySeparator);
        }

        return str;
    }

    public void readFromString(String str) {
        String[] strings = str.split(recomGObjectSeparator);
        name = strings[0];
        String[] booksArr = strings[1].split(recomGArraySeparator);
        for (String actual : booksArr) {
            String [] bookWithRank = actual.split(":");
            Book book = new Book(bookWithRank[0]);
            Integer rank = Integer.parseInt(bookWithRank[1]);
            books.put(book, rank);
        }
        String[] usersArr = strings[2].split(recomGArraySeparator);
        for(String actual : usersArr){
            users.add(new User(actual));
        }

    }
}
