/**
 * Class: ArtificialIntel
 * 
 * This class is used to determine an action of a CPU at any point in a poker round.  This class handles how the CPU bets (including when they are first
 * to bet, when they call, fold, or raise a bet on the table) and which cards the CPU "burns" during the "burning" stage of five card stud.
 * 
 * Author: Jeremy Levitt
 */
public class ArtificialIntel
{
    public Detection tool = new Detection();
    /*
     * detBet (num1, num2, num3, num4, num5, num6, num7)
     * 
     * Randomizes the bet to a certain extent:
     * The CPU will have a 50% chance of betting a closed random amount (the starting and closing value makes the most sense with respect to the player's hand).
     * The CPU will have a 40% chance of betting a second closed random amount.
     * The CPU will have a 8% chance of betting a third closed random amount.
     * The CPU will have a 1% chance of betting a fourth closed random amount.
     * The CPU will have a 0.5% chance of betting a fifth closed random amount.
     * The CPU will have a 0.4% chance of betting a sixth closed random amount.
     * The CPU will have a 0.1% chance of betting a seventh closed random amount.
     * 
     * @param ints: num1, num2, num3, num4, num5, num6, num7
     * @return int: amount of first bet
     */
    public int detBet (int num1, int num2, int num3, int num4, int num5, int num6, int num7){
        int rng = (int)(Math.random()*1001);
        if (rng<500){
            return num1*10;
        }
        else if (rng<900){
            return num2*10;
        }
        else if (rng<980){
            return num3*10;
        }
        else if (rng<990){
            return num4*10;
        }
        else if (rng<995){
            return num5*10;
        }
        else if (rng<999){
            return num6*10;
        }
        else{
            return num7*10;
        }
    }

    /*
     * bet (int[] hand)
     * 
     * This has the CPU bet closed random amounts (depending on the CPU's hand).  This is used only if the CPU is the first to bet in a round.
     * raise() is used when a bet is already on the table.
     * 
     * For explanations of the hand scoring system, see "Detection" class.
     * 
     * @param int[] hand: The CPU's hand.
     * @return int
     */
    public int bet(int[] hand){
        int rng = (int)(Math.random()*1001);
        if (tool.handScore(hand)==1){
            int cool1 = (int)(Math.random()*6)+5;
            int cool2 = (int)(Math.random()*6)+10;
            int cool3 = (int)(Math.random()*11)+20;
            int cool4 = (int)(Math.random()*21)+30;
            int cool5 = (int)(Math.random()*41)+50;
            int cool6 = (int)(Math.random()*61)+90;
            int cool7 = (int)(Math.random()*61)+140;
            return detBet (cool1, cool2, cool3, cool4, cool5, cool6, cool7);
        }
        else if (tool.handScore(hand)==2){
            int cool1 = (int)(Math.random()*16)+5;
            int cool2 = (int)(Math.random()*21)+20;
            int cool3 = (int)(Math.random()*10)+40;
            int cool4 = (int)(Math.random()*21)+60;
            int cool5 = (int)(Math.random()*41)+80;
            int cool6 = (int)(Math.random()*41)+120;
            int cool7 = (int)(Math.random()*41)+160;
            return detBet (cool1, cool2, cool3, cool4, cool5, cool6, cool7);
        }
        else if (tool.handScore(hand)==3){
            int cool1 = (int)(Math.random()*14)+27;
            int cool2 = (int)(Math.random()*23)+5;
            int cool3 = (int)(Math.random()*16)+40;
            int cool4 = (int)(Math.random()*16)+55;
            int cool5 = (int)(Math.random()*26)+70;
            int cool6 = (int)(Math.random()*46)+95;
            int cool7 = (int)(Math.random()*61)+140;
            return detBet (cool1, cool2, cool3, cool4, cool5, cool6, cool7);
        }
        else if (tool.handScore(hand)==4){
            int cool1 = (int)(Math.random()*16)+35;
            int cool2 = (int)(Math.random()*16)+50;
            int cool3 = (int)(Math.random()*16)+65;
            int cool4 = (int)(Math.random()*21)+5;
            int cool5 = (int)(Math.random()*41)+80;
            int cool6 = (int)(Math.random()*41)+120;
            int cool7 = (int)(Math.random()*41)+160;
            return detBet (cool1, cool2, cool3, cool4, cool5, cool6, cool7);
        }
        else if (tool.handScore(hand)==5){
            int cool1 = (int)(Math.random()*31)+50;
            int cool2 = (int)(Math.random()*11)+40;
            int cool3 = (int)(Math.random()*21)+80;
            int cool4 = (int)(Math.random()*21)+30;
            int cool5 = (int)(Math.random()*41)+100;
            int cool6 = (int)(Math.random()*26)+5;
            int cool7 = (int)(Math.random()*61)+140;
            return detBet (cool1, cool2, cool3, cool4, cool5, cool6, cool7);
        }
        else if (tool.handScore(hand)==6){
            int cool1 = (int)(Math.random()*26)+60;
            int cool2 = (int)(Math.random()*16)+45;
            int cool3 = (int)(Math.random()*16)+85;
            int cool4 = (int)(Math.random()*41)+100;
            int cool5 = (int)(Math.random()*16)+30;
            int cool6 = (int)(Math.random()*61)+140;
            int cool7 = (int)(Math.random()*26)+5;
            return detBet (cool1, cool2, cool3, cool4, cool5, cool6, cool7);
        }
        else if (tool.handScore(hand)==7){
            int cool1 = (int)(Math.random()*21)+70;
            int cool2 = (int)(Math.random()*21)+50;
            int cool3 = (int)(Math.random()*16)+90;
            int cool4 = (int)(Math.random()*31)+40;
            int cool5 = (int)(Math.random()*36)+105;
            int cool6 = (int)(Math.random()*61)+140;
            int cool7 = (int)(Math.random()*21)+20;
            return detBet (cool1, cool2, cool3, cool4, cool5, cool6, cool7);
        }
        else if (tool.handScore(hand)==8){
            int cool1 = (int)(Math.random()*21)+75;
            int cool2 = (int)(Math.random()*21)+55;
            int cool3 = (int)(Math.random()*16)+95;
            int cool4 = (int)(Math.random()*21)+110;
            int cool5 = (int)(Math.random()*11)+45;
            int cool6 = (int)(Math.random()*71)+130;
            int cool7 = (int)(Math.random()*26)+20;
            return detBet (cool1, cool2, cool3, cool4, cool5, cool6, cool7);
        }
        else if (tool.handScore(hand)==9){
            int cool1 = (int)(Math.random()*36)+95;
            int cool2 = (int)(Math.random()*31)+130;
            int cool3 = (int)(Math.random()*16)+80;
            int cool4 = (int)(Math.random()*41)+160;
            int cool5 = (int)(Math.random()*11)+70;
            int cool6 = (int)(Math.random()*11)+60;
            int cool7 = (int)(Math.random()*21)+40;
            return detBet (cool1, cool2, cool3, cool4, cool5, cool6, cool7);
        }
        return 0;
    }

    /*
     * raise (int currentBet, int count, int[] hand, boolean firstBet)
     * 
     * This method is used when there is a bet already on the table.  CPU's are programmed to raise, call, or fold depending on if a bet meets a condition
     * of a closed random value.  It is somewhat random so the user cannot learn with experience what hand a CPU has based on if that CPU raised, folded, or
     * called a given bet.  For instance, if I programmed the CPU to constantly call at 250 or greater when it has a pair and raise at a bet of  250 with 
     * other hands, a user may learn this system and continually bet 250 to check if the CPU has a pair (hence why the values are randomized).
     * 
     * @return: int
     *          returns -1 if the CPU folds with a given bet.
     *          returns 0 if the CPU calls a given bet.
     *          if any other value is returned, the CPU would raise the given bet on the table by that amount.
     * @param int currentBet, int count, int[] hand, boolean firstBet
     *        currentBet: The current bet on the table.
     *        count: The number of times the user bet above 800 for consecutive rounds.  If the user attempts to take advantage of this betting system by
     *               betting the maximum value each round, the 
     * 
     * 
     */
    //returns 0 if CPU sees the bet, returns -1 if CPU folds, and the amount to raise if
    //the CPU chooses to raise
    //count is number of times in a row the player initiates a bet of greater than 900
    public int raise(int currentBet, int count, int[] hand, boolean firstBet){
        int devianceHundred = (int)((Math.random()*21)*10)-100;
        int negDevianceFifty = (int)((Math.random()*6)*10)-50;
        int negDevianceHundred = (int)((Math.random()*11)*10)-100;
        int negDevianceOneFifty = (int)((Math.random()*16)*10)-150;
        int oddDeviance = (int)((Math.random()*41)*10)-100;
        if (tool.handScore(hand)==1){
            if (firstBet){
                if (currentBet>650+devianceHundred){
                    return -1;
                }
            }
            else{
                if (currentBet>325+negDevianceHundred){
                    return -1;
                }
            }
            return 0;
        }
        else if (tool.handScore(hand)==2){
            if (currentBet>800){
                if (count>=3){
                    return 0;
                }
                else{
                    return -1;
                }
            }
            else if (currentBet>625+devianceHundred){
                return -1;
            }
            else if (currentBet>200+negDevianceFifty){
                return 0;
            }
            else if (currentBet>49){
                //raise amount
                int rand = (int)((Math.random()*6)*10)+50;
                return rand;
            }
        }
        else if (tool.handScore(hand)==3){
            if (currentBet>800){
                if (count>=3){
                    return 0;
                }
            }
            else if (currentBet>750+devianceHundred){
                return -1;
            }
            else if (currentBet>350+negDevianceFifty){
                return 0;
            }
            else if (currentBet>49){
                //raise amount
                int rand = (int)((Math.random()*16)*10)+100;
                return rand;
            }
        }
        else if (tool.handScore(hand)==4){
            if (currentBet>850){
                if (count>=3){
                    return 0;
                }
            }
            else if (currentBet>850+devianceHundred){
                return -1;
            }
            else if (currentBet>550+negDevianceHundred){
                return 0;
            }
            else if (currentBet>49){
                //raise amount
                int rand = (int)((Math.random()*31)*10)+100;
                return rand;
            }
        }
        else if (tool.handScore(hand)==5){
            if (currentBet>1100+devianceHundred){
                return -1;
            }
            else if (currentBet>450+devianceHundred){
                return 0;
            }
            else if (currentBet>49){
                //raise amount
                int rand = (int)((Math.random()*31)*10)+100;
                return rand;
            }
        }
        else if (tool.handScore(hand)==6){
            if (currentBet>1150+devianceHundred){
                return -1;
            }
            else if (currentBet>500+devianceHundred){
                return 0;
            }
            else if (currentBet>49){
                //raise amount
                int rand = (int)((Math.random()*31)*10)+100;
                return rand;
            }
        }
        else if (tool.handScore(hand)==7){
            if (currentBet>1400+oddDeviance){
                return -1;
            }
            else if (currentBet>800+devianceHundred){
                return 0;
            }
            else if (currentBet>49){
                //raise amount
                int rand = (int)((Math.random()*31)*10)+100;
                return rand;
            }
        }
        else if (tool.handScore(hand)==8){
            if (currentBet>700+devianceHundred){
                return 0;
            }
            else if (currentBet>49){
                //raise amount
                int rand = (int)((Math.random()*31)*10)+100;
                return rand;
            }
        }
        else if (tool.handScore(hand)==9){
            if (currentBet>800+devianceHundred){
                return 0;
            }
            else if (currentBet>49){
                //raise amount
                int rand = (int)((Math.random()*31)*10)+100;
                return rand;
            }
        }
        return 0;
    }

    /*
     * indexOfCardsBurned (int[] hand)
     * 
     * Determines the indexes in the CPU's hand array that the CPU wants to burn.  The cards that are burned are of course dependent on their value to the
     * hand.  For instance, a CPU will never burn a pair, two-pair, etc.  Usually, if they already have a pair or some other hand, they will burn everything
     * but the pair and the highest card in their hand.
     * 
     * @param int[] hand: The CPU's current hand.
     * @return int[] indexOfCardsBurned: An array that contains all of the indexes in the hand array that the CPU will burn.  indexesOfCardsBurned.length
     *                                   would be the amount of cards burned.
     * 
     */
    public int[] indexOfCardsBurned(int[] hand){
        int[] values= new int[hand.length];
        int[] universal = new int[4];
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
        if (tool.handScore(hand)==1){
            int ind=0;
            while (true){
                if (values[ind]<8){
                    break;
                }
                ind++;
            }
            if (ind > 3){
                ind = 3;
            }
            universal = new int[4-ind];
            for (int i=0; i<hand.length; i++){
                if (universal.length == 4){
                    if (hand[i]%13!=values[0]){
                        universal[index] = i;
                        index++;
                    }
                }
                else if (universal.length == 3){
                    if (hand[i]%13!=values[0] && hand[i]%13!=values[1]){
                        universal[index] = i;
                        index++;
                    }
                }
                else if (universal.length == 2){
                    if (hand[i]%13!=values[0] && hand[i]%13!=values[1] && hand[i]%13!=values[2]){
                        universal[index] = i;
                        index++;
                    }
                }
                else if (universal.length == 1){
                    if (hand[i]%13!=values[0] && hand[i]%13!=values[1] && hand[i]%13!=values[2] && hand[i]%13!=values[3]){
                        universal[index] = i;
                        index++;
                    }
                }
            }
            return universal;
        }
        else if (tool.handScore(hand)==2){
            int highInd = 0;
            int count = 0;
            for (int i=0; i<values.length; i++){
                if (values[i]!=tool.pair(hand)){
                    if (values[i] > values[highInd]){
                        highInd = i;
                    }
                    if (values[i]<8){
                        count++;
                    }
                }
                else if (i==0){
                    highInd = 2;
                }
            }
            if (count==3){
                universal = new int[3];
            }
            else if (count<3){
                universal = new int[2];
            }
            universal = new int[2];
            for (int i=0; i<hand.length; i++){
                if (universal.length==3){
                    if (hand[i]%13!=tool.pair(hand)){
                        universal[index] = i;
                        index++;
                    }
                }
                else if (universal.length==2){
                    if (hand[i]%13!=values[highInd] && hand[i]%13!=tool.pair(hand)){
                        universal[index] = i;
                        index++;
                    }
                }
            }
        }
        else if (tool.handScore(hand)==3){
            universal = new int[1];
            String conc = "" + tool.twoPair(hand);
            int pair1;
            int pair2;
            if (conc.length()==2){
                pair1 = Integer.parseInt(conc.substring(0,1));
                pair2 = Integer.parseInt(conc.substring(1));
            }
            else if (conc.length()==3){
                pair1 = Integer.parseInt(conc.substring(0,2));
                pair2 = Integer.parseInt(conc.substring(2));
            }
            else{
                pair1 = Integer.parseInt(conc.substring(0,2));
                pair2 = Integer.parseInt(conc.substring(2));
            }
            for (int i=0; i<hand.length; i++){
                if (hand[i]%13!=pair1 && hand[i]%13!=pair2){
                    universal[0] = i;
                }
            }
        }
        else if (tool.handScore(hand)==4){
            int count = 0;
            int highInd = 0;
            for (int i=0; i<values.length; i++){
                if (values[i]!=tool.triple(hand)){
                    if (values[i] > values[highInd]){
                        highInd = i;
                    }
                    if (values[i]<8){
                        count++;
                    }
                }
                else if (i==0){
                    highInd = 3;
                }
            }
            if (count>1){
                universal = new int[2];
            }
            else{
                universal = new int[1];
            }
            for (int i=0; i<hand.length; i++){
                if (universal.length==1){
                    if (hand[i]%13!=tool.triple(hand) && hand[i]%13!=values[highInd]){
                        universal[index] = i;
                        index++;
                    }
                }
                else{
                    if (hand[i]%13!=tool.triple(hand)){
                        universal[index] = i;
                        index++;
                    }
                }
            }
        }
        else if (tool.handScore(hand)==5){
            universal = new int[1];
            universal[0] = -1;
        }
        else if (tool.handScore(hand)==6){
            universal = new int[1];
            universal[0] = -1;
        }
        else if (tool.handScore(hand)==7){
            universal = new int[1];
            universal[0] = -1;
        }
        else if (tool.handScore(hand)==8){
            int count = 0;
            universal = new int[1];
            if (values[0]!=tool.quadrouple(hand)){
                if (values[0]<8){
                    count++;
                }
            }
            else{
                if (values[4]<8){
                    count++;
                }
            }
            if (count==0){
                universal[0]=-1;
            }
            else{
                for (int i=0; i<hand.length; i++){
                    if (hand[i]!=tool.quadrouple(hand)){
                        universal[0] = i;
                    }
                }
            }
        }
        else if (tool.handScore(hand)==9){
            universal = new int[1];
            universal[0] = -1;
        }
        return universal;
    }
}