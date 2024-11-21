import java.util.List;
import java.util.ArrayList;

public class CurrentCard
{
    private List <String> currentCards;

    public CurrentCard()
    {
        currentCards = new ArrayList<>();
    }

    public void addCard(Deck deck)
    {
        currentCards.add(deck);
    }

    public void resetCard()
    {
        currentCards.clear()
    }

    public boolean containsCard(Deck deck)
    {
        return currentCards.contains(deck);
    }
}