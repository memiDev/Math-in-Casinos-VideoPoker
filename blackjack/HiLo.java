package blackjack;

// TODO: Auto-generated Javadoc
/**
 * The Class HiLo.
 */
public class HiLo {

	/** The game. */
	Game game;

	/**
	 * Instantiates a new hi lo.
	 *
	 * @param game the game
	 */
	public HiLo(Game game) {
		this.game = game;
	}

	/**
	 * Count card.
	 *
	 * @param total_game the total_game
	 * @return the int
	 */
	public int countCard(int total_game) { // é necessário que entre o
											// total_game porque e do deck a
											// contagem das cartas
		int card_player = 0;
		int card_dealer = 0;

		for (int i = 0; i < game.player.idxNextHand; i++) {
			for (int z = 0; z < game.player.playerhand[i].getSize(); z++) {
				card_player = game.player.playerhand[i].getCard(z).getNumber();
				if (card_player >= 2 && card_player <= 6) {
					total_game += 1;
				}
				if (card_player == 1 || card_player == 10) {
					total_game = total_game - 1;
				}
			}
		}
		

		card_dealer = game.dealer.dealerhand.getCard(0).getNumber();
		if (card_dealer >= 2 && card_dealer <= 6) {
			total_game += 1;
		}
		if (card_dealer == 1 || card_dealer == 10) {
			total_game = total_game - 1;
		}


		return total_game;

	}

	/**
	 * Hi lo_advice.
	 *
	 * @param true_count the true_count
	 * @param handPlayer the hand player
	 * @return the string
	 */
	public String hiLo_advice(float true_count, int handPlayer) { // esta true_count tem
															// que ser a
															// total_game/numero
															// de decks
		String command = "";

		int total_player = game.player.playerhand[handPlayer].sum_Cards();
		int card_player1 = game.player.playerhand[handPlayer].getCard(0).getNumber();
		int card_player2 = game.player.playerhand[handPlayer].getCard(1).getNumber();
		int card_dealer = game.dealer.dealerhand.getCard(0).getNumber();

		if (total_player == 16) {
			if (card_dealer == 10) {
				if (true_count >= 0) {
					command = command.concat("stand");
				} else {
					command = command.concat("hit");
				}
			}

			if (card_dealer == 9) {
				if (true_count >= 5) {
					command = command.concat("stand");
				} else {
					command = command.concat("hit");
				}
			}
		}

		if (total_player == 15) {
			if (card_dealer == 10) {
				if (true_count >= 0 && true_count <= 3) {
					command = command.concat("surrender");
				} else if (true_count >= 4) {
					command = command.concat("stand");
				} else {
					command = command.concat("hit");
				}
			}
			
			if(card_dealer == 9){
				if(true_count >= 2){
					command = command.concat("surrender");
				}else{
					command = command.concat("basic");
				}
			}
			
			if(card_dealer == 1){
				if(true_count >= 1){
					command = command.concat("surrender");
				}else{
					command = command.concat("basic");
				}
			}
		}

		if (card_player1 == 10 && card_player2 == 10) {
			if (card_dealer == 5) {
				if (true_count >= 5) {
					command = command.concat("split");
				} else {
					command = command.concat("stand");
				}
			}

			if (card_dealer == 6) {
				if (true_count >= 4) {
					command = command.concat("split");
				} else {
					command = command.concat("stand");
				}
			}
		}

		if (total_player == 10) {
			if (card_dealer == 10) {
				if (true_count >= 4) {
					command = command.concat("double");
				} else {
					command = command.concat("hit");
				}
			}

			if (card_dealer == 1) {
				if (true_count >= 4) {
					command = command.concat("double");
				} else {
					command = command.concat("hit");
				}
			}
		}

		if (total_player == 12) {
			if (card_dealer == 3) {
				if (true_count >= 2) {
					command = command.concat("stand");
				} else {
					command = command.concat("hit");
				}
			}

			if (card_dealer == 2) {
				if (true_count >= 3) {
					command = command.concat("stand");
				} else {
					command = command.concat("hit");
				}
			}

			if (card_dealer == 4) {
				if (true_count >= 0) {
					command = command.concat("stand");
				} else {
					command = command.concat("hit");
				}
			}

			if (card_dealer == 5) {
				if (true_count >= -2) {
					command = command.concat("stand");
				} else {
					command = command.concat("hit");
				}
			}

			if (card_dealer == 6) {
				if (true_count >= -1) {
					command = command.concat("stand");
				} else {
					command = command.concat("hit");
				}
			}
		}

		if (total_player == 11) {
			if (card_dealer == 1) {
				if (true_count >= 1) {
					command = command.concat("double");
				} else {
					command = command.concat("hit");
				}
			}
		}

		if (total_player == 9) {
			if (card_dealer == 2) {
				if (true_count >= 1) {
					command = command.concat("double");
				} else {
					command = command.concat("hit");
				}
			}

			if (card_dealer == 7) {
				if (true_count >= 3) {
					command = command.concat("double");
				} else {
					command = command.concat("hit");
				}
			}
		}

		if (total_player == 13) {
			if (card_dealer == 2) {
				if (true_count >= -1) {
					command = command.concat("stand");
				} else {
					command = command.concat("hit");
				}
			}

			if (card_dealer == 3) {
				if (true_count >= -2) {
					command = command.concat("stand");
				} else {
					command = command.concat("hit");
				}
			}
		}

		// fab4

		if (total_player == 14) {
			if (card_dealer == 10) {
				if (true_count >= 3) {
					command = command.concat("surrender");
				} else {
					command = command.concat("basic");
				}
			}
		}

		if (true_count >= 3) {
			if (command.isEmpty()) {
				command = command.concat("Insurance");
			} else {
				command = command.concat(" Insurance");
			}
		}

		if(command.isEmpty()){
			return "error";
		}else{
			return command;
		}
		
	}

}
