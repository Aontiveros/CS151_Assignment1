import java.io.FileNotFoundException;
import java.util.Scanner;




public class Main
{
   public static void main(String args[]) throws FileNotFoundException
   {
      Bank firstBank = new Bank("A");
      Bank secondBank = new Bank("B");
      initializeBanksAndATMs(firstBank, secondBank);
      String choice = "";
      while(!choice.equals("quit"))
      {
         choice = selectATM(firstBank, secondBank);          
      }

    
   }

   private static String selectATM(Bank firstBank, Bank secondBank) 
         throws FileNotFoundException
   {
      String choice = "";
      Scanner in = new Scanner(System.in);
      System.out.println("Please choose an ATM or type quit to exit: ");
      choice = in.next();
      while(!choice.equals("ATM_A1") && !choice.equals("ATM_A2") && 
            !choice.equals("ATM_B1") && !choice.equals("ATM_B2") 
            && !choice.equals("quit"))
      {
         System.out.println("Sorry, please enter a correct ATM name: ");
         choice = in.next();           
      }
      if(choice.equals("ATM_A1"))
         firstBank.getSpecificATM(1).run();
      else if(choice.equals("ATM_A2"))
         firstBank.getSpecificATM(2).run();
      else if(choice.equals("ATM_B1"))
         secondBank.getSpecificATM(1).run();
      else if(choice.equals("ATM_B2"))
         secondBank.getSpecificATM(2).run();
      return choice;
      
   }

   private static void initializeBanksAndATMs(Bank firstBank, Bank secondBank) throws FileNotFoundException
   {
      firstBank.createATM();
      firstBank.createATM();
     // firstBank.initializeAccount();
     // firstBank.initializeAccount();
      firstBank.displayAccounts();
      firstBank.printATMs();
      
      secondBank.createATM();
      secondBank.createATM();
     // secondBank.initializeAccount();
     // secondBank.initializeAccount();
      secondBank.displayAccounts();
      secondBank.printATMs();
      
   }
}
