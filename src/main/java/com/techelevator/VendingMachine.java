package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VendingMachine {

    double balance = 0;
     int quantity = 5;
        //**

        public List<Snack> importProducts() {
            List<Snack> retProducts = new ArrayList<>();
            File productFile = new File("vendingmachine.csv");
            String[] productInfo = new String[5];

            try (Scanner fileInput = new Scanner(productFile)) {
                while (fileInput.hasNextLine()) {
                    String productLine = fileInput.nextLine();
                    productInfo = productLine.split("\\|");


                    if (productInfo[3].equals("Drink")) {
                        double itemprice = Double.parseDouble(productInfo[2]);
                        String sound = "";
                        retProducts.add(new Beverages(productInfo[1], itemprice, productInfo[0], sound));

                    } else if (productInfo[3].equals("Candy")) {
                        double itemprice = Double.parseDouble(productInfo[2]);
                        String sound = "";
                        retProducts.add(new Candy(productInfo[1], itemprice, productInfo[0],sound));

                    } else if (productInfo[3].equals("Gum")) {
                        double itemprice = Double.parseDouble(productInfo[2]);
                        String sound = "";
                        retProducts.add(new Gum(productInfo[1], itemprice, productInfo[0], sound));
                    }else if (productInfo[3].equals("Chip")) {
                        String sound = "";
                        double itemprice = Double.parseDouble(productInfo[2]);
                        retProducts.add(new Chip(productInfo[1], itemprice, productInfo[0], sound));
                    }
                }

            } catch (FileNotFoundException e) {

            }
            return retProducts;
        }
        //**
        public String feedMoney(double addMoney) {
            Set<Double> values = new HashSet<Double>(Arrays.asList(
                    new Double[]{1.00, 2.00, 5.00, 10.00, 20.00, 50.00, 100.00}));
            String testString = null;
            if (values.contains(addMoney)) {
                balance += addMoney;
            } else {
                testString = "test";
                // System.out.println("That is a invalid amount, try again.\n ");
            }
            return testString;
        }

        public double getBalance() {
            return balance;
        }

        public void returnChange() {
            Double[] change = new Double[] {0.25, 0.10, 0.05};
            String[] coinName = new String[] {"Quarter(s)", "Dime(s)", "Nickle(s)"};
            for(int i = 0; i < change.length; i++) {
                int counter = 0;
                counter = (int) (balance / change[i]);
                balance -= (change[i] * counter);
                System.out.println(counter + " " + coinName[i]);
            }
        }

        //**


//    public int getQuantity() {
//        return quantity;
//    }
}

