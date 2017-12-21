/**
 * Hand
 * 
 * Framework of a hand object which represents a player's hand in a given round of poker.
 * 
 * Author: Jeremy Levitt
 */
public class Hand
{
    //true if user is in during a round, false if they folded that round
    boolean inGame;
    public Detection tool=new Detection();
    //represents the user's hand
    public int[] hand;
    //amount of money a player has at any given point in the game
    public int money;
    //player's name
    String name;

    /*
     * Hand Constructor:
     * 
     * Initializes the class's variables.
     * 
     * @param String n: the name of the player's hand.
     * 
     */
    public Hand(String n){
        inGame=true;
        hand=new int[5];
        name=n;
        money = 10000;
    }
    
    /*
     * value (int cardVal)
     * 
     * Converts a number into the value of the card that the number represents.  This does NOT include suits (Spades, Hearts, Clubs, and Diamonds).
     * This method is used when describing a hand.  For instance, if a hand has a pair of Aces, this method would be used to return "Ace".
     * 
     * @param cardVal: The number of the card desired to be converted.
     * 
     */
    public String value(int cardVal){
        String value = "";
        if (cardVal < 9){
            cardVal += 2;
            value = "" + cardVal;
        }
        else if (cardVal == 9){
            value = "Jack";
        }
        else if (cardVal == 10){
            value = "Queen";
        }
        else if (cardVal == 11){
            value = "King";
        }
        else{
            value = "Ace";
        }
        return value;
    }

    /*
     * conversion (int card)
     * 
     * Converts an integer in a hand to the String/text equivalent.  For instance, 0 would become "2S".
     * 
     * @ param int card: the integer-version of the card desired to convert.
     * @ return String: card equivalent of the integer value in the argument.
     * 
     */
    public String conversion(int card){
        String suit;
        String value;
        int cardVal = card%13;
        if (card/13<1){
            suit = "S";
        }
        else if (card/13<2){
            suit = "C";
        }
        else if (card/13<3){
            suit = "D";
        }
        else{
            suit = "H";
        }
        if (cardVal < 9){
            cardVal += 2;
            value = "" + cardVal;
        }
        else if (cardVal == 9){
            value = "J";
        }
        else if (cardVal == 10){
            value = "Q";
        }
        else if (cardVal == 11){
            value = "K";
        }
        else{
            value = "A";
        }
        return value+suit;
    }

    /*
     * typeOfHand()
     * 
     * Returns the type of hand a player has and what value they have with it.  For instance, if a player has a pair of 2's, this method would return
     * "Pair: 2".
     * 
     * @param none
     * @return String that tells what type of hand a player has
     */
    public String typeOfHand(){
        if (tool.handScore(hand)==1){
            return "High Card: " + getHighCard();
        }
        else if (tool.handScore(hand)==2){
            return "Pair: " + value(tool.pair(hand));
        }
        else if (tool.handScore(hand)==3){
            //edit
            String twoPair1 = "" + tool.twoPair(hand);
            int twoPaira=0;
            int twoPairb=0;
            if (twoPair1.length()==2){
                twoPaira = Integer.parseInt(twoPair1.substring(0,1));
                twoPairb = Integer.parseInt(twoPair1.substring(1));
            }
            else{
                twoPaira = Integer.parseInt(twoPair1.substring(0,2));
                twoPairb = Integer.parseInt(twoPair1.substring(2));
            }
            return "Two Pair:\tFirst Pair: " + value(twoPaira) + " \tSecond Pair: " + value(twoPairb);
        }
        else if (tool.handScore(hand)==4){
            return "Triple: " + value(tool.triple(hand));
        }
        else if (tool.handScore(hand)==5){
            return "Straight, High Card: " + value(tool.straight(hand));
        }
        else if (tool.handScore(hand)==6){
            return "Flush, High Card: " + value(tool.flush(hand));
        }
        else if (tool.handScore(hand)==7){
            return "Full House:\tTriple: " + value(tool.triple(hand)) + "\tDouble: " + value(tool.fullHouse(hand));
        }
        else if (tool.handScore(hand)==8){
            return "Quadrouple: " + value(tool.quadrouple(hand));
        }
        else if (tool.handScore(hand)==9){
            return "Straight-Flush, Highest Card: " + value(tool.straightFlush(hand));
        }
        return "";
    }

    /*
     * cards()
     * 
     * Converts the array of integers stored in hand to actual cards, and returns as a String array of the player's hand.
     * 
     * @param none
     * @return String Array of the user's cards in hand
     */
    public String[] cards(){
        String[] universal = new String[hand.length];
        for (int i=0; i<universal.length; i++){
            universal[i] = conversion(hand[i]);
        }
        return universal;
    }
    
    /*
     * getHighCard()
     * 
     * Returns the highest value card in the player's hand.  Used in typeOfHand().
     * 
     * @param none
     * @return int representative of the highest value card in the player's hand.
     * 
     */
    public int getHighCard(){
        int high = hand[0]%13;
        for (int i=1; i<hand.length; i++){
            if (hand[i]%13 > high){
                high = hand[i]%13;
            }
        }
        return high;
    }
}
