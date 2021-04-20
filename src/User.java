
import java.util.*;
import java.security.*;

public class User {
	//first name of user
	private String first;
	//last name of user
	private String last;
	//UUID = unique universal id
	private String uuid;
	//store the pin in a safe secure way
	private byte pinHash[];
	//list of accounts for this user
	private ArrayList<Account> accounts;

	
public User(String first, String last, String pin, Bank thbank) {
	//creates names
	this.first = first;
	this.last = last;
	
	//store the pin's MD5 hsah, rather than the orginal value ... security
	try {
		//this is an security algorthim
		MessageDigest md = MessageDigest.getInstance("MD5");
		//getting the bytes of the pin and digest(security) and stores it in pinHash
		this.pinHash = md.digest(pin.getBytes());
	} catch (NoSuchAlgorithmException e) {
		System.out.println("NiSuchAlgoException error");
		e.printStackTrace();
		System.exit(1);
	}
	//get a new unique UUID
	this.uuid =thbank.getNewUserUUID();
	//create empty list of account
	this.accounts = new ArrayList<Account>();
	//prints a log message
	System.out.printf("New user %s %s with ID %s created \n",first, last, this.uuid);
}
public void addAccount(Account acc) {
	//simple just add account to the user
	this.accounts.add(acc);
	
}
public String getName() {
	return this.first +" "+ this.last;
}
public String getUUID() {
	return this.uuid;
}
public double getAcctBalance(int money){
	return this.accounts.get(money).getBalance();
}



public boolean validatePin(String pin) {
	try {
		MessageDigest md =MessageDigest.getInstance("MD5");
		return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash);
	} catch (NoSuchAlgorithmException e) {
		System.out.println("NiSuchAlgoException error");
		e.printStackTrace();
		System.exit(1);
	}
	return false;
	}

public void printAccountSummary() {
	System.out.printf("\n\n%s's accounts summary\n", this.getName());
	for(int i = 0; i < this.accounts.size(); i ++) {
		System.out.printf("%d) %s\n",i+1, this.accounts.get(i).getSummaryLine());
	}
}
//gives number of accounts
public int numAccounts() {
	return this.accounts.size();
}

public void printAcctTransHistory(int acc) {
	this.accounts.get(acc).printTransHistory();
}
//get UUID of a particular account
public String getAcctUUID(int num) {
	return this.accounts.get(num).getUUID();
}

public void addAccTransaction(int acc, double amount,String memo) {
	this.accounts.get(acc).addTransaction(amount,memo);
}
}





//end