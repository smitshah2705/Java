
import java.util.ArrayList;

public class CurrentCard
{
    private static ArrayList <Deck> currentCards;
    
        public CurrentCard()
        {
            currentCards = new ArrayList<Deck>();
        }
    
        public static void addCard(Deck deck)
        {
            currentCards.add(deck);
        }
    
        public static void resetCard()
        {
            currentCards.clear();
    }

    public boolean containsCard(Deck deck)
    {
        return currentCards.contains(deck);
    }
}