package org.obidan.type;

import org.obidan.object.Card;

/**
 * Enumeration representing the face value of a {@link Card}.
 * 
 * @author Obi-Dan
 */
public enum FaceValue {
	TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"), SIX(6, "6"), SEVEN(7, "7"), EIGHT(8, "8"), NINE(9, "9"),
	TEN(10, "10"), JACK(11, "J"), QUEEN(12, "Q"), KING(13, "K"), ACE(14, "A");

	private final int numericValue;
	private final String symbol;

	/**
	 * Constructs a {@link FaceValue}.
	 * 
	 * @param numericValue The numeric value for the face value. This must be
	 *                     positive.
	 * @param symbol       The name of the symbol. This must never be blank.
	 */
	private FaceValue(final int numericValue, final String symbol) {
		this.numericValue = numericValue;
		this.symbol = symbol;
	}

	/**
	 * @return Positive numeric value for the face value.
	 */
	public final int getNumericValue() {
		return this.numericValue;
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