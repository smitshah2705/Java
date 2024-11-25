import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private String[] Suits = {"Diamond", "Heart", "Clubs", "Spades"};
    private String[] Rank = {"2", "3", "4", "5", "6", "7", "8", "9", "10", 
    "Jack", "Queen", "King", "Ace"};
    private ArrayList<String> cards;

    public Deck()
    {
        cards = new ArrayList <String> ();
        for (int i = 0;i < Suits.length;i++)
        {
            for (int y = 0;i < Rank.length;y++)
            {
                cards.add(Rank[y] + " of " + Suits[i]);
            }
        }

    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    public String Drawcard()
    {
        if(cards.isEmpty())
        {
            return "No more cards left in the deck";
        }
        return cards.remove(0);
    }

}
