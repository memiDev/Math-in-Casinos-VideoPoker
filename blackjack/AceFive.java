package blackjack;

// TODO: Auto-generated Javadoc
/**
 * The Class AceFive.
 */
public class AceFive {

	/** The game. */
	Game game;

	/**
	 * Instantiates a new ace five.
	 *
	 * @param game the game
	 */
	public AceFive(Game game) {
		this.game = game;
	}

	/**
	 * Ace five.
	 *
	 * @param count_acefive the count_acefive
	 * @param lastbet the lastbet
	 * @param max_bet the max_bet
	 * @param minbet the minbet
	 * @return the float
	 */
	public float aceFive(int count_acefive, float lastbet, float max_bet, float minbet) {

		if (count_acefive >= 2) {
			lastbet = lastbet * 2;
		}

		if (count_acefive <= 1) {
			lastbet = minbet;
		}

		if (lastbet >= max_bet) {
			lastbet = max_bet;
		}

		return lastbet;

	}

	/**
	 * Finalcount ace five.
	 *
	 * @param count_acefive the count_acefive
	 * @return the int
	 */
	public int finalcountAceFive(int count_acefive) { 
		
		for(int q=0; q<game.player.idxNextHand; q++){
			for (int i = 0; i < game.player.playerhand[q].getSize(); i++) {
				if (game.player.playerhand[q].getCard(i).getNumber() == 5) {
					count_acefive += 1;
				}
				if (game.player.playerhand[q].getCard(i).getNumber() == 1) {
					count_acefive -= 1;
				}
			}
		}

		for (int l = 0; l < game.dealer.dealerhand.getSize(); l++) {
			if (game.dealer.dealerhand.getCard(l).getNumber() == 5) {
				count_acefive += 1;
			}
			if (game.dealer.dealerhand.getCard(l).getNumber() == 1) {
				count_acefive -= 1;
			}
		}

		return count_acefive;
	}

}
