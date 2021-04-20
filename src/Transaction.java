
import java.util.*;

public class Transaction {
	//amount of the transaction
	private double amount;
	//time and date of transcation
	private Date timestamp;
	//note 
	private String memo;
	//links the transaction to the account
	private Account inAccount;
	
	
public Transaction( double amount, Account inAccount) {
	this.amount = amount;
	this.inAccount = inAccount;
	this.timestamp = new Date();
	this.memo = "";
	
}
public Transaction( double amount, String memo,Account inAccount) {
	// fancy way of calling the other Transaction constructor
	this(amount, inAccount);
	//set memo
	this.memo = memo;
	
}
public double getAmount() {
	return this.amount;
}
//string summarizing the transaction
public String getSummaryLine() {
	if(this.amount >= 0) {
		return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
	}
	else {
		return String.format("%s : ($%.02f) : %s", this.timestamp.toString(), this.amount, this.memo);
	}
}
}