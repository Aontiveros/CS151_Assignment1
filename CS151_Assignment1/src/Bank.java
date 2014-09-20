import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
  Bank Class that holds all the accounts and is accessible by the bank computer.
  Bank class for CS 151 Assignment #1  
  @author Antonio Ontiveros
  @version 1.0 9/10/2014 
 */
public class Bank
{
	private Hashtable<Integer, Customer> accounts;

	/**
	  The constructor for the bank class, the bank class contains all the 
	  accounts that belong to the bank. The bank will look for the text file with
	  its corresponding bank ID and read the accounts from there. If there is
	  no text file, or if there isn't any accounts then the Bank will print to 
	  the console that the Bank has no accounts currently attached to it. Use
	  the initialize account method in the Bank
	  @param BankID The ID or the Bank.
	 */ 
	public Bank(String BankID)
	{
	   accounts = new Hashtable<>();
	   File inFile = new File(BankID + ".txt");
	   try
	   {
	      Scanner in = new Scanner(inFile);
	      Customer newCustomer;
	      
	      while(in.hasNext()) 
	      {
	         newCustomer = new Customer();
	         newCustomer.setCardId(in.nextInt());
	         newCustomer.setChecking(in.nextDouble());
	         newCustomer.setSavings(in.nextDouble());        
	         newCustomer.setPassword(in.next());
	         newCustomer.setExpirationMonth(in.nextInt());
	         newCustomer.setExpirationYear(in.nextInt());
	         newCustomer.setCheckingNumber(in.nextInt());
	         newCustomer.setSavingsNumber(in.nextInt());
	         newCustomer.setAccessible(in.nextBoolean());
	         //System.out.println(newCustomer);
	         accounts.put(newCustomer.getCardId(), newCustomer);
	      }
	      in.close();
	   }
	   catch(FileNotFoundException e)
	   {
	      System.out.println("There are no accounts, please load a database or "
	            + "initialize accounts.");
	   }        
	}
	/**
	  The getter to return the customer that corresponds from the card ID that
	  was put in through the ATM
	  @param cardID The ID of the card that was placed in by the Customer into 
	  the ATM
	  @return The customer that is in the database
	 */
	public Customer getCustomer(int cardID)
	{
	   return accounts.get(cardID);
	}
	/**
	  This method checks whether the card exists in the bank.
	  @param cardID The card id of the card that the customer put into the ATM
	  @return
	 */
	public boolean doesCardExist(int cardID)
	{
	   return accounts.containsKey(cardID);
	}
	/**
	  This method gets the password of the customer that is currently accessing 
	  the ATM
	  @param cardID The ID of the card that the customer placed into the ATM
	  @return the password that corresponds with the account that the customer
	  has.
	 */
	public String getUserPassword(int cardID)
	{
	   return accounts.get(cardID).getPassword();
	}
	/**
	  This method saves the accounts that are currently in the system into a 
	  text file so that it saves the customers that are currently in the bank	  
	  @param BankID The ID of the Bank
	  @throws FileNotFoundException In case the file fails to save or write
	 */
	public void saveDatabase(String BankID) throws FileNotFoundException
	{
	   File inFile = new File(BankID + ".txt");    
	   inFile.delete();
      PrintWriter writer = new PrintWriter(inFile);
      Set<Integer> keys = accounts.keySet(); 
      for(Integer key: keys)
      {
         writer.println(accounts.get(key).getCardId());
         writer.println(accounts.get(key).getChecking());
         writer.println(accounts.get(key).getSavings());
         writer.println(accounts.get(key).getPassword());
         writer.println(accounts.get(key).getExpirationMonth());
         writer.println(accounts.get(key).getExpirationYear());
         writer.println(accounts.get(key).getCheckingNumber());
         writer.println(accounts.get(key).getSavingsNumber());
         writer.println(accounts.get(key).isAccessible());
      }
      writer.close();
      
	}
	/**
	  Getter method to get the hashtable of accounts that belong to the bank
	  @return The hashtable that contains the customers in the bank
	 */
	public Hashtable<Integer, Customer> getCustomers()
	{
	   return accounts;
	}
	/**
	  Returns the dollar amount of the customer's checking account.
	  @param amountWithdrawn The withdraw amount that was verified by the 
	                          bank computer to be valid.
	  @param cardID The ID of the customer's card.
	 */
	public void withdrawChecking(double amountWithdrawn, int cardID)
	{
	   accounts.get(cardID).withdrawChecking(amountWithdrawn);
	}
	/**
   Returns the dollar amount of the customer's savings account.
   @param amountWithdrawn The withdraw amount that was verified by the 
                           bank computer to be valid.
   @param cardID The ID of the customer's card.
  */
	public void withdrawSavings(double amountWithdrawn, int cardID)
   {
      accounts.get(cardID).withdrawSavings(amountWithdrawn);
   }
	
}
