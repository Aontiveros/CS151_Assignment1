import java.io.FileNotFoundException;
import java.util.Scanner;


/**
  ATM Class that interacts with the user and the bank computer
  ATM class for CS 151 Assignment #1  
  @author Antonio Ontiveros
  @version 1.0 9/10/2014 
 */
public class ATM
{
   private static final int INCORRECT_ATTEMPTS = 3;
   
   private int ATMNumber;
	private double withdrawLimit;
   private double transactionLimit;
	private double cashOnATM;
	private double minimumCash;
	private String bankName;
	private String ATMName;
	private CashCard currentCustomer;
	private BankComputer supportedBank;
	
	

	/**
	  Class constructor specifying the values of the ATM
	  @param bankName The name of the bank that the ATM now supports
	  @param ATMNumber The name of the ATM
	  @param bankComputer The Bank Object that the ATM references
	  @param transactionLimit 
	  @param withdrawLimit
	  @param minimumCash
	  @param cashOnATM
	 */
	public ATM(String bankName, int ATMNumber, BankComputer bankComputer, int transactionLimit
	      , int withdrawLimit, int minimumCash, int cashOnATM)
	{
		this.setATMNumber(ATMNumber);
		this.setBankName(bankName);
		this.transactionLimit = transactionLimit;
		this.cashOnATM = cashOnATM;
		this.minimumCash = minimumCash;
		this.withdrawLimit = withdrawLimit;
		
		supportedBank = bankComputer;
		ATMName = "ATM" + "_" + bankName + ATMNumber;
	}
	/**
	  Method to start the ATM sequence when a customer chooses 
	  the corresponding ATM
	  @param in the scanner object for detecting input
	  @throws FileNotFoundException if the file that is in the data 
	                                base is not found
	 */
	public void run(Scanner in) throws FileNotFoundException
	{
	   String progress = "";
	   while(!progress.equals("quit"))
	   {
	      if(checkCard(in))
	         if(checkPassword(in))
	            progress = completeDeposit(in); 
	         else
	         {
	            progress = "quit";
	         }
	   }
	}
	/**
	  Function for the ATM to complete a deposit based on whether a user selects
	  their checking or they select their savings. Once selected the user selects
	  the account they would like to access, the function to deposit from that 
	  account is called.
	  @param in Scanner to grab user input
	  @return a string on whether or not the user would like to quit.
	  @throws FileNotFoundException In case the Database file is not found
	 */
	private String completeDeposit(Scanner in) throws FileNotFoundException
   {
      String accountInput = in.next();
      while(!accountInput.equals("Checking") && !accountInput.equals("Savings"))
      {
         System.out.println("Sorry, please enter Savings or Checking: ");
         accountInput = in.next();
      }
      if(accountInput.equals("Checking"))
      {
         return withdrawChecking(in);
        
      }
      else if(accountInput.equals("Savings"))
      {
         return withdrawSavings(in);
             
      }
      return "quit";
         
   }
	/**
	  The method so that a user may withdraw cash from their savings account.
	  The ATM will communicate with the bank to verify if the user has enough
	  money in their account to take the requested dollar amount out. The ATM
	  will also determine if the amount that the user has inputed is within 
	  the ATM's restrictions.
	  @param in The scanner to grab user input
	  @return The value to quit the ATM once the transaction is done
	  @throws FileNotFoundException An exception for the database file
	 */
   private String withdrawSavings(Scanner in) throws FileNotFoundException
   {
      System.out.println("Current balance: " + 
      supportedBank.getSavingsBalance(currentCustomer));
      boolean successWithdraw = false;
      while(!successWithdraw)
      {
         System.out.println(
                "Please enter an ammount you like to withdraw: ");
         double withdrawAmount = in.nextDouble();
         if(withdrawAmount > withdrawLimit || withdrawAmount > transactionLimit
               || withdrawAmount < minimumCash)
         {
            System.out.println("Please enter an ammount smaller than "
                  + withdrawLimit + " or greater than " + minimumCash);
         }
         else if(withdrawAmount > cashOnATM || cashOnATM == 0)
         {
             System.out.println("Sorry, the ATM only has " + cashOnATM);
         }
         else if(withdrawAmount > 
                  supportedBank.getSavingsBalance(currentCustomer))
         {
             System.out.println("Isufficient funds, please try a different"
                           + " a different ammount.");
         }
         else
         {
             supportedBank.withdrawSavings(withdrawAmount, currentCustomer);
             System.out.println("Transaction successful, "
                   + "your account balance is: $"
                   + supportedBank.getSavingsBalance(currentCustomer));
             
             successWithdraw = true;
         }          
      }
      return "quit";      
      
   }
   /**
   The method so that a user may withdraw cash from their checking account.
   The ATM will communicate with the bank to verify if the user has enough
   money in their account to take the requested dollar amount out. The ATM
   will also determine if the amount that the user has inputed is within 
   the ATM's restrictions.
   @param in The scanner to grab user input
   @return The value to quit the ATM once the transaction is done
   @throws FileNotFoundException An exception for the database file
  */
   private String withdrawChecking(Scanner in) throws FileNotFoundException
   {
      System.out.println("Current balance: " + 
      supportedBank.getCheckingBalance(currentCustomer));
      boolean successWithdraw = false;
      while(!successWithdraw)
      {
         System.out.println(
               "Please enter an ammount you like to withdraw: ");
         double withdrawAmount = in.nextDouble();
         if(withdrawAmount > withdrawLimit || withdrawAmount > transactionLimit)
         {
            System.out.println("Please enter an ammount smaller than "
                           + withdrawLimit);
         }
         else if(withdrawAmount > cashOnATM || cashOnATM == 0)
         {
            System.out.println("Sorry, the ATM only has " + cashOnATM);
         }
         else if(withdrawAmount > supportedBank.getCheckingBalance(currentCustomer))
         {
            System.out.println("Isufficient funds, please try a different"
                           + " a different ammount.");
         }
         else
         {
            supportedBank.withdrawChecking(withdrawAmount, currentCustomer);
            System.out.println("Transaction successful, your account balance "
                  + "is: $"
                  + supportedBank.getCheckingBalance(currentCustomer));
            cashOnATM -= withdrawAmount;
            successWithdraw = true;                    
         }
         
                                   
     }
      return "quit";
   }
   /**
     A method for the ATM to check with the bank if the user's account is 
     locked. If the user's account is not locked the ATM will proceed with 
     retrieving the user's password and verify that it is correct with the bank
     if the user fails to put in a correct password three times, the ATM will
     let the bank know, and the bank will lock the account.
     @param in The scanner to get user input
     @return The boolean value the respecting shows whether or not the account
             has been successfully accessed.
     @throws FileNotFoundException An exception for the database file
    */
   private boolean checkPassword(Scanner in) throws FileNotFoundException
   {
	   int incorrectCounter = 0;
	   String getPassword = "";
	   if(!supportedBank.accountStatus(currentCustomer))
      {
         System.out.println("Sorry this account is locked, "
               + "please call the bank");
         return false;
      }
	   System.out.println("This card is accepted, please enter your"
            + " password: ");
	   getPassword = in.next();
	   if(supportedBank.verifyUserPassword(getPassword, currentCustomer) &&
	         supportedBank.accountStatus(currentCustomer))
	   {
	      System.out.println("Welcome! Which account would you like to "
	            + "access?:");
	      return true;
	   }
	   else
	   {
	      incorrectCounter++;
	      while (incorrectCounter < INCORRECT_ATTEMPTS)
	      {
	         System.out.println("Incorrect Password, Please try again (" +
	              (INCORRECT_ATTEMPTS - incorrectCounter) +" attempts left): ");
	         
	         if(supportedBank.verifyUserPassword(in.nextLine(), currentCustomer))
	         {
	            System.out.println("Welcome! Which account would you like to "
	                  + "access?");
	            return true;
	         }
	         incorrectCounter++;
	      }
	      System.out.println("You have entered the incorrect password 3"
               + " times, Please call the bank");
	      supportedBank.setAccountStatusFalse(currentCustomer);
	      return false;
	   }

      
   }
   /**
     A method to check the card that the user has put into the machine. The ATM
     will verify whether or not the card is a valid card in its respective 
     bank, is not expired, or is locked. 
     @param in The scanner to read user input
     @return a boolean value to determine whether or not the card is valid and 
             the account can be accessed.
    */
   private boolean checkCard(Scanner in)
   {
      System.out.println("Please enter your card information : ");
      String inputBankID = in.next();
      int inputCardID = Integer.parseInt(in.next());
      currentCustomer = new CashCard(inputBankID, inputCardID);
      if(inputBankID.equals(supportedBank.getBankID())
            && supportedBank.verifyCardID(currentCustomer))
      {
         if(supportedBank.verifyExpiration(currentCustomer))
         {
            return true;
         }
         else
         {
            System.out.println("Sorry your card is expired.");
            return false;
         }
      }
      else if(!inputBankID.equals(supportedBank.getBankID()))
      {
         System.out.println("Sorry, this ATM doesn't support your Bank.");
         return false;
      }
      else
      {
         System.out.println("Sorry, this bank doesn't recognize your card.");
         return false;
      }
      
      
   }
   /**
     The getter to return the withdraw limit of the ATM
     @return The withdraw limit of the ATM
    */
   public double getWithdrawLimit()
   {
      return withdrawLimit;
   }
   /**
     The setter to set the withdraw limit of the ATM
     @param withdrawLimit the double amount to set the withdraw limit of the ATM
    */
   public void setWithdrawLimit(double withdrawLimit)
   {
      this.withdrawLimit = withdrawLimit;
   }
   /**
     The getter for the transaction limit of the ATM
     @return the value of the ATM's transaction limit
    */
   public double getTransactionLimit()
   {
      return transactionLimit;
   }
   /**
     The setter for the ATM's transaction limit
     @param transactionLimit The double amount to change the ATM's transaction 
                             limit
    */
   public void setTransactionLimit(double transactionLimit)
   {
      this.transactionLimit = transactionLimit;
   }
   /**
     The getter to get the amount of cash the ATM current has on it.
     @return cashOnATM The double amount of cash the ATM has on it.
    */
   public double getCashOnATM()
   {
      return cashOnATM;
   }
   /**
    * The setter to set the value of the cash that the ATM has on it
    * @param cashOnATM The value of cash that the ATM current has.
    */
   public void setCashOnATM(double cashOnATM)
   {
      this.cashOnATM = cashOnATM;
   }
   /**
     Overrides toString method that displays the details of the ATM when 
     passed through the outstream
     @see Object
     @return The string containing the values of the ATM
    */
   public String toString()
   {
      return "\nATM name: " + ATMName  + "\nTransaction Limit: " 
             + transactionLimit + "\nWithdraw Limit: " + withdrawLimit + 
             "\nCash on ATM: " + cashOnATM + "\nMinimum withdraw: " 
             + minimumCash;     
   }
   /**
     The getter to return the ATM ID number of the ATM.
     @return aTMNumber The ID number of the ATM.
    */
   public int getATMNumber()
   {
      return ATMNumber;
   }
   /**
     The setter to change ATM ID number of the ATM.
     @param aTMNumber The ID number to change the ATM to.
    */
   public void setATMNumber(int ATMNumber)
   {
      this.ATMNumber = ATMNumber;
   }
   /**
     The getter to return the value of the bank's name that the ATM belongs to.
     @return bankName The name of the bank that the ATM belongs to.
    */
   public String getBankName()
   {
      return bankName;
   }
   /**
     The setter to replace the value of the bank's name.
     @param bankName the bankName to set
    */
   public void setBankName(String bankName)
   {
      this.bankName = bankName;
   }
   
   

}
