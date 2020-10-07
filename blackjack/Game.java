package blackjack;

import java.util.LinkedList;

import statistics.Statistics;

public class Game {

	float bet;
	Player player;
	Dealer dealer;
	Shoe shoe;
	boolean insuranceCheck = false;
	Basic basic = new Basic(this);
	HiLo hilo = new HiLo(this);
	AceFive acefive = new AceFive(this);
	Statistics st;
	LinkedList<Card> junk;
	int nbdecks;
	public int running_count;
	public int final_running_count;
	public int count_acefive;

	public Game() {
	};

	/**
	 * @param bet
	 * @param nbdecks
	 */
	public Game(float bet, int nbdecks, float chips) {
		this.bet = bet;
		shoe = new Shoe(nbdecks, true);
		this.st = new Statistics(chips);
		this.junk = new LinkedList<Card>();
		this.nbdecks = nbdecks;
	}

	public boolean reshuffle(float pShuffle) {

		int size = this.junk.size();
		float r = ((float) size / (52 * this.nbdecks)) * 100;
		if (r > pShuffle) {
			for (int i = 0; i < size; i++)
				shoe.deck.add(junk.remove());
			System.out.println("Shuffling the shoe...");
			this.count_acefive = 0;
			this.final_running_count = 0;
			this.running_count = 0;
			shoe.shuffleDeck();
			return true;
		}
		return false;
	}

	/**
	 * @param chips
	 */
	public void deal(float chips) {

		Card[] topPlayer = new Card[2];
		Card[] topDealer = new Card[2];

		for (int i = 0; i < 2; i++) {
			topDealer[i] = this.shoe.removeTop();
		}
		for (int i = 0; i < 2; i++) {
			topPlayer[i] = this.shoe.removeTop();
		}
		if (player == null && dealer == null) {
			player = new Player(chips);
			player.playerDeal(topPlayer);
			dealer = new Dealer(topDealer);
		} else {
			for (int i = 0; i < player.idxNextHand; i++) {
				int size = player.playerhand[i].getSize();
				for (int j = 0; j < size; j++)
					junk.add(player.playerhand[i].cards.remove());
			}
			int size = dealer.dealerhand.getSize();
			for (int i = 0; i < size; i++)
				junk.add(dealer.dealerhand.cards.remove());
			player.removeHand();
			player.playerDeal(topPlayer);
			dealer = new Dealer(topDealer);
			player.removeHand();
			player.playerDeal(topPlayer);
			dealer = new Dealer(topDealer);
		}
	}

	/**
	 * @param bet
	 */
	public void newBet(float bet) {
		this.bet = bet;

	}

	public void playerlose() {
		player.playerhand[player.actualHand - 1].checkWin = 0; // Dealer ganha
	}

	public void stand() {
		player.playerhand[player.actualHand - 1].stand();

	}

	public int dealerPlay() throws Exception {
		this.printHandGame("dealer");
		if (dealer.dealerhand.checkBlackjack() == true) {
			return 2; // dealer fez blackjack
		}

		if (player.playerhand[player.actualHand - 1].checkBlackjack() == true) {
			if (dealer.dealerhand.checkBlackjack() == false) {
				System.out.println("Dealer's stands");
				return 1; // dealer stand
			}
		}

		while (dealer.dealerhand.sum_Cards() < 17) {
			dealer.dealerhand.hit(this.shoe.removeTop());
			System.out.println("Dealer's hit");
			this.printHandGame("dealer");
		}
		if (dealer.dealerhand.sum_Cards() <= 21) {
			System.out.println("Dealer's stands");
			return 1; // dealer stand
		} else {
			System.out.println("Dealer's bust");
			return 0; // dealer bust
		}
	}

	public void hit() {
		System.out.println("player hits");
		player.playerhand[player.actualHand - 1].hit(this.shoe.removeTop());
	}

	public void verifywin() {
		if (player.playerhand[player.actualHand - 1].checkBlackjack())
			player.playerhand[player.actualHand - 1].blackjack = true;
		else
			player.playerhand[player.actualHand - 1].blackjack = false;
	}

	public boolean checkbust() {
		return player.playerhand[player.actualHand - 1].check_bust();
	}

	public boolean splitting() throws Exception {

		if (player.playerhand[player.actualHand - 1].getSize() != 2) {
			return false;

		} else if (!player.playerhand[player.actualHand - 1].getFirst()
				.equals(player.playerhand[player.actualHand - 1].getLast())) {
			return false;

		} else if (player.idxNextHand == 4) {
			return false;

		} else {
			System.out.println("Player is splitting");

			Card[] c2 = new Card[2];

			c2[0] = this.shoe.removeTop();
			c2[1] = this.shoe.removeTop();

			player.playerhand[player.actualHand - 1].split(c2, player.actualHand);
			System.out.println(
					"Playing " + player.playerhand[player.actualHand - 1].printNbHand(player.actualHand) + " hand...");
			printHandGame("player");
		}
		return true;

	}

	public void printHandGame(String string) throws Exception {

		if (string.equals("player")) {
			if (player.idxNextHand == 1) {
				System.out.println("Player's hand " + player.playerhand[player.actualHand - 1].printHand() + "("
						+ player.playerhand[player.actualHand - 1].sum_Cards() + ")");
			} else {
				System.out.println("Player's hand " + "[" + player.actualHand + "] "
						+ player.playerhand[player.actualHand - 1].printHand() + "("
						+ player.playerhand[player.actualHand - 1].sum_Cards() + ")");
			}
		} else if (string.equals("dealer")) {
			System.out.println(
					"Dealer's hand " + dealer.dealerhand.printHand() + "(" + dealer.dealerhand.sum_Cards() + ")");
			if (dealer.dealerhand.checkBlackjack()) {
				System.out.println("blackjack!!");
				st.stats2[1]++;
			}
		} else
			throw new Exception();
	}

	public boolean doublingDown() throws Exception {

		if (player.playerhand[player.actualHand - 1].doubleDown()) {
			hit();
			printHandGame("player");
			this.player.playerhand[player.actualHand -1].doublehand = true;
			return true;

		} else {
			return false;
		}

	}

	public boolean insurance() {
		if (dealer.dealerhand.getCard(0).getNumber() == 1) {
			this.insuranceCheck = true;
			System.out.println("Player is insuring");
			return true;
		} else {
			return false;
		}

	}

	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public float printFinalGame(float chips) {
		if (player.idxNextHand == 1) {
			if (player.playerhand[player.idxNextHand - 1].checkWin == 1) {

				if (player.playerhand[player.idxNextHand - 1].blackjack) {
					chips += 2.5 * this.bet;
					System.out.println("Player wins and his current balance is " + chips);
					st.stats2[0]++;
					st.stats1[0]++;
				} else if (player.playerhand[player.idxNextHand - 1].doublehand) {
					chips += 4*this.bet;
					System.out.println("Player wins and his current balance is " + chips);
					st.stats1[0]++;
				} else {
					chips += 2 * this.bet;
					System.out.println("Player wins and his current balance is " + chips);
					st.stats1[0]++;
				}

			} else if (player.playerhand[player.idxNextHand - 1].checkWin == 0) {
				if (dealer.dealerhand.checkBlackjack() == true && this.insuranceCheck == true) {
					chips += 2 * this.bet;
					System.out.println("Player's current balance is " + chips);
				} else if (player.surrenderCheck || dealer.dealerhand.checkBlackjack()) {
					System.out.println("Player's current balance is " + chips);
				} else {
					System.out.println("Player loses and his current balance is " + chips);
					st.stats1[1]++;
				}

			} else if (player.playerhand[player.idxNextHand - 1].checkWin == 2) {
				chips += this.bet;
				System.out.println("Player pushes and his current balance is " + chips);
				st.stats1[2]++;

			}

		} else {

			for (int i = 0; i < player.idxNextHand; i++) {
				if (player.playerhand[i].checkWin == 1) {
					if (player.playerhand[i].blackjack) {
						chips += 2 * this.bet;
						System.out
								.println("Player wins " + "[" + (i + 1) + "] " + "and his current balance is " + chips);
						st.stats1[0]++;
					} else if (player.playerhand[i].doublehand) {
						chips += 4*this.bet;
						System.out.println("Player wins " + "[" + (i + 1) + "] " + "and his current balance is " + chips);
						st.stats1[0]++;
					} else {
						chips += 2*this.bet;
						System.out
								.println("Player wins " + "[" + (i + 1) + "] " + "and his current balance is " + chips);
						st.stats1[0]++;

					}

				} else if (player.playerhand[i].checkWin == 0) {
					if (dealer.dealerhand.checkBlackjack() == true && this.insuranceCheck == true) {
						chips += 2 * this.bet;
						System.out.println("Player's current balance is " + chips);
					} else if (player.surrenderCheck || dealer.dealerhand.checkBlackjack()) {
						System.out.println("Player's current balance is " + chips);
					} else {
						System.out.println(
								"Player loses " + "[" + (i + 1) + "] " + "and his current balance is " + chips);
						st.stats1[1]++;
					}

				} else if (player.playerhand[i].checkWin == 2) {
					chips += this.bet;
					System.out.println("Player pushes " + "[" + (i + 1) + "] " + "and his current balance is " + chips);
					st.stats1[2]++;

				}

			}

		}
		if (this.insuranceCheck) {
			this.insuranceCheck = false;
		}
		if (this.player.surrenderCheck) {
			this.player.surrenderCheck = false;
		}
		st.stats3[1] = chips;
		return chips;
	}

	public void printFirstHandDealer() {
		System.out.println("Dealer's hand " + dealer.dealerhand.getCard(0) + " X");
	}

	public boolean checkActualHand() {

		if (player.actualHand == (player.idxNextHand))
			return false;

		return true;
	}

	public void incrementPlayerActualHand() {
		player.actualHand++;
	}

	public void printBust() {
		if (player.idxNextHand == 1)
			System.out.println("Player busts");
		else
			System.out.println("Player busts [" + player.actualHand + "]");
	}

	public void checkWhoWins() {

		for (int i = 0; i < player.idxNextHand; i++) {
			if (player.playerhand[i].check_bust()) {
				player.playerhand[i].checkWin = 0;
				continue;
			}
			if (dealer.dealerhand.check_bust()) {
				player.playerhand[i].checkWin = 1;
				continue;
			}
			
			if(dealer.dealerhand.checkBlackjack() == true
					&& player.playerhand[player.actualHand-1].checkBlackjack()== false){ // Se o Dealer fez blackjck e o jogador nao, ganha o dealer
				player.playerhand[i].checkWin = 0;;
				continue;
			}
			if(player.playerhand[player.actualHand-1].checkBlackjack()== true &&
					dealer.dealerhand.checkBlackjack() == true){ // Se o jogador faz blackjack e o dealer nao, ganha o jogoado
				player.playerhand[i].checkWin = 1;
				continue;
			}
			
			if(dealer.dealerhand.checkBlackjack() == true
					&& player.playerhand[player.actualHand-1].checkBlackjack()== true){// se fazem os 2 blackjack, e push
				player.playerhand[i].checkWin = 2;
				continue;
			}

			if (player.playerhand[i].sum_Cards() > dealer.dealerhand.sum_Cards()) { // jogador
				// ganha
				player.playerhand[i].checkWin = 1;
				continue;
			} else if (player.playerhand[i].sum_Cards() < dealer.dealerhand.sum_Cards()) { // Dealer
				// ganha
				player.playerhand[i].checkWin = 0;
				continue;
			} else if (player.playerhand[i].sum_Cards() == dealer.dealerhand.sum_Cards()) { // push
				player.playerhand[i].checkWin = 2;
				continue;
			}

		}
	}

	public void printHandPlaying() {
		System.out.println(
				"Playing " + player.playerhand[player.actualHand - 1].printNbHand(player.actualHand) + " hand...");
	}

	public void surrender() {

		if (player.idxNextHand == 1) {
			player.surrenderCheck = true;
			System.out.println("player is surrendering");
		} else {
			System.out.println("player can't surrender");
		}

	}

	public void printStats() {
		System.out.println(st.N1N2());
		System.out.println(st.N3());
		System.out.println(st.N4());
		System.out.println(st.N5());
		System.out.println(st.N6N7());
	}

	public String basic_advice() {
		String c = "";

		c = basic.basic_strategy(player.actualHand - 1);

		if (c.equals("hard")) {
			c = basic.adviceHard(player.actualHand - 1);
		} else if (c.equals("soft")) {
			c = basic.adviceSoft(player.actualHand - 1);
		} else if (c.equals("pair")) {
			c = basic.advicePairs(player.actualHand - 1);
		}

		return c;
	}

	public String hilo_advice() {
		String c = "";
		float remaining_shoe = (float) shoe.deck.size() / 52;
		this.running_count = hilo.countCard(this.running_count); // falta
																	// colocar o
																	// running
																	// count a
																	// zero
																	// quando
																	// muda de
																	// shoe
		this.running_count += this.final_running_count;
		float true_count = (float) this.running_count / remaining_shoe;
		c = hilo.hiLo_advice(true_count, player.actualHand - 1);
		return c;
	}

	public void hilo_countrestcard() {
		int card_dealer = 0;
		int card_player = 0;
		int total_game = 0;

		for (int i = 1; i < dealer.dealerhand.getSize(); i++) {
			card_dealer = dealer.dealerhand.getCard(i).getNumber();
			if (card_dealer >= 2 && card_dealer <= 6) {
				total_game += 1;
			}
			if (card_dealer == 1 || card_dealer == 10) {
				total_game -= 1;
			}
		}

		for (int i = 0; i < player.idxNextHand; i++) {
			for (int z = 2; z < player.playerhand[i].getSize(); z++) {
				card_player = player.playerhand[i].getCard(z).getNumber();
				if (card_player >= 2 && card_player <= 6) {
					total_game += 1;
				}
				if (card_player == 1 || card_player == 10) {
					total_game = total_game - 1;
				}
			}
		}
		this.running_count += total_game;
	}

	public void count_cards_hilo() {
		this.running_count = hilo.countCard(this.running_count);
	}

	public void setFinalRunningCount() {
		this.final_running_count += this.running_count;
		this.running_count = 0;
	}

	public float acefive(float lastbet, float minbet, float maxbet_game) {

		float maxbet = 32 * minbet;
		if (maxbet > maxbet_game) {
			maxbet = maxbet_game;
		}
		float bet = 0;

		if (player == null) {
			return minbet;
		}

		bet = acefive.aceFive(this.count_acefive, lastbet, maxbet, minbet);

		return bet;
	}

	public void count_cards_acefive() {
		this.count_acefive = acefive.finalcountAceFive(this.count_acefive);
	}

	public void createShoe(float minbet, String string, float chips) {
		Shoe shoe = new Shoe(this);
		shoe.CreateShoe(minbet, string);
		this.bet = minbet;
		this.shoe = shoe;
		this.st = new Statistics(chips);
		this.junk = new LinkedList<Card>();

	}

	public int checkSum() {
		return player.playerhand[player.actualHand - 1].sum_Cards();
	}

	public int simulatewhowins() {
		// 1 jogador ganha 0 dealer ganha
		int sum_wins = 0;
		for (int i = 0; i < player.idxNextHand; i++) {
			if (player.playerhand[i].checkWin == 0) {
				sum_wins--;
			} else if (player.playerhand[i].checkWin == 1) {
				sum_wins++;
			}
		}
		return sum_wins;

	}
}
