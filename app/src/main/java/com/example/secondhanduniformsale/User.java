package com.example.secondhanduniformsale;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userType;
    private double amountSold;
    private double amountBought;
    private String uid;
    private ArrayList<String> itemsBought;
    private ArrayList<String> itemsSold;

    public User(String firstName, String lastName, String email, String password, String userType, String uid, double amountBought, double amountSold, ArrayList<String> itemsBought, ArrayList<String> itemsSold) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.uid = uid;
        this.amountBought = amountBought;
        this.amountSold = amountSold;
        this.itemsBought = itemsBought;
        this.itemsSold = itemsSold;
    }

    public User() {
    }

    public User(String firstName, String lastName, String email, String password) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public double getAmountSold() {
        return amountSold;
    }

    public void setAmountSold(double amountSold) {
        this.amountSold = amountSold;
    }

    public double getAmountBought() {
        return amountBought;
    }

    public void setAmountBought(double amountBought) {
        this.amountBought = amountBought;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ArrayList<String> getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(ArrayList<String> itemsBought) {
        this.itemsBought = itemsBought;
    }

    public ArrayList<String> getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(ArrayList<String> itemsSold) {
        this.itemsSold = itemsSold;
    }
}
