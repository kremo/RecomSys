package com.example.gyuri.recomsys.model;

import java.util.ArrayList;

/**
 * Created by Gyuri on 2016. 03. 29..
 */
public class Book {
    private String title;
    private String author;
    private String released;
    private String publisher;
    private int price;
    private ArrayList<String> genres;

    public Book(String title, String author, String released, String publisher, int price, ArrayList<String> genres) {
        this.title = title;
        this.author = author;
        this.released = released;
        this.publisher = publisher;
        this.price = price;
        this.genres = genres;
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

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
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
}
