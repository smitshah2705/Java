import java.io.*; // provides classes and interfaces for system input and output such as: BufferedWriter, BufferedReader
import java.net.*; // provides necessary network operations such as: ServerSocket
import java.util.Scanner;

public class BlackjackServer {
    private static final int PORT = 12346; // Defines a constant PORT that specifies the port number on which the server will listen for the players
    private static ServerSocket serverSocket; // Provides function for the server to accept player request to join
    private static Socket player1Socket, player2Socket; // Socket objects that represent the connection between the player and the server
    private static BufferedWriter out1, out2; // Sending output messages to the 2 players
    private static BufferedReader in1, in2; // Recieving input messages for the 2 players

    // Handles the turn of a player, asking if they want to hit or stand.
    private static void Playerturn(Player player1, String playerName1, int playerBet1, Player player2, String playerName2, int playerBet2 ) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        try{
        while(!player1.getStand())
        {
            out1.write("\n");
            out1.flush();
            out2.write("\n");
            out2.flush();
            out1.write(playerName1 + "'s Turn.\n");
            out1.flush();
            out2.write(playerName1 + "'s Turn.\n");
            out2.flush();
            out1.write("Current Bet: " + playerBet1 + " and Total Chip: " + player1.getTotalChip() + "\n");
            out1.flush();
            out1.write(playerName1 + "'s cards are " + player1.playerCards + "\n");
            out1.flush();
            out1.write("Hand Value: " + player1.getPlayerHandValue() + "\n");
            out1.flush();
            out1.write("Do you want to hit? Return true or false\n");
            out1.flush();
            boolean status1 = scanner.nextBoolean();
            player1.setStand(!status1); // Checks if Player stand or hit
            if(!player1.getStand())
            {
                player1.hit();
                player1.aceCalculate(); // manages the value of aces
    
                if (player1.getPlayerHandValue() > 21)
                {
                    out1.write("\n");
                    out1.flush();
                    out1.write(playerName1 + ", you have busted! and lost the round.\n");
                    out1.flush();
                    out2.write("\n");
                    out2.flush();
                    out2.write(playerName1 + ", you have busted! and lost the round.\n");
                    out2.flush();
                    player1.setBusted(true); // Manages if the players has busted
                    player1.setStand(true); // End player loop
                    out1.write("\n");
                    out1.flush();
                    out1.write(playerName1 + " has lost the bet! Total chips now: " + player1.getTotalChip() + "\n");
                    out1.flush();
                    out2.write("\n");
                    out2.flush();
                    out2.write(playerName1 + " has lost the bet! Total chips now: " + player1.getTotalChip() + "\n");
                    out2.flush();
                }
            }

        while(!player2.getStand()){
            out1.write("\n");
            out1.flush();
            out2.write("\n");
            out2.flush();
            out2.flush();
            out1.write(playerName2 + "'s Turn.\n");
            out1.flush();
            out2.write(playerName2 + "'s Turn.\n");
            out2.flush();
            out2.flush();
            out2.write("Current Bet: " + playerBet2 + " and Total Chip: " + player2.getTotalChip() + "\n");
            out2.flush();
            out2.write(playerName2 + "'s cards are " + player2.playerCards + ".\n");
            out2.flush();
            out2.write("Hand Value: " + player2.getPlayerHandValue());
            out2.flush();
            out2.write("Do you want to hit? Return true or false\n");
            out2.flush();
            boolean status2 = scanner.nextBoolean();
            player2.setStand(!status2); // Checks if Player stand or hit
            if(!player2.getStand())
            {
                player2.hit();
                player2.aceCalculate(); // manages the value of aces
    
                if (player2.getPlayerHandValue() > 21)
                {
                    out1.write("\n");
                    out1.flush();
                    out1.write(playerName2 + ", you have busted! and lost the round.\n");
                    out1.flush();
                    out2.write("\n");
                    out2.flush();
                    out2.write(playerName2 + ", you have busted! and lost the round.\n");
                    out2.flush();
                    player2.setBusted(true); // Manages if the players has busted
                    player2.setStand(true); // End player loop
                    out1.write("\n");
                    out1.flush();
                    out1.write(playerName2 + " has lost the bet! Total chips now: " + player2.getTotalChip() + "\n");
                    out1.flush();
                    out2.write("\n");
                    out2.flush();
                    out2.write(playerName2 + " has lost the bet! Total chips now: " + player2.getTotalChip() + "/n");
                    out2.flush();
                }
            }
        }
    }
    }finally {
        scanner.close(); // Close the scanner to avoid resource leak
    }
    }

    // Calculates the results for both the players after both have finished their turns
    private static void resultscalculator(Player Player1, int player1bet, Player Player2, int player2bet, Dealer dealer) throws IOException {
        try {
            out1.write("-----RESULTS-----\n");
            out1.flush();
            out2.write("-----RESULTS-----\n");
            out2.flush();
            if (Player1.getBusted() == true) { // Checks if Player 1 busted
                out1.write(Player1.getName() + " loses against the dealer! Total Chips now: " + Player1.getTotalChip() + "\n");
                out1.flush();
                out2.write(Player1.getName() + " loses against the dealer! Total Chips now: " + Player1.getTotalChip() + "\n");
                out2.flush();
            }
    
            if (Player2.getBusted() == true) { // Checks if Player 2 busted
                out1.write(Player2.getName() + " loses against the dealer! Total Chips now: " + Player2.getTotalChip() + "\n");
                out1.flush();
                out2.write(Player2.getName() + " loses against the dealer! Total Chips now: " + Player2.getTotalChip() + "\n");
                out2.flush();
            }
    
            if (dealer.getDealerHandValue() > 21) { // Checks if dealer busted
                if (Player1.getBusted() == false) { // If player 1 didn't bust then they win
                    Player1.winBet(player1bet);
                    out1.write(Player1.getName() + " wins against the dealer! Total Chips now: " + Player1.getTotalChip() + "\n");
                    out1.flush();
                    out2.write(Player1.getName() + " wins against the dealer! Total Chips now: " + Player1.getTotalChip() + "\n");
                    out2.flush();

                }
                if (Player2.getBusted() == false) { // If player2 didn't bust they win
                    Player2.winBet(player2bet);
                    out1.write(Player2.getName() + " wins against the dealer! Total Chips now: " + Player2.getTotalChip() + "\n");
                    out1.flush();
                    out2.write(Player2.getName() + " wins against the dealer! Total Chips now: " + Player2.getTotalChip() + "\n");
                    out2.flush();
                }
            }
    
            if (dealer.getDealerHandValue() <= 21) { // If Dealer hasn't busted
                if (Player1.getBusted() == false) { // Compares with player 1
                    if (Player1.getPlayerHandValue() > dealer.getDealerHandValue()) {
                        Player1.winBet(player1bet);
                        out1.write(Player1.getName() + " wins against the dealer! Total Chips now: " + Player1.getTotalChip()+ "\n");
                        out1.flush();
                        out2.write(Player1.getName() + " wins against the dealer! Total Chips now: " + Player1.getTotalChip()+ "\n");
                        out2.flush();
                    }
    
                    if (Player1.getPlayerHandValue() == dealer.getDealerHandValue()) {
                        out1.write(Player1.getName() + " draws against the dealer! Total Chips now: " + Player1.getTotalChip()+ "\n");
                        out1.flush();
                        out2.write(Player1.getName() + " draws against the dealer! Total Chips now: " + Player1.getTotalChip()+ "\n");
                        out2.flush();
                    }
    
                    if (Player1.getPlayerHandValue() < dealer.getDealerHandValue()) {
                        out1.write(Player1.getName() + " loses against the dealer! Total Chips now: " + Player1.getTotalChip()+ "\n");
                        out1.flush();
                        out2.write(Player1.getName() + " loses against the dealer! Total Chips now: " + Player1.getTotalChip()+ "\n");
                        out2.flush();
                    }
                }
    
                if (Player2.getBusted() == false) { // Compared with player 2
                    if (Player2.getPlayerHandValue() > dealer.getDealerHandValue()) {
                        Player2.winBet(player2bet);
                        out1.write(Player2.getName() + " wins against the dealer! Total Chips now: " + Player2.getTotalChip()+ "\n");
                        out1.flush();
                        out2.write(Player2.getName() + " wins against the dealer! Total Chips now: " + Player2.getTotalChip()+ "\n");
                        out2.flush();
                    }
    
                    if (Player2.getPlayerHandValue() == dealer.getDealerHandValue()) {
                        out1.write(Player2.getName() + " draws against the dealer! Total Chips now: " + Player2.getTotalChip()+ "\n");
                        out1.flush();
                        out2.write(Player2.getName() + " draws against the dealer! Total Chips now: " + Player2.getTotalChip()+ "\n");
                        out2.flush();
                    }
    
                    if (Player2.getPlayerHandValue() < dealer.getDealerHandValue()) {
                        out1.write(Player2.getName() + " loses against the dealer! Total Chips now: " + Player2.getTotalChip()+ "\n");
                        out1.flush();
                        out2.write(Player2.getName() + " loses against the dealer! Total Chips now: " + Player2.getTotalChip()+ "\n");
                        out2.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close or release resources, if any (e.g., closing streams, scanner, etc.)
            // For now, no specific cleanup is necessary, so you could leave this empty or add your cleanup logic.
        }
    }
    
    // Handles dealer's turn
    private static void Dealerturn(Dealer dealer) {
        try {
            while (dealer.getDealerHandValue() < 17) {
                dealer.Playcard();
                dealer.aceCalculate();
                out1.write("Dealer hits!\n");
                out1.flush();
                out1.write("Dealer's cards are " + Dealer.dealerCards + ".\n");
                out1.flush();
                out1.write("The dealer's hand value = " + dealer.getDealerHandValue()+ "\n");
                out1.flush();
                out1.write("\n");
                out1.flush();
    
                out2.write("Dealer hits!\n");
                out2.flush();
                out2.write("Dealer's cards are " + Dealer.dealerCards + ".\n");
                out2.flush();
                out2.write("The dealer's hand value = " + dealer.getDealerHandValue()+ "\n");
                out2.flush();
                out2.write("\n");
                out2.flush();
            }
    
            if (dealer.getDealerHandValue() > 21) {
                out1.write("Dealer has busted!\n");
                out1.flush();
                out1.write("\n");
                out1.flush();
    
                out2.write("Dealer has busted!\n");
                out2.flush();
                out2.write("\n");
                out2.flush();
            } else {
                out1.write("Dealer stands with his current hands!\n");
                out1.flush();
                out1.write("\n");
                out1.flush();
    
                out2.write("Dealer stands with his current hands!\n");
                out2.flush();
                out2.write("\n");
                out2.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cleanup code (if needed)
            // For now, you may not need any cleanup logic
        }
    }
public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT); // Creates a server socket 12345 for the players to join
            System.out.println("Server started... Waiting for players to join...\n");

            // Accept two players
            player1Socket = serverSocket.accept(); // The code will pause over here and wait till a player is connected
            System.out.println("Player 1 connected.\n");
            out1 = new BufferedWriter(new OutputStreamWriter(player1Socket.getOutputStream())); // After Player 1 is connected, the BufferedWriter is initialized for communication with Player 1
            in1 = new BufferedReader(new InputStreamReader(player1Socket.getInputStream())); // the Buffered Reader is initialized for communication with Player 1

            player2Socket = serverSocket.accept(); // The code will pause over here and wait till a player is connected
            System.out.println("Player 2 connected.\n");
            out2 = new BufferedWriter(new OutputStreamWriter(player2Socket.getOutputStream())); // After Player 2 is connected, the BufferedWriter is initialized for communication with Player 2
            in2 = new BufferedReader(new InputStreamReader(player2Socket.getInputStream())); //the Buffered Reader is initialized for communication with Player 2
            
            // Start the game
            startGame();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Handles Game logic. Compied from MainGameEngine which is an offline version
    private static void startGame() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            Deck deck = new Deck();
            Deck.shuffle();
            CurrentCard currentCard = new CurrentCard();

            out1.write("Welcome to BLACKJACK\n");
            out1.flush();
            out2.write("Welcome to BLACKJACK\n");
            out2.flush();
            
            //Recieve Player 1 information
            out1.write("Please enter your name:\n");
            out1.flush();
            
            String player1Name = in1.readLine(); // Read the name
            
            out1.write("Please enter your starting chips amount:\n");
            out1.flush();
            
            int player1TotalChips = 0;
            try {
                player1TotalChips = Integer.parseInt(in1.readLine()); // Read chips amount
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
            
            //Recieve Player 2 information
            out2.write("Please enter your name:\n");
            out2.flush();

            String player2Name = in1.readLine(); // Read the name

            out2.write("Please enter your starting chips amount:\n");
            out2.flush();

            int player2TotalChips = 0;
            try {
                player2TotalChips = Integer.parseInt(in2.readLine()); // Read chips amount
            } catch (NumberFormatException e) {
            System.out.println(e);
        }

            out1.write("Welcome " + player1Name + "!" + " You have been dealt your cards.\n");
            out1.flush();
            out2.write("Welcome " + player2Name + "!" + " You have been dealt your cards.\n");
            out2.flush();

            // Initialise. Create objects for Player1, Player2 and dealer
            Player Player1 = new Player(player1Name, player1TotalChips);
            Player Player2 = new Player(player2Name, player2TotalChips);
            Dealer dealer = new Dealer();

            boolean Continue = true;

            while (Continue)
            {
                //Game setup after each round
                Player1.PlayerclearHands();
                Player2.PlayerclearHands();
                Player1.setStand(false);
                Player2.setStand(false);
                Dealer.DealerclearHands();
                Deck.resetDeck();
                Deck.shuffle();

                // Deal initial cards
                Player1.hit();
                Player1.hit();
                Player2.hit();
                Player2.hit();
                dealer.Playcard();

                    // Manage Player 1 betting
                out1.write(player1Name + " how much do you want to bet?\n");
                out1.flush();
                int player1bet = Integer.parseInt(in1.readLine());
                while(player1bet > Player1.getTotalChip())
                {
                    out1.write(player1Name + ", you do not have enough money. Please enter an amount less than or equal to your total chips.\n");
                    out1.flush();
                    player1bet = Integer.parseInt(in1.readLine());
                    out1.write("\n");
                    out1.flush();
                    
                }
                Player1.loseBet(player1bet); 

                // Dealer initial card is shown for Player 1
                out1.write("\n");
                out1.flush();
                out1.write("Dealer's cards are " + Dealer.dealerCards +".\n");
                out1.flush();
                out1.write("The dealer's hand value = " + dealer.getDealerHandValue()+ "\n");
                out1.flush();
                out1.write("\n");
                out1.flush();
                
                // Manage Player 2 betting
                out2.write(player2Name + " how much do you want to bet?\n");
                out2.flush();
                int player2bet = Integer.parseInt(in2.readLine());
                while(player2bet > Player2.getTotalChip())
                {
                    out2.write(player2Name + ", you do not have enough money. Please enter an amount less than or equal to your total chips.\n");
                    out2.flush();
                    player2bet = Integer.parseInt(in2.readLine());
                    out2.write("\n");
                    out2.flush();
                }
                Player2.loseBet(player2bet);

                // Dealer initial card is shown for Player 2
                out2.write("\n");
                out2.flush();
                out2.write("Dealer's cards are " + Dealer.dealerCards +".\n");
                out2.flush();
                out2.write("The dealer's hand value = " + dealer.getDealerHandValue()+ "\n");
                out2.flush();
                out2.write("\n");
                out2.flush();

                // Players' turn
                Playerturn(Player1, player1Name, player1bet, Player2, player2Name, player2bet);

                // Shows the dealers first card again
                out1.write("\n");
                out1.flush();
                out1.write("Dealer's cards are " + Dealer.dealerCards +".\n");
                out1.flush();
                out1.write("The dealer's hand value = " + dealer.getDealerHandValue()+ "\n");
                out1.flush();
                out1.write("\n");
                out1.flush();

                out2.write("\n");
                out2.flush();
                out2.write("Dealer's cards are " + Dealer.dealerCards +".\n");
                out2.flush();
                out2.write("The dealer's hand value = " + dealer.getDealerHandValue()+ "\n");
                out2.flush();
                out2.write("\n");
                out2.flush();
                
                // Dealer's turn
                Dealerturn(dealer);

                resultscalculator(Player1, player1bet, Player2, player2bet, dealer); // Calculates Results

                if (Player1.getTotalChip() <= 0) // Checks if Player 1 has lost all his money
                {
                    out1.write("\n");
                    out1.flush();
                    out1.write("GAME OVER!\n");
                    out1.flush();
                    out1.write("\n");
                    out1.flush();
                    out1.write(player1Name + " has lost all his money!\n");
                    out1.flush();
                    out1.write(player2Name + " has won!\n");
                    out1.flush();

                    out2.write("\n");
                    out2.flush();
                    out2.write("GAME OVER!\n");
                    out2.flush();
                    out2.write("\n");
                    out2.flush();
                    out2.write(player1Name + " has lost all his money!\n");
                    out2.flush();
                    out2.write(player2Name + " has won!\n");
                    out2.flush();
                    Continue = false;
                }

                if (Player2.getTotalChip() <= 0) // Checks if Player 2 has lost all his money
                {
                    out1.write("\n");
                    out1.flush();
                    out1.write("GAME OVER!\n");
                    out1.flush();
                    out1.write("\n");
                    out1.flush();
                    out1.write(player2Name + " has lost all his money!\n");
                    out1.flush();
                    out1.write(player1Name + " has won!\n");
                    out1.flush();
                    Continue = false;

                    out2.write("\n");
                    out2.flush();
                    out2.write("GAME OVER!\n");
                    out2.flush();
                    out2.write("\n");
                    out2.flush();
                    out2.write(player2Name + " has lost all his money!\n");
                    out2.flush();
                    out2.write(player1Name + " has won!\n");
                    out2.flush();
                    Continue = false;
                }

                // If no players have lost all their money then next ask each player if they wan to continue playing
                if (Continue) {
                    out1.write("\n");
                    out1.flush();
                    out1.write("The round is over.\n");
                    out1.flush();
                    out1.write("\n");
                    out1.flush();

                    out2.write("\n");
                    out2.flush();
                    out2.write("The round is over.\n");
                    out2.flush();
                    out2.write("\n");
                    out2.flush();

                    out1.write(player1Name + " do you want to continue? yes or no.\n");
                    out1.flush();
                    String player1choice = in1.readLine();
                    
                    out2.write(player2Name + " do you want to continue? yes or no.\n");
                    out2.flush();
                    String player2choice = in2.readLine();
                    
                    // If any of the players say no, then the player ends. If both say yes, then the next round begins
                    if (player1choice.equals("no"))
                    {
                        out1.write("\n");
                        out1.flush();
                        out1.write("GAME OVER!\n");
                        out1.flush();
                        out1.write("\n");
                        out1.flush();
                        out1.write(player1Name + " has dicontinued.\n");
                        out1.flush();

                        out2.write("\n");
                        out2.flush();
                        out2.write("GAME OVER!\n");
                        out2.flush();
                        out2.write("\n");
                        out2.flush();
                        out2.write(player1Name + " has dicontinued.\n");
                        out2.flush();
                        Continue = false;
                        break;
                    }
        
                    if (player2choice.equals("no"))
                    {
                        out1.write("\n");
                        out1.flush();
                        out1.write("GAME OVER!\n");
                        out1.flush();
                        out1.write("\n");
                        out1.flush();
                        out1.write(player2Name + " has dicontinued.\n");
                        out1.flush();
                        Continue = false;

                        out2.write("\n");
                        out2.flush();
                        out2.write("GAME OVER!\n");
                        out2.flush();
                        out2.write("\n");
                        out2.flush();
                        out2.write(player2Name + " has dicontinued.\n");
                        out2.flush();
                        Continue = false;
                    }
                }


            }
            
            player1Socket.close();
            player2Socket.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            scanner.close(); // Close the scanner to avoid resource leak
        }
    }
}

    
