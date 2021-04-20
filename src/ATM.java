
import java.util.*;
/*
 * THIS IS AN PROJECT I SAW ON YOUTUBE it took 2 hours
 */


public class ATM {
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	
	
	Bank tBank = new Bank("100 Thieves");	
	//add User which also creates saving account
	User aUser = tBank.addUser("Gilded", "Nova", "0911");
	//add checking account for user
	Account nAccount = new Account("Checking", aUser, tBank);
	aUser.addAccount(nAccount);
	tBank.addAccount(nAccount);
	
	User cUser;
	while(true) {
		//stay in login prompt until login is success
		cUser = ATM.mainMenuPrompt(tBank,sc);
		
		//stay in main menu unitl user quits
		ATM.printUserMenu(cUser,sc);
		
	}
	}
	
public static User mainMenuPrompt(Bank hBank, Scanner sc) {
	String userID;
	String pin;
	User athUser;
	//prompt user ID/COMBO until correct one is reached
	do {
		System.out.printf("\n\nWelcome to %s\n\n",hBank.getName());
		System.out.print("Enter User ID: ");
		userID = sc.nextLine();
		System.out.print("Enter pin: ");
		pin = sc.nextLine();
		
		athUser = hBank.userLogin(userID, pin);
		if(athUser == null) {
			System.out.println("Incorrect user ID/pin combination PLEASE TRY AGAIN!!");
		}
	}while(athUser == null); // continue loop until successful login
	return athUser;
	}
public static void printUserMenu(User theUSer, Scanner sc) {
	int choice;
	//print a summary of the user account
	theUSer.printAccountSummary();
	//user menu
	do {
		System.out.printf("Welcome %s what would you like to do?\n", theUSer.getName());
		System.out.println("\s1) Show account transaction history");
		System.out.println("\s2) Withdraw");
		System.out.println("\s3) Deposit");
		System.out.println("\s4) Transfer");
		System.out.println("\s5) Quit\n");
		System.out.print("Enter choice:\t");
		choice = sc.nextInt();
		
		if(choice < 1 || choice > 5) {
			System.out.println("Invalid Choice. Please choose 1-5");
		}
	}while(choice < 1 || choice > 5);
	switch(choice) {
	case 1:
		ATM.showTransHistory(theUSer,sc);
		break;
	case 2:
		ATM.withdrawFu(theUSer,sc);
		break;
	case 3:
		ATM.depositFu(theUSer,sc);
		break;
	case 4:
		ATM.transferFu(theUSer,sc);
		break;
	case 5:
		//gobble up rest of previous input
		sc.nextLine();
		break;
	}
	//redisplay menu unless user wants to quit(recursion)
	if(choice != 5) {
		ATM.printUserMenu(theUSer, sc);
	}
	
	}
	public static void showTransHistory(User theUser, Scanner sc) {
		int theACC;
		//get account trans user wants to look at
		do {
			System.out.printf("Enter the number (1-%d) of the acounnt\n "
					+ "whose transactions you want to see:",theUser.numAccounts());
			theACC = sc.nextInt()-1;
			if(theACC < 0 || theACC >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again");
			}
		}while(theACC < 0 || theACC >= theUser.numAccounts());
		//print the transaction history
		theUser.printAcctTransHistory(theACC);
			
		
	}
	public static void withdrawFu(User theUser, Scanner sc) {
		int from;
		double amount;
		double accBAL;
		String memo;
		//get the account to transfer from
		do {
			System.out.printf("Enter the number (1-%d) of the acounnt\n "
					+ "to withdraw from: ",theUser.numAccounts());
			from = sc.nextInt()-1;
			if(from < 0 || from >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again");
			}
		}while(from < 0 || from >= theUser.numAccounts());
		accBAL = theUser.getAcctBalance(from);
		//get amount to transfer
		do {
			System.out.printf("Enter the amount to withdraw (max $%.02f):  $", accBAL);
			amount = sc.nextDouble();
			if(amount < 0)	System.out.println("Amount must be greater than zero");
			if(amount > accBAL)	System.out.printf("Amount must not be greater than\n "
					+ "Balance of $%.02f \n", accBAL);
	}while(amount < 0 || amount > accBAL);
		//gobble up rest of previous input
		sc.nextLine();
		//get a memo
		System.out.println("ENTER A MEMO:  ");
		memo = sc.nextLine();
		//do the actual withdraw
		theUser.addAccTransaction(from, -1 * amount, memo);
		
		
		
	}
	public static void depositFu(User theUser, Scanner sc) {
		int to;
		double amount;
		double accBAL;
		String memo;
		//get the account to transfer from
		do {
			System.out.printf("Enter the number (1-%d) of the acounnt\n "
					+ "to deposit to: ",theUser.numAccounts());
			to = sc.nextInt()-1;
			if(to < 0 || to >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again");
			}
		}while(to < 0 || to >= theUser.numAccounts());
		accBAL = theUser.getAcctBalance(to);
		//get amount to transfer
		do {
			System.out.printf("Enter the amount to deposit (max $%.02f):  $", accBAL);
			amount = sc.nextDouble();
			if(amount < 0)	System.out.println("Amount must be greater than zero");
	}while(amount < 0 );
		//gobble up rest of previous input
		sc.nextLine();
		//get a memo
		System.out.println("ENTER A MEMO:  ");
		memo = sc.nextLine();
		//do the actual transfer
		theUser.addAccTransaction(to, amount, memo);
		
		
	}
	
	//both withdraw and deposit from one account to another
	public static void transferFu(User theUser, Scanner sc) {
		int from;
		int to;
		double amount;
		double accBAL;
		
		do {
			System.out.printf("Enter the number (1-%d) of the acounnt\n "
					+ "to transfer from: ",theUser.numAccounts());
			from = sc.nextInt()-1;
			if(from < 0 || from >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again");
			}
		}while(from < 0 || from >= theUser.numAccounts());
		accBAL = theUser.getAcctBalance(from);
		//get the account to transfer to
		do {
			System.out.printf("Enter the number (1-%d) of the acounnt\n "
					+ "to transfer to: ",theUser.numAccounts());
			to = sc.nextInt()-1;
			if(to < 0 || to >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again");
			}
		}while(to < 0 || to >= theUser.numAccounts());
		
		//get amount to transfer
		do {
			System.out.printf("Enter the amount to transfer (max $%.02f):  $", accBAL);
			amount = sc.nextDouble();
			if(amount < 0)	System.out.println("Amount must be greater than zero");
			if(amount > accBAL)	System.out.printf("Amount must not be greater than\n "
					+ "Balance of $%.02f \n", accBAL);
	}while(amount < 0 || amount > accBAL);
		//finally do transfer
	theUser.addAccTransaction(from,-1 * amount,String.format("Transfer to account %s", theUser.getAcctUUID(to)));
	theUser.addAccTransaction(to,amount,String.format("Transfer to account %s", theUser.getAcctUUID(from)));
	
}
}













//end