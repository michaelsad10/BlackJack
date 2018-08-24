public class Card {
    private Value value;
    private Suit suit;

    public Card(Suit suit, Value value){
        this.value = value;
        this.suit = suit;
    }

    public String toString(){
        return this.suit.toString() + "-" + this.value.toString();
    }

    public Value getValue(){
        return this.value;
    }
}
