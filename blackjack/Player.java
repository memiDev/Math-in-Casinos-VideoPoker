package blackjack;

import java.util.Arrays;

// TODO: Auto-generated Javadoc
/**
 * The Class Player.
 */
public class Player{
	
	/** The idx next hand. */
	int idxNextHand = 0;
	
	/** The actual hand. */
	int actualHand = 0;
	
	/** The Constant nbMaxHand. */
	static final int nbMaxHand = 4;
	
	/** The playerhand. */
	PlayerHand[] playerhand;
	
	/** The chips. */
	float chips;
	
	/** The surrender check. */
	boolean surrenderCheck = false;

	/**
	 * Instantiates a new player.
	 *
	 * @param chips the chips
	 */
	public Player(float chips){
		this.playerhand = new PlayerHand[nbMaxHand];
		Arrays.fill(this.playerhand, null);
		this.chips = chips;
	}
	
	/**
	 * Player deal.
	 *
	 * @param c2 the c2
	 */
	public void playerDeal(Card[] c2){
		this.playerhand[idxNextHand ++] = new PlayerHand(c2, ++actualHand, this);
	}
	
	/**
	 * Removes the hand.
	 */
	public void removeHand(){
		Arrays.fill(this.playerhand, null);
		this.idxNextHand = 0;
		this.actualHand = 0;
	}
	
	
}
