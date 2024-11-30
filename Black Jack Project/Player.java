
import java.util.ArrayList;

public class Player {
    private String PlayerName;
    ArrayList <String> playerCards;
    private int PlayerHandValue;
    private boolean Stand; // Keeps track if the player has stood or not
    private int TotalChips;
    private boolean busted = false; // Keeps track if the player busted or not
    private int aceCount11 = 0; // keeps counts of the aces that are valued at 11 and could potentially be changed to a 1

    public Player(String thisPlayerName, int thisTotalChips)
    {
        PlayerName = thisPlayerName;
        TotalChips = thisTotalChips;
        playerCards = new ArrayList<String>();
        PlayerHandValue = 0;
        Stand = false;
        aceCount11 = 0;

    }

    public int getTotalChip(){
        return TotalChips;
    }

    public void setTotalChip(int bet) 
    {
        TotalChips -= bet;
    }

    public boolean getStand()
    {
        return Stand;
    }

    public boolean getBusted()
    {
        return busted;
    }

    public void setBusted(boolean status)
    {
        busted = status;
    }

    public int getPlayerHandValue()
    {
        return PlayerHandValue;
    }

    // In this method a card is drawn from the Deck and added to the Players hand. 
    //Then it is evaluated and the corresponding value of the card is added to the player's total hand value
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
        //If an Ace is drawn it is first checks if the ace is added with a value of 11, will the dealer bust?
        // If yes than the value of the ace is changed to 1
        //If no then the value of Ace stays as 11 and the variable aceCount11 is incremented (Explaination below)
        else if(card.contains("Ace"))
        {
            if (PlayerHandValue + 11 > 21 ) {
                PlayerHandValue += 1; // Convert Ace value from 11 to 1
            }
            else{
                PlayerHandValue += 11;
                aceCount11 ++;
            }
        }
        return card;
    }

    public void addCardtoHand(String card)
    {
        playerCards.add(card);
        CurrentCard.addCard(card);
    }

    public String getName()
    {
        return PlayerName;
    }

    public void PlayerclearHands()
    {
        playerCards.clear();
        CurrentCard.resetCard();
        PlayerHandValue = 0;
        aceCount11 = 0;
    }

    public void setStand(boolean status)
    {
        Stand = status;
    }

    
    public void winBet(int CurrentChips)
    {
        if(PlayerHandValue == 21)
        {
            TotalChips += 2 * CurrentChips; 
        }
        else{
            TotalChips += 1.5 * CurrentChips;
        }
    }

    public void loseBet(int CurrentChips)
    {
        TotalChips -= CurrentChips;
    }

    //This method is used to adject the vakue of the ace as the dealer continues to hit
    // The aceCount11 keeps counts of the aces that are valued at 11 and could potentially be changed to a 1
    // So a while loop is used to chnage the values of the aces to 11 until the Dealer hand value < 21 or there are no more aces valued at 11 
    public void aceCalculate(){
        while(PlayerHandValue > 21 && aceCount11 > 0){
            PlayerHandValue -= 10;
            aceCount11 --;
        }
    }

    


}
