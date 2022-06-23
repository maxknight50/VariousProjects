package salesData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.NumberFormat;

/**
 * @author Maxine Knight 
 * Reads product sales from a file, calculates total revenue per region
 */
public class SalesData {

    public static void main(String[] args) throws FileNotFoundException {
        NumberFormat fmt1 = NumberFormat.getCurrencyInstance();
        
    try {
//        Scanner in = new Scanner(System.in); 
//        System.out.print("Enter file path: ");
//        String path = in.next();
//        System.out.println();
        File data = new File("salesdata1.txt"); // Pass path 
        Scanner readLines = new Scanner(data); // Scanner to read in the file
        
        int productCount = 0; 
        while(readLines.hasNextLine()) { // Count the number of lines in the file which equals the number of products
            readLines.nextLine();
            productCount++; 
        }
        
        Scanner file = new Scanner(data); // Reopen the scanner to reread through file
        // Create arrays, using the number of products as the size of each
        String productNames[] = new String[productCount];
        int totalUnits[] = new int[productCount];
        double totalRevenue[] = new double[productCount];
        double regionalRevenue[] = new double[4]; // Revenue for North, East, South, West
        
        double sumRevenue = 0; 
        for (int i = 0; i < productCount; i++) {
            productNames[i] = file.next(); // First value stores the product name
            totalRevenue[i] = file.nextDouble(); // Second value stores the revenue
            for (int j = 0; j < 4; j++) { // For the next four regional values:
                int unit = file.nextInt(); // Store the next int in unit
                regionalRevenue[j] += unit * totalRevenue[i]; // N/E/S/W units are each multiplied by the revenue in their row
                totalUnits[i] += unit; // Sum the total units in the row and store
            }
        } 
        for(int i = 0; i < 4; i++) { // After regionalRevenue array is fully populated, create new loop
            sumRevenue += regionalRevenue[i]; // Sums the values of regional revenue
        }
        
        System.out.println("Revenue by region");
        System.out.println("North" + "\t" + fmt1.format(regionalRevenue[0]));
        System.out.println("South" + "\t" + fmt1.format(regionalRevenue[1]));
        System.out.println("East"  + "\t" + fmt1.format(regionalRevenue[2]));
        System.out.println("West " + "\t" + fmt1.format(regionalRevenue[3]));
        System.out.println("Total Revenue: " + fmt1.format(sumRevenue) + "\n");
            
        int unitSum = 0;
        double revenueSum = 0.0; 
        System.out.println("Product summary"); // Print the values for the product summary
        System.out.println("Product Type" + "\t" + "Units" + "\t" + "Revenue" + "\t");
        for (int i = 0; i < productCount; i++) { 
            System.out.print(productNames[i] + "\t");
            System.out.print(totalUnits[i] + "\t");
            unitSum += totalUnits[i]; // Sum the total units in the row for GRAND TOTAL
            totalRevenue[i] = totalRevenue[i] * totalUnits[i];
            System.out.print(fmt1.format(totalRevenue[i]) + "\t");
            revenueSum += totalRevenue[i]; // Sum the total revenue to dispay for GRAND TOTAL
            System.out.println();
        }
        System.out.println("GRAND TOTAL" + "\t" + unitSum + "\t" + fmt1.format(revenueSum));
        System.out.println("AVERAGE REVENUE" + "  " + fmt1.format(revenueSum / productCount) + "\n");
       
        System.out.println("The following products generated higher than the average revenue:");
        for(int i = 0; i < productCount; i++) {
            if(totalRevenue[i] > (revenueSum / productCount)) { // If the revenue for a product is greater than the average revenue:
                System.out.println("\t" + productNames[i]); // Print the product name
                }
            }
        
        } catch (FileNotFoundException e) {
            System.out.println("This file does not exist in path specified");

        }
    }
}


        
