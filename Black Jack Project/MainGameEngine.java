import java.util.Scanner;

public class MainGameEngine {
    @SuppressWarnings("static-access")
    public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Initialise
        Deck deck = new Deck();
        deck.shuffle();
        CurrentCard CurrentCard = new CurrentCard();

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

        Player Player1 = new Player(player1Name, player1TotalChips);
        Player Player2 = new Player(player2Name, player2TotalChips);
        Dealer dealer = new Dealer();

        boolean Continue = true;

        while (Continue)
        {
            Player1.PlayerclearHands();
            Player2.PlayerclearHands();
            Player1.setStand(false);
            Player2.setStand(false);
            dealer.DealerclearHands();
            deck.resetDeck();
            deck.shuffle();

            Player1.hit();
            Player1.hit();
            
            Player2.hit();
            Player2.hit();

            dealer.Playcard();

            System.out.println("");
            System.out.println(player1Name + " how much do you want to bet?");
            int player1bet = scanner.nextInt();
            System.out.println("");
            while(player1bet > Player1.getTotalChip())
            {
                System.out.println("You do not have enough money. Please enter an amount less than or equal to your total chips.");
                player1bet = scanner.nextInt();
                System.out.println("");
                
            }

            System.out.println(player2Name + " how much do you want to bet?");
            int player2bet = scanner.nextInt();
            scanner.nextLine();
            while(player2bet > Player2.getTotalChip())
            {
                System.out.println("You do not have enough money. Please enter an amount less than or equal to your total chips.");
                player2bet = scanner.nextInt();
                System.out.println("");
                scanner.nextLine();
            }

            System.out.println("");
            System.out.println("Dealer's cards are " + dealer.dealerCards +".");
            System.out.println("The dealer's hand value = " + dealer.getDealerHandValue());
            System.out.println("");

            while(Player1.getStand() == false)
            {
                System.out.println("");
                System.out.println(player1Name + "'s Turn.");
                System.out.println("Current Bet: " + player1bet + " and Total Chip: " + Player1.getTotalChip());
                System.out.println(player1Name + "'s cards are " + Player1.playerCards + ".");
                System.out.println("Hand Value: " + Player1.getPlayerHandValue());
                System.out.println("Do you want to stand? Return true or false");
                boolean status = scanner.nextBoolean();
                scanner.nextLine();
                Player1.setStand(status);
                if(Player1.getStand() == false)
                {
                    Player1.hit();
                    if (Player1.getPlayerHandValue() > 21)
                    {
                        System.out.println("");
                        System.out.println("You have busted! and the lost the round");
                        Player1.setBusted(true);
                        Player1.setStand(true);
                        Player1.loseBet(player1bet);
                        System.out.println("");
                        System.out.println(player1Name + " has lost the bet! Total chips now: " + Player1.getTotalChip());
                    }

                }

            }

            while(Player2.getStand() == false)
            {
                System.out.println("");
                System.out.println(player2Name + "'s Turn.");
                System.out.println("Current Bet: " + player2bet + " and Total Chip: " + Player2.getTotalChip());
                System.out.println(player2Name + "'s cards are " + Player2.playerCards + ".");
                System.out.println("Hand Value: " + Player2.getPlayerHandValue());
                System.out.println("Do you want to stand? Return true or false");
                boolean status = scanner.nextBoolean();
                scanner.nextLine();
                Player2.setStand(status);
                if(Player2.getStand() == false)
                {
                    Player2.hit();
                    if (Player2.getPlayerHandValue() > 21)
                    {
                        System.out.println("");
                        System.out.println("You have busted! and lost the round.");
                        Player2.setBusted(true);
                        Player2.setStand(true);
                        Player2.loseBet(player1bet);
                        System.out.println("");
                        System.out.println(player2Name + " has lost the bet! Total chips now: " + Player2.getTotalChip());
                    }

                }


            }

            System.out.println("");
            System.out.println("Dealer's cards are " + dealer.dealerCards +".");
            System.out.println("The dealer's hand value = " + dealer.getDealerHandValue());
            System.out.println("");
            while (dealer.getDealerHandValue() < 17)
            {
                dealer.Playcard();
                System.out.println("Dealer hits!");
                System.out.println("Dealer's cards are " + dealer.dealerCards +".");
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

            resultscalculator(Player1, player1bet, Player2, player2bet, dealer);

            if (Player1.getTotalChip() <= 0)
            {
                System.out.println("");
                System.out.println("GAME OVER!");
                System.out.println("");
                System.out.println(player1Name + " has lost all his money!");
                System.out.println(player2Name + " has won!");
                Continue = false;
                break;
            }

            if (Player2.getTotalChip() <= 0)
            {
                System.out.println("");
                System.out.println("GAME OVER!");
                System.out.println("");
                System.out.println(player2Name + " has lost all his money!");
                System.out.println(player1Name + " has won!");
                Continue = false;
                break;
            }

            System.out.println("");
            System.out.println("The round is over.");
            System.out.println("");
            System.out.println(player1Name + " do you want to continue? yes or no.");
            String player1choice = scanner.nextLine();
            System.out.println("");
            System.out.println(player2Name + " do you want to continue? yes or no.");
            String player2choice = scanner.nextLine();
            scanner.nextLine();

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
                break;
            }









        }




    }
}

private static void resultscalculator(Player Player1, int player1bet, Player Player2, int player2bet, Dealer dealer)
{
   System.out.println("-----RESULTS-----");
    if (dealer.getDealerHandValue() > 21)
    {
        if (Player1.getBusted() == false)
        {
            Player1.winBet(player1bet);
            System.out.println(Player1.getName() + " wins against the dealer! Total Chips now: " + Player1.getTotalChip());

        }
        if(Player2.getBusted() == false)
        {
            Player2.winBet(player2bet);
            System.out.println(Player2.getName() + " wins against the dealer! Total Chips now: " + Player2.getTotalChip());
        }
    }

    if (dealer.getDealerHandValue() <= 21)
    {
        if (Player1.getBusted() == false)
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
                Player1.loseBet(player1bet);
                System.out.println(Player1.getName() + " loses against the dealer! Total Chips now: " + Player1.getTotalChip());
            }
        }

        if (Player2.getBusted() == false)
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
                Player2.loseBet(player2bet);
                System.out.println(Player2.getName() + " loses against the dealer! Total Chips now: " + Player2.getTotalChip());
            }
        }
    }
}
