package assignment2;

import java.util.Scanner;

/**
 * @author Maxine Knight 
 * Read integers, finds the largest and counts occurences
 */
public class MaxNumber {

    public static void main(String[] strings) {
        // Program to reads integers, find the largest, then count its occurrences
        
        Scanner in = new Scanner(System.in);
        // User enters list of numbers to analyze
        System.out.println("Enter numbers separated by spaces, ending with 0: ");
        int max = in.nextInt(); // Set the first value equal to the max
        int number = max; 
        int count = 1;
        
        while(number != 0) { // Once number is 0, exits the while loop
            number = in.nextInt(); 
            if (number > max){ // Check if the next number is greater than the current max
                max = number; // If it is, sets the number as the new max
                count = 1; // Resets the count variable
            }
            else if (number == max) { 
                count++; // Increases the count with an equal occurrence
            }
        }
        System.out.println("The largest number is " + max);
        System.out.println("The occurrence count of the largest number is " + count); 

    }
}

