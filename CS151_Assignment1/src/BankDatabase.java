import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;


public class BankDatabase
{
	private Hashtable<Integer, Customer> accounts;

	public BankDatabase(String BankID) throws FileNotFoundException
	{
	   File inFile = new File(BankID + ".txt");
	   Scanner in = new Scanner(inFile);	   

	   Customer newCustomer;
	   accounts = new Hashtable<>();
	   while(in.hasNext()) 
	   {
	      newCustomer = new Customer();
	      newCustomer.setCardId(in.nextInt());
	      newCustomer.setChecking(in.nextDouble());
	      newCustomer.setSavings(in.nextDouble());	      
	      newCustomer.setPassword(in.next());
	      newCustomer.setExpirationMonth(in.nextInt());
	      newCustomer.setExpirationYear(in.nextInt());
	      //System.out.println(newCustomer);
	      accounts.put(newCustomer.getCardId(), newCustomer);
	            
	   }
	}
	
	public Customer getCustomer(int cardID)
	{
	   return accounts.get(cardID);
	}
	
	public boolean doesCardExist(int cardID)
	{
	   return accounts.containsKey(cardID);
	}
	public String getUserPassword(int cardID)
	{
	   return accounts.get(cardID).getPassword();
	}
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
     
      }
      writer.close();
      
	}
	public Hashtable<Integer, Customer> getCustomers()
	{
	   return accounts;
	}
	public void withdrawChecking(double amountWithdrawn, int cardID)
	{
	   accounts.get(cardID).withdrawChecking(amountWithdrawn);
	}
	public void withdrawSavings(double amountWithdrawn, int cardID)
   {
      accounts.get(cardID).withdrawSavings(amountWithdrawn);
   }
	
}
