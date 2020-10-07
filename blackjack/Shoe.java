package blackjack;

import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

// TODO: Auto-generated Javadoc
/**
 * The Class Shoe.
 */
public class Shoe{
	
	/** The nb decks. */
	int nbDecks;
	
	/** The deck. */
	LinkedList <Card> deck = new LinkedList<Card>();
	
	/** The game. */
	Game game;
	
	/**
	 * Instantiates a new shoe.
	 *
	 * @param game the game
	 */
	public Shoe(Game game){
		this.game = game;
	}
	
	/**
	 * Instantiates a new shoe.
	 *
	 * @param nbDecks the nb decks
	 * @param S the s
	 */
	public Shoe(int nbDecks, boolean S){
		this.nbDecks = nbDecks;
		initializeDeck(S);
	}
	
	/**
	 * New shoe.
	 */
	private void newShoe(){
		
		for(int j = 0; j < this.nbDecks; j++){
			for(Suit naipe: Suit.values()){
				for(int i = 1; i <= 13; i++){
					Card c = new Card(naipe,i);
					this.deck.add(c);			
				}
			}
		}
	}
	
	/**
	 * Initialize deck.
	 *
	 * @param S the s
	 */
	public void initializeDeck(boolean S){	
		newShoe();
		
		if(S){
			shuffleDeck();
		}	
	}
	
	/**
	 * Shuffle deck.
	 */
	public void shuffleDeck(){
		Collections.shuffle(this.deck);	
	}
	
	/**
	 * Removes the top.
	 *
	 * @return the card
	 */
	public Card removeTop(){	
		return this.deck.removeFirst();		
	}
	
	/**
	 * Creates the shoe.
	 *
	 * @param minbet the minbet
	 * @param string the string
	 */
	public void CreateShoe(float minbet, String string) {
		LinkedList<Card> deck = new LinkedList<Card>();
		BufferedReader reader = null;
		Card c = new Card();
		int card;
		String path = System.getProperty("user.dir");

		try {
			reader = new BufferedReader(new FileReader(path + "/" + string));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(0);
		}

		while (true) {
			String line;
			try {
				line = reader.readLine();
				if (line == null)
					break; // end of file
				String[] tokens = line.split("(?<!^)");
				for (int i = 0; i < tokens.length; i = i + 3) {
					if(tokens[i].equals("A")){
						card = 1;
					}else if(tokens[i].equals("J")){
						card = 11;
					}else if(tokens[i].equals("Q")){
						card = 12;
					}else if(tokens[i].equals("K")){
						card = 13;
					}else if (Integer.parseInt(tokens[i]) == 1){
						card = 10;
						i++;
					}else{
						card = Integer.parseInt(tokens[i]);
					}
					Suit suit = Suit.valueOf(tokens[i + 1]);
					c = new Card(suit, card);
					deck.add(c);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(0);
			}

		}
		this.deck = deck;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Deck [shoe=" + deck + "]";
	}
	
}
