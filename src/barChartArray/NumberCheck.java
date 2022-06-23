package barChartArray;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Maxine Knight 
 * User enters list of numbers, various mathematical operations performed
 */

public class NumberCheck {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        /* This is an outer while loop for the main method. It is used with the try catch in order to display an error message
        when the user enters incorrect values for the numberArray size or values. Once in the second while loop, this while loop
        will not continue to execute.
        */
        int entry = -1; 
        while (entry == -1) {
            // This try block extends through main so that the numberArray is included within the scope of the second while loop
            try {
                System.out.print("How many numbers in the array? "); // User enters size of the array
                int numbersInArray = sc.nextInt();
                double numberArray[] = new double[numbersInArray];
                System.out.print("Please enter " + numbersInArray + " decimal or integer values separated by spaces: ");
                for (int i = 0; i < numbersInArray; i++) { // User enters each value of the array
                    numberArray[i] = sc.nextDouble();
                }
                System.out.println();

                /* This is the second while loop and try statement. They cover the menu options so that if an incorrect value is
                entered, the program will display an error message and repeat the menu, allowing another input attempt. Will loop
                until a zero is entered in the menu.
                */
                while (entry != 0) {
                    try {
                        // Displays the menu 
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("Enter : ");
                        System.out.println("1 -- to determine if a number is prime");
                        System.out.println("2 -- to list prime numbers below a given value");
                        System.out.println("3 -- to compute the statistics from an array of numbers");
                        System.out.println("4 -- to perform linear search finding a number in the array");
                        System.out.println("5 -- to display a bar chart of values");
                        System.out.println("0 -- to quit");
                        System.out.println();

                        // User enters choice. If entry is invalid, will be caught by catch block, message displays, and menu repeats
                        System.out.print("Enter a menu number: ");
                        entry = sc.nextInt();
                        System.out.println();

                        switch (entry) {
                            case 0: // If user enters 0, program will exit the switch and retest at the while, and then exit loop
                                System.out.println("Goodbye!");
                                break;
                            
                            case 1: // User enters value, isPrime() is called to determine if it is prime, and message is displayed depending on the returned result
                                System.out.print("Enter a number to check if prime: ");
                                int numToCheck = sc.nextInt();
                                if (isPrime(numToCheck) == true) {
                                    System.out.println(numToCheck + " IS a prime number \n");
                                } else if (isPrime(numToCheck) == false) {
                                    System.out.println(numToCheck + " is NOT a prime number \n");
                                }
                                break;
                            
                            case 2: // User enters value, program will display all prime values below that value with 10 values per line by calling listPrimes()
                                System.out.print("Enter a number to list all primes below it: ");
                                int listPrimesNum = sc.nextInt();
                                System.out.println("The prime numbers up to " + listPrimesNum + " are: \n" + listPrimes(listPrimesNum) + "\n");
                                break;
                            
                            case 3: /* The previously entered numberArray is passed to average() and standardDeviation() to compute statistics. numberArray is 
                                     * converted to a String for display in output
                                    */
                                System.out.print("The average of: " + Arrays.toString(numberArray) + " is " + average(numberArray) + "\n");
                                System.out.println("The standard deviation of: " + Arrays.toString(numberArray) + " is " + standardDeviation(numberArray) + "\n");
                                break;
                            
                            case 4: /* User enters a search value, and the program will search in numberArray using a call to linearSearch(), passing in the search 
                                        value and numberArray. The position will be returned and printed if it is found. If not found, -1 will be returned and a 
                                        "not found" message will print instead
                                    */
                                System.out.print("Enter a double value to search for: ");
                                double linearSearchValue = sc.nextDouble();
                                if (linearSearch(numberArray, linearSearchValue) != -1) { // If -1 is returned, this means the value was not found
                                    System.out.println("The value " + linearSearchValue + " is found in position " + linearSearch(numberArray, linearSearchValue) + "\n");
                                } else {
                                    System.out.println("The value " + linearSearchValue + " is not in the array \n");
                                }
                                break;
                            
                            case 5: /* The numberArray will be passed to displayBarChart, which will display a bar chart representing each value of the array. Interacting
                                       with one of the bars will display the decimal value and whether is is above, below, or equal to the average. 
                                    */ 
                                displayBarChart(numberArray);
                                break;
                            
                            default: // An error message will display if a non-menu integer is entered, then will display the menu again
                                System.out.println("**Error. Please retry with one of the menu integers** \n");
                        }

                    } catch (Exception e) { // This is the inner catch. If any exception is thrown, will display an error message and which error it was
                        System.out.println("Error. Please try again: " + e.toString());
                        String store = sc.nextLine(); // The token created by catch will be stored in a string so that the next user input reads correctly
                        System.out.println();
                    }
                }
            } catch (Exception e) { // This is the outer catch for the main menu, specifically catching errors with populating numberArray
                System.out.println("Error. Please enter a proper value: " + e.toString() + "\n");
                String store = sc.nextLine(); // It will also store the line of values entered by a user so improper ones will not affect the next user input
            }
        }
    }

    static boolean isPrime(int number) { // Tests if passed in number is prime
        boolean isPrime = true; // Create default value of true
        for (int i = 2; i <= number / 2; i++) { // Loop up to half of the number we are testing
            if (number % i == 0) { // If the number is divisible by anything greater than 1, it is not prime
                isPrime = false; 
                break;
            }
        }
        return isPrime; // Returns true or false
    }

    static String listPrimes(int number) { // Returns string of primes less than passed in number
        String listOfPrimes = "";
        int count = 0;
        for (int i = 1; i <= number; i++) {
            if (isPrime(i) == true) { // For each number less than the value, calls isPrime to test is
                listOfPrimes += i + " "; // Adds the prime to a string
                count++;
                if (count % 10 == 0) {
                    listOfPrimes = listOfPrimes + "\n"; // Adds a new line every 10 values 
                }
            }
        }
        return listOfPrimes; // Returns the formatted string of primes
    }

    static double average(double[] nbrs) { // Calculates the average of the passed in array
        double average = 0;
        double sum = 0;
        for (int i = 0; i < nbrs.length; i++) {
            sum += nbrs[i]; // Calculates sum of all values
        }
        average = sum / nbrs.length; // Divides the sum by the number of array elements
        return average; // Returns the double average
    }

    static double standardDeviation(double[] nbrs) { // Calculates the standard deviation of the passed in array
        double standardDeviation = 0;
        for (int i = 0; i < nbrs.length; i++) {
            standardDeviation += Math.pow(nbrs[i] - average(nbrs), 2); // Calculates the element at position i minus the average, then squared
        }
        return Math.sqrt(standardDeviation / (nbrs.length - 1)); // Returns the double of the square root of the length of the array minus 1
    }

    public static int linearSearch(double[] list, double value) { // Finds a specified value in the array
        int position = -1;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == value) { // Checks if the array element equals the search value
                position = i; // Stores the position if found
            }
        }
        return position; // Returns -1 if not found, and the position if found
    }

    public static void displayBarChart(double[] values) { // Displays a bar chart with all array values and compares each to average
        double average = average(values); // Call the average method for the array and store the value
        // Create labels, info, and values arrays
        String[] labels = new String[values.length];
        String[] info = new String[values.length];
        int[] intValues = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            intValues[i] = (int) values[i]; // Converts each double element in array to an int for the bar chart
            labels[i] = "Bar " + i; // Creates the labels for each element of the array

            // Checks each position in the array. Creates message in info array if it is lower than, higher than, or equal to average
            if (values[i] < average) {
                info[i] = "Bar " + i + " value is " + values[i] + "\n Lower than average";
            } else if (values[i] > average) {
                info[i] = "Bar " + i + " value is " + values[i] + "\n Higher than average";
            } else {
                info[i] = "Bar " + i + " value is " + values[i] + "\n Equal to average";
            }
        }

        // Create object of BarChartFrame which will create the bar chart
        BarChartFrame bcf = new BarChartFrame("Array Values", intValues, labels, info);

    }

}
