package org.obidan.type;

import org.obidan.object.Card;

/**
 * Enumeration representing the suit of a {@link Card}.
 * 
 * @author Obi-Dan
 */
public enum Suit {
	DIAMONDS(0x2666), CLUBS(0x2663), HEARTS(0x2665), SPADES(0x2660);

	private final String symbol;

	/**
	 * Constructs a {@link Suit}.
	 * 
	 * @param symbolUnicodeCharacter The unicode character number for the suit
	 *                               symbol. This must be positive.
	 */
	private Suit(final int symbolUnicodeCharacter) {
		this.symbol = Character.toString((char) symbolUnicodeCharacter);
	}

	/**
	 * @return Non-null symbol.
	 */
	public final String getSymbol() {
		return this.symbol;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return getSymbol();
	}
}