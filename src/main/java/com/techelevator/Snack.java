package com.techelevator;

public abstract class Snack {
    private String name;
    private double price;
    private String itemCode;
    private int quantity = 5;

    private String sound;
    private String type;

    public Snack(String name, double price, String itemCode,String sound) {
        this.name = name;
        this.price = price;
        this.itemCode = itemCode;
        this.sound = sound;


    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//     public int getCount() {
//         return count;
//     }

    public String getItemCode() {
        return itemCode;

    }

//     public void setCount(int count) {
//      this.count = count;
//     }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public abstract String getSound();

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}