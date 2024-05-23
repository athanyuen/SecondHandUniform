package com.example.secondhanduniformsale;

import java.util.ArrayList;

public class Teacher extends User{
    private String inSchoolTitle;
    public Teacher(String firstName, String lastName, String email, String password, String userType, String uid, double amountBought, double amountSold, ArrayList<String> itemsBought, ArrayList<String> itemsSold, String inSchoolTitle){
        super(firstName, lastName, email, password, userType, uid, amountBought, amountSold, itemsBought,  itemsSold);
        this.inSchoolTitle = inSchoolTitle;
    }

    public String getInSchoolTitle() {
        return inSchoolTitle;
    }

    public void setInSchoolTitle(String inSchoolTitle) {
        this.inSchoolTitle = inSchoolTitle;
    }
}
