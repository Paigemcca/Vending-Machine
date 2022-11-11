package com.techelevator;

public class Chip extends Snack {

    private String chipSound = "Crunch Crunch, Yum!";

    public Chip(String name, double price, String itemCode, String sound) {
        super( name, price, itemCode, sound);
    }

    @Override
    public String getSound() {
        return chipSound;
    }




    //   public String sound() {
   //     return "Crunch Crunch, Yum!";
    //}

}
