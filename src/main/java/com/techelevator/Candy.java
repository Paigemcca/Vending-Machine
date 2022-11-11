package com.techelevator;

public class Candy extends Snack {
    private String candySound = "Munch Munch, Yum!";

    public Candy(String name, double price, String itemCode, String sound) {
        super( name, price, itemCode, sound);
    }

    @Override
    public String getSound() {
        return candySound;
    }

//    public String sound(){
  //      return "Munch Munch, Yum!";
   // }
}
