import javax.security.sasl.SaslClient;
import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args){
        Deck playingDeck = new Deck();
        Deck player = new Deck();
        Deck dealer = new Deck();
        Scanner userInput = new Scanner(System.in);
        String playerHand, dealerHand;
        double playerMoney = 1000.00;
        playingDeck.createDeck(); // Makes deck
        playingDeck.shuffle(); //Shuffles deck
        while(playerMoney > 0){
            System.out.println("You have $" + playerMoney + " how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if(playerBet>playerMoney){
                System.out.println("You don't have that kind of money pal");
                break;
            }
            System.out.println("You bet $" + playerBet);
            System.out.println("Good luck... dealing the cards");
            int playerCardValue = 0;
            int dealerCardValue = 0;
            int count = 0;
            player.draw(playingDeck);
            dealer.draw(playingDeck);
            player.draw(playingDeck);
            dealer.draw(playingDeck);
            playerHand = player.toString();
            dealerHand = dealer.toString();
            System.out.println("Your cards: " + playerHand);
            System.out.println("Dealers cards: " + dealerHand);
            System.out.println("---------------");
            playerCardValue = player.getCardValue();
            System.out.println("Hand value: " + playerCardValue);
            dealerCardValue = dealer.getCardValue();
            System.out.println("Dealer value: " + dealerCardValue);
            System.out.println("Hit 1, Double 2, Stay 3");
            int move = userInput.nextInt();
            while(move!=3 && playerCardValue<21){
                switch(move){
                    case 1: player.draw(playingDeck);
                            playerHand = player.toString();
                            System.out.println(playerHand);
                            playerCardValue = player.getCardValue();
                            if(playerCardValue>21){
                                System.out.println("Bust... you lose...");
                                break;
                            }
                            break;
                    case 2: player.draw(playingDeck);
                            playerCardValue = player.getCardValue();
                            playerMoney-= playerBet;
                            playerBet*=2;
                            if(playerCardValue>21){
                                System.out.println("Bust... you lose...");
                                break;
                            }
                            break;
                }
            }



        }


    }
}

//mainDeck = playingDeck.toString();
//        System.out.println(mainDeck);
//        player.draw(playingDeck);
//        playerDeck = player.toString();
//        System.out.println(playerDeck);
//        mainDeck = playingDeck.toString();
//        System.out.println(mainDeck);
//        System.out.println(originalDeck);
//        System.out.println("-----------------");
