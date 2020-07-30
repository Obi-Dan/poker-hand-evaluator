Poker Hand Evaluator

Overview
--------
This program will read a list of player names from an input file,
assign each player a set of 5 random cards from a deck, display all
players with their cards, and declare a winner based on the ranking
of their poker hands.

Needed Software
-------------
The Java Runtime version 8 or higher is required.

How to run the application
--------------------------	
At the command line type: java -jar poker-hand-evaluator.jar <filename>
Example: java -jar poker-hand-evaluator.jar Input1.txt

Assumptions
-----------
- Each line in the given file will be treated as a player name.
- The only supported characters for player names are unicode letters, numbers,
and whitespace along with commas, periods, and hyphens.
- Blank (only whitespace) player names will be ignored.
- In the event of a tie a tie will be declared instead of a single winner.
- A maximum of 10 players are allowed and any remaining players over 10 will
be dropped.
- Ties are broken first by hand rank and then by card rank. Ties will not be broken by suit.

Rationale for decisions
-----------------------
- A custom, specialized collection was chosen for the deck which essentially
represents a stack of cards with the following additional features:
    - The deck comes initialized with valid cards (based on the number of decks needed).
    - The deck has a custom operation for shuffling itself.
    - Multiple cards may be removed from the deck at one time rather than a singular pop operation.