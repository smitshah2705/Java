
import java.util.ArrayList;


public class Dealer
{
    static ArrayList <String> dealerCards; 
                private static int DealerHandValue; 
                private static int aceCount11; // keeps counts of the aces that are valued at 11 and could potentially be changed to a 1
                                    
                                        public Dealer()
                                        {
                                            dealerCards = new ArrayList<String>();
                                            DealerHandValue = 0;
                                            aceCount11 = 0;
                                        }
                                    
                                        // In this method a card is drawn from the Deck and added to the dealers card. 
                                        //Then it is evaluated and the corresponding value of the card is added to the Dealer's total hand value
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
                                            //If an Ace is drawn it is first checks if the ace is added with a value of 11, will the dealer bust?
                                            // If yes than the value of the ace is changed to 1
                                            //If no then the value of Ace stays as 11 and the variable aceCount11 is incremented (Explaination below)
                                            else if(card.contains("Ace"))
                                            {
                                                if (DealerHandValue + 11 > 21 ) {
                                                    DealerHandValue += 1; // Convert Ace value from 11 to 1
                                                }
                                                else{
                                                    DealerHandValue += 11;
                                                    aceCount11 ++;
                                                }
                                            }   
                                            return card;
                                        }
                                        
                                        public void addCardtoHand(String card)
                                        {
                                            dealerCards.add(card);
                                            CurrentCard.addCard(card);
                                        }
                                    
                                        public static void DealerclearHands()
                                        {
                                            dealerCards.clear();
                                        CurrentCard.resetCard();
                                        DealerHandValue = 0;
                                        aceCount11 = 0;

                                        }   

                public int getDealerHandValue()
                {
                    return DealerHandValue;
                }

                //This method is used to adject the vakue of the ace as the dealer continues to hit
                // The aceCount11 keeps counts of the aces that are valued at 11 and could potentially be changed to a 1
                // So a while loop is used to chnage the values of the aces to 11 until the Dealer hand value < 21 or there are no more aces valued at 11 
                public void aceCalculate(){
                    while(DealerHandValue >= 17 && aceCount11 > 0){
                        DealerHandValue -= 10;
                        aceCount11 --;
                    }
                }

}