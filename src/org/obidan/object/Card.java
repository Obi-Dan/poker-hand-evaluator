package org.obidan.object;

import java.util.Objects;

import org.obidan.type.FaceValue;
import org.obidan.type.Suit;

/**
 * Represents a card of any kind.
 * 
 * @author Obi-Dan
 */
public class Card {

	private final Suit suit;
	private final FaceValue faceValue;

	/**
	 * Constructs a {@link Card}.
	 * 
	 * @param suit      The {@link Suit}. This must never be null.
	 * @param faceValue The {@link FaceValue}. This must never be null.
	 */
	private Card(final Suit suit, final FaceValue faceValue) {

		this.suit = suit;
		this.faceValue = faceValue;
	}

	/**
	 * Creates a {@link Card}.
	 * 
	 * @param suit      The {@link Suit}. This must never be null.
	 * @param faceValue The {@link FaceValue}. This must never be null.
	 * @return Non-null {@link Card}.
	 */
	public static Card create(final Suit suit, final FaceValue faceValue) {

		return new Card(suit, faceValue);
	}

	/**
	 * Creates a {@link Card} from a {@link Card}.
	 * 
	 * @param suit The {@link Card}. This may be null.
	 * @return Possibly null {@link Card}. If the given {@link Card} was non-null
	 *         then this will be non-null.
	 */
	public static Card create(final Card card) {

		if (card == null) {
			return null;
		}

		return new Card(card.getSuit(), card.getFaceValue());
	}

	/**
	 * @return Non-null {@link Suit}.
	 */
	public final Suit getSuit() {

		return this.suit;
	}

	/**
	 * @return Non-null {@link FaceValue}.
	 */
	public final FaceValue getFaceValue() {

		return this.faceValue;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {

		return Objects.hash(this.faceValue, this.suit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {

		if (obj == null) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Card)) {
			return false;
		}

		final Card other = (Card) obj;
		return this.faceValue == other.faceValue && this.suit == other.suit;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		return new StringBuilder().append(this.faceValue).append(this.suit).toString();
	}
}