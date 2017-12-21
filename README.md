# PokerSimulation
Simulates a Five-Card Stud Poker game.  The user plays against a maximum of four different bots, who bet intelligently based on their hand and other information present in-game.

PLAY INSTRUCTIONS: Run the main method in the DRIVER class. From there, playing the game is generally self-explanatory.

*********************************************--------------------------------**********************************************

Class Layout:

DRIVER- The class that brings the program together to actually play a poker game.

ARTIFICIALINTEL- The class that is used to determine any decision a bot may have to make.  For instance, if a bot has to start the betting, this class will determine how much the bot will start out with.  This class also will determine whether a bot calls, raises, or folds depending on the given bet on the table.

DECK- This class is used to represent the deck of cards in the game.  In this class, an array of integers, numbered 0 to 51, is used to represent different cards.  Intervals of 13 in this array is used to represent a single suit, and the value of the card is determined by it's numeric value in the deck modula 13 (therefore 0, 13, 26, and 39 are cards of different suit but all of value 2).

DETECTION- Using the algorithm established in Deck, this class has methods to detect whether or not a given poker hand.  For instance, this class contains methods to determine whether or not a hand contains a pair, two-pair, triple, etc.

HAND- This class is used to represent any hand in the poker game.  This class contains a conversion method that converts a value in the deck or hand to a readible version of the card ("king of spades" would be read "KS").

MECHANICS- This class is used to determine the winner of a given game.
