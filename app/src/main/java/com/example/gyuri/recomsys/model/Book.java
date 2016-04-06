package com.example.gyuri.recomsys.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Gyuri on 2016. 03. 29..
 */
public class Book {
    private static final String bookArraySeparator = "-#BA#-";
    private static final String bookObjectSeparator = "-#BO#-";

    private String title;
    private String author;
    private int released;
    private String publisher;
    private int price;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    private String picture = new String();
    private ArrayList<String> genres = new ArrayList<>();


    public Book(String title, String author, int released, String publisher, int price, String picture, ArrayList<String> genres) {
        this.title = title;
        this.author = author;
        this.released = released;
        this.publisher = publisher;
        this.price = price;
        this.picture = picture;
        this.genres = genres;
    }

    public Book(String saved) {
        this.readFromString(saved);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public String writeToString() {
        String str = new String();

        str = str.concat(title + bookObjectSeparator);
        str = str.concat(author + bookObjectSeparator);
        str = str.concat(Integer.toString(released) + bookObjectSeparator);
        str = str.concat(publisher + bookObjectSeparator);
        str = str.concat(Integer.toString(price) + bookObjectSeparator);
        str = str.concat(picture + bookObjectSeparator);
        for (String actual : genres) {
            str = str.concat(actual + bookArraySeparator);
        }
        return str;
    }

    public void readFromString(String str) {
        Log.d("BOOKSTR", str);

        String[] strings = str.split(Pattern.quote(bookObjectSeparator));
        title = strings[0];
        author = strings[1];
        released = Integer.parseInt(strings[2]);
        publisher = strings[3];
        price = Integer.parseInt(strings[4]);
        picture = strings[5];

        String[] gs = strings[6].split(bookArraySeparator);
        for (String actual : gs) {
            genres.add(actual);
        }

    }

    @Override
    public boolean equals(Object b) {
        if (this.getGenres().equals(((Book) b).getGenres()) &
                this.getAuthor().equals(((Book) b).getAuthor()) &
                this.getTitle().equals(((Book) b).getTitle()) &
                this.getPicture().equals(((Book) b).getPicture()) &
                this.getPublisher().equals(((Book) b).getPublisher()) &
                this.getReleased() == ((Book) b).getReleased() &
                this.getPrice() == ((Book) b).getPrice())
            return true;
        else
            return false;

    }

    @Override
    public int hashCode() {
        return released*price*title.hashCode();
    }
}
