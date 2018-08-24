import java.util.ArrayList;
import java.util.Random;

//Make deck
//Shuffle
//Draw
//Deck Count
//Get card
//

public class Deck {
    private ArrayList<Card> cards = new ArrayList<Card>();

    public Deck(){
        this.cards = new ArrayList<Card>();
    }

    public void createDeck(){
        for(Value value : Value.values()){
            for(Suit suit : Suit.values()){
                this.cards.add(new Card(suit,value)); // Adds to the cards array list
            }
        }
    }

    public String toString(){
        int i=1;
        String cardList = "";
        for(Card aCard : this.cards){
            cardList += "\n" + i + "-" + aCard.toString();
            i++;
        }
        return cardList;
    }

    public void shuffle(){
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        int index = 0;
        Random random = new Random();
        int originalSize = this.cards.size();
        for(int i=0; i<originalSize; i++){
            //int randomNum = rand.nextInt((max - min) + 1) + min; got this from stack overflow
            index = random.nextInt(((this.cards.size()-1) - 0) + 1) + 0;
            tempDeck.add(this.cards.get(index));
            // remove card from og deck
            this.cards.remove(index);
        }
        this.cards = tempDeck;
    }


}
