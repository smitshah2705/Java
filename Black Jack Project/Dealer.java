import java.util.List;
import java.util.ArrayList;


public class Dealer
{
    private List <Deck> dealerCards;
    private int DealerHandValue;

    public Dealer()
    {
        dealerCards = new ArrayList<>();
        DealerHandValue = 0;
    }

    public Deck Playcard(Deck deck)
    {
        addCardtoHand(Deck deck);
        return deck;
    }
    
    public void addCardtoHand(Deck deck)
    {
        dealerCards.add(deck);
        CurrentCard.addCard(deck);
    }

    // public void calculateHand()
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