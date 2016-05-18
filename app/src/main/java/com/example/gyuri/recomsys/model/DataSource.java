package com.example.gyuri.recomsys.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Andras on 18/05/16.
 */
public class DataSource {
    public static String arraySeparator = "[!-_-!]";
    public static User currentUser;

    public static User getLoggedInUser(){
        return null;
    }

    public static void registerNewUser(){

    }

    public static void addUserToSavedUsers(ArrayList<User> users, Context context){

    }
    public static void addRecomGroupToSavedGroups(ArrayList<RecomGroup> groups, Context context){

    }
    public static void addPurchaseToSavedpurchased(ArrayList<Purchase> purchases,Context context){

    }

    public static void addUserToSavedUsers(User user, Context context){
        ArrayList<User>users = getUsers(context);
        users.add(user);
        writeUsers(users, context);
    }
    public static void addRecomGroupToSavedGroups(RecomGroup group, Context context){
        ArrayList<RecomGroup>rgs = getRecomGroups(context);
        rgs.add(group);
        writeRecomGroups(rgs, context);
    }
    public static void addPurchaseToSavedpurchased(Purchase purchase,Context context){
        ArrayList<Purchase>prs = getPurchases(context);
        prs.add(purchase);
        writePurchases(prs, context);
    }

    public static void writeUsers(ArrayList<User> users, Context context) {
        String str = new String();
        for (User u : users) {
            str = str.concat(u.writeToString() + arraySeparator);
        }
        try {
            FileOutputStream outputStream;
            outputStream = context.openFileOutput("users.txt", Context.MODE_PRIVATE);
            outputStream.write(str.getBytes());
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeRecomGroups(ArrayList<RecomGroup> recGroups, Context context) {
        String str = new String();
        for (RecomGroup rg : recGroups) {
            str = str.concat(rg.writeToString() + arraySeparator);
        }

        try {
            FileOutputStream outputStream;
            outputStream = context.openFileOutput("recomGroups.txt", Context.MODE_PRIVATE);
            outputStream.write(str.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writePurchases(ArrayList<Purchase> purchases,Context context) {
        String str = new String();
        for (Purchase p : purchases) {
            str = str.concat(p.writeToString() + arraySeparator);
        }
        try {
            FileOutputStream outputStream;
            outputStream = context.openFileOutput("purchases.txt", Context.MODE_PRIVATE);
            outputStream.write(str.getBytes());
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Purchase> getPurchases(Context context) {
        FileInputStream fis = null;
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        try {
            fis = context.openFileInput("purchases.txt");


            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String all = sb.toString();

            purchases.clear();
            String[] ps = all.split(Pattern.quote(arraySeparator));
            for (String str : ps) {
                if (str.length() > 0)
                    purchases.add(new Purchase(str));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return purchases;
    }

    public static ArrayList<RecomGroup> getRecomGroups(Context context) {
        FileInputStream fis = null;
        ArrayList<RecomGroup> recomGroups = new ArrayList<RecomGroup>();

        try {
            fis = context.openFileInput("recomGroups.txt");


            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String all = new String();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                all = all.concat(line);
            }
            bufferedReader.close();


            String[] rgs = all.split(Pattern.quote(arraySeparator));

            recomGroups.clear();
            for (String str : rgs) {
                if (str.length() > 0) {
                    RecomGroup rg = new RecomGroup(str);
                    recomGroups.add(rg);
                }
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return recomGroups;
    }


    public static ArrayList<User> getUsers(Context context) {
        FileInputStream fis = null;
        ArrayList<User> users = new ArrayList<User>();
        try {
            fis = context.openFileInput("users.txt");


            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String all = sb.toString();


            users.clear();
            String[] us = all.split(Pattern.quote(arraySeparator));
            for (String str : us)
                if (str.length() > 0)
                    users.add(new User(str));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }


    public static ArrayList<Purchase> getPurchasesForUser(User usr, Context context){
        ArrayList<Purchase> retVal = new ArrayList<Purchase>();
        for (Purchase actual:getPurchases(context)) {
            if (usr.getId() == actual.user.getId()){
                retVal.add(actual);
            }
        }
        return retVal;
    }


}
