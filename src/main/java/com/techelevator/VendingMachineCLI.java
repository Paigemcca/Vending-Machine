package com.techelevator;

import com.techelevator.view.Menu;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class VendingMachineCLI extends VendingMachine {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String PURCHASE_MENU_FEED_MONEY= "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANS= "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANS};

	private Menu menu;
	private int quantity = 0;
	//double balance = 0.0;
	Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	VendingMachine vendors = new VendingMachine();
	List<Snack> snacks = vendors.importProducts();
	ToLog logger = new ToLog();
	DecimalFormat df = new DecimalFormat("0.00");
	File file = new File("Log.txt");



	public String printSnacks(){
		String snacksToPrint = "";
		for(Snack snack: snacks){
			//System.out.println("%s %-20s $%-7.2f  %-7s %n", snack.getItemCode(), snack.getName(), snack.getPrice(), "amount remaining ", snack.getQuantity());
			snacksToPrint += snack.getItemCode() + " " + snack.getName() + " " + df.format(snack.getPrice()) + " " + "amount remaining " + snack.getQuantity() + "\n";
		}																					//Snack.getQuantity()
		return snacksToPrint;
	}

	public void run(){

	//	VendingMachine vendors = new VendingMachine();
		//List<Snack> snacks = vendors.importProducts();


		boolean vending = true;
		while (vending) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println(printSnacks() /*+ LocalDateTime.now()*/);
				//System.out.printf("%s %-20s $%-7.2f  %-7s %n", printSnacks());
//				for  (Snack snack: snacks){
//					System.out.printf("%s %-20s $%-7.2f  %-7s %n", snack.getItemCode(), snack.getName(), snack.getPrice(), "amount remaining ", snack.getQuantity() );
//				}

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				String choice2 = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				if (choice2.equals(PURCHASE_MENU_FEED_MONEY)){

					try{
						System.out.println("how much money would you like to add?");
						double moneyAdded = userInput.nextDouble();
						vendors.feedMoney(moneyAdded);
						System.out.println("Current balance $" + df.format(vendors.getBalance()));

						//File file = new File("Log1.txt");

						DateTimeFormatter time = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
						try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true /* This true sets the file to be appended */))) {
							writer.println(time.format(LocalDateTime.now()) + " " + "FEED MONEY: " + "$" + df.format(vendors.getBalance() - moneyAdded) + " " + "$" + df.format(vendors.getBalance()));
						} catch (FileNotFoundException fnfe){

						}
					}catch (NumberFormatException nfe) {
						System.out.println("Not a valid amount");
					}

				}else if (choice2.equals(PURCHASE_MENU_SELECT_PRODUCT)){
						System.out.println(printSnacks());
						//try {
							System.out.println("Please enter item code of desired product: ");
							String choiceCode = userInput.next();
							//}catch for custom exception if input doesnt match an Item code
					for  (Snack snack: snacks) {
						if (choiceCode.equals(snack.getItemCode())) {
							if (vendors.getBalance() >= snack.getPrice() && snack.getQuantity() > 0){
								snack.setQuantity(snack.getQuantity() - 1);

								balance = vendors.getBalance() - snack.getPrice();

								System.out.println(snack.getName() + " " + "$" + snack.getPrice() + " " + "current balance: " + "$" + df.format(balance));
								System.out.println(snack.getSound());


									DateTimeFormatter time = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
									try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true ))) {
										writer.println(time.format(LocalDateTime.now()) + " " + snack.getName() + " " + snack.getItemCode() + " " + "$" + snack.getPrice() + " " + "$" + df.format(balance));
									} catch (FileNotFoundException fnfe){

									}


							} else if(snack.getQuantity() == 0){
								System.out.println("SOLD OUT");
							} else if (balance < snack.getPrice()){
								System.out.println("Not enough funds, please feed more money");
							}

						}
					}


				}else if (choice2.equals(PURCHASE_MENU_FINISH_TRANS)){
					vending = false;
					System.out.println();
					System.out.println("Thanks for vending!");
					System.out.println();
					System.out.println("Here is your change: ");
					vendors.returnChange();
					DateTimeFormatter time = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
					try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
						writer.println(time.format(LocalDateTime.now()) + " " + "GIVE CHANGE: " + "$" + df.format(balance) + " " + "$" + df.format((balance - balance)));
					} catch (FileNotFoundException fnfe){

					}
				}


			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				vending = false;
			}
		}



	}

}
