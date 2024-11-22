import java.util.List;
import java.util.ArrayList;

public class CurrentCard
{
    private static List <Deck> currentCards;
    
        public CurrentCard()
        {
            currentCards = new ArrayList<>();
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