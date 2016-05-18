package com.example.gyuri.recomsys.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Gyuri on 2016. 03. 29..
 */
public class RecomGroup {
    public static String recomGObjectSeparator = "-|f|-";
    public static String recomGArraySeparator = "-|B|-";


    String name;

    public RecomGroup() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void sortByValue() {

        final Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return lhs - rhs;
            }

            @Override
            public boolean equals(Object object) {
                return this.equals(object);
            }
        };
        List<Map.Entry<Book, Integer>> entries = new ArrayList<>(books.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<Book, Integer>>() {
            @Override
            public int compare(Map.Entry<Book, Integer> lhs, Map.Entry<Book, Integer> rhs) {
                return c.compare(lhs.getValue(), rhs.getValue());
            }
        });

        books.clear();
        for (Map.Entry<Book, Integer> e : entries) {
            books.put(e.getKey(), e.getValue());
        }

    }

    public LinkedHashMap<Book, Integer> getBooks() {
        return books;
    }

    public void setBooks(LinkedHashMap<Book, Integer> books) {
        this.books = books;
    }

    private LinkedHashMap<Book, Integer> books = new LinkedHashMap<>();
    private ArrayList<User> users = new ArrayList<>();

    public RecomGroup(String m) {

        this.readFromString(m);
    }


    public String getName() {
        return name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addBook(Book book, int i) {
        if (myContainsKey(book) & !users.contains(DataSource.currentUser)) {
            books.put(book, books.get(book) + 1);
        } else if (!myContainsKey(book)) {
            books.put(book, i);
        }
    }

    private boolean myContainsKey(Book book) {
        boolean equals = false;
        for (Book b : books.keySet()) {
            if (book.getGenres().equals(b.getGenres()) &
                    book.getAuthor().equals(b.getAuthor()) &
                    book.getTitle().equals(b.getTitle()) &
                    book.getPicture().equals(b.getPicture()) &
                    book.getPublisher().equals(b.getPublisher()) &
                    book.getReleased() == b.getReleased() &
                    book.getPrice() == b.getPrice())
                equals = true;
        }
        return equals;
    }

    public String writeToString() {
        String str = new String();
        str = str.concat(name + recomGObjectSeparator);

        for (Book book : books.keySet()) {
            Integer rank = books.get(book);
            str = str.concat(book.writeToString() + ":" + Integer.toString(rank) + recomGArraySeparator);
        }
        str = str.concat(recomGObjectSeparator);

        for (User u : users) {
            str = str.concat(u.writeToString() + recomGArraySeparator);
        }

        return str;
    }

    public void readFromString(String str) {
        String[] strings = str.split(Pattern.quote(recomGObjectSeparator));
        name = strings[0];
        String[] booksArr = strings[1].split(Pattern.quote(recomGArraySeparator));
        for (String actual : booksArr) {
            String[] bookWithRank = actual.split(Pattern.quote(":"));
            Book book = new Book(bookWithRank[0]);
            Integer rank = Integer.parseInt(bookWithRank[1]);
            books.put(book, rank);
        }
        String[] usersArr = strings[2].split(Pattern.quote(recomGArraySeparator));
        for (String actual : usersArr) {
            users.add(new User(actual));
        }

    }
}
