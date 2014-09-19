import java.util.Date;

/**
 
  @author tony
 
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
       
   }  
   public Customer()
   {
      this.cardId = 0;
      this.expirationYear = 0;
      this.expirationMonth = 0;
      this.checking = 0;
      this.savings = 0;
      this.password = "";
   }
   public int getExpirationYear()
   {
     return expirationYear;
   }
   public void setExpirationYear(int expirationYear)
   {
      this.expirationYear = expirationYear;
   }
   public int getExpirationMonth()
   {
      return expirationMonth;
   }
   public void setExpirationMonth(int expirationMonth)
   {
      this.expirationMonth = expirationMonth;
   }
   public double getChecking()
   {
      return checking;
   }
   public void setChecking(double checking)    
   {
      this.checking = checking;
   }
   public double getSavings()
   {
      return savings;
   }
   public void setSavings(double savings)
   {
      this.savings = savings;
   }
   public String getPassword()
   {
      return password;
   }
   public void setPassword(String password)
   {
      this.password = password;
   }
   public int getCardId()
   {
      return cardId;
   }
   public void setCardId(int cardId)
   {
      this.cardId = cardId;
   }
   public String toString()
   {
      return "Card ID: " + cardId + " Expiration: " + expirationMonth + "/" + 
            expirationYear + " Savings (#"+ savingsNumber+ "): $" + savings 
                  + " Checking (#"+ checkingNumber+ "):$" + checking
            + " Password: " + password;
      
   }
   public void withdrawChecking(double amount)
   {
      checking -= amount;
   }
   public void withdrawSavings(double withdrawAmount)
   {
      savings -= withdrawAmount;
      
   }
   public int getCheckingNumber()
   {
      return checkingNumber;
   }
   public void setCheckingNumber(int checkingNumber)
   {
      this.checkingNumber = checkingNumber;
   }
   public int getSavingsNumber()
   {
      return savingsNumber;
   }
   public void setSavingsNumber(int savingsNumber)
   {
      this.savingsNumber = savingsNumber;
   }
   
    
    
	
	
}
