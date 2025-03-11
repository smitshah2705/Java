import java.util.LinkedList;
import java.util.Scanner;

public class Player {
    private LinkedList<Card> hand;

    public Player() {
        hand = new LinkedList<Card>();
    }

    public void addCard(Card c)
    {
        hand.add(c);
    }

    public boolean canPlay(Card topCard)
    {
        for (Card c : hand)
        {
            if (c.color == topCard.color) return true;
            if (c.num   == topCard.num) return true;
            if (c.color == 'X') return true;
        }
        return false;
    }

    public Card removeCard()
    {
        System.out.println("Choose your card to play");
        for (int i = 0; i < hand.size(); i++)
        {
            System.out.println("" + i + " : " + hand.get(i));
        }
        Scanner in = new Scanner(System.in);
        int pos = in.nextInt();
        return hand.remove(pos);

    }

    public String toString()
    {
        if (hand.size() == 0) return "()";
        String output = "(";
        for (Card c: hand)
        {
            output += c + "),(";
        }
        return output.substring(0, output.length()-2);
    }
}
