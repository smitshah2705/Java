
import java.util.ArrayList;

public class Player {
    private String PlayerName;
    ArrayList <String> playerCards;
    private int PlayerHandValue;
    private boolean Stand;
    private int TotalChips;
    private int CurrentChips;

    public Player(String thisPlayerName, int thisTotalChips)
    {
        PlayerName = thisPlayerName;
        TotalChips = thisTotalChips;
        playerCards = new ArrayList<String>();
        PlayerHandValue = 0;
        Stand = false;
        CurrentChips = 0;

    }

    public int getTotalChip(){
        return TotalChips;
    }

    public boolean getStand()
    {
        return Stand;
    }

    public int getPlayerHandValue()
    {
        return PlayerHandValue;
    }

    public String hit()
    {
        String card = Deck.Drawcard();
        addCardtoHand(card);
        if(card.contains("King") || card.contains("Queen") || card.contains("Jack") || card.contains("10"))
        {
            PlayerHandValue += 10;
        }
        else if(card.contains("9"))
        {
            PlayerHandValue += 9;
        }
        else if(card.contains("8"))
        {
            PlayerHandValue += 8;
        }
        else if(card.contains("7"))
        {
            PlayerHandValue += 7;
        }
        else if(card.contains("6"))
        {
            PlayerHandValue += 6;
        }
        else if(card.contains("5"))
        {
            PlayerHandValue += 5;
        }
        else if(card.contains("4"))
        {
            PlayerHandValue += 4;
        }
        else if(card.contains("3"))
        {
            PlayerHandValue += 3;
        }
        else if(card.contains("2"))
        {
            PlayerHandValue += 2;
        }
        else if(card.contains("Ace"))
        {
            PlayerHandValue += 11;
        }
        return card;
    }

    public void addCardtoHand(String card)
    {
        playerCards.add(card);
        CurrentCard.addCard(card);
    }

    public void placeBet(int amount)
    {
        if (amount > TotalChips)
        {
            System.err.println("You don't have enough money");
            return;
        }
        TotalChips -= amount;
        CurrentChips = amount;
    }

    public void PlayerclearHands()
    {
        playerCards.clear();
        CurrentCard.resetCard();
        PlayerHandValue = 0;
    }

    public void setStand(boolean status)
    {
        Stand = status;
    }

    public void winBet()
    {
        if(PlayerHandValue == 21)
        {
            TotalChips += 2 * CurrentChips;
        }
        else{
            TotalChips += 1.5 * CurrentChips;
        }
    }

    public void loseBet()
    {
        if (TotalChips - CurrentChips <= 0)
        {
            System.out.println("You are Bankrupt. You lose!");
        }
        else
        {
            TotalChips -= CurrentChips;
        }
    }


    


}
