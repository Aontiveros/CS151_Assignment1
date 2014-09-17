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
   private String password;
    
   public Customer(int cardId, int expirationYear, int expirationMonth, double
            checking, double savings, String password)
   {
      this.cardId = cardId;
      this.expirationYear = expirationYear;
      this.expirationMonth = expirationMonth;
      this.checking = checking;
      this.savings = savings;
      this.password = password;
       
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
            expirationYear + " Savings: $" + savings + " Checking: $" + checking
            + " Password: " + password;
      
   }
    
    
	
	
}
