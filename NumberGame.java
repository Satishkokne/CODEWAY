import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Specify the range
        int lowerBound = 1;
        int upperBound = 100;

        // Number of attempts allowed
        int maxAttempts = 5;

        // Number of rounds played
        int roundsPlayed = 0;

        // Scorekeeping
        int totalAttempts = 0;
        int totalRoundsWon = 0;

        do {
            int randomNumber = generateRandomNumber(lowerBound, upperBound);
            int attempts = 0;

            System.out.println("Round " + (roundsPlayed + 1) + ": Guess the number between " + lowerBound + " and " + upperBound);

            do {
                // Prompt user for guess
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                // Compare user's guess with the generated number
                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    totalRoundsWon++;
                    break;
                } else if (userGuess < randomNumber) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }

                attempts++;
            } while (attempts < maxAttempts);

            totalAttempts += attempts;
            roundsPlayed++;

            // Ask if the user wants to play again
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();

            if (!playAgain.equals("yes")) {
                break;
            }
        } while (true);

        // Display final statistics
        System.out.println("Game Over!");
        System.out.println("Total Rounds Played: " + roundsPlayed);
        System.out.println("Total Rounds Won: " + totalRoundsWon);
        System.out.println("Average Attempts per Round: " + (totalRoundsWon > 0 ? (double) totalAttempts / totalRoundsWon : 0));

        // Close the scanner
        scanner.close();
    }

    // Method to generate a random number within a specified range
    private static int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }
}
