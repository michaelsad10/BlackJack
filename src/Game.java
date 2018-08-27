import java.util.Scanner;

public class Game {
    Game game = new Game();
    private Deck playingDeck = new Deck();
    private Deck player = new Deck();
    private Deck dealer = new Deck();
    private Scanner userInput = new Scanner(System.in);
    String playerHand, dealerHand;
    char yn;
    private double playerMoney = 1000.00;
    private double insuranceBet = 0;
    double playerCardValue = 0;
    double dealerCardValue = 0;
    double playerBet = 0;
    boolean dealerHas21 = false;
    boolean keepGoing = true;
    boolean playerDouble = false;
    boolean playerBust = false;
    boolean dealerKeepGoing = true;
    boolean dealerBust = false;


    public void Run(){
        playingDeck.createDeck();
        playingDeck.shuffle();
        while(playerMoney > 0) {
            game.dealingCards();
            game.showHand();
            game.betting();
            dealerHas21 = checkHandforAce();
            while(!dealerHas21){
                game.calls();
                game.dealerMoves();
                game.findWinner();
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
            if (playerBet > playerMoney) {
                System.out.println("You don't have that kind of money pal");
                bettingAmt = true;
            }
            else {
                System.out.println("You bet $" + playerBet);
                System.out.println("Good luck... dealing cards");
                playerMoney-=playerBet;
                break;
            }

        }
    }

    public void showHand() {
        playerHand = player.toString();
        dealerHand = dealer.toString();
        System.out.println("Your cards: " + playerHand);
        System.out.println("Dealers cards: " + dealerHand);
        System.out.println("---------------");
        playerCardValue = player.getCardValue();
        System.out.println("Hand value: " + playerCardValue);
        dealerCardValue = dealer.getCardValue();
        System.out.println("Dealer value: " + dealerCardValue);
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
        while(keepGoing){ // This is the loop for the player if he hits/doubles/stays
            int move = 0;
            System.out.println("Hit 1, Double 2, Stay 3");
            move = userInput.nextInt();
            if(move==2){
                if(playerBet > playerMoney){
                    System.out.println("You cannot double here is a hit");
                    move = 1;
                }
            }
            switch(move){
                case 1: player.draw(playingDeck);
                    playerHand = player.toString();
                    System.out.println(playerHand);
                    playerCardValue = player.getCardValue();
                    System.out.println("Hand value: " + playerCardValue);
                    if(playerCardValue>21){
                        playerBust = true;
                        keepGoing = false;
                        break;
                    }
                    break;
                case 2: player.draw(playingDeck);
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
                case 3: keepGoing = false;
                    break;
                // Still need code for ace/soft cards
            }
        }

    }

    public void dealerMoves() {
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
            playerMoney-=playerBet;
        }
        else if(dealerCardValue == playerCardValue){
            System.out.println("Push...");
            playerMoney+=playerBet;
        }
        else{
            if(playerDouble){
                System.out.println();
            }
            System.out.println("You won $ " + playerBet);
            playerMoney+= playerBet;
        }
        // Reset the hands back to having no cards
        player.clearHand();
        dealer.clearHand();
    }




}




