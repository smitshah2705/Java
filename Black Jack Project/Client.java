import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost"; // Address of the server. 
    //"Local host" = the client will connect to a server running on the same machine
    private static final int PORT = 12346; // Port to connect to the server
    private static Socket socket; // an obect of Socket is created that connects to the server
    private static BufferedReader in; // To read messages from the server
    private static BufferedWriter out; // To send messages to the server
    private static Scanner scanner; // For user input

    public static void main(String[] args) {
        try {
            socket = new Socket(SERVER_ADDRESS, PORT); // Connect to the server
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // For reading server messages
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // For sending messages to the server
            scanner = new Scanner(System.in); // For getting user input
            
            // Read initial welcome messages and instructions from the server
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);
                
                if (message.contains("Please enter your name:")) {
                    String playerName = scanner.nextLine();
                    out.write(playerName + "\n");
                    out.flush();
                }

                if (message.contains("Please enter your starting chips amount:")) {
                    int startingChips = Integer.parseInt(scanner.nextLine());
                    out.write(startingChips + "\n");
                    out.flush();
                }

                if (message.contains("how much do you want to bet?")) {
                    int betAmount = Integer.parseInt(scanner.nextLine());
                    out.write(betAmount + "\n");
                    out.flush();
                }

                if (message.contains("Do you want to hit? Return true or false")) {
                    boolean hit = Boolean.parseBoolean(scanner.nextLine());
                    out.write(hit + "\n");
                    out.flush();
                }

                if (message.contains("do you want to continue? yes or no")) {
                    String choice = scanner.nextLine();
                    out.write(choice + "\n");
                    out.flush();
                    if (choice.equals("no")) {
                        break;
                    }
                }
            }

            // Close the socket and input/output streams
            socket.close();
            in.close();
            out.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}