package org.obidan.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.obidan.type.FaceValue;
import org.obidan.type.Suit;

/**
 * {@link Collection} representing a deck of cards. Note that this collection is
 * not thread safe.
 * 
 * @author Obi-Dan
 */
public class Deck implements Collection<Card> {

	private static final int STANDARD_DECK_SIZE = 52;
	private final Card[] cards;
	private int size = 0;

	/**
	 * Constructs a {@link Deck}.
	 * 
	 * @param numberOfDecks The number of decks of cards to be in this single deck
	 *                      (a 52 card deck is assumed). This must be positive.
	 */
	private Deck(final short numberOfDecks) {

		this.cards = initializeCards(numberOfDecks);
	}

	/**
	 * Creates an array of {@link Card} for the {@link Deck}.
	 * 
	 * @param numberOfDecks The number of decks of cards to be in this single deck
	 *                      (a 52 card deck is assumed). This must be positive.
	 * @return Non-null, non-empty array of non-null {@link Card} for the
	 *         {@link Deck}.
	 */
	private Card[] initializeCards(final short numberOfDecks) {

		final Card[] cards = new Card[STANDARD_DECK_SIZE * numberOfDecks];

		int cardIndex = 0;
		for (short deckIndex = 0; deckIndex < numberOfDecks; deckIndex++) {
			for (final Suit suit : Suit.values()) {
				for (final FaceValue faceValue : FaceValue.values()) {
					this.size++;
					cards[cardIndex++] = Card.create(suit, faceValue);
				}
			}
		}

		return cards;
	}

	/**
	 * Creates a {@link Deck}.
	 * 
	 * @return Non-null, non-empty single {@link Deck}.
	 */
	public static Deck createSingleDeck() {

		return new Deck((short) 1);
	}

	/**
	 * Removes one or more {@link Card}s from the {@link Deck}.
	 * 
	 * @param numberOfCardsToRemove The number of {@link Card} to remove. This must
	 *                              positive and may not exceed the size of the
	 *                              {@link Deck}.
	 * @return Non-null, non-empty {@link List} of non-null {@link Card}s that will
	 *         contain exactly the same number of {@link Card}s as the given input.
	 * @throws IllegalArgumentException If the number of {@link Card}s to remove
	 *                                  exceeds the current size of the
	 *                                  {@link Deck}.
	 */
	public List<Card> remove(final int numberOfCardsToRemove) {

		if (numberOfCardsToRemove <= 0) {
			throw new IllegalArgumentException("Must remove at least one card from the deck.");
		}

		if (numberOfCardsToRemove > size()) {
			throw new IllegalArgumentException("Cannot remove more cards that exist in the deck (numberOfCardsToRemove="
					+ numberOfCardsToRemove + ",size=" + size() + ").");
		}

		final int newSize = size() - numberOfCardsToRemove;
		final List<Card> removedCards = new ArrayList<>(numberOfCardsToRemove);

		for (int cardIndex = newSize; cardIndex < size(); cardIndex++) {
			removedCards.add(this.cards[cardIndex]);
		}

		this.size = newSize;

		return removedCards;
	}

	/**
	 * Shuffles the {@link Deck} using a Fisher–Yates shuffle.
	 */
	public void shuffle() {

		final Random random = ThreadLocalRandom.current();

		for (int cardIndex = size() - 1; cardIndex > 0; cardIndex--) {
			final int randomCardIndex = random.nextInt(cardIndex + 1);
			final Card randomCard = this.cards[randomCardIndex];
			final Card currentCard = this.cards[cardIndex];
			this.cards[cardIndex] = randomCard;
			this.cards[randomCardIndex] = currentCard;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {

		return this.size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {

		return size() == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(final Object object) {

		if (object == null) {
			// No null cards are permitted in a deck.
			return false;
		}

		for (int cardIndex = 0; cardIndex < size(); cardIndex++) {
			final Card card = this.cards[cardIndex];
			if (object.equals(card)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<Card> iterator() {

		return new Iterator<Card>() {
			private int cardIndex = 0;

			@Override
			public boolean hasNext() {
				return this.cardIndex < size() - 1;
			}

			@Override
			public Card next() {
				return Deck.this.cards[this.cardIndex++];
			}
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {

		final Card[] returnCards = new Card[this.cards.length];

		for (int cardIndex = 0; cardIndex < this.cards.length; cardIndex++) {
			returnCards[cardIndex] = Card.create(this.cards[cardIndex]);
		}

		return returnCards;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T[] toArray(final T[] array) {

		throw new UnsupportedOperationException(
				"toArray(T[]) is not supported by this collection. Use toArray() instead.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(final Card card) {

		throw new UnsupportedOperationException("Cards may not be added to a deck.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean remove(final Object object) {

		throw new UnsupportedOperationException(
				"Cards may only be removed from a deck by using the remove(int) method.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsAll(final Collection<?> collection) {

		int findCount = size();

		for (final Object object : collection) {
			for (final Card card : this) {
				if (card.equals(object)) {
					findCount--;
					break;
				}
			}
		}
		return findCount <= 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addAll(final Collection<? extends Card> collection) {

		throw new UnsupportedOperationException("Cards may not be added to a deck.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeAll(final Collection<?> c) {

		throw new UnsupportedOperationException(
				"Cards may only be removed from a deck by using the remove(int) method.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean retainAll(final Collection<?> c) {

		throw new UnsupportedOperationException("Retaining cards in the deck is not supported.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {

		this.size = 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		final StringBuilder stringBuilder = new StringBuilder("Deck[size=").append(this.size).append(",cards=[");

		boolean firstCard = true;
		for (final Card card : this) {
			if (firstCard) {
				firstCard = false;
			} else {
				stringBuilder.append(", ");
			}

			stringBuilder.append(card);
		}

		return stringBuilder.append("]]").toString();
	}
}