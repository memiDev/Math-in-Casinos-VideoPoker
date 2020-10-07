package blackjack;

// TODO: Auto-generated Javadoc
/**
 * The Class Basic.
 */
public class Basic {

	/** The game. */
	Game game;

	/**
	 * Instantiates a new basic.
	 *
	 * @param game the game
	 */
	public Basic(Game game) {
		this.game = game;
	}

	/**
	 * Basic_strategy.
	 *
	 * @param handPlayer the hand player
	 * @return the string
	 */
	public String basic_strategy(int handPlayer) {
		int save_last_card = 0;
		int check_ace = 0;

		for (int l = 0; l < game.player.playerhand[handPlayer].getSize(); l++) {
			if (game.player.playerhand[handPlayer].getSize() <= 2) {
				if (l == 0) {
					save_last_card = game.player.playerhand[handPlayer].getCard(l).getNumber();
				} else {
					if (save_last_card == game.player.playerhand[handPlayer].getCard(l).getNumber()) {
						return "pair";
					}
				}
			}

			if (game.player.playerhand[handPlayer].getCard(l).getNumber() == 1) {
				check_ace++;
			}
		}

		if (check_ace != 0) {
			int sum_ace = 0;
			for (int q = 0; q < game.player.playerhand[handPlayer].getSize(); q++) {
				sum_ace += game.player.playerhand[handPlayer].getCard(q).getNumber(); 
			}
			if (game.player.playerhand[handPlayer].sum_Cards() == sum_ace) { // se alguma vez o ás valer 11, é logo soft!
				return "hard";
			} else {
				return "soft";
			}
		} else {
			return "hard";
		}

	}

	/**
	 * Advice hard.
	 *
	 * @param handPlayer the hand player
	 * @return the string
	 */
	public String adviceHard(int handPlayer) { // mao sem ases e pares que
												// precisa de saber a mao que é
												// para dar o advice

		int total_player = game.player.playerhand[handPlayer].sum_Cards();
		int card_dealer = game.dealer.dealerhand.getCard(0).getNumber();

		if (total_player >= 5 && total_player <= 8) { // 1ª a 4ª linha da tabela
			return "hit";
		}

		if (total_player == 9) { // linha do player 9
			if (card_dealer == 2 || card_dealer >= 7 || card_dealer == 1) {
				return "hit";
			}
			if (card_dealer >= 3 && card_dealer <= 6) {
				return "double";
			}
		}

		if (total_player == 10) { // linha do player 10
			if (card_dealer >= 2 && card_dealer <= 9) {
				return "double";
			}
			if (card_dealer == 10 || card_dealer == 1) {
				return "hit";
			}
		}

		if (total_player == 11) { // linha do player 11
			if (card_dealer >= 2 && card_dealer <= 10) {
				return "double";
			}
			if (card_dealer == 1) {
				return "hit";
			}
		}

		if (total_player == 12) { // linha do player 12
			if (card_dealer == 2 || card_dealer == 3 || card_dealer >= 7 || card_dealer == 1) {
				return "hit";
			}
			if (card_dealer >= 4 && card_dealer <= 6) {
				return "stand";
			}
		}

		if (total_player == 13 || total_player == 14) { // linha do player 13 e
														// 14
			if (card_dealer >= 2 && card_dealer <= 6) {
				return "stand";
			}
			if (card_dealer >= 7 || card_dealer == 1) {
				return "hit";
			}
		}

		if (total_player == 15) { // linha do player 15
			if (card_dealer >= 2 && card_dealer <= 6) {
				return "stand";
			}
			if ((card_dealer >= 7 && card_dealer <= 9) || card_dealer == 1) {
				return "hit";
			}
			if (card_dealer == 10) {
				return "surrender";
			}
		}

		if (total_player == 16) { // linha do player 16
			if (card_dealer >= 2 && card_dealer <= 6) {
				return "stand";
			}
			if (card_dealer == 7 || card_dealer == 8) {
				return "hit";
			}
			if (card_dealer >= 9 || card_dealer == 1) {
				return "surrender";
			}
		}

		if (total_player >= 17 && total_player <= 21) { // 1ª a 4ª linha da
														// tabela
			return "stand";
		}

		return "erro";
	}

	/**
	 * Advice soft.
	 *
	 * @param handPlayer the hand player
	 * @return the string
	 */
	public String adviceSoft(int handPlayer) {

		int total_player = game.player.playerhand[handPlayer].sum_Cards();
		int card_dealer = game.dealer.dealerhand.getCard(0).getNumber();

		if (total_player >= 13 && total_player <= 17) {
			return "hit";
		}

		if (total_player == 18) {
			if (card_dealer >= 2 && card_dealer <= 8) {
				return "stand";
			}
			if (card_dealer == 9 || card_dealer == 10 || card_dealer == 1) {
				return "hit";
			}
		}

		if (total_player >= 19) {
			return "stand";
		}
		return "erro";
	}

	/**
	 * Advice pairs.
	 *
	 * @param handPlayer the hand player
	 * @return the string
	 */
	public String advicePairs(int handPlayer) {

		if (game.player.playerhand[handPlayer].getCard(0).getNumber() != game.player.playerhand[handPlayer].getCard(1)
				.getNumber()) {
			return "erro";
		}

		int pair = game.player.playerhand[handPlayer].getCard(0).getNumber();
		int card_dealer = game.dealer.dealerhand.getCard(0).getNumber();

		if (pair == 2 || pair == 3) {
			if (card_dealer <= 3 || (card_dealer >= 8 && card_dealer <= 10)) {
				return "hit";
			}
			if (card_dealer >= 4 && card_dealer <= 7) {
				return "split";
			}
		}

		if (pair == 4) {
			return "hit";
		}

		if (pair == 5) {
			if (card_dealer >= 2 && card_dealer <= 9) {
				return "double";
			}
			if (card_dealer == 10 || card_dealer == 1) {
				return "hit";
			}
		}

		if (pair == 6) {
			if (card_dealer >= 3 && card_dealer <= 6) {
				return "split";
			}
			if (card_dealer == 2 || card_dealer == 1 || (card_dealer >= 7 && card_dealer <= 10)) {
				return "hit";
			}
		}

		if (pair == 7) {
			if (card_dealer >= 2 && card_dealer <= 7) {
				return "split";
			}
			if (card_dealer == 1 || (card_dealer >= 8 && card_dealer <= 10)) {
				return "hit";
			}
		}

		if (pair == 8 || pair == 1) {
			return "split";
		}

		if (pair == 9) {
			if ((card_dealer >= 2 && card_dealer <= 6) || card_dealer == 8 || card_dealer == 9) {
				return "split";
			}
			if (card_dealer == 1 || card_dealer == 7 || card_dealer == 10) {
				return "stand";
			}
		}

		if (pair == 10) {
			return "stand";
		}

		return "erro";
	}

}
