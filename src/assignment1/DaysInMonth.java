package assignment1;

import java.util.Scanner;

/**
 * @author Maxine Knight 
 * Displays number of days in a month
 */

public class DaysInMonth {

    public static void main(String[] args) { 
        // User enters the month and year
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter in the month (number format) and year separated by a space: ");
        int month = sc.nextInt();
        int year = sc.nextInt(); 
        boolean leap = false; 
        switch (month) {
            case 1: System.out.println("January " + year + " has 31 days"); break;  
            case 2:
                if (year % 4 == 0) { //Leap year determination. If the year is divisible by 4...
                    if ((year % 100 != 0) || (year % 400 == 0)) { //If the year is not a century or divisible by 400...
                        System.out.println("February " + year + " is a leap year and has 29 days");
                    } 
                } else {
                    System.out.println("February " + year + " is not a leap year and has 28 days");
                } 
                break;
            case 3: System.out.println("March " + year + " has 31 days"); break;  
            case 4: System.out.println("April " + year + " has 30 days"); break;  
            case 5: System.out.println("May " + year + " has 31 days"); break;  
            case 6: System.out.println("June " + year + " has 30 days"); break;  
            case 7: System.out.println("July " + year + " has 31 days"); break;  
            case 8: System.out.println("August " + year + " has 31 days"); break;  
            case 9: System.out.println("September " + year + " has 30 days"); break;  
            case 10: System.out.println("October " + year + " has 31 days"); break;  
            case 11: System.out.println("November " + year + " has 30 days"); break;  
            case 12: System.out.println("December " + year + "  has 31 days"); break;  
        }
    }

}
