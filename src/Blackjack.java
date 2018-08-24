public class Blackjack {

    public static void main(String[] args){
        //Welcome Message
        System.out.println("Welcome to Blackjack!");
        String deck;
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();
        deck = playingDeck.toString();
        System.out.println(deck);

    }
}
