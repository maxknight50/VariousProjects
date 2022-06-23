package assignment2;

import java.util.Scanner;

/**
 * @author Maxine Knight 
 * Reads in 10 numbers and displays the distinct numbers
 */
public class DistinctNumbers {

    public static void main(String[] args) {
        // This program reads 10 numbers and displays the number and values of the distinct ones
        
        Scanner in = new Scanner(System.in);
        int[] numbers = new int[10]; // Create an array for the incoming user values
        int[] distinct = new int[10]; // Create an array to store distinct values
        int i, j, distinctCount = 0;
        System.out.print("Enter 10 numbers: ");

        // In the outer loop, each value is stored into the numbers array
        for (i = 0; i < numbers.length; i++) {
            boolean match = false; // Match will be true if the values are equal when compared. Default is false
            numbers[i] = in.nextInt();

            /* In the inner loop, the current value (located in numbers[]) is compared against each previously 
             * identified distinct value (located in distinct[]) to determine whether it is distinct or a match */
            for (j = 0; j < distinctCount; j++) {
                if (distinct[j] == numbers[i]) {
                    match = true;
                    break; // If the current number is not distinct, the loop is exited
                }
            }
            if (match == false) { // If there is no match, then the number is distinct
                distinct[distinctCount] = numbers[i]; // The value is stored in the distinct array
                distinctCount++; // The count of how many distinct values there are is updated 
            }
        }

        System.out.println("The number of distinct numbers is " + distinctCount);
        System.out.print("The distinct numbers are: ");
        for (int a = 0; a < distinctCount; a++) {
            System.out.print(distinct[a] + " "); // Loops through and prints each stored distinct value
        }
    }
}
