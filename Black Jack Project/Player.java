import java.util.List;
import java.util.ArrayList;

public class Player {
    private String PlayerName;
    private List <Deck> playerCards;
    private int PlayerHandValue;
    private boolean Stand;
    private int TotalChips;
    private int CurrentChips;

    public Player(String thisPlayerName, int thisTotalChips)
    {
        PlayerName = thisPlayerName;
        TotalChips = thisTotalChips;
        playerCards = new ArrayList<>();
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

    


}
