package com.example.secondhanduniformsale;

public class Items {
    private String itemID;
    private String owner;
    private int age;
    private double price;
    private String ownerID;
    private String itemType;
    private boolean sold;
    private String name;

    public Items(){

    }
    public Items(String name, String itemID, String owner, int age, double price, String ownerID, String itemType, boolean sold){
        this.name = name;
        this.itemID = itemID;
        this.owner = owner;
        this.age = age;
        this.price = price;
        this.ownerID = ownerID;
        this.itemType = itemType;
        this.sold = sold;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
