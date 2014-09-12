/**
  ATM Class that interacts with the user and the bank computer
  ATM class for CS 151 Assignment #1  
  @author Antonio Ontiveros
  @version 1.0 9/10/2014 
 */
public class ATM
{
	private float withdrawCashLimit;
	private float withdrawTransactionLimit;
	private float cashOnATM;
	private int currentCardID;
	
	/**
	  ATM default constructor. Initializes everything to 0 so it cannot 
	  be used.
	 */
	public ATM()
	{
		withdrawCashLimit = 0;
		withdrawTransactionLimit = 0;
		cashOnATM = 0;
	}
	/**
	 Verifies that the ID given to the ATM by card matches a bank on 
	 the ATM.
	  @return boolean value based on successful or unsuccessful verification.
	 */
	public boolean verifyBankId()
	{
		return false;	
	}
	/**
	  Verifies with the bank that there is an account in the bank database
	  that is associated with the card given to the ATM	  
	  @return a boolean value based on verification of successful or unsuccessful verification.
	 */
	public boolean verifyCardId()
	{
		return false;
	}
	/**
	  Dispenses cash to the current user if they have sufficient funds in their account.
	 */
	public void dispenseCash()
	{
	
	}
	/**
	  Verifies that the card given to the ATM by the customer has a valid expiration date
	  @return boolean value based on successful or unsuccessful expiration date.
	 */
	public boolean verifyExpirationDate()
	{ 
		return true;
	}
	/**
	  Verifies whether or not the user password is correct.
	 */
	public void verifyPassword()
	{
	   
	}
}
