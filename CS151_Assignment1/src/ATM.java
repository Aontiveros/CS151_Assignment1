import java.util.Scanner;


/**
  ATM Class that interacts with the user and the bank computer
  ATM class for CS 151 Assignment #1  
  @author Antonio Ontiveros
  @version 1.0 9/10/2014 
 */
public class ATM
{
   private int ATMNumber;
	private double withdrawLimit;
   private double transactionLimit;
	private double cashOnATM;
	private double minimumCash;
	private String bankName;
	private String ATMName;
	private CashCard currentCard;
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
		ATMName = "ATM" + bankName + "_" + ATMNumber;
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
   

}
