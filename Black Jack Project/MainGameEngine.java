import java.util.Scanner;

public class MainGameEngine {
    public static void main(String[] args) {
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

        while (Continue = true )
        {
            Player1.PlayerclearHands();
            Player2.PlayerclearHands();
            Player1.setStand(false);
            Player2.setStand(false);
            Dealer.DealerclearHands();
            Deck.resetDeck();

            Player1.hit();
            Player1.hit();
            
            Player2.hit();
            Player2.hit();

            dealer.Playcard();

            System.out.println("");
            System.out.println(player1Name + " how much do you want to bet?");
            int player1bet = scanner.nextInt();
            System.out.println("");
            while(player1bet > Player2.getTotalChip())
            {
                System.out.println("You do not have enough money. Please enter an amount less than or equal to your total chips.");
                player1bet = scanner.nextInt();
                System.out.println("");
                
            }

            System.out.println(player2Name + " how much do you want to bet?");
            int player2bet = scanner.nextInt();
            scanner.nextLine();
            while(player1bet > Player1.getTotalChip())
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
                        System.out.println("You have busted!");
                        Player1.setStand(true);
                        Player1.loseBet(player1bet);
                        System.out.println("");
                        System.out.println("Total Chip: " + Player1.getTotalChip());
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
                        System.out.println("You have busted!");
                        Player2.setStand(true);
                        Player2.loseBet(player1bet);
                        System.out.println("");
                        System.out.println("Total Chip: " + Player2.getTotalChip());
                    }

                }


            }


        }




    }
}
