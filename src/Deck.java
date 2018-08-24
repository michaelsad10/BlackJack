import java.util.ArrayList;
import java.util.Random;

public class Deck {


    // instance vars
    private ArrayList<Card> cards;

    //construct
    public Deck(){
        this.cards = new ArrayList<Card>();
    }

    public void createFullDeck(){
        // Generate cards
        for(Suit cardSuit : Suit.values()){
            for(Value cardValue: Value.values()){
                this.cards.add(new Card(cardSuit, cardValue));

            }
        }
    }

    public void shuffle(){
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();
        for(int i=0; i<originalSize; i++){
            //Generate random index rand.nextInt((max-min) + 1 + min;
            randomCardIndex = random.nextInt((this.cards.size()-1 - 0)+1)+0;
            tempDeck.add(this.cards.get(randomCardIndex));
            //remove from original deck
            this.cards.remove(randomCardIndex);
        }
        this.cards = tempDeck;
    }

    public String toString(){
        String cardListOutput = "";
        int i = 0;
        for(Card aCard : this.cards){
            cardListOutput += "\n" + i + "-" + aCard.toString();
            i++;
        }
        return cardListOutput;
    }
}
