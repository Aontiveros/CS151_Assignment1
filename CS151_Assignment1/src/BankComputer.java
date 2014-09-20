import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;


/**
  BankComputer Class that provides the interface between the ATM and the bank.
  BankComputer class for CS 151 Assignment #1  
  @author Antonio Ontiveros
  @version 1.0 9/10/2014 
 */
public class BankComputer
{
   private static final int NUMBER_ONE = 1;
   private static final int ONE_HUNDRED = 100;
   private static final int RANDOM_MULTIPLIER = 3213;
   private static final int TWELVE_MONTHS = 12;
   private static final int YEAR_TWO_THOUSAND = 2000;
   private static final int TEN = 10;
   
   
   private Bank Database;
   private String bankID;
   private Hashtable<Integer, ATM> supportedATMs;
   private int ATMCounter;
   /**
      The BankComputer Class constructor, it will create a bank with the ID 
      given and create a table to store future ATMs that belong to its bank.
      @param bankID The name of the bank the computer will belong to.
      @throws FileNotFoundException 
    */
   public BankComputer(String bankID) throws FileNotFoundException
   {
      Database = new Bank(bankID);
      this.bankID = bankID;
      supportedATMs = new Hashtable<>(); 
      ATMCounter = NUMBER_ONE;
   }  
   /**
      Method to create ATMs that will belong to the bank. It will pass in values
      to the ATM that limit its withdraw, transaction, and cash on ATM values 
      Also increases the ATMcounter to speficy how many ATMS belong in to the 
      bank.
    */
   public void createATM()
   {
      supportedATMs.put(ATMCounter, new ATM(bankID, ATMCounter, this, 50, 25, 5,
                                            100));
      ATMCounter++;
   }
   /**
      The getter method to return the ID of the bank that the computer belongs
      to
      @return ID for the bank that the computer belongs.
    */
   public String getBankID()
   {
      return bankID;
   }
   /**
      The getter method to retrieve the ATMs that are supported by the bank
      computer. 
      @return The Hashtable containing all the ATMs that the computer support.
    */
   public Hashtable<Integer, ATM> getATMs()
   {
      return supportedATMs;
   }
   /**
      The getter method to get a specific ATM, by its number. 
      @param ATMNumber The number that the ATM belongs to.
      @return The ATM that the bank computer has at that ID.
    */
   public ATM getSpecificATM(int ATMNumber)
   {
      return supportedATMs.get(ATMNumber);   
   }
   /**
     Displays the accounts that are contained in the Bank. Displays all 
     details of each customer in the bank supported by the computer.
    */
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
   /**
      This method randomly creates accounts to add to the bank. It will generate
      random values to fulfill the values of the customer class that seem 
      appropriate.
      @throws FileNotFoundException
    */
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
   /**
      A method to verify the expiration of a cash card that was entered into 
      the ATM. The customer's date has to be greater than the current date.
      @param currentCustomer The cash card entered by the customer into the ATM.
      @return The boolean value corresponding the expiration date of the ATM.
    */
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
   /**
      The bank computer will check to see if the card exists in the bank. If 
      if the card doesn't belong it will return false. If the card does exist 
      it will return true.
      @param currentCustomer The cash card entered by the customer into the ATM.
      @return The boolean value returned by the database in the bank.
    */
   public boolean verifyCardID(CashCard currentCustomer)
   { 
      return Database.doesCardExist(currentCustomer.getCardId());
   }
   /**
      The bank computer will verify that the password entered by the user is 
      correct by checking the value of the password that the bank has for the
      customer.
      @param inputPassword The password that the user entered into the ATM.
      @param currentCustomer The cash card that the ATM currently has.
      @return The boolean value of the result from the Bank database.
    */
   public boolean verifyUserPassword(String inputPassword, 
                                     CashCard currentCustomer)
   {
      
      return inputPassword.equals(
                         Database.getUserPassword(currentCustomer.getCardId()));
   }
   /**
      The getter method to check the customer's balance in their checking 
      account.
      @param currentCustomer The cash card of the customer currently 
                             accessing the ATM
      @return The balance in the user's checking account.
    */
   public double getCheckingBalance(CashCard currentCustomer)
   {
      return Database.getCustomer(currentCustomer.getCardId()).getChecking();
   }
   /**
      The getter method to check the customer's balance in their 
      savings account.
      @param currentCustomer The cash card of the current user 
                             accessing the ATM.
      @return The balance in the customer's savings account.
    */
   public double getSavingsBalance(CashCard currentCustomer)
   {
      return Database.getCustomer(currentCustomer.getCardId()).getSavings();
   }
   /**
      Withdraws an amount that the user has entered into the ATM and has been 
      verified to meet the restrictions by the ATM and the Bank from their 
      checking.
      @param withdrawAmount The amount the user is withdrawing from their
                            account.
      @param currentCustomer The cash card of the current customer accessing the
                              ATM.
      @throws FileNotFoundException
    */
   public void withdrawChecking(double withdrawAmount, CashCard currentCustomer) 
         throws FileNotFoundException
   {
      Database.getCustomer(
            currentCustomer.getCardId()).withdrawChecking(withdrawAmount);
      Database.saveDatabase(bankID);
      
   }
   /**
      Withdraws an amount that the user has entered into the ATM and has been 
      verified to meet the restrictions by the ATM and the Bank from thier
      savings.
      @param withdrawAmount The amount the user is withdrawing from their
                            account.
      @param currentCustomer The cash card of the current customer accessing the
                              ATM.
      @throws FileNotFoundException
    */
   public void withdrawSavings(double withdrawAmount, CashCard currentCustomer)
         throws FileNotFoundException
   {
      Database.getCustomer(
            currentCustomer.getCardId()).withdrawSavings(withdrawAmount);
      Database.saveDatabase(bankID);
      
   }
   /**
    * Prints all the ATMs that the bank computer currently has.
    */
   public void printATMs()
   {
      Set<Integer> keys = supportedATMs.keySet();
      System.out.println("Bank " + bankID + " ATMs:");
      for(Integer key: keys)
      {
         System.out.println(supportedATMs.get(key));
      }
   }
   /**
      Retrieves the current status of the customer's account.
      @param currentCustomer The cash card of the current customer
                             accessing the ATM
      @return The boolean value corresponding the accessible trait of the 
              customer's account.
    */
   public boolean accountStatus(CashCard currentCustomer)
   {
      return Database.getCustomer(currentCustomer.getCardId()).isAccessible();     
   }
   /**
      The setter method to change the account status of the customer so
      that their account is locked and no longer accessible.
      @param currentCustomer The cash card of the current user who accessed the 
                             ATM
      @throws FileNotFoundException
    */
   public void setAccountStatusFalse(CashCard currentCustomer) 
         throws FileNotFoundException
   {
      Database.getCustomer(currentCustomer.getCardId()).setAccessible(false);
      Database.saveDatabase(currentCustomer.getBankId());
   }
   

}

	

	
	

