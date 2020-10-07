package blackjack;

import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * The Class DealerHand.
 */
public class DealerHand extends BasicHand {

	/** The dealer. */
	Dealer dealer;

	/**
	 * Instantiates a new dealer hand.
	 *
	 * @param c2 the c2
	 * @param dealer the dealer
	 */
	public DealerHand(Card[] c2, Dealer dealer) {
		super (c2, false);
		this.dealer = dealer;
	}


	/**
	 * Show card.
	 *
	 * @param hand the hand
	 */
	public void showCard(LinkedList<Card> hand) { // imprime a carta que está
													// virada para baixo do												// dealer
		System.out.println(hand);
	}

	
	/* (non-Javadoc)
	 * @see blackjack.BasicHand#hit(blackjack.Card)
	 */
	@Override
	void hit(Card C) {
		// TODO Auto-generated method stub
		super.hit(C);
	}


	/* (non-Javadoc)
	 * @see blackjack.BasicHand#checkBlackjack()
	 */
	@Override
	boolean checkBlackjack() {
		// TODO Auto-generated method stub
		return super.checkBlackjack();
	}


	/* (non-Javadoc)
	 * @see blackjack.BasicHand#stand()
	 */
	@Override
	void stand() {
		// TODO Auto-generated method stub
		super.stand();
	}


	/* (non-Javadoc)
	 * @see blackjack.BasicHand#getHand()
	 */
	@Override
	LinkedList<Card> getHand() {
		// TODO Auto-generated method stub
		return super.getHand();
	}


	/* (non-Javadoc)
	 * @see blackjack.BasicHand#getCard(int)
	 */
	@Override
	Card getCard(int index) {
		// TODO Auto-generated method stub
		return super.getCard(index);
	}


	/* (non-Javadoc)
	 * @see blackjack.BasicHand#sum_Cards()
	 */
	@Override
	int sum_Cards() {
		// TODO Auto-generated method stub
		return super.sum_Cards();
	}


	/* (non-Javadoc)
	 * @see blackjack.BasicHand#check_bust()
	 */
	@Override
	boolean check_bust() {
		// TODO Auto-generated method stub
		return super.check_bust();
	}


	/* (non-Javadoc)
	 * @see blackjack.BasicHand#getSize()
	 */
	@Override
	int getSize() {
		// TODO Auto-generated method stub
		return super.getSize();
	}

	/* (non-Javadoc)
	 * @see blackjack.BasicHand#printHand()
	 */
	@Override
	public String printHand() { 
		String x = "";
		for (int i = 0; i < super.getSize(); i++) {
			x += this.getCard(i).toString() + " ";
		}
		return x;
	}

	/**
	 * Gets the check.
	 *
	 * @return the check
	 */
	public boolean getCheck() {
		return this.blackjack;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dhand [hand=" + super.getHand() + ", blackjack=" + blackjack + "]";
	}




}
