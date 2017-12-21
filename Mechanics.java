/**
 * Class: Mechanics
 * 
 * This class is mostly used for determining the winner of a given round of poker.
 * 
 * Author: Jeremy Levitt
 */
public class Mechanics
{
    public Detection tool = new Detection();
    public int pot;
    /*
     * winner (Hand[] hands)
     * 
     * Determines and returns the winner of a round.  Returns null if there is a complete tie of hands.
     * 
     * @param Hand[] hands: An array of all of the Hand objects in the round of poker played.
     * @return Hand Object: The winner of the round.
     * 
     */
    public Hand winner(Hand[] hands){
        int[] tiedHands;
        int[] tiedHandsUnAdj = new int[hands.length];
        tiedHandsUnAdj = resetArr(tiedHandsUnAdj);
        int tiedHandsIndex = 0;
        int bestHandScore = tool.handScore(hands[0].hand);
        int winnerIndex = 0;
        int indexTie=-1;
        for (int i=1; i<hands.length; i++){
            if (tool.handScore(hands[i].hand)>bestHandScore){
                bestHandScore = tool.handScore(hands[i].hand);
                winnerIndex=i;
                indexTie=-1;
                tiedHandsIndex=0;
                tiedHandsUnAdj = resetArr(tiedHandsUnAdj);
            }
            else if (tool.handScore(hands[i].hand)==bestHandScore){
                tiedHandsUnAdj[tiedHandsIndex] = i;
                tiedHandsIndex++;
            }
        }
        if (tiedHandsUnAdj[0]==-1){
            return hands[winnerIndex];
        }
        else{
            tiedHands = new int[tiedHandsIndex+1];
            tiedHandsIndex=0;
            for (int i=0; i<tiedHands.length; i++){
                if (tiedHandsUnAdj[i]!=-1){
                    tiedHands[tiedHandsIndex] = tiedHandsUnAdj[i];
                    tiedHandsIndex++;
                }
            }
            if (tool.handScore(hands[winnerIndex].hand)==1){
                
            }
            else if (tool.handScore(hands[winnerIndex].hand)==2){
                int indexOfTiedPair=-1;
                for (int i=0; i<tiedHands.length; i++){
                    if (tool.pair(hands[tiedHands[i]].hand)>tool.pair(hands[winnerIndex].hand)){
                        winnerIndex = tiedHands[i];
                        indexOfTiedPair = -1;
                    }
                    else if (tool.pair(hands[tiedHands[i]].hand)==tool.pair(hands[winnerIndex].hand) && tiedHands[i]!=winnerIndex){
                        indexOfTiedPair = tiedHands[i];
                    }
                }
                if (indexOfTiedPair==-1){
                    return hands[winnerIndex];
                }
                else{

                    if (isHigher(hands[indexOfTiedPair].hand, hands[winnerIndex].hand, tool.pair(hands[winnerIndex].hand), -100, tool.pair(hands[indexOfTiedPair].hand), -100)==1){
                        winnerIndex=indexOfTiedPair;
                    }
                    //0 return value means the hands are tied
                    else if (isHigher(hands[indexOfTiedPair].hand, hands[winnerIndex].hand, tool.pair(hands[winnerIndex].hand), -100, tool.pair(hands[indexOfTiedPair].hand), -100)==0){
                        return null;
                    }
                    return hands[winnerIndex];
                }
            }
            else if (tool.handScore(hands[winnerIndex].hand)==3){
                int indexOfTiedPair=-1;
                for (int i=0; i<tiedHands.length; i++){
                    if (tool.twoPair(hands[tiedHands[i]].hand)>tool.twoPair(hands[winnerIndex].hand)){
                        winnerIndex = tiedHands[i];
                        indexOfTiedPair = -1;
                    }
                    else if (tool.twoPair(hands[tiedHands[i]].hand)==tool.twoPair(hands[winnerIndex].hand)){
                        indexOfTiedPair = tiedHands[i];
                    }
                }
                if (indexOfTiedPair==-1){
                    return hands[winnerIndex];
                }
                else{
                    String twoPair1 = "" + tool.twoPair(hands[indexOfTiedPair].hand);
                    int twoPair1a=0;
                    int twoPair1b=0;
                    String twoPair2 = "" + tool.twoPair(hands[winnerIndex].hand);
                    int twoPair2a=0;
                    int twoPair2b=0;
                    if (twoPair1.length()==2){
                        twoPair1a = Integer.parseInt(twoPair1.substring(0,1));
                        twoPair1b = Integer.parseInt(twoPair1.substring(1));
                    }
                    else{
                        twoPair1a = Integer.parseInt(twoPair1.substring(0,2));
                        twoPair1b = Integer.parseInt(twoPair1.substring(2));
                    }
                    if (twoPair2.length()==2){
                        twoPair2a = Integer.parseInt(twoPair2.substring(0,1));
                        twoPair2b = Integer.parseInt(twoPair2.substring(1));
                    }
                    else if (twoPair2.length()==3){
                        twoPair2a = Integer.parseInt(twoPair2.substring(0,2));
                        twoPair2b = Integer.parseInt(twoPair2.substring(2));
                    }
                    else if (twoPair2.length()==3){
                        twoPair2a = Integer.parseInt(twoPair2.substring(0,2));
                        twoPair2b = Integer.parseInt(twoPair2.substring(2));
                    }

                    if (isHigher(hands[indexOfTiedPair].hand, hands[winnerIndex].hand, twoPair1a, twoPair1b, twoPair2a, twoPair2b)==1){
                        if (indexTie!=-1){
                            winnerIndex=indexTie;
                        }
                    }
                    //0 return value means the hands are tied
                    else if (isHigher(hands[indexOfTiedPair].hand, hands[winnerIndex].hand, twoPair1a, twoPair1b, twoPair2a, twoPair2b)==0){
                        return null;
                    }
                    return hands[winnerIndex];
                }
            }
            else if (tool.handScore(hands[winnerIndex].hand)==4){
                for (int i=0; i<tiedHands.length; i++){
                    if (tool.triple(hands[tiedHands[i]].hand)>tool.triple(hands[winnerIndex].hand)){
                        winnerIndex = tiedHands[i];
                    }
                }
                return hands[winnerIndex];
            }
            else if (tool.handScore(hands[winnerIndex].hand)==5){
                int[] indexOfTiedHands = new int[tiedHands.length];
                indexOfTiedHands = resetArr(indexOfTiedHands);
                int index=0;
                for (int i=0; i<tiedHands.length; i++){
                    if (tool.straight(hands[tiedHands[i]].hand)>tool.straight(hands[winnerIndex].hand)){
                        winnerIndex = tiedHands[i];
                        indexOfTiedHands = resetArr(indexOfTiedHands);
                        index=0;
                    }
                    else if (tool.straight(hands[tiedHands[i]].hand)==tool.straight(hands[winnerIndex].hand)){
                        indexOfTiedHands[index] = tiedHands[i];
                        index++;
                    }
                }

                //this means there are no ties
                if (indexOfTiedHands[0]!=-1){
                    return hands[winnerIndex];
                }
                //complete later
                else{
                    return null;
                }
            }
            else if (tool.handScore(hands[winnerIndex].hand)==6){
                int[] indexOfTiedHands = new int[tiedHands.length];
                indexOfTiedHands = resetArr(indexOfTiedHands);
                int index=0;
                for (int i=0; i<tiedHands.length; i++){
                    if (tool.flush(hands[tiedHands[i]].hand)>tool.flush(hands[winnerIndex].hand)){
                        winnerIndex = tiedHands[i];
                        indexOfTiedHands = resetArr(indexOfTiedHands);
                        index=0;
                    }
                    else if (tool.flush(hands[tiedHands[i]].hand)==tool.flush(hands[winnerIndex].hand)){
                        indexOfTiedHands[index] = tiedHands[i];
                        index++;
                    }
                }

                //this means there are no ties
                if (indexOfTiedHands[0]!=-1){
                    return hands[winnerIndex];
                }
                //complete later
                else{
                    return null;
                }
            }
            else if (tool.handScore(hands[winnerIndex].hand)==7){
                for (int i=0; i<tiedHands.length; i++){
                    if (tool.triple(hands[tiedHands[i]].hand)>tool.triple(hands[winnerIndex].hand)){
                        winnerIndex=i;
                    }
                }
                return hands[winnerIndex];
            }
            else if (tool.handScore(hands[winnerIndex].hand)==8){
                for (int i=0; i<tiedHands.length; i++){
                    if (tool.quadrouple(hands[tiedHands[i]].hand)>tool.quadrouple(hands[winnerIndex].hand)){
                        winnerIndex=i;
                    }
                }
                return hands[winnerIndex];
            }
            else if (tool.handScore(hands[winnerIndex].hand)==9){
                int[] indexOfTiedHands = new int[tiedHands.length];
                indexOfTiedHands = resetArr(indexOfTiedHands);
                int index=0;
                for (int i=0; i<tiedHands.length; i++){
                    if (tool.straightFlush(hands[tiedHands[i]].hand)>tool.straightFlush(hands[winnerIndex].hand)){
                        winnerIndex = tiedHands[i];
                        indexOfTiedHands = resetArr(indexOfTiedHands);
                        index=0;
                    }
                    else if (tool.straightFlush(hands[tiedHands[i]].hand)==tool.straightFlush(hands[winnerIndex].hand)){
                        indexOfTiedHands[index] = tiedHands[i];
                        index++;
                    }
                }
                //this means there are no ties
                if (indexOfTiedHands[0]!=-1){
                    return hands[winnerIndex];
                }
                else{
                    return null;
                }
            }

            return null;
        }
    }

    /*
     * isHigher(int[] hand1, int[] hand2, int offLimits1a, int offLimits1b, int offLimits2a, int offLimits2b)
     * 
     * Determines whether one hand is better than another purely based on the value of cards in their hand (not based on special poker hands such as pair,
     * twoPair, triple, etc.) 
     * 
     * @param int[] hand1: the first player's hand
     *        int[] hand2: the second player's hand
     *        int offLimits1a: used if there is a value that cannot be considered a "high card" in the hand.  For instance, if hand1[] has a pair of 0 and 13,
     *                         the offLimits1a value would be 0 % 13 or 13 % 13=0.  If the hand does not contain a pair, two pair, etc. then this argument will
     *                         simply be a negative number.
     *        int offLimits1b: used if there is a value that cannot be considered a "high card" in the hand.  For instance, if hand1[] has a pair of 0 and 13,
     *                         the offLimits1b value would be 0 % 13 or 13 % 13=0.  This is only used if the hand has a hand with two pairs.  Otherwise,
     *                         this argument will simply be a negative number.
     *        int offLimits2a: used if there is a value that cannot be considered a "high card" in the hand.  For instance, if hand2[] has a pair of 0 and 13,
     *                         the offLimits2a value would be 0 % 13 or 13 % 13=0.  If the hand does not contain a pair, two pair, etc. then this argument will
     *                         simply be a negative number.
     *        int offLimits2b: used if there is a value that cannot be considered a "high card" in the hand.  For instance, if hand2[] has a pair of 0 and 13,
     *                         the offLimits2b value would be 0 % 13 or 13 % 13=0.  This is only used if the hand has a hand with two pairs.  Otherwise,
     *                         this argument will simply be a negative number.
     * 
     */
    public int isHigher(int[] hand1, int[] hand2, int offLimits1a, int offLimits1b, int offLimits2a, int offLimits2b){
        int[] values1= new int[hand1.length];
        int[] values2= new int[hand2.length];
        values1=resetArr(values1);
        values2=resetArr(values2);
        int index1=0;
        int index2=0;
        for (int i=0; i<hand1.length; i++){
            if (hand1[i]%13!=offLimits1a && hand1[i]%13!=offLimits1b ){
                values1[index1] = hand1[i]%13;
                index1++;
            }
            if (hand2[i]%13!=offLimits2a&&hand2[i]%13!=offLimits2b){
                values2[index2] = hand2[i]%13;
                index2++;
            }
        }
        for (int i=0; i<values1.length; i++){
            for (int i2=i+1; i2<values1.length; i2++){
                if (values1[i]<values1[i2]){
                    int temp1=values1[i];
                    values1[i]=values1[i2];
                    values1[i2]=temp1;
                }
                if (values2[i]<values2[i2]){
                    int temp2=values2[i];
                    values2[i]=values2[i2];
                    values2[i2]=temp2;
                }
            }
        }

        for (int i=values1.length-1; i>=0; i--){
            if (values1[i]!=-1){
                if (values1[i]>values2[i]){
                    return 1;
                }
                else if (values1[i]<values2[i]){
                    return -1;
                }
            }
        }
        return -1;
    }

    /*
     * resetArr (int[] arr)
     * 
     * Sets all of the elements in a given array equal to -1.
     * 
     * @param int[] arr -> the desired array to be reset.
     * @reurn int[]
     * 
     */
    public int[] resetArr(int[] arr){
        int[] universal = new int[arr.length];
        for (int i=0; i<universal.length; i++){
            universal[i]=-1;
        }
        return universal;
    }
}
