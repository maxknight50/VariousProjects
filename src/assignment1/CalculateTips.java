package assignment1;

import java.util.Scanner;

/**
 * @author Maxine Knight 
 * Read subtotal and gratuity rate and calculate gratuity and total
 */

public class CalculateTips {

    public static void main(String[] args) {
        // User enters subtotal and gratuity rate 
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the subtotal and a gratuity rate (no % symbol) separated by a space: ");
        Double subtotal = sc.nextDouble();
        Double gratuityRate = sc.nextDouble();
        
        // Gratuity amount and total are calculated and printed to output
        Double gratuity = subtotal * (gratuityRate / 100);
        Double overallTotal = subtotal + gratuity;
        System.out.println("The gratuity is $" + String.format("%.2f",gratuity) + " and total is $" + String.format("%.2f",overallTotal));
    }

}
