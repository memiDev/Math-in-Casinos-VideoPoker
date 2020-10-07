package blackjack;


// TODO: Auto-generated Javadoc
/**
 * The Class Dealer.
 */
public class Dealer{
	
	/** The dealerhand. */
	DealerHand dealerhand;
	
	/** The blackjack. */
	boolean blackjack;

	/**
	 * Instantiates a new dealer.
	 *
	 * @param c2 the c2
	 */
	Dealer(Card[] c2){
		dealerhand = new DealerHand(c2, this);
		this.blackjack = dealerhand.getCheck();
	}
 	
}
