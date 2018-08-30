import java.util.Scanner;

public class Game {
    private Deck playingDeck = new Deck();
    private Deck player = new Deck();
    private Deck dealer = new Deck();
    private int deckSize = 0;
    private Scanner userInput = new Scanner(System.in);
    private String playerHand, dealerHand,dealerHandHidden;
    char yn;
    private double playerMoney = 1000.00;
    private double insuranceBet = 0;
    private double playerCardValue = 0;
    private double dealerCardValue = 0;
    private double playerCardValueWithAce = 0;
    private double dealerCardValueWithAce = 0;
    private double dealerCardValueHidden = 0;
    private double playerBet = 0;
    private boolean dealerHas21 = false;
    private boolean keepGoing = true;
    private boolean playerDouble = false;
    private boolean playerBust = false;
    private boolean dealerKeepGoing = true;
    private boolean dealerBust = false;

    public void Run(){
        playingDeck.createDeck();
        playingDeck.shuffle();
        while(playerMoney > 0) {
             deckSize = playingDeck.deckSize();
             if(deckSize <= 10){
                 //We need to shuffle the deck
                 System.out.println("Shuffling the cards...");
                 playingDeck.clearHand();
                 playingDeck.createDeck();
                 playingDeck.shuffle();
             }
            betting();
            dealingCards();
            showHand();
            dealerHas21 = checkHandforAce();
            while(!dealerHas21){
               calls();
               dealerMoves();
               findWinner();
               break;
            }

        }
    }

    public void dealingCards(){
        player.draw(playingDeck);
        dealer.draw(playingDeck);
        player.draw(playingDeck);
        dealer.draw(playingDeck);
    }
    public void betting() {
            boolean bettingAmt = true;
            while(bettingAmt){
            System.out.println("You have $" + playerMoney + " how much would you like to bet?");
            playerBet = userInput.nextDouble();
            if (playerBet > playerMoney || playerBet < 0) {
                System.out.println("You don't have that kind of money pal");
                bettingAmt = true;
            }
            else if(playerBet < 0){
                System.out.println("Hmmm you think you can bet negative money try a different casino pal");
                bettingAmt = true;
            }
            else {
                System.out.println("You bet $" + playerBet);
                System.out.println("Good luck... dealing cards");
                playerMoney-=playerBet;
                bettingAmt = false;
            }

        }
    }

    public void showHand() {
        playerHand = player.toString();
        dealerHandHidden = dealer.firstCard();
        System.out.println("Your cards: " + playerHand);
        System.out.println("Dealers cards: " + dealerHandHidden); // Hides the dealers second card
        System.out.println("---------------");
        playerCardValue = player.getCardValue();
        System.out.println("Hand value: " + playerCardValue);
        dealerCardValueHidden = dealer.getFirstCardVal(); // Only shows the first card value so the second is hidden
        System.out.println("Dealer value: " + dealerCardValueHidden);
    }
    //How to hide dealer second card.
    //How to hide dealer second card value.

    public boolean checkHandforAce() {
        boolean insurance = false;
        insurance = dealer.checkAce();
        if (insurance) {
            System.out.println("Dealer is showing Ace would you like to buy insurance (y/n)");
            yn = userInput.next().charAt(0);
            if (yn == 'y') {
                insuranceBet = playerBet / 2;
                playerMoney -= insuranceBet;
                if (dealerCardValue == 21) {
                    playerMoney = playerMoney + (insuranceBet * 2);
                    System.out.println("Dealer had 21... ");
                    return true;
                }
            } else {
                if (dealerCardValue == 21) {
                    System.out.println("Dealer had 21... you lose...");
                    return true;
                } else {
                    System.out.println("Dealer didn't have 21... keep playing...");
                    return false;
                }
            }

        }
        return false;
    }

    public void calls() {
        int hitCount = 0;
        keepGoing = true;
        playerBust = false;
        while(keepGoing){ // This is the loop for the player if he hits/doubles/stays
            int move = 0;
            System.out.println("Hit 1, Double 2, Stay 3");
            move = userInput.nextInt();
            if(move==2){
                if((playerBet > playerMoney)||(hitCount>0)){
                    System.out.println("You cannot double here is a hit");
                    move = 1;
                }
            }
            switch(move){
                case 1:
                    player.draw(playingDeck);
                    hitCount++;
                    playerHand = player.toString();
                    System.out.println(playerHand);
                    playerCardValue = player.getCardValue();
                    System.out.println("Hand value: " + playerCardValue);
                    if(playerCardValue>21){ //Player busts
                        playerBust = true;
                        keepGoing = false;
                    }
                    // if someone hits we have to make it so they can't double
                    break;
                case 2:
                    player.draw(playingDeck);
                    playerCardValue = player.getCardValue();
                    playerHand = player.toString();
                    System.out.println(playerHand);
                    System.out.println("Hand value: " + playerCardValue);
                    playerDouble = true;
                    if(playerCardValue>21){
                        playerBust = true;
                        keepGoing = false;
                        break;
                    }
                    keepGoing = false;
                    break;
                case 3:
                    keepGoing = false;
                    break;
                // Still need code for ace/soft cards
            }
        }

    }

    public void dealerMoves() {
        dealerKeepGoing = true;
        dealerBust = false;
        dealerCardValue = dealer.getCardValue();
        System.out.println("Dealer value: " + dealerCardValue);

        while(dealerKeepGoing){
            if(playerCardValue>21){ // This is so that we don't go through this while loop
                dealerKeepGoing = false;
                dealerBust = true;
            }
            else if(dealerCardValue<17){
                // we keep hitting until it bust or hits 17
                dealer.draw(playingDeck);
                dealerCardValue = dealer.getCardValue();
            }
            else if(dealerCardValue>=17 && dealerCardValue<=21){
                System.out.println("Dealer value: " + dealerCardValue);
                dealerKeepGoing = false;
            }
            else{
                System.out.println("Dealer bust... you win...");
                dealerBust = true;
                dealerKeepGoing = false;
            }
        }
    }

    public void findWinner() {
        // After everyone has made there move we decide on a winner
        if((!dealerBust && dealerCardValue>playerCardValue)||playerBust){
            System.out.println("You lost...");
            System.out.println("Your cards: " + playerHand);
            System.out.println("Hand value: " + playerCardValue);
            dealerHand = dealer.toString();
            System.out.println("Dealers cards: " + dealerHand);
            System.out.println("Dealer value: " + dealerCardValue);
        }
        else if(dealerCardValue == playerCardValue){
            System.out.println("Push...");
            System.out.println("You lost...");
            System.out.println("Your cards: " + playerHand);
            System.out.println("Hand value: " + playerCardValue);
            dealerHand = dealer.toString();
            System.out.println("Dealers cards: " + dealerHand);
            System.out.println("Dealer value: " + dealerCardValue);
            playerMoney+=playerBet;
        }
        else{
            if(playerDouble){
                dealerHand = dealer.toString();
                System.out.println("Dealers cards: " + dealerHand);
                System.out.println("Dealer value: " + dealerCardValue);
            }
            System.out.println("You won $" + playerBet);
            dealerHand = dealer.toString();
            System.out.println("Dealers cards: " + dealerHand);
            System.out.println("Dealer value: " + dealerCardValue);
            playerMoney+= (2*playerBet);
        }

        // Reset the hands back to having no cards
        player.clearHand();
        dealer.clearHand();
    }


    // Make a function for printing




}




