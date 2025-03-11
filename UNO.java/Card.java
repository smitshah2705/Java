public class Card {
    char num;
    char color;

    public Card (char num, char color)
    {
        this.num = num;
        this.color= color;
    }

    @Override
    public String toString()
    {
        return "" + color + " " + num;
    }
}