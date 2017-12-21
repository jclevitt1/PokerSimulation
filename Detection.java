
/**
 * Detection:
 * 
 * A class used to detect whether a hand has certain poker hands such as a pair, two pair, straight, etc.  This class also
 * ranks hands based on if they have a certain type of hand.
 * 
 * Author: Jeremy Levitt
 * 
 */
public class Detection
{
    /*
     * handScore (int[] hand)
     * 
     * Ranks a hand based on what type of poker hand it is.  9 is the best type of hand, being a straight-flush, and 1 is the worst type of hand, being just
     * a high card.
     * 
     * @param int[] hand: the desired hand to be ranked.
     * @return int: the ranking of the hand.
     * 
     */
    public int handScore(int[] hand){
        if (straightFlush(hand)!=-1){
            return 9;
        }
        else if (quadrouple(hand)!=-1){
            return 8;
        }
        else if (fullHouse(hand)!=-1){
            return 7;
        }
        else if (flush(hand)!=-1){
            return 6;
        }
        else if (straight(hand)!=-1){
            return 5;
        }
        else if (triple(hand)!=-1){
            return 4;
        }
        else if (twoPair(hand)!=-1){
            return 3;
        }
        else if (pair(hand)!=-1){
            return 2;
        }
        else{
            return 1;
        }
    }

    /*
     * pair (int[] hand)
     * 
     * Returns -1 if there is no pair present in a given hand.  If there is a pair present, this method returns the value of that pair.  For instance,
     * if a hand contains 18 and 31 (which are pairs because they are a multiple of 13 apart from one another), this method returns 5, as 18 % 13 = 5.
     * 
     * @param int[] hand: the hand desired to test.
     * @return int: Either the pair value or -1 if there is no pair present.
     * 
     */
    public int pair(int[] hand){
        if(quadrouple(hand)!=-1){
            return -1;
        }
        else if(triple(hand)!=-1){
            return -1;
        }
        else{
            int count=1;
            for (int i=0; i<hand.length; i++){
                int centerCard=hand[i];
                for (int i2=i+1; i2<hand.length; i2++){
                    if (hand[i2]%13==centerCard%13){
                        count++;
                    }
                }
                if (count==2){
                    return centerCard%13;
                }
                else{
                    count=1;
                }
            }
            return -1;
        }
    }

    /*
     * triple (int[] hand)
     * 
     * Returns -1 if there is no triple present in a given hand.  If there is a triple present, this method returns the value of that triple.  For instance,
     * if a hand contains 18, 31, and 44 (which are pairs because they are a multiple of 13 apart from one another), this method returns 5, as 18 % 13 = 5.
     * 
     * @param int[] hand: the hand desired to test.
     * @return int: Either the triple value or -1 if there is no triple present.
     * 
     */
    public int triple(int[] hand){
        if(quadrouple(hand)!=-1){
            return -1;
        }
        else{
            int count=1;
            for (int i=0; i<hand.length; i++){
                int centerCard=hand[i];
                for (int i2=i+1; i2<hand.length; i2++){
                    if (hand[i2]%13==centerCard%13){
                        count++;
                    }
                }
                if (count==3){
                    return centerCard%13;
                }
                else{
                    count=1;
                }
            }
            return -1;
        }
    }

    /*
     * quadrouple (int[] hand)
     * 
     * Returns -1 if there is no quadrouple present in a given hand.  If there is a quadrouple present, this method returns the value of that pair.  For 
     * instance, if a hand contains 5, 18, 31, and 44 (which are pairs because they are a multiple of 13 apart from one another), this method returns 5, 
     * as 18 % 13 = 5.
     * 
     * @param int[] hand: the hand desired to test.
     * @return int: Either the quadrouple value or -1 if there is no quadrouple present.
     * 
     */
    public int quadrouple(int[] hand){
        int count=1;
        for (int i=0; i<hand.length; i++){
            int centerCard=hand[i];
            for (int i2=i+1; i2<hand.length; i2++){
                if (hand[i2]%13==centerCard%13){
                    count++;
                }
            }
            if (count==4){
                return centerCard%13;
            }
            else{
                count=1;
            }
        }
        return -1;
    }

    /*
     * range: 00-1211
     * 1110 is directly below 121
     * 
     * twoPair (int[] hand)
     * 
     * Returns -1 if there is not two pairs present in a given hand.  If there are two pairs present, this method returns the greater value of that pair, 
     * then the smaller value next to it.  For instance, if a hand contains 1 pair of 15 and 28, and another pair of 50 and 37, this method would first
     * calculate the values of these pairs: 
     * 1st pair: 15 % 13 = 2
     * 2nd pair: 37 % 13 = 11
     * In this case, the method would return 112, as the bigger pair is 11 which comes first, then directly after comes the smaller value of the other pair.
     * 
     * @param int[] hand: the hand desired to test.
     * @return int: Either the pair value or -1 if there is no pair present.
     * 
     */
    public int twoPair(int[] hand){
        int endPair1, offLimits;
        int[] values= new int[hand.length];
        if (pair(hand)!=-1){
            endPair1=pair(hand);
            offLimits=endPair1%13;
        }
        else{
            return -1;
        }

        for (int i=0; i<hand.length; i++){
            values[i]=hand[i]%13;
        }
        int small=values[0];
        int index=0;
        for (int i=0; i<values.length; i++){
            for (int i2=i+1; i2<values.length; i2++){
                if (values[i]<values[i2]){
                    int temp=values[i];
                    values[i]=values[i2];
                    values[i2]=temp;
                }
            }
        }
        for (int i=1; i<values.length; i++){
            if (values[i]!=offLimits){
                if (values[i]==values[i-1]){
                    if (endPair1>values[i]%13){
                        String concatenate = ""+ endPair1 + (values[i]%13);
                        int result = Integer.parseInt(concatenate);
                        return result;
                    }
                    else{
                        String concatenate = ""+ (values[i]%13)+ endPair1;
                        int result = Integer.parseInt(concatenate);
                        return result;
                    }
                }
            }
        }
        return -1;
    }

    /*
     * fullHouse (int[] hand)
     * 
     * Returns -1 if there is not a full house present in a given hand.  If there is a full house present, this method returns first the value of the triple,
     * followed by the value of the pair.  For instance, if a hand contains 1 triple of 15, 28, and 41, and another pair of 50 and 37, this method would 
     * first calculate the values of the triple and pair: 
     * Triple: 15 % 13 = 2
     * Pair: 37 % 13 = 11
     * In this case, the method would return 211, as the value of the triple always comes first, then directly after comes the smaller value of the 
     * other pair.
     * 
     * @param int[] hand: the hand desired to test.
     * @return int: Either the pair value or -1 if there is no pair present.
     * 
     */
    public int fullHouse(int[] hand){
        int endPair1, offLimits, count=1;
        int[] values= new int[hand.length];
        if (triple(hand)!=-1){
            endPair1=triple(hand);
            offLimits=triple(hand);
        }
        else{
            return -1;
        }
        for (int i=0; i<hand.length; i++){
            values[i]=hand[i]%13;
        }
        for (int i=0; i<values.length; i++){
            int centerCard = values[i];
            for (int i2=i+1; i2<values.length; i2++){
                if (values[i2]==centerCard&&values[i2]!=offLimits){
                    count++;
                }
            }
            if (count==2){
                return values[i]%13;
            }
            else{
                count=1;
            }
        }
        return -1;
    }

    /*
     * flush (int[] hand)
     * 
     * Returns -1 if there is not a flush present in a given hand.  If there is a flush present, this method returns the highest value in the hand.
     * 
     * @param int[] hand: the hand desired to test.
     * @return int: Either the pair value or -1 if there is no pair present.
     * 
     */
    public int flush(int[] hand){
        int suit=hand[0]/13;
        int large=hand[0];
        for (int i=0; i<hand.length; i++){
            if (hand[i]/13!=suit){
                return -1;
            }
            if (hand[i]>large){
                large=hand[i];
            }
        }
        return large%13;
    }

    /*
     * straight (int[] hand)
     * 
     * Returns -1 if there is not a straight present in a given hand.  If there is a straight present, this method returns the highest value in the hand.
     * 
     * @param int[] hand: the hand desired to test.
     * @return int: Either the pair value or -1 if there is no pair present.
     * 
     */
    public int straight(int[] hand){
        int[] values= new int[hand.length];
        for (int i=0; i<hand.length; i++){
            values[i]=hand[i]%13;
        }
        int small=values[0];
        int index=0;
        for (int i=0; i<values.length; i++){
            for (int i2=i+1; i2<values.length; i2++){
                if (values[i]<values[i2]){
                    int temp=values[i];
                    values[i]=values[i2];
                    values[i2]=temp;
                }
            }
        }
        for (int i=1; i<values.length; i++){
            if (values[i]!=values[i-1]-1){
                return -1;
            }
        }
        return values[0];
    }

    /*
     * straightFlush (int[] hand)
     * 
     * Returns -1 if there is not a straight- flush present in a given hand.  If there is a straight-flush present, this method returns the highest 
     * value in the hand.
     * 
     * @param int[] hand: the hand desired to test.
     * @return int: Either the pair value or -1 if there is no pair present.
     * 
     */
    public int straightFlush(int[] hand){
        if (straight(hand)!=-1&&flush(hand)!=-1){
            return straight(hand);
        }
        else{
            return -1;
        }
    }
}