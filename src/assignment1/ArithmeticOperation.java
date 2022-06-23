package assignment1;

import java.util.Scanner;

/**
 * @author Maxine Knight 
 * Perform a user-specified arithmetic operation on two numbers
 */

public class ArithmeticOperation {

    public static void main(String[] args) {
        
        //User enters two values 
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter in two decimal numbers separated by a space:");
            Double value1 = sc.nextDouble();
            Double value2 = sc.nextDouble();
        //User enters the operation they would like to be performed 
        System.out.println("Enter 1 for add, 2 for subtract, 3 for multiply, or 4 for divide: ");
            int operation = sc.nextInt();
        
        //The result is printed in output
        if (operation == 1) {
            System.out.println(value1 + " + " + value2 + " = " + (value1 + value2));
        }
        if (operation == 2) {
            System.out.println(value1 + " - " + value2 + " = " + (value1 - value2));
        }
        if (operation == 3) {
            System.out.println(value1 + " * " + value2 + " = " + (value1 * value2));
        }
        if (operation == 4) {
            System.out.println(value1 + " / " + value2 + " = " + (value1 / value2));
        }
    }
}
