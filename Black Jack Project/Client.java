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
        try {
            Scanner inputScanner = new Scanner(System.in);
            String serverMessage;

            // Receive and display initial welcome message from the server
            serverMessage = serverIn.readLine();
            System.out.println(serverMessage);

            // Continue communication as long as the game is not over
            while ((serverMessage = serverIn.readLine()) != null) {
                System.out.println(serverMessage);

                // Read the player's input and send it to the server
                String playerInput = inputScanner.nextLine();
                serverOut.println(playerInput);

                // Exit condition: If the server sends "GAME OVER", stop
                if (serverMessage.contains("GAME OVER")) {
                    break;
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



