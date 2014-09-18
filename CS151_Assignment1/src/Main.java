import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Scanner;




public class Main
{
   public static void main(String args[]) throws FileNotFoundException
   {
      Bank bank1 = new Bank("BankA");
      bank1.createATM();
      bank1.getSpecificATM(1).run();
   }
}
