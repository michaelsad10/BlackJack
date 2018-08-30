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
    public String firstCard(){ //This gets me the dealers first card
        int i=1;
        String cardList = "";
        Card aCard;
        cardList += "\n" + i + "-" + this.cards.get(0).toString();
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

    public void removeCard(int i){
        this.cards.remove(i);
    }

    public Card getCard(int i){
        return this.cards.get(i);
    }
    public void addCard(Card addCard){
        this.cards.add(addCard);
    }

    // Draws from the deck
    public void draw(Deck comingFrom){
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }
    public int getCardValue(){
        int totalValue = 0;
        Card card;
        Value value;
        for (int i=0; i<this.cards.size(); i++){
            card = this.cards.get(i); // This grabs 1 card from the array list at that index
            value = card.getValue(); // Since card has value and suit we need the value
            totalValue += value.getNumVal(); // we use that constructor from the enum class to get the actual number value
        }
        return totalValue;
    }
    public int getCount(){
        int count = 0;
        Card card;
        Value value;
        for (int i=0; i<this.cards.size(); i++){
            card = this.cards.get(i);
            value = card.getValue();
            if(value.getNumVal() >= 2 && value.getNumVal() <= 6){
                count++;
            }
            else if(value.getNumVal() >= 10){
                count--;
            }
            else{
                count += 0;
            }
        }
        return count;
    }
    public void clearHand(){
        cards.clear();
    }

    public boolean checkAce(){
        Card card;
        Value value;
        int rank = 0;
        card = cards.get(0);
        value = card.getValue();
        rank = value.getNumVal();
        if(rank == 1){
            return true;
        }
        else{
            return false;
        }

    }




}
