import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input marks for each subject
        System.out.print("Enter marks for Subject 1 (out of 100): ");
        int subject1Marks = scanner.nextInt();

        System.out.print("Enter marks for Subject 2 (out of 100): ");
        int subject2Marks = scanner.nextInt();

        System.out.print("Enter marks for Subject 3 (out of 100): ");
        int subject3Marks = scanner.nextInt();

        // Calculate total marks
        int totalMarks = subject1Marks + subject2Marks + subject3Marks;

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / 3;

        // Determine grade based on average percentage
        char grade = determineGrade(averagePercentage);

        // Display results
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Final Grade: " + grade);

        scanner.close();
    }

    // Function to determine the grade based on the average percentage
    private static char determineGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}
