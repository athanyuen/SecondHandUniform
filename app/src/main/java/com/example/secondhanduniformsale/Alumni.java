package com.example.secondhanduniformsale;

import java.util.ArrayList;

public class Alumni extends User{
    private int graduateYear;
    public Alumni(String firstName, String lastName, String email, String password, String userType, String uid, double amountBought, double amountSold, ArrayList<String> itemsBought, ArrayList<String> itemsSold, int graduateYear){
        super(firstName, lastName, email, password, userType, uid, amountBought, amountSold, itemsBought,  itemsSold);
        this.graduateYear = graduateYear;
    }

    public int getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(int graduateYear) {
        this.graduateYear = graduateYear;
    }
}
