/**
  Customer Class that holds the data to the account in the Bank
  Customer class for CS 151 Assignment #1  
  @author Antonio Ontiveros
  @version 1.0 9/10/2014 
 */
public class Customer
{
   
   private int cardId;
   private int expirationYear;
   private int expirationMonth;
   private double checking;
   private double savings; 
   private int checkingNumber;
   private int savingsNumber;
   private String password;
   private boolean accessible;
   /**
     The class constructor for the Customer class. It gets the values of the 
     customer's card ID, expiration date, balance of their checking, balance of
     their savings, their checking account's ID number, savings account 
     ID number, their password, and whether or not their account is locked.
     @param cardId The ID of the customer's card that accesses their account
     @param expirationYear The expiration year of the customer's card.
     @param expirationMonth The expiration Month of the customer's card.
     @param checking The dollar amount in the customer's checking.
     @param savings The dollar amount in the customer's savings.
     @param password The password the belongs in the user's account.
     @param checkingNumber The checking account number of the user's checking 
                           account.
     @param savingsNumber The savings account number of the user's savings 
                          account.
    */
   public Customer(int cardId, int expirationYear, int expirationMonth, double
            checking, double savings, String password, int checkingNumber,
            int savingsNumber)
   {
      this.cardId = cardId;
      this.expirationYear = expirationYear;
      this.expirationMonth = expirationMonth;
      this.checking = checking;
      this.savings = savings;
      this.password = password;
      this.checkingNumber = checkingNumber;
      this.savingsNumber = savingsNumber;
      this.accessible = true;
       
   }  
   /**
    * Default constructor for the customer class.
    */
   public Customer()
   {
      this.cardId = 0;
      this.expirationYear = 0;
      this.expirationMonth = 0;
      this.checking = 0;
      this.savings = 0;
      this.password = "0";
      this.checkingNumber = 0;
      this.savingsNumber = 0;
      this.accessible = false;
   }
   /**
     The getter method for the expiration year of the user's card.
     @return The expiration year for the user's card.
    */
   public int getExpirationYear()
   {
     return expirationYear;
   }
   /**
     The setter for the user's expiration year.
     @param expirationYear An integer value for the year.
    */
   public void setExpirationYear(int expirationYear)
   {
      this.expirationYear = expirationYear;
   }
   /**
     The getter method for the expiration month of the user's card.
     @return The expiration month of user's card.
    */
   public int getExpirationMonth()
   {
      return expirationMonth;
   }
   /**
     The setter method to set the expiration month of the user's card.
     @param expirationMonth
    */
   public void setExpirationMonth(int expirationMonth)
   {
      this.expirationMonth = expirationMonth;
   }
   /**
     The getter method for the user's checking account dollar amount.
     @return The dollar amount of the user's checking balance
    */
   public double getChecking()
   {
      return checking;
   }
   /**
     The setter method for the dollar amount in the user's checking account.
     @param checking The double amount to set the user's checking balance to.
    */
   public void setChecking(double checking)    
   {
      this.checking = checking;
   }
   /**
      The getter method for the balance of the customer's savings account.
      @return The value that is in the user's savings account.
    */
   public double getSavings()
   {
      return savings;
   }
   /**
      The setter method for the balance of the customer's savings account.     
      @param savings The value to change the balance of the customer's savings
                     account.
    */
   public void setSavings(double savings)
   {
      this.savings = savings;
   }
   /**
      The getter function that returns the customer's password.     
      @return The password of the customer.
    */
   public String getPassword()
   {
      return password;
   }
   /**
      The setter method for the customer's password.
      @param password The password to the customer's account.
    */
   public void setPassword(String password)
   {
      this.password = password;
   }
   /**
      The getter method for the customer's card ID that belongs to the card of 
      the customer.
      @return The card ID of the customer's card.
    */
   public int getCardId()
   {
      return cardId;
   }
   /**
      The setter for the customer's card ID
      @param cardId The card ID to change.
    */
   public void setCardId(int cardId)
   {
      this.cardId = cardId;
   }
   /**
     The overridden toString method that returns a string of the values when
     sent to the outstream.
    */
   public String toString()
   {
      return "Card ID: " + cardId + " Expiration: " + expirationMonth + "/" + 
            expirationYear + " Savings (#"+ savingsNumber+ "): $" + savings 
                  + " Checking (#"+ checkingNumber+ "):$" + checking
            + " Password: " + password + " Accessible: " + accessible;
      
   }
   /**
      Changes the checking balance in the customer's account based on what the Bank 
      and bank computer verified was okay for the customer to take out from the 
      ATM
      @param amount The amount that the account is going be deducted.
    */ 
   public void withdrawChecking(double amount)
   {
      checking -= amount;
   }
   /**
     Changes the savings balance in the customer's account based on what the Bank 
     and bank computer verified was okay for the customer to take out from the 
     ATM
     @param amount The amount that the account is going be deducted.
   */ 
   public void withdrawSavings(double withdrawAmount)
   {
      savings -= withdrawAmount;
      
   }
   /**
      The getter for the checking account number that belongs in the customer's
      account
      @return The integer value for the checking account number.
    */
   public int getCheckingNumber()
   {
      return checkingNumber;
   }
   /**
      The setter for the customer's checking account.
      @param checkingNumber The value to set the checking number to.
    */
   public void setCheckingNumber(int checkingNumber)
   {
      this.checkingNumber = checkingNumber;
   }
   /**
      The getter method to get the value of the customer's savings number.
      @return The customer's savings account number.
    */
   public int getSavingsNumber()
   {
      return savingsNumber;
   }
   /**
      The setter method to set the value of the savings account number.
      @param savingsNumber The value to change the savings account number to.
    */
   public void setSavingsNumber(int savingsNumber)
   {
      this.savingsNumber = savingsNumber;
   }
   /**
    * The getter to check whether or not the account is accessible.
    * @return The boolean value of the accessibility of the account.
    */
   public boolean isAccessible()
   {
      return accessible;
   }
   /**
      The setter method to change the boolean value of the account, so that
      the bank is determined to be accessible or not.
      @param accessible The boolean value to set the accessible trait to.
    */
   public void setAccessible(boolean accessible)
   {
      this.accessible = accessible;
   }
   
    
    
	
	
}
