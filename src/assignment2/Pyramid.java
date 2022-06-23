package assignment2;

import java.util.Scanner;

/**
 * @author Maxine Knight 
 * Enter an integer, displays a pyramid
 */
public class Pyramid {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // User enters the number of rows in the pyramid
        System.out.print("Enter the number of lines (between 1 and 15): ");
        int numLines = sc.nextInt();
        
        // Outer loop moves down each row until it reaches the specified numLines
        for (int row = 1; row <= numLines; row++) {
            // Add in blank spaces on left side to keep spacing; prints 1 less each row until reaches last row
            for (int blank = numLines - row; blank >= 1; blank--) {
                System.out.printf("%3s", " ");
            }
            // Prints left side numbers, stopping at value 2 as middle column of 1s is not included
            // Prints corresponding row number, then decrements until reaching 2
            for (int left = row; left >= 2; left--) {
                System.out.printf("%3s", left);
            }
            // Enter middle ones and right side values
            // Begins at printing one in middle, then increments and prints the next value corresponding to row value
            for (int right = 1; right <= row; right++) {
                System.out.printf("%3s", right);
            }
            System.out.println();
        }
    }
}
