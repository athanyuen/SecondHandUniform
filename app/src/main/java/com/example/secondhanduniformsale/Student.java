package com.example.secondhanduniformsale;

import java.util.ArrayList;

public class Student extends User{
    private int graduatingYear;
    private ArrayList<String> parentUIDs;

    public Student(String firstName, String lastName, String email, String password, String userType, String uid, double amountBought, double amountSold, ArrayList<String> itemsBought, ArrayList<String> itemsSold, int graduatingYear, ArrayList<String> parentUIDs){
        super(firstName, lastName, email, password, userType, uid, amountBought, amountSold, itemsBought,  itemsSold);
        this.graduatingYear = graduatingYear;
        this.parentUIDs = parentUIDs;
    }

    public int getGraduatingYear() {
        return graduatingYear;
    }

    public void setGraduatingYear(int graduatingYear) {
        this.graduatingYear = graduatingYear;
    }

    public ArrayList<String> getParentUIDs() {
        return parentUIDs;
    }

    public void setParentUIDs(ArrayList<String> parentUIDs) {
        this.parentUIDs = parentUIDs;
    }
}
