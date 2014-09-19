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
      Scanner in = new Scanner(System.in);
      while(!choice.equals("quit"))
      {
         choice = selectATM(firstBank, secondBank, in);          
      }

    
   }

   private static String selectATM(Bank firstBank, Bank secondBank, Scanner in) 
         throws FileNotFoundException
   {
      String choice = "";
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
         firstBank.getSpecificATM(1).run(in);
      else if(choice.equals("ATM_A2"))
         firstBank.getSpecificATM(2).run(in);
      else if(choice.equals("ATM_B1"))
         secondBank.getSpecificATM(1).run(in);
      else if(choice.equals("ATM_B2"))
         secondBank.getSpecificATM(2).run(in);
      return choice;
      
      
   }

   private static void initializeBanksAndATMs(Bank firstBank, Bank secondBank) throws FileNotFoundException
   {
      firstBank.createATM();
      firstBank.createATM();
      firstBank.initializeAccount();
      firstBank.initializeAccount();
      firstBank.displayAccounts();
      firstBank.printATMs();
      
      secondBank.createATM();
      secondBank.createATM();
      secondBank.initializeAccount();
      secondBank.initializeAccount();
      secondBank.displayAccounts();
      secondBank.printATMs();
      
   }
}
