
import java.util.ArrayList;


public class Dealer
{
    private ArrayList <Deck> dealerCards;
    private int DealerHandValue;

    public Dealer()
    {
        dealerCards = new ArrayList<Deck>();
        DealerHandValue = 0;
    }

    public Deck Playcard(Deck deck)
    {
        addCardtoHand(Deck deck);
        return deck;
        DealerCalculateHand()
    }
    
    public void addCardtoHand(Deck deck)
    {
        dealerCards.add(deck);
        CurrentCard.addCard(deck);
    }

    // public void DealerCalculateHand()
    // {
    //     for (int i =0; i<dealerCards.size()-1; i++)
    //     {
    //         HandValue += dealerCards.get(i);
    //     }
    // }

    public void DealerclearHands()
    {
        dealerCards.clear();
        CurrentCard.resetCard();
        DealerHandValue = 0;
    }

}