import java.io.FileNotFoundException;




public class Main
{
   public static void main(String args[]) throws FileNotFoundException
   {
      Bank bank1 = new Bank("BankA");
      Bank bank2 = new Bank("BankB");
      bank1.createATM();
      bank1.createATM();
      bank1.initializeAccount();
      bank1.initializeAccount();
      bank1.displayAccounts();
      bank1.printATMs();
      
      bank2.createATM();
      bank2.createATM();
      bank2.initializeAccount();
      bank2.initializeAccount();
      bank2.displayAccounts();
      bank2.printATMs();
    //
      //bank2.
    
   }
}
