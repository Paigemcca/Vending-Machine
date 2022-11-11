package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Scanner;

import static java.time.LocalDateTime.*;

public class ToLog{

    VendingMachine vendors = new VendingMachine();
    List<Snack> snacks = vendors.importProducts();
    //DateTimeFormatter time = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");


        public static void purchaseLogger(){
            VendingMachine vendors = new VendingMachine();
            List<Snack> snacks = vendors.importProducts();
            //DateTimeFormatter niceTIme = new DateTimeFormatter("MMM d yyyy  hh:mm a");
            DateTimeFormatter time = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");

            File file = new File("Log1.txt");

            for  (Snack snack: snacks) {
                try (PrintWriter writer = new PrintWriter(file)) {

                    writer.println(time.format(LocalDateTime.now()) + snack.getName() +  snack.getItemCode() + snack.getPrice() + vendors.getBalance());
                } catch (FileNotFoundException e) {

                }
            }
        }
//    DateTimeFormatter time = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
//        for (Snack snack: snacks) {
//            try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true ))) {
//        writer.println(time.format(LocalDateTime.now()) + " " + snack.getName() +  snack.getItemCode() + snack.getPrice() + vendors.getBalance());
//    } catch (FileNotFoundException fnfe){
//
//    }
 //       }

        public void feedMoneyLogger(){
            File file = new File("Log1.txt");

            DateTimeFormatter time = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
            try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true ))) {
                writer.println(time.format(LocalDateTime.now()) + " " + "FEED MONEY: " + vendors.getBalance());
            } catch (FileNotFoundException fnfe){

            }
            //date time snack name snack code cost and remaining balance
        }

        public static void giveChangeLogger(){
            // date time "GIVE CHANGE" balance remaining, $0.00
            File file = new File("Log1.txt");
            DateTimeFormatter time = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
            try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true /* This true sets the file to be appended */))) {
                writer.println(time.format(LocalDateTime.now()) + " " + "GIVE CHANGE: " );
            } catch (FileNotFoundException fnfe){

            }
            //date time snack name snack code cost and remaining balance
        }

}
