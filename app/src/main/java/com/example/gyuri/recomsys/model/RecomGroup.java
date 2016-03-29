package com.example.gyuri.recomsys.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Gyuri on 2016. 03. 29..
 */
public class RecomGroup {
    String name;
    LinkedHashMap<Book, Integer> books;

    public RecomGroup(String m){
        name = m;
    }


    public String getName() {
        return name;
    }
}
