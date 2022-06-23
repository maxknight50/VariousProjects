package assignment1;

import java.util.Scanner;

/**
 * @author Maxine Knight 
 * Calculates geometry distance between two points
 */

public class TwoPointsDistance {
     
public static void main(String[] args) {
    // User enters two coordinates (x1, y1) and (x2, y2)
    // Displays their distance using formula 
    Scanner sc = new Scanner(System.in); 
    System.out.println("Enter x1 and y1 separated by a space: ");
        Double x1 = sc.nextDouble();  
        Double y1 = sc.nextDouble(); 
        
    System.out.println("Enter x2 and y2 separated by a space: ");
        Double x2 = sc.nextDouble();  
        Double y2 = sc.nextDouble();  
        
    Double distance = Math.sqrt((Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2)));
    System.out.println("The distance between the square roots is " + String.format("%.3f",distance));
  
}

}

