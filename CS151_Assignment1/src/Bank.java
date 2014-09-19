import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;


public class Bank
{
   private static final int NUMBER_ONE = 1;
   private static final int ONE_HUNDRED = 100;
   private static final int RANDOM_MULTIPLIER = 3213;
   private static final int TWELVE_MONTHS = 12;
   private static final int YEAR_TWO_THOUSAND = 2000;
   private static final int TEN = 10;
   
   
   private BankDatabase Database;
   private String bankID;
   private Hashtable<Integer, ATM> supportedATMs;
   private int ATMCounter;
   
   public Bank(String bankID) throws FileNotFoundException
   {
      Database = new BankDatabase(bankID);
      this.bankID = bankID;
      supportedATMs = new Hashtable<>(); 
      ATMCounter = NUMBER_ONE;
   }  
   public void createATM()
   {
      supportedATMs.put(ATMCounter, new ATM(bankID, ATMCounter, this, 50, 25, 5,
                                            100));
      ATMCounter++;
   }
   public String getBankID()
   {
      return bankID;
   }
   public void setBankID(String bankID)
   {
      this.bankID = bankID;
   }
   public Hashtable<Integer, ATM> getATMs()
   {
      return supportedATMs;
   }
   public ATM getSpecificATM(int ATMNumber)
   {
      return supportedATMs.get(ATMNumber);
      
   }
   public void displayAccounts()
   {
      Hashtable<Integer, Customer> copy = Database.getCustomers();
      Set<Integer> keys = copy.keySet();
      System.out.println("Bank " + bankID + " Accounts:");
      for(Integer key: keys)
      {
         System.out.println(copy.get(key));
      }
   }
   public void initializeAccount() throws FileNotFoundException
   {
      Random rand = new Random();
      int randomNum = rand.nextInt(ONE_HUNDRED) + NUMBER_ONE;
      while(Database.getCustomers().containsKey(randomNum))
      {
         rand = new Random();
         randomNum = rand.nextInt(ONE_HUNDRED) + NUMBER_ONE;
      }
      DecimalFormat df = new DecimalFormat("####.##");
      SecureRandom random = new SecureRandom();
      String randomPassword = new BigInteger(TEN, random).toString();
      int randomMonth = rand.nextInt(TWELVE_MONTHS) + NUMBER_ONE;
      int randomYear = YEAR_TWO_THOUSAND + 
            rand.nextInt(ONE_HUNDRED) + NUMBER_ONE;
      int randomChkNumber = rand.nextInt(ONE_HUNDRED) + NUMBER_ONE;
      int randomSvgNumber = rand.nextInt(ONE_HUNDRED) + NUMBER_ONE;
      double randomChecking = Double.parseDouble(df.format(
            rand.nextDouble() * RANDOM_MULTIPLIER));
      double randomSavings = Double.parseDouble(df.format(
            rand.nextDouble() * RANDOM_MULTIPLIER));
      
      Database.getCustomers().put(randomNum, new Customer(randomNum, randomYear,
            randomMonth, randomChecking, randomSavings, randomPassword, 
            randomChkNumber, randomSvgNumber));
      Database.saveDatabase(bankID);
                  
   }
   public boolean verifyExpiration(CashCard currentCustomer)
   {
      if(Database.doesCardExist(currentCustomer.getCardId()))
      {
         if(Database.getCustomer(
                    currentCustomer.getCardId()).getExpirationYear() >
                     Calendar.getInstance().get(Calendar.YEAR))
         {
            return true;
         }
         else if(Database.getCustomer(
               currentCustomer.getCardId()).getExpirationYear() == 
                     Calendar.getInstance().get(Calendar.YEAR))
         {
            if(Database.getCustomer(
                  currentCustomer.getCardId()).getExpirationMonth() >= 
                        Calendar.getInstance().get(Calendar.MONTH))
            {
               return true;
            }
         }
      }
      return false;

      
    
   }
   public boolean verifyCardID(CashCard currentCustomer)
   { 
      return Database.doesCardExist(currentCustomer.getCardId());
   }
   public boolean verifyUserPassword(String inputPassword, 
                                     CashCard currentCustomer)
   {
      
      return inputPassword.equals(
                         Database.getUserPassword(currentCustomer.getCardId()));
   }
   public double getCheckingBalance(CashCard currentCustomer)
   {
      return Database.getCustomer(currentCustomer.getCardId()).getChecking();
   }
   public double getSavingsBalance(CashCard currentCustomer)
   {
      return Database.getCustomer(currentCustomer.getCardId()).getSavings();
   }
   public void withdrawChecking(double withdrawAmount, CashCard currentCustomer) 
         throws FileNotFoundException
   {
      Database.getCustomer(
            currentCustomer.getCardId()).withdrawChecking(withdrawAmount);
      Database.saveDatabase(bankID);
      
   }
   public void withdrawSavings(double withdrawAmount, CashCard currentCustomer)
         throws FileNotFoundException
   {
      Database.getCustomer(
            currentCustomer.getCardId()).withdrawSavings(withdrawAmount);
      Database.saveDatabase(bankID);
      
   }
   public void printATMs()
   {
      Set<Integer> keys = supportedATMs.keySet();
      System.out.println("Bank " + bankID + " ATMs:");
      for(Integer key: keys)
      {
         System.out.println(supportedATMs.get(key));
      }
   }
   public boolean accountStatus(CashCard currentCustomer)
   {
      return Database.getCustomer(currentCustomer.getCardId()).isAccessible();     
   }
   public void setAccountStatusFalse(CashCard currentCustomer) 
         throws FileNotFoundException
   {
      Database.getCustomer(currentCustomer.getCardId()).setAccessible(false);
      Database.saveDatabase(currentCustomer.getBankId());
   }
   

}

	

	
	

