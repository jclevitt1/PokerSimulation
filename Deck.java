import java.util.Random; //// import the Random class to generate random numbers
import java.util.Scanner; // import the Scanner class to get user input
/**
 * Class Deck:
 * 
 * This class is used to represent the deck of cards in the game.  A basic algorithm of assigning numbers from 0-51 to the deck array is used. (See deck()).
 * 
 * Author: Jeremy Levitt, with one method written by Samuel Thacker (see shuffle())
 * 
 */
public class Deck
{
    //represents the top of the deck
    public int firstIndex=0;
    public int[] deck=deck();
    /*
     * deck()
     * 
     * A method used to populate the deck[] array from index 0 to 51 with the same number as its index.
     * Algorithm:
     * 0-12 represents one suit, 13-25 represents another, etc.
     * 0 and 13 holds a card value of 2, 1 and 14 hold a card value of 3, ..., 12 and 25 hold a card value of an Ace.
     * 
     * @param none
     * @return int[]
     */
    private int[] deck(){
        int[] universal=new int[52];
        for (int i=0; i<universal.length; i++){
            universal[i]=i;
        }
        return universal;
    }

    /*
     * shuffle()
     *
     * The method to shuffle the cards in the Deck based on a user's lucky number, taken from input.
     * 
     * This method was originally designed by Samuel Thacker (initially meant for a differnt program)
     * Samuel Thacker was a college-student TA to my AP Computer Science class last year.
     *
     * @param none
     * @return none
     */

    public void shuffle() {
        firstIndex=0;
        int i, j, temp;
        int seed;
        Random r;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your lucky number> ");
        seed = scan.nextInt();
        r = new Random(seed);
        for(i = firstIndex; i < 52; i++){
            j = r.nextInt(52);
            temp = deck[j];
            deck[j] = deck[i];
            deck[i] = temp; 
        }
    }//end shuffle

    /*
     * deal(Hand[] hand)
     * 
     * Deals the cards hands out to all objects in the array of Hand objects.
     * 
     * @param Array of Hand Objects (representative of the various players)
     * @return none
     */
    public void deal(Hand[] hand){
        for (int i2=0; i2<hand[0].hand.length; i2++){
            for (int i=0; i<hand.length; i++){
                hand[i].hand[i2]=deck[(i2*hand.length)+i];
                firstIndex++;
            }
        }
    }

    /*
     * burn (int[] indexesBurned, int[] hand)
     * 
     * Replaces the cards in hand a player wishes to burn with a card from the deck.
     * 
     * @param indexesBurned: an array of integers which represent the indexes of the hand array a player wishes to burn.
     *         hand: the player's hand.
     * @return none
     */
    public void burn(int[] indexesBurned, int[] hand){
        for (int i=0; i<indexesBurned.length; i++){
            hand[indexesBurned[i]] = deck[firstIndex];
            firstIndex++;
        }
    }
}