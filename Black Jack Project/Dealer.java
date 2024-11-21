import java.util.List;
import java.util.ArrayList;

public class Dealer
{
    private List <String> dealerCards;
    private int HandValue;

    public Dealer()
    {
        dealerCards = new ArrayList<>();
        HandValue = 0;
    }

    public void addCardtoHand(Deck deck)
    {
        dealerCards.add(deck);
        CurrentCard.addCard(deck);
    }

    public void calculateHand()
    {
        for (int i =0; i<dealerCards.size()-1; i++)
        {
            HandValue += dealerCards.get(i);
        }
    }

}