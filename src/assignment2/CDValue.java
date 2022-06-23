
package assignment2;

import java.util.Scanner;

/**
 * @author Maxine Knight 
 * Compute value of CD over time
 */
public class CDValue {

    public static void main(String[] args) {
        // Have the user enter initial deposit, annual yield, and maturity period to calculate CD Value
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the initial deposit amount: ");
            Double initialAmount = in.nextDouble();
        System.out.print("Enter annual percentage yield (no % symbol): ");
            Double annPercentYield = in.nextDouble();
        System.out.print("Enter maturity period (number of months): ");
            int maturityPeriod = in.nextInt();

        // Format the output headers of Month and CD Value
        System.out.println("Month" + "\t" + "CD Value");
        // Calculate the CD Value, output the month and amount, then loop and recalculate with new value
        for (int i = 1; i <= maturityPeriod; i++) {
            Double CDValue = initialAmount + initialAmount * annPercentYield / 1200;
            System.out.println(i + "\t" + String.format("%.2f", CDValue));
            initialAmount = CDValue; // Reassigns initial amount to new CD value
        }
    }
}
