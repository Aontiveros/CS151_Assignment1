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
	private Bank supportedBank;
	

	public ATM(String bankName, int ATMNumber, Bank newBank, int transactionLimit
	      , int withdrawLimit, int minimumCash, int cashOnATM)
	{
		this.ATMNumber = ATMNumber;
		this.bankName = bankName;
		this.transactionLimit = transactionLimit;
		this.cashOnATM = cashOnATM;
		this.minimumCash = minimumCash;
		this.withdrawLimit = withdrawLimit;
		
		supportedBank = newBank;
		ATMName = "ATM" + "_" + bankName + ATMNumber;
	}
	public void run() throws FileNotFoundException
	{
	   String progress = "";
	   while(!progress.equals("quit"))
	   {
	      if(checkCard())
	         if(checkPassword())
	            progress = completeDeposit(); 
	         else
	         {
	            System.out.println("You have entered the incorrect password 3"
	                  + " times, Please call the bank");
	            progress = "quit";
	         }
	   }
	}
	
	private String completeDeposit() throws FileNotFoundException
   {
      Scanner in = new Scanner(System.in);
      String accountInput = in.next();
      while(!accountInput.equals("Checking") && !accountInput.equals("Savings"))
      {
         System.out.println("Sorry, please enter Savings or Checking: ");
         accountInput = in.next();
      }
      if(accountInput.equals("Checking"))
      {
         return withdrawChecking();
        
      }
      else if(accountInput.equals("Savings"))
      {
         return withdrawSavings();
             
      }
      return "quit";
         
   }
   private String withdrawSavings() throws FileNotFoundException
   {
      Scanner in = new Scanner(System.in);
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

   private String withdrawChecking() throws FileNotFoundException
   {
      System.out.println("Current balance: " + 
      supportedBank.getCheckingBalance(currentCustomer));
      boolean successWithdraw = false;
      while(!successWithdraw)
      {
         Scanner in = new Scanner(System.in);
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
            System.out.println("Transaction successful, your account balance is:"
                  + supportedBank.getCheckingBalance(currentCustomer));
            successWithdraw = true;                    
         }
         
                                   
     }
      return "quit";
   }
   
   private boolean checkPassword()
   {
	   Scanner in = new Scanner(System.in);
	   int incorrectCounter = 0;
	   if(supportedBank.verifyUserPassword(in.nextLine(), currentCustomer))
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
	      return false;
	   }

      
   }
   private boolean checkCard()
   {
      System.out.println("Please enter your card information : ");
      Scanner in = new Scanner(System.in);
      String inputBankID = in.next();
      int inputCardID = Integer.parseInt(in.next());
      
      currentCustomer = new CashCard(inputBankID, inputCardID);
      if(inputBankID.equals(supportedBank.getBankID())
            && supportedBank.verifyCardID(currentCustomer))
      {
         if(supportedBank.verifyExpiration(currentCustomer))
         {
            System.out.println("This card is accepted, please enter your"
                  + " password: ");
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
   public double getWithdrawLimit()
   {
      return withdrawLimit;
   }
   public void setWithdrawLimit(float withdrawLimit)
   {
      this.withdrawLimit = withdrawLimit;
   }
   public double getTransactionLimit()
   {
      return transactionLimit;
   }
   public void setTransactionLimit(float transactionLimit)
   {
      this.transactionLimit = transactionLimit;
   }
   public double getCashOnATM()
   {
      return cashOnATM;
   }
   public void setCashOnATM(float cashOnATM)
   {
      this.cashOnATM = cashOnATM;
   }
   public String toString()
   {
      return "\nATM name: " + ATMName  + "\nTransaction Limit: " 
             + transactionLimit + "\nWithdraw Limit: " + withdrawLimit + 
             "\nCash on ATM: " + cashOnATM + "\nMinimum withdraw: " 
             + minimumCash;
      
      
   }
   

}
