
import java.util.ArrayList;

public class CurrentCard
{
    private static ArrayList <String> currentCards;
    
        public CurrentCard()
        {
            currentCards = new ArrayList<String>();
        }
    
        public static void addCard(String card)
        {
            currentCards.add(card);
        }
    
        public static void resetCard()
        {
            currentCards.clear();
    }

    public boolean containsCard(String card)
    {
        return currentCards.contains(card);
    }
}