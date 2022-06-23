package assignment1;

import java.util.Scanner;

/**
 * @author Maxine Knight 
 * Displays future investment amount
 */
public class FutureInvestment {
     
public static void main(String[] args) {
    // Reads in investment amount, annual int rate, and # of years. 
    // Returns future investment value = investAmount x (1 + monthlyInterestRate)^(numberOfYears*12)
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the investment amount: ");
        Double investAmount = sc.nextDouble();
        
    System.out.println("Enter the annual interest rate (without % sign): ");
        Double intRate = sc.nextDouble();
        Double annIntRate = (intRate/12)/100; 
        
    System.out.println("Enter the number of years: ");
        int numYears = sc.nextInt();
    Double futInvestVal = investAmount * Math.pow((1+annIntRate),(numYears*12)); 
   
    System.out.println("Your future investment value is $" + String.format("%.2f",futInvestVal)); 
}

}

