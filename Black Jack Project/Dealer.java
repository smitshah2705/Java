
import java.util.ArrayList;


public class Dealer
{
    private ArrayList <String> dealerCards;
    private int DealerHandValue;

    public Dealer()
    {
        dealerCards = new ArrayList<String>();
        DealerHandValue = 0;
    }

    public String Playcard()
    {
        String card = Deck.Drawcard();
        addCardtoHand(card);
        if(card.contains("King") || card.contains("Queen") || card.contains("Jack") || card.contains("10"))
        {
            DealerHandValue += 10;
        }
        else if(card.contains("9"))
        {
            DealerHandValue += 9;
        }
        else if(card.contains("8"))
        {
            DealerHandValue += 8;
        }
        else if(card.contains("7"))
        {
            DealerHandValue += 7;
        }
        else if(card.contains("6"))
        {
            DealerHandValue += 6;
        }
        else if(card.contains("5"))
        {
            DealerHandValue += 5;
        }
        else if(card.contains("4"))
        {
            DealerHandValue += 4;
        }
        else if(card.contains("3"))
        {
            DealerHandValue += 3;
        }
        else if(card.contains("2"))
        {
            DealerHandValue += 2;
        }
        else if(card.contains("Ace"))
        {
            DealerHandValue += 11;
        }
        return card;
    }
    
    public void addCardtoHand(String card)
    {
        dealerCards.add(card);
        CurrentCard.addCard(card);
    }

    public void DealerclearHands()
    {
        dealerCards.clear();
        CurrentCard.resetCard();
        DealerHandValue = 0;
    }

}