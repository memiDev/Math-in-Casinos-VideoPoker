package blackjack;

import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * The Class BasicHand.
 */
public abstract class BasicHand {

	/** The cards. */
	LinkedList<Card> cards;
	
	/** The blackjack. */
	boolean blackjack;

	/**
	 * Instantiates a new basic hand.
	 *
	 * @param c2 the c2
	 * @param blackjack the blackjack
	 */
	public BasicHand(Card[] c2, boolean blackjack) {
		this.cards = new LinkedList<Card>();

		for (int i = 0; i < c2.length; i++) {
			hit(c2[i]);
		}
		this.blackjack = blackjack;
	}

	/**
	 * Hit.
	 *
	 * @param C the c
	 */
	void hit(Card C) {
		this.cards.add(C);
	}

	/**
	 * Check blackjack.
	 *
	 * @return true, if successful
	 */
	boolean checkBlackjack() {
		if (this.sum_Cards() == 21 && this.cards.size() == 2)
			return true;
		return false;
	}

	/**
	 * Stand.
	 */
	void stand() {}

	/**
	 * Prints the hand.
	 *
	 * @return the string
	 */
	public String printHand() {
		return null;
	}

	/**
	 * Gets the hand.
	 *
	 * @return the hand
	 */
	LinkedList<Card> getHand(){
		return this.cards;
	}

	/**
	 * Gets the card.
	 *
	 * @param index the index
	 * @return the card
	 */
	Card getCard(int index){
		return cards.get(index);
	}

	/**
	 * Sum_ cards.
	 *
	 * @return the int
	 */
	int sum_Cards() {
		int total =0;
		int checkace;
		int numace=0;
		for(Card a : cards){
			checkace = a.getNumber();
			if(checkace == 1){
				numace++;
				if((total+11) <= 21){
					if(numace>=2){
						total=0;
						for(Card b:cards){
							checkace = b.getNumber();
								total+=checkace;
						}
					}else{
						total =total+11;
					}
				}else{
					total =total+1;
				}
			}else{
				if(numace==0){
					total=total +checkace;
				}else{
					if((total+checkace) <= 21){
						total=total +checkace;
					}else{
						total=0;
						for(Card b:cards){
							checkace = b.getNumber();
								total+=checkace;
						}
					}
				}
				
			}
		}
		return total;
	}

	/**
	 * Check_bust.
	 *
	 * @return true, if successful
	 */
	boolean check_bust() {
		if (this.sum_Cards() > 21) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	int getSize(){
		return cards.size();
	}
	
	/**
	 * Gets the first.
	 *
	 * @return the first
	 */
	Card getFirst(){
		return cards.getFirst();
	}
	
	/**
	 * Gets the last.
	 *
	 * @return the last
	 */
	Card getLast(){
		return cards.getLast();
	}
	
}
