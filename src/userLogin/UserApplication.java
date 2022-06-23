package userLogin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Maxine Knight 
 * Presents menu for various user login options. Hashes passwords, reads and writes to file, sets permissions for admin user
 */

public class UserApplication {

    public static void main(String args[]) throws NoSuchAlgorithmException, FileNotFoundException {
        int input = 0;
        Scanner sc = new Scanner(System.in); // Scanner for user input
        
        while (input != -1) {  
        try { // Outside try/catch for any input mismatch errors in menu, with loop for retry 
            while (input != 9) {
                System.out.println("----------------------------------------");
                System.out.println("Menu choices are:");
                System.out.println("(1) Read user file");
                System.out.println("(2) Add new user");
                System.out.println("(3) List users");
                System.out.println("(4) Display information about a user");
                System.out.println("(5) User login");
                System.out.println("(6) Add an admin user");
                System.out.println("(7) List admin users");
                System.out.println("(8) Write user file");
                System.out.println("(9) Quit");
                System.out.print("\n(1-9) What is your choice? ");

                input = sc.nextInt(); // Get menu input from user
                switch (input) {
                    case 1: // Reading user file data
                        try {
                        File data = new File("users.txt"); // Pass path to find file from project folder 
                        Scanner readLines = new Scanner(data); // Scanner to read in the file// For each line, call User's static addUser method. Pass is already hashed, so boolean is false
                        System.out.println("\n(1) Read user file:");
                        while (readLines.hasNext()) { // If there is another line to read, call the addUser method. Password is already hashed, so boolean is false
                            User.addUser(readLines.next(), readLines.next(), readLines.next(), readLines.next(), false);
                        }
                        System.out.println("File read successfully.\n");
                    } catch (FileNotFoundException e) {
                        System.out.println("This file does not exist in path specified.\n");
                    }
                    break;

                    case 2: // Adding new user 
                        System.out.print("\n(2) Add new user:");
                        System.out.print("\nEnter in user first name and last name separated by a space: ");
                        String first = sc.next();
                        String last = sc.next();
                        
                        System.out.print("Enter the user ID: ");
                        String store = sc.nextLine(); // Variable to skip the new line
                        String id = sc.nextLine(); // Capture the whole line, including spaces which will then set id to "Unknown" in setUserID()
                        System.out.print("Enter the user password: ");
                        String pass = sc.nextLine();

                        if (User.addUser(first, last, id, pass, true) == true) { // Call the addUser method with indication that the password needs to be hashed
                            System.out.println("User added successfully.\n"); // If the addUser method returns true, user added successfully
                        } else {
                            System.out.println("Error: No space, user was not added.\n"); // If addUser returns false (full), display error message
                        }
                        break;

                    case 3: // Print list of all current stored users
                        System.out.println("\n(3) User list:");
                        System.out.println(User.listUsers()); // listUsers will return the string of all users
                        break;

                    case 4: // Search for and display information about a user
                        System.out.println("\n(4) Display information about a user:");
                        System.out.print("Enter the user first and last name you would like to search for: ");
                        first = sc.next();
                        last = sc.next();
                        int index = User.findUser(first + " " + last); // Passes in first and last name as parameter for findUser
                        if (index == -1) { // Index of -1 is returned if user is not found 
                            System.out.println("Error: User not found\n");
                        } else {
                            System.out.println("\nAccount type: " + User.getUser(index).toString()); // Calls the toString method for the given user
                            System.out.println(AdminUser.getUser(index).userInfo(true) + "\n"); // Calls the userInfo method which will return the string, which is printed
                        }
                        break;

                    case 5: // Attempt user login
                        System.out.println("\n(5) User Login:");
                        System.out.print("User ID: ");
                        id = sc.next();
                        System.out.print("Password: ");
                        pass = sc.next();
                        System.out.println(User.userLogin(id, pass) + "\n");
                        break;
                    
                    case 6: // Add admin user
                        System.out.println("\n(6) Add an admin user:");
                        System.out.print("Enter in user first name and last name separated by a space: ");
                        first = sc.next();
                        last = sc.next();
                        
                        System.out.print("Enter the user ID: ");
                        store = sc.nextLine(); // Variable to skip the new line
                        id = sc.nextLine(); // Capture the whole line, including spaces which will then set id to "Unknown" in setUserID()
                        System.out.print("Enter the user password: ");
                        pass = sc.nextLine();
                        System.out.print("Enter permissions separated by a space (database, cloud, security): ");
                        String perm = sc.nextLine();

                        if (AdminUser.addAdminUser(first, last, id, pass, perm, true) == true) { // Call the addAdminUser method with indication that the password needs to be hashed
                            System.out.println("\nUser added successfully.\n"); // If the addUser method returns true, user added successfully
                        } else {
                            System.out.println("Error: No space, user was not added.\n"); // If addAdminUser returns false (full), display error message
                        }
                        break;
                        
                    case 7: // List admin users
                        System.out.println("\n(7) List admin users:");
                        System.out.print("Which permission do you want to focus on? (database, cloud, security, or any): ");
                        perm = sc.next(); 
                        System.out.println("\nAdmin users with " + perm.toLowerCase() + " permission:");
                        System.out.println(AdminUser.listAdminUsers(perm)); // Passes in desired user permission and returns the users that have it
                        break;
                               
                    case 8: // Write user file
                        System.out.println("\n(8) Write user file:");
                        File newFile = new File("userInfo.txt"); // Change file name here
                        PrintWriter output = new PrintWriter(newFile);
                        output.print(User.printUserInfo()); // printUserInfo() will return a string with each user's info on a new line
                        System.out.println("User file written.\n");
                        output.close();
                        break;

                    case 9: // Exit menu 
                        System.out.println("(9) Exiting menu. Goodbye.\n");
                        break;

                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input error. Please try again.\n"); // Will loop the menu for incorrect entry
            String token = sc.nextLine(); // Resets user input
            }
        }
    }
}
