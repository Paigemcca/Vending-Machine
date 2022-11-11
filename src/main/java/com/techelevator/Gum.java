package com.techelevator;

public class Gum extends Snack {

    private String gumSound = "Chew Chew Yum!";

    public Gum(String name, double price, String itemCode, String sound) {
        super( name, price, itemCode, sound);
    }

    @Override
    public String getSound() {
        return gumSound;
    }



//    @Override
//    public String getsound() {
//        return "Chew Chew, Yum!";
//    }

}
