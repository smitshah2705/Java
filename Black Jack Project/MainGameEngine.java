import java.util.Scanner;

public class MainGameEngine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Initialise
        Deck deck = new Deck();
        deck.shuffle();
        CurrentCard CurrentCard = new CurrentCard();

        System.out.println("WELCOME to the game of BLACKJACK!");
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
            Dealer.DealerclearHands();
            Deck.resetDeck();

            String player1FirstInitial = Player1.hit();
            String player1SecondInitial = Player1.hit();
            
            String player2FirstInitial = Player2.hit();
            String player2SecondInitial = Player2.hit();

            String dealerFirstInitial = dealer.Playcard();
            String dealerSecondInitial = dealer.Playcard();

            System.out.println("Dealer's cards are " + dealerFirstInitial + " and " + dealerSecondInitial + ".");
            System.out.println("The dealer's hand value = " + dealer.getDealerHandValue());


        }




    }
}
