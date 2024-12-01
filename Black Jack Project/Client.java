import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    // Will allow the player to interact with the server
    //This is from the player side.
    private static final String SERVER_HOST = "localhost";  // Server address (localhost for local testing)
    private static final int SERVER_PORT = 12345;  // Server port number
    private static Socket clientSocket;  // Socket to connect to the server
    private static PrintWriter serverOut;  // send messages to the server
    private static BufferedReader serverIn;  // receive messages from the server

    public static void main(String[] args) {
        try {
            // Connect to the server
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            System.out.println("Connected to the server.");

            // Create input and output streams for communication
            serverOut = new PrintWriter(clientSocket.getOutputStream(), true);
            serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Start communication with the server
            interactWithServer();

        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    private static void interactWithServer() {
        try (Scanner scanner = new Scanner(System.in)) { // Ensure scanner is properly closed
            String serverMessage;

            // Loop to interact with the server, continuously read and process messages sent by the server
            while ((serverMessage = serverIn.readLine()) != null) {
                System.out.println(serverMessage);

                // If the server sends "GAME OVER" or contains " discontinued", stop
                if (serverMessage.contains("GAME OVER") || serverMessage.contains("dicontinued") ) {
                    break;
                }

                // Handle user input for bets or other actions
                if (serverMessage.toLowerCase().contains("enter your bet")) {
                    System.out.print("Enter your bet: ");
                    int bet = scanner.nextInt();
                    scanner.nextLine(); // Clear the newline character
                    serverOut.println(bet);
                } else {
                    System.out.print("> "); // General input prompt
                    String userInput = scanner.nextLine();
                    serverOut.println(userInput);
                }
            }


            // Close the connection once the game ends
            clientSocket.close();
            System.out.println("Disconnected from the server.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



