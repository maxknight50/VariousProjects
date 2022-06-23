package assignment2;

import java.util.Scanner;

/**
 * @author Maxine Knight 
 * Reads student scores, gets the best score, assigns grades based on a scheme
 */
public class AssignGrades {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // User enters the number of students  
        System.out.print("Enter the number of students: ");
        int numStudents = input.nextInt();

        // Array is created with the size equal to the number of students
        int studentScore[] = new int[numStudents];
        // User enters in the scores for each student
        System.out.print("Enter " + numStudents + " scores: ");

        // The array is populated with the next number, then max is determined
        int max = 0;
        for (int i = 0; i < numStudents; i++) {
            studentScore[i] = input.nextInt();
            if (studentScore[i] > max) { // If the next value is greater than the max
                max = studentScore[i]; // Reassigns the value of max 
            }
        }
        // The grade is determined for each student based on a subtraction from the max score
        char grade = 'a';
        for (int i = 0; i < numStudents; i++) {
            if (studentScore[i] >= max - 10) {
                grade = 'A';
            } else if (studentScore[i] >= max - 20) {
                grade = 'B';
            } else if (studentScore[i] >= max - 30) {
                grade = 'C';
            } else if (studentScore[i] >= max - 40) {
                grade = 'D';
            } else {
                grade = 'F';
            }
            System.out.println("Student " + i + " score is " + studentScore[i] + " and grade is " + grade);
        }
    }
}
