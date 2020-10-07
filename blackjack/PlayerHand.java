package blackjack;

import blackjack.PlayerHand;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerHand.
 */
public class PlayerHand extends BasicHand implements SideRules {

	/** The player. */
	Player player;
	
	/** The nb hand. */
	private int nbHand;
	
	/** The check win. */
	int checkWin;
	
	boolean doublehand;

	/**
	 * Instantiates a new player hand.
	 *
	 * @param c2 the c2
	 * @param nbHand the nb hand
	 * @param player the player
	 */
	public PlayerHand(Card[] c2, int nbHand, Player player) {
		super(c2, false);
		this.nbHand = nbHand;
		this.player = player;
		this.checkWin = -1;
		this.doublehand = false;
	}

	/* (non-Javadoc)
	 * @see blackjack.BasicHand#hit(blackjack.Card)
	 */
	@Override
	public void hit(Card C) {
		// TODO Auto-generated method stub
		super.hit(C);
	}

	/* (non-Javadoc)
	 * @see blackjack.BasicHand#checkBlackjack()
	 */
	@Override
	boolean checkBlackjack() {
		// TODO Auto-generated method stub
		if (player.idxNextHand == 1) {
			return super.checkBlackjack();
		} else {
			return false;
		}
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

	/**
	 * Gets the check.
	 *
	 * @return the check
	 */
	public boolean getCheck() {
		return this.blackjack;
	}

	/* (non-Javadoc)
	 * @see blackjack.SideRules#split(blackjack.Card[], int)
	 */
	@Override
	public void split(Card[] c2, int index) {

		if (player.idxNextHand != 4) {

			Card[] h1 = new Card[2];
			Card[] h2 = new Card[2];

			h1[0] = super.getHand().remove();
			h1[1] = c2[0];

			h2[0] = super.getHand().remove();
			h2[1] = c2[1];

			this.player.playerhand[index - 1] = new PlayerHand(h1, index, player);

			if (index < this.player.idxNextHand) {

				for (int i = player.idxNextHand; i > index; i--) {

					this.player.playerhand[i] = this.player.playerhand[i - 1];
					this.player.playerhand[i].nbHand = i + 1;

				}

				this.player.playerhand[index] = new PlayerHand(h2, index + 1, player);

			} else
				this.player.playerhand[index] = new PlayerHand(h2, index + 1, player);

			this.player.idxNextHand++;

		} 

	}

	/* (non-Javadoc)
	 * @see blackjack.BasicHand#getFirst()
	 */
	@Override
	Card getFirst() {
		// TODO Auto-generated method stub
		return super.getFirst();
	}

	/* (non-Javadoc)
	 * @see blackjack.BasicHand#getLast()
	 */
	@Override
	Card getLast() {
		// TODO Auto-generated method stub
		return super.getLast();
	}

	/* (non-Javadoc)
	 * @see blackjack.BasicHand#getSize()
	 */
	@Override
	int getSize() {
		// TODO Auto-generated method stub
		return super.getSize();
	}

	/**
	 * Prints the nb hand.
	 *
	 * @param index the index
	 * @return the string
	 */
	String printNbHand(int index) {

		String s;

		switch (index) {
		case 1:
			s = "st";
			break;
		case 2:
			s = "nd";
			break;
		case 3:
			s = "rd";
			break;
		default:
			s = "th";
			break;
		}

		return index + s;
	}

	/*
	 * O stand faz qq coisa!!
	 */

	/* (non-Javadoc)
	 * @see blackjack.BasicHand#stand()
	 */
	@Override
	public void stand() {

		if (player.idxNextHand == 1)
			System.out.println("Player stands");
		else
			System.out.println("Player stands [" + player.actualHand + "]");

	}
	/*
	 * Double down deve pertencer ao player!!!
	 */

	/* (non-Javadoc)
	 * @see blackjack.SideRules#doubleDown()
	 */
	@Override
	public boolean doubleDown() {
		if (sum_Cards() >= 9 && sum_Cards() <= 11 && this.getSize() == 2) {
			return true;
		} else
			return false;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Playerhand [hand=" + super.getHand() + ", nbHand=" + nbHand + ", blackjack=" + blackjack + "]";
	}

}
