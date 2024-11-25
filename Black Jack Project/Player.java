
import java.util.ArrayList;

public class Player {
    private String PlayerName;
    private ArrayList <Deck> playerCards;
    private int PlayerHandValue;
    private boolean Stand;
    private int TotalChips;
    private int CurrentChips;

    public Player(String thisPlayerName, int thisTotalChips)
    {
        PlayerName = thisPlayerName;
        TotalChips = thisTotalChips;
        playerCards = new ArrayList<Deck>();
        PlayerHandValue = 0;
        Stand = false;
        CurrentChips = 0;

    }

    public Deck hit(Deck deck)
    {
        addCardtoHand(Deck deck);
        return deck;
    }

    public void addCardtoHand(Deck deck)
    {
        playerCards.add(deck);
        CurrentCard.addCard(deck);
    }

    public void placeBet(int amount)
    {
        if (amount > TotalChips)
        {
            System.err.println("You don't have enough money");
            return;
        }
        TotalChips -= amount;
        CurrentChips = amount;
    }

    public void PlayerclearHands()
    {
        playerCards.clear();
        CurrentCard.resetCard();
        PlayerHandValue = 0;
    }

    public void stand()
    {
        PlayerCalculateHand();
    }

    public void winBet()
    {
        if(PlayerHandValue == 21)
        {
            TotalChips += 2 * CurrentChips;
        }
        else{
            TotalChips += 1.5 * CurrentChips;
        }
    }

    public void loseBet()
    {
        if (TotalChips - CurrentChips <= 0)
        {
            System.out.println("You are Bankrupt. You lose!");
        }
        else
        {
            TotalChips -= CurrentChips;
        }
    }


    


}
