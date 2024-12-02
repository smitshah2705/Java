import java.io.*; // provides classes and interfaces for system input and output such as: PrintWriter, BufferedReader
import java.net.*; // provides necessary network operations such as: ServerSocket
import java.util.Scanner;

public class BlackjackServer {
    private static final int PORT = 12345; // Defines a constant PORT that specifies the port number on which the server will listen for the players
    private static ServerSocket serverSocket; // Provides function for the server to accept player request to join
    private static Socket player1Socket, player2Socket; // Socket objects that represent the connection between the player and the server
    private static PrintWriter out1, out2; // Sending output messages to the 2 players
    private static BufferedReader in1, in2; // Recieving input messages for the 2 players

    // Handles the turn of a player, asking if they want to hit or stand.
    private static void Playerturn(Player player, String playerName, int playerBet  )
    {
        Scanner scanner = new Scanner(System.in);
        while(player.getStand() == false)
        {
            out1.println("");
            out2.println("");
            out1.println(playerName + "'s Turn.");
            out2.println(playerName + "'s Turn.");
            out1.println("Current Bet: " + playerBet + " and Total Chip: " + player.getTotalChip());
            out1.println(playerName + "'s cards are " + player.playerCards + ".");
            out1.println("Hand Value: " + player.getPlayerHandValue());
            out1.println("Do you want to hit? Return true or false");

            out2.println("Current Bet: " + playerBet + " and Total Chip: " + player.getTotalChip());
            out2.println(playerName + "'s cards are " + player.playerCards + ".");
            out2.println("Hand Value: " + player.getPlayerHandValue());
            out2.println("Do you want to hit? Return true or false");
            boolean status = scanner.nextBoolean();
            player.setStand(!status); // Checks if Player stand or hit
            if(player.getStand() == false)
            {
                player.hit();
                player.aceCalculate(); // manages the value of aces
                out1.println(playerName + "'s cards are " + player.playerCards + ".");
                out1.println("Hand Value: " + player.getPlayerHandValue());

                out2.println(playerName + "'s cards are " + player.playerCards + ".");
                out2.println("Hand Value: " + player.getPlayerHandValue());
    
                if (player.getPlayerHandValue() > 21)
                {
                    out1.println("");
                    out1.println(playerName + ", you have busted! and lost the round.");
                    out2.println("");
                    out2.println(playerName + ", you have busted! and lost the round.");
                    player.setBusted(true); // Manages if the players has busted
                    player.setStand(true); // End player loop
                    out1.println("");
                    out1.println(playerName + " has lost the bet! Total chips now: " + player.getTotalChip());
                    out2.println("");
                    out2.println(playerName + " has lost the bet! Total chips now: " + player.getTotalChip());
                }
            }
        }
    }

    // Calculates the results for both the players after both have finished their turns
    private static void resultscalculator(Player Player1, int player1bet, Player Player2, int player2bet, Dealer dealer)
{
   out1.println("-----RESULTS-----");
   out2.println("-----RESULTS-----");
    if(Player1.getBusted() == true) //Checks if Player 1 busted
    {
        out1.println(Player1.getName() + " loses against the dealer! Total Chips now: " + Player1.getTotalChip());
        out2.println(Player1.getName() + " loses against the dealer! Total Chips now: " + Player1.getTotalChip());
    }

    if(Player2.getBusted() == true) //Checks if Player 2 busted
    {
        out1.println(Player2.getName() + " loses against the dealer! Total Chips now: " + Player2.getTotalChip());
        out2.println(Player2.getName() + " loses against the dealer! Total Chips now: " + Player2.getTotalChip());
    }
   
   if (dealer.getDealerHandValue() > 21) // Checks if dealer busted
    {
        if (Player1.getBusted() == false) // If player 1 didnt bust then they win
        {
            Player1.winBet(player1bet);
            out1.println(Player1.getName() + " wins against the dealer! Total Chips now: " + Player1.getTotalChip());
            out2.println(Player1.getName() + " wins against the dealer! Total Chips now: " + Player1.getTotalChip());
        }
        if(Player2.getBusted() == false) // If player2 didnt bust they win
        {
            Player2.winBet(player2bet);
            out1.println(Player2.getName() + " wins against the dealer! Total Chips now: " + Player2.getTotalChip());
            out2.println(Player2.getName() + " wins against the dealer! Total Chips now: " + Player2.getTotalChip());
        }
    }

    if (dealer.getDealerHandValue() <= 21) // If Dealer hasnt busted then the dealers hand value is calculated with the 2 other players
    {
        if (Player1.getBusted() == false) // Compares with player 1
        {
            if(Player1.getPlayerHandValue() > dealer.getDealerHandValue())
            {
                Player1.winBet(player1bet);
                out1.println(Player1.getName() + " wins against the dealer! Total Chips now: " + Player1.getTotalChip());
                out2.println(Player1.getName() + " wins against the dealer! Total Chips now: " + Player1.getTotalChip());
            }

            if(Player1.getPlayerHandValue() == dealer.getDealerHandValue())
            {
                out1.println(Player1.getName() + " draws against the dealer! Total Chips now: " + Player1.getTotalChip());
                out2.println(Player1.getName() + " draws against the dealer! Total Chips now: " + Player1.getTotalChip());
            }

            if(Player1.getPlayerHandValue() < dealer.getDealerHandValue())
            {
                out1.println(Player1.getName() + " loses against the dealer! Total Chips now: " + Player1.getTotalChip());
                out2.println(Player1.getName() + " loses against the dealer! Total Chips now: " + Player1.getTotalChip());
            }
        }

        if (Player2.getBusted() == false) // Compared with player 2
        {
            if(Player2.getPlayerHandValue() > dealer.getDealerHandValue())
            {
                Player2.winBet(player2bet);
                out1.println(Player2.getName() + " wins against the dealer! Total Chips now: " + Player2.getTotalChip());
                out2.println(Player2.getName() + " wins against the dealer! Total Chips now: " + Player2.getTotalChip());
            }

            if(Player2.getPlayerHandValue() == dealer.getDealerHandValue())
            {
                out1.println(Player2.getName() + " draws against the dealer! Total Chips now: " + Player2.getTotalChip());
                out2.println(Player2.getName() + " draws against the dealer! Total Chips now: " + Player2.getTotalChip());
            }

            if(Player2.getPlayerHandValue() < dealer.getDealerHandValue())
            {
                out1.println(Player2.getName() + " loses against the dealer! Total Chips now: " + Player2.getTotalChip());
                out2.println(Player2.getName() + " loses against the dealer! Total Chips now: " + Player2.getTotalChip());
            }
        }
    }
}
    
    // Handles dealer's turn
    private static void Dealerturn(Dealer dealer)
    {
        while (dealer.getDealerHandValue() < 17)
        {
            dealer.Playcard();
            dealer.aceCalculate();
            out1.println("Dealer hits!");
            out1.println("Dealer's cards are " + Dealer.dealerCards +".");
            out1.println("The dealer's hand value = " + dealer.getDealerHandValue());
            out1.println("");

            out2.println("Dealer hits!");
            out2.println("Dealer's cards are " + Dealer.dealerCards +".");
            out2.println("The dealer's hand value = " + dealer.getDealerHandValue());
            out2.println("");
        }

        if(dealer.getDealerHandValue() > 21)
        {
            out1.println("Dealer has busted!");
            out1.println("");

            out2.println("Dealer has busted!");
            out2.println("");
        }
        else{
            out1.println("Dealer stands with his current hands !");
            out1.println("");

            out2.println("Dealer stands with his current hands !");
            out2.println("");
        }
    }
public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT); // Creates a server socket 12345 for the players to join
            System.out.println("Server started... Waiting for players to join...");

            // Accept two players
            player1Socket = serverSocket.accept(); // The code will pause over here and wait till a player is connected
            System.out.println("Player 1 connected.");
            out1 = new PrintWriter(player1Socket.getOutputStream(), true); // After Player 1 is connected, the PrintWriter is initialized for communication with Player 1
            in1 = new BufferedReader(new InputStreamReader(player1Socket.getInputStream())); // the Buffered Reader is initialized for communication with Player 1

            player2Socket = serverSocket.accept(); // The code will pause over here and wait till a player is connected
            System.out.println("Player 2 connected.");
            out2 = new PrintWriter(player2Socket.getOutputStream(), true); // After Player 2 is connected, the PrintWriter is initialized for communication with Player 2
            in2 = new BufferedReader(new InputStreamReader(player2Socket.getInputStream())); //the Buffered Reader is initialized for communication with Player 2

            // Start the game
            startGame();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Handles Game logic. Compied from MainGameEngine which is an offline version
    private static void startGame() {
        try {
            Scanner scanner = new Scanner(System.in);
            Deck deck = new Deck();
            Deck.shuffle();
            CurrentCard currentCard = new CurrentCard();

            out1.println("Welcome to BLACKJACK");
            out2.println("Welcome to BLACKJACK");
            
            //Recieve Player 1 information
            out1.println("Please enter your name:");
            System.out.println("HJKHJKHKJHKJ");
            
            String player1Name = in1.readLine();
            out1.println("Please enter your starting chips amount:");
            int player1TotalChips = Integer.parseInt(in1.readLine());

            //Recieve Player 2 information
            out2.println("Please enter your name:");
            String player2Name = in2.readLine();
            out2.println("Please enter your starting chips amount:");
            int player2TotalChips = Integer.parseInt(in2.readLine());

            out1.println("Welcome " + player1Name + "!" + " You have been dealt your cards.");
            out2.println("Welcome " + player2Name + "!" + " You have been dealt your cards.");

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
                out1.println("");
                out1.println(player1Name + " how much do you want to bet?");
                int player1bet = Integer.parseInt(in1.readLine());
                out1.println("");
                while(player1bet > Player1.getTotalChip())
                {
                    out1.println(player1Name + ", you do not have enough money. Please enter an amount less than or equal to your total chips.");
                    player1bet = Integer.parseInt(in1.readLine());
                    out1.println("");
                    
                }
                Player1.loseBet(player1bet); 

                // Dealer initial card is shown for Player 1
                out1.println("");
                out1.println("Dealer's cards are " + Dealer.dealerCards +".");
                out1.println("The dealer's hand value = " + dealer.getDealerHandValue());
                out1.println("");

                // Player 1's turn
                Playerturn(Player1, player1Name, player1bet);
                
                // Manage Player 2 betting
                out2.println(player2Name + " how much do you want to bet?");
                int player2bet = Integer.parseInt(in2.readLine());
                while(player2bet > Player2.getTotalChip())
                {
                    out2.println(player2Name + ", you do not have enough money. Please enter an amount less than or equal to your total chips.");
                    player2bet = Integer.parseInt(in2.readLine());
                    out2.println("");
                }
                Player2.loseBet(player2bet);

                // Dealer initial card is shown for Player 2
                out2.println("");
                out2.println("Dealer's cards are " + Dealer.dealerCards +".");
                out2.println("The dealer's hand value = " + dealer.getDealerHandValue());
                out2.println("");

                // Player 2's turn
                Playerturn(Player2, player2Name, player2bet);

                // Shows the dealers first card again
                out1.println("");
                out1.println("Dealer's cards are " + Dealer.dealerCards +".");
                out1.println("The dealer's hand value = " + dealer.getDealerHandValue());
                out1.println("");

                out2.println("");
                out2.println("Dealer's cards are " + Dealer.dealerCards +".");
                out2.println("The dealer's hand value = " + dealer.getDealerHandValue());
                out2.println("");
                
                // Dealer's turn
                Dealerturn(dealer);

                resultscalculator(Player1, player1bet, Player2, player2bet, dealer); // Calculates Results

                if (Player1.getTotalChip() <= 0) // Checks if Player 1 has lost all his money
                {
                    out1.println("");
                    out1.println("GAME OVER!");
                    out1.println("");
                    out1.println(player1Name + " has lost all his money!");
                    out1.println(player2Name + " has won!");

                    out2.println("");
                    out2.println("GAME OVER!");
                    out2.println("");
                    out2.println(player1Name + " has lost all his money!");
                    out2.println(player2Name + " has won!");
                    Continue = false;
                }

                if (Player2.getTotalChip() <= 0) // Checks if Player 2 has lost all his money
                {
                    out1.println("");
                    out1.println("GAME OVER!");
                    out1.println("");
                    out1.println(player2Name + " has lost all his money!");
                    out1.println(player1Name + " has won!");
                    Continue = false;

                    out2.println("");
                    out2.println("GAME OVER!");
                    out2.println("");
                    out2.println(player2Name + " has lost all his money!");
                    out2.println(player1Name + " has won!");
                    Continue = false;
                }

                // If no players have lost all their money then next ask each player if they wan to continue playing
                if (Continue) {
                    out1.println("");
                    out1.println("The round is over.");
                    out1.println("");

                    out2.println("");
                    out2.println("The round is over.");
                    out2.println("");

                    out1.println(player1Name + " do you want to continue? yes or no.");
                    String player1choice = in1.readLine();
                    
                    out2.println(player2Name + " do you want to continue? yes or no.");
                    String player2choice = in2.readLine();
                    
                    // If any of the players say no, then the player ends. If both say yes, then the next round begins
                    if (player1choice.equals("no"))
                    {
                        out1.println("");
                        out1.println("GAME OVER!");
                        out1.println("");
                        out1.println(player1Name + " has dicontinued.");

                        out2.println("");
                        out2.println("GAME OVER!");
                        out2.println("");
                        out2.println(player1Name + " has dicontinued.");
                        Continue = false;
                        break;
                    }
        
                    if (player2choice.equals("no"))
                    {
                        out1.println("");
                        out1.println("GAME OVER!");
                        out1.println("");
                        out1.println(player2Name + " has dicontinued.");
                        Continue = false;

                        out2.println("");
                        out2.println("GAME OVER!");
                        out2.println("");
                        out2.println(player2Name + " has dicontinued.");
                        Continue = false;
                    }
                }


            }
            
            player1Socket.close();
            player2Socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    
