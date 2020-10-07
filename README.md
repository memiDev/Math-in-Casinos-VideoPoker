# Math-in-Casinos-VideoPoker
VideoPoker is a casino game where the prize is given by a combination of five cards that the player can make after receiving an initial 'hand' and deciding which cards to discard and which to hold.

The variant played is called Double Bonus 10/7, where the theoretical return is 100.2%. In addition to the simulation of the game, it is possible to get advice designed through a strategy, which defines which cards to keep.

## Packages
In order to organise and make the code more accessible, it is divided into seven packages: cardgame, poker, advice, payout, mode, readers and main. This type of distribution allows the use and redefinition of classes for other projects.

### cardgame
This package consists of Card, Deck, Rank and Suit classes. Being the program thus built, it allows them to be used in any card game. It is also mentioned that in the listed rank there are two names for an ace: HIGHACE and LOWACE. The Deck is usually made up of a "high ace". Further on, if the hand so requires, it is converted into a "low ace".

### poker
The following package consists of the PokerHand, PokerPlayer and VideoPoker classes. The PokerHand class creates the 5-card hands, in which the quality of the hand is checked, being therefore one of the most laborious parts of this project. In the VideoPoker class are presented the rules of play and how each round is processed.

### mode
This package consists of the classes that run the Video Pokers game in one of three different modes: interactive mode (Interactive), simulation mode (Simulation) and debug mode (Debug). An interface, Mode, has been implemented, which defines how a game mode should be implemented, since the behaviour of the various modes is quite similar. In interactive mode, the user chooses the commands using the terminal. In simulation mode, the program starts one until the entered credit or number of rounds is exhausted. In debug mode, both commands and cards are provided in the program input through text files.

### Interactive
In this mode, it is the user who decides the direction of the game, using the finish. It is the only mode in which a method is implemented that receives one from the terminal and interprets it as a string. The user has the option to bet, ask for the cards, choose which cards to keep, ask for advice, view round statistics or exit the program. Of course, the commands have to be executed in a specific order.

### Simulation
Taking into account the strategy defined in the documentation provided, this mode simulates the number of rounds that are desired, or until the credit is exhausted. Finally, it shows the statistics of all the rounds.

### Debug
This method is used to test the program in its interactive mode. The program receives two text files, one of which is the sequence of commands desired and the letters desired for the test. Through this mode it is possible to test the correct functioning of the program.

### advice
This package has only one class which is called Advicer which from the player's hand returns the cards to be kept taking into account the available documentation.

### payout
Like the package, it also has only one class. It returns the payment for the final hand, according to the rules of the game.
 
### readers
In this package are all classes implemented in order to read external text files. It contains CommandReader, FileReader and DeckReader classes and InvalidCommand and InvalidDeck classes that extend the Exception class and are released when an invalid command or deck is received.

### main
Package containing the Main class with the main method and running the game in a certain mode, which is given as input. It also has a class called InvalidInput that determines if the inputs are valid or not.
