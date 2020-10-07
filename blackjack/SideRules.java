package blackjack;

// TODO: Auto-generated Javadoc
/**
 * The Interface SideRules.
 */
public interface SideRules {
	
	/**
	 * Hit.
	 *
	 * @param c the c
	 */
	void hit(Card c);
	
	/**
	 * Split.
	 *
	 * @param c2 the c2
	 * @param index the index
	 */
	void split(Card[] c2, int index);
	
	/**
	 * Double down.
	 *
	 * @return true, if successful
	 */
	boolean doubleDown();
}
