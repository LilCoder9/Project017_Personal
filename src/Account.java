
import java.util.*;

public class Account {
	//name of the account
	private String name;
	//account ID number
	private String uuid;
	//links user to the account
	private User holder;
	//list of transactions of this account
	private ArrayList<Transaction> transaction;
	
public Account(String name, User holder, Bank thbank) {
	this.name = name;
	this.holder = holder;
	
	//get new account UUID
	this.uuid =thbank.getNewAccountUUID();
	//links empty transcation list to the account
	this.transaction = new ArrayList<Transaction>();	
}
public String getUUID() {
	return this.uuid;
}

public String getSummaryLine() {
	//get account balance
	double balance = this.getBalance();
	//format summary line depends if NEGATIVE OR NOT
	if(balance >= 0) {
		return String.format("%s : $%.02f : %s", this.uuid,balance, this.name);
	}
	else {
			return String.format("%s : $(%.02f) : %s", this.uuid,balance, this.name);
	}
	
}

public double getBalance() {
	double balance = 0;
	
	//loop through all transactions add the amounts
	for(Transaction t : this.transaction) {
		balance += t.getAmount();
	}
	return balance;
}

//print transaction history of account
public void printTransHistory() {
	System.out.printf("\nTransaction history for account %s\n", this.uuid);
	//going from recent to old
	for(int i = this.transaction.size()-1; i>= 0 ; i--) {
		System.out.println(this.transaction.get(i).getSummaryLine());	
	}
	System.out.println();
}

public void addTransaction(double amount, String memo) {
	//create new transaction object and add it to our list
	Transaction trans = new Transaction(amount, memo, this);
	this.transaction.add(trans);
}
}





















//end