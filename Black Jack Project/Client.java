import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost"; // Server address (can be IP or "localhost" if running locally)
    private static final int PORT = 12345; // Server's port

    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static Scanner scanner;

    public static void main(String[] args) {
        try {
            // Connect to the server
            socket = new Socket(SERVER_ADDRESS, PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scanner = new Scanner(System.in);


            // Receive welcome message from server
            String message = in.readLine();
            System.out.println(message);

            // Handle player information and interaction
            String name = promptForName();
            int totalChips = promptForChips();

            out.println(name); // Send player name
            out.println(totalChips); // Send player chips

            // Handle the rounds of the game
            while (true) {
                message = in.readLine();
                System.out.println(message); // Receive and display server messages

                // if (message.contains("how much do you want to bet?")) {
                //     int bet = promptForBet();
                //     out.println(bet); // Send bet to server
                // }

                // // message = in.readLine();
                // // System.out.println(message); // Display cards and hand value

                // if (message.contains("Do you want to hit?")) {
                //     boolean hit = promptForAction();
                //     out.println(hit); // Send action (true = hit, false = stand)
                // }

                // // Wait for dealer's turn and results
                // // message = in.readLine();
                // // System.out.println(message);

                // if (message.contains("GAME OVER")) {
                //     break; // End game if it's over
                // }

                // String continueGame = promptToContinue();
                // out.println(continueGame); // Send response to continue or not
            }

            // Close the connection
            socket.close();
            System.out.println("Game Over. Goodbye!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String promptForName() {
        return scanner.nextLine();
    }

    private static int promptForChips() {
        return scanner.nextInt();
    }

    private static int promptForBet() {
        return scanner.nextInt();
    }

    private static boolean promptForAction() {
        return scanner.nextBoolean();
    }

    private static String promptToContinue() {
        return scanner.next();
    }


}