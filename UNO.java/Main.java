public class Main
{
    public static void main(String[] args) {
        Deck d = new Deck();
        Player a = new Player();

        System.out.println(a);
        System.out.println(d);

        a.addCard(d.getTop());

        System.out.println(a);
        System.out.println(d);
        
        Card c = a.removeCard();

        System.out.println(a);

        
    }
}