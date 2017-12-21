import java.util.Scanner;
/**
 * Class: Driver
 * 
 * Contains the main method of the program, and puts together the various classes and methods to construct a working game of Five Card Stud.
 * 
 * Author: Jeremy Levitt
 */
public class Driver
{
    public static boolean playing = true;
    public static boolean raise = true;
    public static int numOfHighBets=0;
    public static int currentBet=0;
    public static int indexOfHighestBet=0;
    public static int startingPlayer;
    public static int count=0;
    public static int pot = 0;
    public static ArtificialIntel ai = new ArtificialIntel();
    public static Scanner sc = new Scanner(System.in);
    public static Mechanics m = new Mechanics();
    public static Hand[] allHands;
    public static Deck d = new Deck();
    public static int[] alreadyBet;
    public static boolean consecutive = true;

    /*
     * Main method of Program
     * 
     * A single user plays five card stud against CPU's for as many rounds until the user wishes to stop. 
     * 
     * Interactions Of Various Classes:
     * The core of the program runs on a numeric-card assigning system (see "Deck" class).
     * Detection of poker hands such as pair, two-pair, triple, straight, etc. can be found in the "Detection" class.
     * Each player is represented in the array allHands.  If there are n amount of players (including the user), allHands.length will be n.
     * allHands[0] is the Hand object representative of the user playing.
     * allHands[n].hand is an array of five integers, each representing a different card based on the algorithm established in the "Deck" class.
     * CPU's are programmed to bet mostly based on their hand (see "ArtificalIntel" Class), but I implimented a random
     * element so the user playing cannot learn what bet corresponds to what hand.  If I did not impliment a random element, and I assigned a 
     * bet value of "x" when the CPU has a pair, then the user would eventually learn in the future that when the CPU bets "x" amount, they have a pair.
     * CPU's are also programmed to call, raise, or fold depending on their hand and the current bet on the table.  However, if the user takes advantage
     * of this and bluffs too much for consecutive multiple rounds, I have programmed the CPU's to call their bluff.
     * At the end of the game, the winner is determined through the method "winner(Hand[] hand)" of the "Mechanics" class, which returns the winner.
     * 
     * Rules of Five Card Stud:
     * In my game, players start with $10,000.  Every player antes $50, then is dealt a hand of 5 cards.  Players bet based on the hand they have.  
     * Each player then will be given the opportunity to exchange up to four cards in their hands to cards from the deck.  This stage is called "burning."
     * After the burning stage, there will be a round of final bets, and then the winner is determined.
     * 
     * Glitches: 
     * Many times, instead of printing out the name of the person playing (i.e. "Player 1, CPU 1, CPU 2, etc.), the program will instead
     * pring out the memory location of the Hand object corresponding to that name.  I believe this is a processing issue, or maybe there is simply not enough
     * RAM allocated to run my program effectively.
     * Sometimes, the program may print out a series of numbers after the final bet occurs.  Still not sure why this happens, as there is no print statement
     * with any of these numbers.
     * 
     * @return: none
     */
    public static void main(String[] args){
        System.out.println("FIVE CARD STUD: POKER");
        System.out.println("The AI is programmed to act somewhat intelligently, so play like you mean it!  Use all information to your advantage.");
        System.out.println("Every player starts with $10,000.");
        System.out.println("How many players, besides yourself? (Max: 4)");
        int num = sc.nextInt();
        allHands = new Hand[num+1];
        for (int i=0; i<=num; i++){
            if (i==0){
                allHands[i]=new Hand("Player 1");
            }
            else{
                allHands[i]=new Hand("CPU " + i);
            }
        }
        while (playing){
            startingPlayer = count;
            raise=true;
            int winnerIndex=0;
            System.out.println("Welcome! Maximum bet is $2,000, minimum bet is $50.");
            System.out.println("Ante-ing is $50.");
            for (int i=0; i<allHands.length; i++){
                System.out.println(allHands[i].name + " has put in $50 as an ante.");
                allHands[i].money -= 50;
                pot += 50;
            }
            d.shuffle();
            d.deal(allHands);
            System.out.println("View your hand, player.\n");
            for (int i=0; i<allHands[0].cards().length; i++){
                System.out.print(allHands[0].cards()[i] + "\t");
            }
            System.out.println("\n\n" + allHands[count].name + " gets to bet first this round.");
            if (count == 0 ){
                while (currentBet<50 || currentBet>2000){
                    System.out.println("How much would you like to bet? (MIN: 50, MAX: 2000)");
                    currentBet = sc.nextInt();
                }
                if (currentBet > 800){
                    numOfHighBets++;
                    consecutive = true;
                }
                allHands[count].money -= currentBet;
                pot += currentBet;
                indexOfHighestBet = 0;
            }
            else{
                currentBet = ai.bet(allHands[count].hand);
                allHands[count].money -= currentBet;
                System.out.println(allHands[count].name + " bets $" + currentBet + ".\n");
                pot += currentBet;
                indexOfHighestBet = count;
            }

            alreadyBet = new int[allHands.length];
            for (int i=0; i<alreadyBet.length; i++){
                alreadyBet[i]=0;
            }
            alreadyBet[count] = currentBet;
            bet(indexOfHighestBet, indexOfHighestBet+1);
            System.out.println("Currently, the total money in the pot is $" + pot + ".");
            raise = true;
            int numOfPlayers=0;
            for (int i=0; i<allHands.length; i++){
                if (allHands[i].inGame){
                    numOfPlayers++;
                }
            }
            if (numOfPlayers!=1){
                while (!allHands[startingPlayer].inGame){
                    startingPlayer++;
                    if (startingPlayer==allHands.length){
                        startingPlayer = 0;
                    }
                }

                System.out.println("The first player to burn will be " + allHands[startingPlayer].name + ".");
                for (int ind=startingPlayer; ind<startingPlayer+allHands.length; ind++){
                    int i = ind % allHands.length;
                    if (allHands[i].inGame){
                        if (i==0){
                            System.out.println("\nIt is your turn to burn.");
                            System.out.println("Now, view your hand again.  Enter the number of cards you wish to burn (4 max).\n");
                            for (int i2=0; i2<allHands[0].cards().length; i2++){
                                System.out.print(allHands[0].cards()[i2] + "\t");
                            }
                            System.out.println("\n1\t2\t3\t4\t5\n");
                            int numBurned = sc.nextInt();
                            System.out.println("Enter the numbers that corresponds to the cards you wish to burn, and press enter after each one.");
                            int[] indexBurned = new int[numBurned];
                            for (int i2=0; i2<indexBurned.length; i2++){
                                indexBurned[i2] = sc.nextInt()-1;
                            }
                            d.burn(indexBurned, allHands[0].hand);
                            System.out.println("Your hand after burning: \n");
                            for (int i2=0; i2<allHands[0].cards().length; i2++){
                                System.out.print(allHands[0].cards()[i2] + "\t");
                            }
                            System.out.println();
                        }
                        else{
                            int[] burned = ai.indexOfCardsBurned(allHands[i].hand);
                            System.out.println(allHands[i].name + " burns " + burned.length + " cards.");
                            d.burn (burned, allHands[i].hand);
                        }
                    }
                }
                currentBet=0;
                startingPlayer = indexOfHighestBet;
                System.out.println("\nNow, there will be a final bet and the winner will be determined.");
                System.out.println("Last round's highest bidder, "+ allHands[startingPlayer].name + " will begin the betting.");

                if (startingPlayer==0){
                    while (currentBet < 50 || currentBet>2000){
                        System.out.println("How much would you like to bet? (MIN: $50, MAX: $2000)");
                        currentBet = sc.nextInt();
                    }
                    allHands[startingPlayer].money -= currentBet;
                    pot += currentBet;
                }
                else{
                    currentBet = ai.bet(allHands[count].hand);
                    allHands[startingPlayer].money -= currentBet;
                    System.out.println(allHands[startingPlayer].name + " bets " + currentBet + ".\n");
                    indexOfHighestBet = startingPlayer;
                    pot += currentBet;
                }
                indexOfHighestBet = startingPlayer;
                alreadyBet[startingPlayer]=currentBet;
                bet(indexOfHighestBet, startingPlayer+1);
                numOfPlayers=0;
                for (int i=0; i<allHands.length; i++){
                    if (allHands[i].inGame){
                        winnerIndex=i;
                        numOfPlayers++;
                    }
                }
                Hand[] handsLeft = new Hand[numOfPlayers];
                int playingIndexes=0;
                for (int i=0; i<allHands.length; i++){
                    if (allHands[i].inGame){
                        handsLeft[playingIndexes] = allHands[i];
                        playingIndexes++;
                    }
                }
                if (numOfPlayers!=1){
                    for (int i=0; i<allHands.length; i++){
                        if (m.winner(handsLeft).name.equals(allHands[i].name)){
                            winnerIndex = i;
                        }
                    }
                }
                allHands[winnerIndex].money += pot;
                for (int r=0; r<allHands.length; r++){
                    System.out.println(allHands[r].name + "'s hand: ");
                    for (int c=0; c<allHands[0].hand.length; c++){
                        System.out.print (allHands[r].cards()[c] + "\t");
                    }
                    System.out.println("\t" + allHands[r].typeOfHand() + "\n");
                    allHands[r].inGame=true;
                }

            }
            System.out.println("\n\n"+allHands[winnerIndex].name + " wins this round!!");
            System.out.println(allHands[winnerIndex].name + " currently has $" + allHands[winnerIndex].money + ".");
            pot = 0;
            count++;
            System.out.println("Keep Playing?  Type 1 for 'yes' and 2 for 'no'.");
            int det = sc.nextInt();
            if (det==2){
                playing = false;
            }
            if (count==allHands.length){
                count=0;
            }
            if (!consecutive){
                numOfHighBets = 0;
            }
        }
    }

    /* MAKE RECURSIVE TMRW INSTEAD OF WHILE LOOP.
     * bet()
     * 
     * Runs through the betting round of the game.  Used twice in the main method.
     * 
     * @param indexOfAlreadyBet: used so a player or CPU does not bet and have to raise the round after.
     *        startingPlayer: used to keep track of whose bet it is
     * @return none
     * 
     */
    public static void bet(int indexOfAlreadyBet, int startingPlayer){
            int actualBet;
            int possibleRaise;
            int index = startingPlayer % allHands.length;
                if (index!=indexOfAlreadyBet){
                    possibleRaise = ai.raise(currentBet, numOfHighBets, allHands[index].hand, true);
                    if (allHands[index].inGame){
                        if (index==0){
                            System.out.println("Please enter 0 to fold, 1 to call, or any other number to raise.  You have $" + allHands[0].money + ".");
                            int determine = sc.nextInt();
                            if (determine==0){
                                allHands[0].inGame = false;
                                bet(indexOfAlreadyBet, index+1);
                            }
                            else if (determine==1){
                                System.out.println(allHands[0].name + " calls.");
                                actualBet = currentBet-alreadyBet[0];
                                pot += actualBet;
                                allHands[0].money -= actualBet;
                                alreadyBet[0] = currentBet;
                                bet(indexOfAlreadyBet, index+1);
                            }
                            else{
                                raise = true;
                                int temp=currentBet;
                                while (currentBet<50 || currentBet>2000){
                                    currentBet = temp;
                                    System.out.println("How much would you like to raise the current highest bet by? (MIN: 50, MAX: 2000)");
                                    determine = sc.nextInt();
                                    temp = currentBet;
                                    currentBet += determine;
                                }
                                actualBet = currentBet - alreadyBet[0];
                                alreadyBet[0] = currentBet;
                                indexOfHighestBet = index;
                                pot += actualBet;
                                bet(index, index+1);
                            }
                        }
                        else if (possibleRaise==0){
                            actualBet = currentBet-alreadyBet[index];
                            alreadyBet[index] = currentBet;
                            allHands[index].money -= actualBet;
                            pot += actualBet;
                            System.out.println(allHands[index].name + " calls, leaving this player with $" + allHands[index].money + ".");
                            bet(indexOfAlreadyBet, index+1);
                        }
                        else if (possibleRaise==-1){
                            System.out.println(allHands[index].name + " folds, with $" + allHands[index].money + ".");
                            allHands[index].inGame = false;
                            bet(indexOfAlreadyBet, index+1);
                        }
                        else{
                            currentBet += possibleRaise;
                            actualBet = currentBet - alreadyBet[index];
                            alreadyBet[index] = currentBet;
                            raise = true;
                            indexOfHighestBet = index;
                            pot += actualBet;
                            allHands[index].money -= actualBet;
                            System.out.println(allHands[index] + " raises to $" + currentBet + ",");
                            bet(index, index+1);
                        }
                    }
                }
                else{
                    
                }
            }
    }


