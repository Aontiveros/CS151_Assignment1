import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class BankDatabase
{
	private Hashtable<Integer, Customer> accounts;

	public BankDatabase(String BankID) throws FileNotFoundException
	{
	   File inFile = new File(BankID + ".txt");
	   Scanner in = new Scanner(inFile);	   

	   Customer newCustomer;
	   
	   while(in.hasNext())
	   {
	      newCustomer = new Customer();
	      newCustomer.setCardId(in.nextInt());
	      newCustomer.setChecking(in.nextDouble());
	      newCustomer.setSavings(in.nextDouble());
	      newCustomer.setPassword(in.nextLine());
	      newCustomer.setExpirationMonth(in.nextInt());
	      newCustomer.setExpirationYear(in.nextInt());
	      
	      accounts.put(newCustomer.getCardId(), newCustomer);
	            
	   }
	}
	
	public Customer getCustomer(int cardID)
	{
	   return accounts.get(accounts);
	}
	
	public boolean doesCardExist(int cardID)
	{
	   return accounts.containsKey(cardID);
	}
	public void saveDatabase()
	{
	   
	}
	public Hashtable<Integer, Customer> getCustomers()
	{
	   return accounts;
	}
	
}
