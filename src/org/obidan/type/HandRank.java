package org.obidan.type;

import org.obidan.object.Player;

/**
 * Enumeration representing the rank of a {@link Player}'s hand.
 * 
 * @author Obi-Dan
 */
public enum HandRank {
	STRAIGHT_FLUSH(1, "straight flush"), FOUR_OF_A_KIND(2, "four of a kind"), FULL_HOUSE(3, "full house"),
	FLUSH(4, "flush"), STRAIGHT(5, "straight"), THREE_OF_A_KIND(6, "three of a kind"), TWO_PAIR(7, "two pair"),
	ONE_PAIR(8, "one pair"), HIGH_CARD(9, "high card");

	private final int numericValue;
	private final String display;

	/**
	 * Constructs a {@link HandRank}.
	 * 
	 * @param numericValue The numeric value for the hand rank. This must be
	 *                     positive.
	 * @param display      The display of the hand rank. This must never be blank.
	 */
	private HandRank(final int numericValue, final String display) {
		this.numericValue = numericValue;
		this.display = display;
	}

	/**
	 * @return Positive numeric value for the hand rank.
	 */
	public final int getNumericValue() {
		return this.numericValue;
	}

	/**
	 * @return Non-blank display.
	 */
	public final String getDisplay() {
		return this.display;
	}
}