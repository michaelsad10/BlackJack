public class Card {
    private Suit suit;
    private Value value;

    //Need to initialize the card
    public Card(Suit suit, Value value){
        this.suit = suit;
        this.value = value;
    }

    public String toString(){
        return this.value + "-" + this.suit;
    }

    public Value getValue(){
        return this.value;
    }
}
