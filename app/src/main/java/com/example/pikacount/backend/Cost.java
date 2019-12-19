package com.example.pikacount.backend;

public class Cost {
    private String name;
    private int price;
    private String date;
    private String type;
    private int costId;

    public Cost (String name, int price, String date, String type, int id) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.type = type;
        this.costId = id;
    }

    public int getPrice() {
        return price;
    }

    public String getCostName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getCostId() {
        return costId;
    }
}
