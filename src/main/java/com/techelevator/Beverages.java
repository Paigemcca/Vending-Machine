package com.techelevator;

public class Beverages extends Snack {
    private String bevSound = "Glug Glug, Yum!";

    public Beverages(String name, double price, String itemCode, String sound) {
        super( name, price, itemCode, sound);
    }

    @Override
    public String getSound() {
        return bevSound;
    }

    //  public String sound() {
   //     return "Glug Glug, Yum!";
  //  }
}
