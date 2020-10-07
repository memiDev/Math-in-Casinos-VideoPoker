package blackjack;

// TODO: Auto-generated Javadoc
/**
 * The Class Card.
 */
public class Card {
	
	/** The suit. */
	private Suit suit;

	/** The number. */
	/*Ace = 1; Jack-King = 11 - 13*/
	private int number;
	
	/**
	 * Instantiates a new card.
	 */
	public Card (){	
	}
	
	/**
	 * Instantiates a new card.
	 *
	 * @param naipe the naipe
	 * @param number the number
	 */
	public Card(Suit naipe, int number){
		this.suit = naipe;
		this.number = number;	
	}
	
	/**
	 * Gets the number.
	 *
	 * @return number
	 */
	public int getNumber(){ //Falta ter em conta o ás valer 1 ou 11
		if(this.number < 10){
			return number;
		}else{
			return 10;
		}
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		String numberS = "Error - toString";
		
		switch(number){
		
		case(1):
			numberS = "A";
			break;
		case(11):
			numberS = "J";
			break;
		case(12):
			numberS = "Q";
			break;
		case(13):
			numberS = "K";
			break;
		default:
			numberS = Integer.toString(this.number);
			break;			
			
		}
		
		return numberS + suit.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (number != other.number){
			
			if(number >= 10 && other.number >= 10)
				return true;
			
			return false;
		}
			
		return true;
	}
	
	
	
}
