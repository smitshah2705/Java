import java.util.LinkedList;
import java.util.Collections;

public class Deck {
    private LinkedList<Card> cards;

    public Deck()
    {
        cards = new LinkedList<Card>();

        String numbers = "1234567890TRS";
        String colours = "RGYB";

        for (char color : colours.toCharArray())
        {
            for (char number : numbers.toCharArray())
            {
                cards.add(new Card(number, color));
            }
        }

        for (int i = 0; i < 4; i++)
        {
            cards.add(new Card('C', 'X'));
            cards.add(new Card('F', 'X'));
        }
    }

    public String toString()
    {
        String output = "(";

        for (Card c : cards)
        {
            output += c + "),(";
        }
        return output.substring(0, output.length() - 2);
    }
    
    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    public Card getTop()
    {
        return cards.remove(0);
    }
}
