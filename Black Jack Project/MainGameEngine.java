import java.util.Scanner;

public class MainGameEngine {

    // Method to calculate and print the results of the round
    private static void resultscalculator(Player Player1, int player1bet, Player Player2, int player2bet, Dealer dealer)
{
   System.out.println("-----RESULTS-----");
    if(Player1.getBusted() == true) //Checks if Player 1 busted
    {
        System.out.println(Player1.getName() + " loses against the dealer! Total Chips now: " + Player1.getTotalChip());
    }

    if(Player2.getBusted() == true) //Checks if Player 2 busted
    {
        System.out.println(Player2.getName() + " loses against the dealer! Total Chips now: " + Player2.getTotalChip());
    }
   
   if (dealer.getDealerHandValue() > 21) // Checks if dealer busted
    {
        if (Player1.getBusted() == false) // If player 1 didnt bust then they win
        {
            Player1.winBet(player1bet);
            System.out.println(Player1.getName() + " wins against the dealer! Total Chips now: " + Player1.getTotalChip());

        }
        if(Player2.getBusted() == false) // If player2 didnt bust they win
        {
            Player2.winBet(player2bet);
            System.out.println(Player2.getName() + " wins against the dealer! Total Chips now: " + Player2.getTotalChip());
        }
    }

    if (dealer.getDealerHandValue() <= 21) // If Dealer hasnt busted then the dealers hand value is calculated with the 2 other players
    {
        if (Player1.getBusted() == false) // Compares with player 1
        {
            if(Player1.getPlayerHandValue() > dealer.getDealerHandValue())
            {
                Player1.winBet(player1bet);
                System.out.println(Player1.getName() + " wins against the dealer! Total Chips now: " + Player1.getTotalChip());
            }

            if(Player1.getPlayerHandValue() == dealer.getDealerHandValue())
            {
                System.out.println(Player1.getName() + " draws against the dealer! Total Chips now: " + Player1.getTotalChip());
            }

            if(Player1.getPlayerHandValue() < dealer.getDealerHandValue())
            {
                System.out.println(Player1.getName() + " loses against the dealer! Total Chips now: " + Player1.getTotalChip());
            }
        }

        if (Player2.getBusted() == false) // Compared with player 2
        {
            if(Player2.getPlayerHandValue() > dealer.getDealerHandValue())
            {
                Player2.winBet(player2bet);
                System.out.println(Player2.getName() + " wins against the dealer! Total Chips now: " + Player2.getTotalChip());
            }

            if(Player2.getPlayerHandValue() == dealer.getDealerHandValue())
            {
                System.out.println(Player2.getName() + " draws against the dealer! Total Chips now: " + Player2.getTotalChip());
            }

            if(Player2.getPlayerHandValue() < dealer.getDealerHandValue())
            {
                System.out.println(Player2.getName() + " loses against the dealer! Total Chips now: " + Player2.getTotalChip());
            }
        }
    }
    
}
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Initialize the deck, shuffle, and current card instances
        Deck deck = new Deck();
        Deck.shuffle();
        CurrentCard currentCard = new CurrentCard();
        
        // Welcome message and player details input
        System.out.println("WELCOME to the game of BLACKJACK!");
        System.out.println("");
        System.out.println("Player 1, please enter your name: ");
        String player1Name = scanner.nextLine();
        System.out.println("Player 1, please enter the amount of chips you will play with: ");
        int player1TotalChips = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("");

        System.out.println("Player 2, please enter your name: ");
        String player2Name = scanner.nextLine();
        System.out.println("Player 2, please enter the amount of chips you will play with: ");
        int player2TotalChips = scanner.nextInt();
        scanner.nextLine();

        // Create Player objects with the details from input
        Player Player1 = new Player(player1Name, player1TotalChips);
        Player Player2 = new Player(player2Name, player2TotalChips);
        Dealer dealer = new Dealer();

        // Flag that manages the game loop
        boolean Continue = true;

        while (Continue)
        {
            // Clear hands and reset hand values before each round
            // Resets their Stand status
            // Resets and shuffles deck
            Player1.PlayerclearHands();
            Player2.PlayerclearHands();
            Player1.setStand(false);
            Player2.setStand(false);
            Dealer.DealerclearHands();
            Deck.resetDeck();
            Deck.shuffle();

            // Deal initial cards to players and dealer
            Player1.hit();
            Player1.hit();
            Player2.hit();
            Player2.hit();
            dealer.Playcard();

            // Manage Player 1 betting
            System.out.println("");
            System.out.println(player1Name + " how much do you want to bet?");
            int player1bet = scanner.nextInt();
            System.out.println("");
            while(player1bet > Player1.getTotalChip())
            {
                System.out.println(player1Name + ", you do not have enough money. Please enter an amount less than or equal to your total chips.");
                player1bet = scanner.nextInt();
                System.out.println("");
                
            }
            Player1.loseBet(player1bet); 
            
            // Manage Player 2 betting
            System.out.println(player2Name + " how much do you want to bet?");
            int player2bet = scanner.nextInt();
            scanner.nextLine();
            while(player2bet > Player2.getTotalChip())
            {
                System.out.println(player2Name + ", you do not have enough money. Please enter an amount less than or equal to your total chips.");
                player2bet = scanner.nextInt();
                System.out.println("");
                scanner.nextLine();
            }
            Player2.loseBet(player2bet);

            // Dealer initial card is shown
            System.out.println("");
            System.out.println("Dealer's cards are " + Dealer.dealerCards +".");
            System.out.println("The dealer's hand value = " + dealer.getDealerHandValue());
            System.out.println("");

            // Player 1's turn
            while(Player1.getStand() == false)
            {
                System.out.println("");
                System.out.println(player1Name + "'s Turn.");
                System.out.println("Current Bet: " + player1bet + " and Total Chip: " + Player1.getTotalChip());
                System.out.println(player1Name + "'s cards are " + Player1.playerCards + ".");
                System.out.println("Hand Value: " + Player1.getPlayerHandValue());
                System.out.println(player1Name + " do you want to hit? Return true or false");
                boolean status = scanner.nextBoolean();
                scanner.nextLine();
                Player1.setStand(!status); // Checks if Player stand or hit
                if(Player1.getStand() == false)
                {
                    Player1.hit();
                    Player1.aceCalculate(); // manages the value of aces
                    if (Player1.getPlayerHandValue() > 21) // Manages if the players has busted
                    {
                        System.out.println("");
                        System.out.println(player1Name + ", you have busted! and the lost the round");
                        Player1.setBusted(true); 
                        Player1.setStand(true); // End player loop
                        System.out.println("");
                        System.out.println(player1Name + " has lost the bet! Total chips now: " + Player1.getTotalChip());
                    }

                }

            }

            // Player 2's turn
            while(Player2.getStand() == false)
            {
                System.out.println("");
                System.out.println(player2Name + "'s Turn.");
                System.out.println("Current Bet: " + player2bet + " and Total Chip: " + Player2.getTotalChip());
                System.out.println(player2Name + "'s cards are " + Player2.playerCards + ".");
                System.out.println("Hand Value: " + Player2.getPlayerHandValue());
                System.out.println("Do you want to hit? Return true or false");
                boolean status = scanner.nextBoolean();
                scanner.nextLine();
                Player2.setStand(!status); // Checks if Player stand or hit
                if(Player2.getStand() == false)
                {
                    Player2.hit();
                    Player2.aceCalculate(); // manages the value of aces
                    System.out.println(player2Name + "'s cards are " + Player2.playerCards + ".");
                    System.out.println("Hand Value: " + Player2.getPlayerHandValue());

                    if (Player2.getPlayerHandValue() > 21)
                    {
                        System.out.println("");
                        System.out.println(player2Name + ", you have busted! and lost the round.");
                        Player2.setBusted(true); // Manages if the players has busted
                        Player2.setStand(true); // End player loop
                        System.out.println("");
                        System.out.println(player2Name + " has lost the bet! Total chips now: " + Player2.getTotalChip());
                    }

                }


            }
            // Shows the dealers first card again
            System.out.println("");
            System.out.println("Dealer's cards are " + Dealer.dealerCards +".");
            System.out.println("The dealer's hand value = " + dealer.getDealerHandValue());
            System.out.println("");
            
            // Dealer's turn
            while (dealer.getDealerHandValue() < 17)
            {
                dealer.Playcard();
                dealer.aceCalculate();
                System.out.println("Dealer hits!");
                System.out.println("Dealer's cards are " + Dealer.dealerCards +".");
                System.out.println("The dealer's hand value = " + dealer.getDealerHandValue());
                System.out.println("");
            }

            if(dealer.getDealerHandValue() > 21)
            {
                System.out.println("Dealer has busted!");
                System.out.println("");
            }
            else{
                System.out.println("Dealer stands with his current hands !");
                System.out.println("");
            }

            resultscalculator(Player1, player1bet, Player2, player2bet, dealer); // Calculates Results

            if (Player1.getTotalChip() <= 0) // Checks if Player 1 has lost all his money
            {
                System.out.println("");
                System.out.println("GAME OVER!");
                System.out.println("");
                System.out.println(player1Name + " has lost all his money!");
                System.out.println(player2Name + " has won!");
                Continue = false;
            }

            if (Player2.getTotalChip() <= 0) // Checks if Player 2 has lost all his money
            {
                System.out.println("");
                System.out.println("GAME OVER!");
                System.out.println("");
                System.out.println(player2Name + " has lost all his money!");
                System.out.println(player1Name + " has won!");
                Continue = false;
            }

            // If no players have lost all their money then next ask each player if they wan to continue playing
            if (Continue) {
                System.out.println("");
                System.out.println("The round is over.");
                System.out.println("");
                System.out.println(player1Name + " do you want to continue? yes or no.");
                String player1choice = scanner.nextLine();
                System.out.println("");
                System.out.println(player2Name + " do you want to continue? yes or no.");
                String player2choice = scanner.nextLine();
                
                // If an of the players say no, then the player ends. If both say yes, then the next round begins
                if (player1choice.equals("no"))
                {
                    System.out.println("");
                    System.out.println("GAME OVER!");
                    System.out.println("");
                    System.out.println(player1Name + " has dicontinued.");
                    Continue = false;
                    break;
                }
    
                if (player2choice.equals("no"))
                {
                    System.out.println("");
                    System.out.println("GAME OVER!");
                    System.out.println("");
                    System.out.println(player2Name + " has dicontinued.");
                    Continue = false;
                }
            }
           

        }



    }
}

