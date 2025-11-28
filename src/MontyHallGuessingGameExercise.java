import java.util.Random;
import java.util.Scanner;

/**
 * Monty Hall Guessing Game.
 * The user chooses which door to open.
 * Monty opens one of the remaining doors with a goat behind.
 * The user decides whether to switch door or not.
 * Since Monty knows what is hidden behind the doors, there is a greater chance to win if the user switches door.
 */
public class MontyHallGuessingGameExercise {
    /**
     * Runs the guessing game
     * @param args not used
     */
    public static void main(String[] args)  { // Frågar användaren vilken dörr de väljer, åter Monty öppna en get-dörr, Frågar om spelaren vill byta, pAvgör om spelaren vann eller ej
        Scanner scanner = new Scanner(System.in);
        
        int[] door = {0, 0, 0};
        randomlyPlaceAPrize(door);

        System.out.println("Pick a door (0, 1, or 2):");
        int playerChoice = scanner.nextInt();
        int montysChoice = montySelectsAGoat(door, playerChoice);

        System.out.println("You picked door " + playerChoice + "!");
        openDoor(montysChoice, door);
        System.out.println("Monty asks: Do you want to switch door? (yes/no)");
        scanner.nextLine(); //Flush the newline character in the scanner

        boolean switchDoor = playerWantsToSwitchDoor(scanner);
        if (switchDoor) {
            playerChoice = switchDoor(playerChoice, montysChoice);
        }

        determineWinner(door, playerChoice);
        scanner.close();
    }

    private static int switchDoor(int playerChoice, int montysChoice) { //byter spelarens val till den enda dörr som varken är spelarens nuvarande val eller Montys öppnade dörr.
        for (int i = 0; i < 3; i++) {
            if (i != playerChoice && i != montysChoice) {
                return i;
            }
        }
        return playerChoice;
    }

    private static boolean playerWantsToSwitchDoor(Scanner scan) {//läser spelarens svar och returnerar om spelaren vill byta dörr eller inte.
        String choice = scan.nextLine().trim();
        if (choice.equals("yes")) { return true; }
        return false;
    }

    private static int montySelectsAGoat(int[] doors, int playerChoice) {//hittar en dörr som inte är spelarens val och som innehåller en get för Monty att öppna.
        Random random = new Random();
        int choice;

        while (true) {
            choice = random.nextInt(3);
            if (choice != playerChoice && doors[choice] == 0) {
                return choice;
            }
        }
    }

    private static void determineWinner(int[] doors, int playerChoice) {//avgör om spelaren valde dörren med bilen och skriver ut vinst eller förlust.
        if(doors[playerChoice] == 1) {
            System.out.println("You won!");
        }
        else {
            System.out.println("You got the goat! :(");
        }
    }


    public static void randomlyPlaceAPrize(int[] doors) { //Placerar bilen bakom en slumpmässig dörr. Sätter en av arrayens positioner till 1.
        Random random = new Random();
        int randomNumber = random.nextInt(doors.length);// det ska bara vara doors.lenght eftersom det är 3 arrayer inte 4.
        doors[randomNumber] = 1;
    }

    public static void openDoor(int n, int[] doors) {// skriver ut vilken dörr Monty öppnar och vad som finns bakom den.
        String contains = "";
        if (doors[n] == 0) { contains = "goat"; }
        else { contains = "prize"; };

        System.out.println("Monty opens door " + n + " and shows you a " + contains);
    }
}
