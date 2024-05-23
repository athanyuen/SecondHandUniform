package com.example.secondhanduniformsale;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Parent extends User{
    private ArrayList<String> studentUIDs;

    public Parent(String firstName, String lastName, String email, String password, String userType, String uid, double amountBought, double amountSold, ArrayList<String> itemsBought, ArrayList<String> itemsSold, ArrayList<String> studentUIDs){
        super(firstName, lastName, email, password, userType, uid, amountBought, amountSold, itemsBought,  itemsSold);
        this.studentUIDs = studentUIDs;
    }

    public ArrayList<String> getStudentUIDs() {
        return studentUIDs;
    }

    public void setStudentUIDs(ArrayList<String> studentUIDs) {
        this.studentUIDs = studentUIDs;
    }
}
