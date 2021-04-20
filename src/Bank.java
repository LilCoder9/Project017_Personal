
import java.util.*;

public class Bank {
	//name of bank
	private String name;
	//list of users in the bank
	private ArrayList<User> user;
	//list of accounts 
	private ArrayList<Account> account;
	
	public Bank(String name) {
		this.name = name;
		this.user = new ArrayList<User>();
		this.account = new ArrayList<Account>();
	}
	
	public String getName() {
		return this.name;
	}
	
	
	public String getNewUserUUID() {
		String rando;
		int len = 6;
		boolean notUnique;	
		Random rng = new Random();
		//continue loop until unqiue ID
		do {
			//generate number
			rando = "";
			for(int c =0; c <len; c++) {
				rando +=((Integer)rng.nextInt(10)).toString();
			}
			//check to make sure its unqiue
		notUnique = false;
		for(User u: this.user) {
			if(rando.compareTo(u.getUUID())== 0) {
				notUnique = true;
				break; //takes out of loop
			}
		}
			
		}while(notUnique);
		
		return rando;
		
	}
	public String getNewAccountUUID() {
		String rando;
		int len = 10;
		boolean notUnique;	
		Random rng = new Random();
		//continue loop until unqiue ID
		do {
			//generate number
			rando = "";
			for(int c =0; c <len; c++) {
				rando +=((Integer)rng.nextInt(10)).toString();
			}
			//check to make sure its unqiue
		notUnique = false;
		for(Account u: this.account) {
			if(rando.compareTo(u.getUUID())== 0) {
				notUnique = true;
				break; //takes out of loop
			}
		}
			
		}while(notUnique);
		
		return rando;	
	}
	
	public void addAccount(Account account) {
		this.account.add(account);
	}
	
	public User addUser(String first, String last, String pin) {
		//creates New User object  and add to lsit
		User newUser = new User(first, last, pin, this);
		this.user.add(newUser);
		
		//create saving account
		Account newAccount = new Account("Savings", newUser,this);
		//add to to User and Bank accounts list
		newUser.addAccount(newAccount);
		this.account.add(newAccount);
		return newUser;
	}
	
	public User userLogin(String userID, String pin) {
		//search through list of users
		for(User u : this.user) {
			
			//check if user IF correct
			if(u.getUUID().compareTo(userID)==0 && u.validatePin(pin)) {
				return u;
			}
		}
		return null;
	}
	
}










//end