import java.util.ArrayList;

public class Blackjack {
    public static void main(String[] args){
        ArrayList<Card> dealer = new ArrayList<Card>();
        ArrayList<Card> player = new ArrayList<Card>();
        Deck playingDeck = new Deck();
        playingDeck.createDeck();
        playingDeck.shuffle();
        String deckOutput = playingDeck.toString();
        System.out.println(deckOutput);
    }
}
