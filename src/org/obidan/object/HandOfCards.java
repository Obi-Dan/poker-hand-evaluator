package org.obidan.object;

import java.util.List;

import org.obidan.type.FaceValue;
import org.obidan.utility.CardFaceValueGrouper;
import org.obidan.utility.SequentialCardFinder;

/**
 * Represents a hand of cards.
 * 
 * @author Obi-Dan
 */
public class HandOfCards {

	private final List<Card> cards;
	private final List<List<Card>> sequentialCardCollections;
	private final List<List<Card>> cardCollectionsGroupedByFaceValue;

	/**
	 * Constructs a {@link HandOfCards}.
	 * 
	 * @param cards                                The {@link List} of player
	 *                                             {@link Card}s. This must never be
	 *                                             null or contain null values.
	 * @param sequentialCardCollections            The collection of the lists of
	 *                                             sequential cards (ignoring suit),
	 *                                             each sorted from highest to
	 *                                             lowest. For instance, if a hand
	 *                                             has the cards with a face value
	 *                                             2, 3, 4, 7, 8 then two lists will
	 *                                             be returned. One list containing
	 *                                             4, 3, 2 and another containing 8
	 *                                             and 7. Ace will be considered
	 *                                             high and will only return as low
	 *                                             in a five-high straight flush.
	 *                                             This must never be null or empty,
	 *                                             the internal card collections
	 *                                             must never be empty or contain
	 *                                             null, and the total number of
	 *                                             elements in the internal
	 *                                             collections must be the same as
	 *                                             the total number of elements in
	 *                                             the player's hand.
	 * @param cardCollectionsGroupedByFaceValueThe list of card collections grouped
	 *                                             by {@link FaceValue}, which will
	 *                                             contain one or more
	 *                                             {@link Card}s. The collections of
	 *                                             cards must be sorted with the
	 *                                             largest collection first, down to
	 *                                             the smallest collection. After
	 *                                             being sorted by size the
	 *                                             collections must be sorted by
	 *                                             {@link FaceValue} (for instance,
	 *                                             if there are two pairs the higher
	 *                                             {@link FaceValue} pair must come
	 *                                             first). This must never be null
	 *                                             or empty or contain null or empty
	 *                                             lists or elements.
	 * @return Non-null {@link HandOfCards}.
	 */
	private HandOfCards(final List<Card> cards, final List<List<Card>> sequentialCardCollections,
			final List<List<Card>> cardCollectionsGroupedByFaceValue) {

		this.cards = cards;
		this.sequentialCardCollections = sequentialCardCollections;
		this.cardCollectionsGroupedByFaceValue = cardCollectionsGroupedByFaceValue;
	}

	/**
	 * Creates a {@link HandOfCards}.
	 * 
	 * @param cards                The {@link List} of player {@link Card}s. This
	 *                             must never be null, empty, or contain null
	 *                             values. This must never be null.
	 * @param sequentialCardFinder The {@link SequentialCardFinder}. This must never
	 *                             be null.
	 * @param cardFaceValueGrouper The {@link CardFaceValueGrouper}. This must never
	 *                             be null.
	 * @return Non-null {@link HandOfCards}.
	 */
	public static HandOfCards create(final List<Card> cards, final SequentialCardFinder sequentialCardFinder,
			final CardFaceValueGrouper cardFaceValueGrouper) {

		final List<List<Card>> sequentialCardCollections = sequentialCardFinder.getSequentialCardCollections(cards);
		final List<List<Card>> cardCollectionsGroupedByFaceValue = cardFaceValueGrouper
				.getCardCollectionsGroupedByFaceValue(cards);
		return new HandOfCards(cards, sequentialCardCollections, cardCollectionsGroupedByFaceValue);
	}

	/**
	 * @return Non-null, non-empty {@link List} of non-null player {@link Card}s.
	 */
	public final List<Card> getCards() {

		return this.cards;
	}

	/**
	 * @return Non-null, non-empty list of sequential card collections, which will
	 *         contain one or more non-null {@link Card}s. All lists will be sorted
	 *         from highest to lowest.
	 */
	public final List<List<Card>> getSequentialCardCollections() {

		return this.sequentialCardCollections;
	}

	/**
	 * @return Non-null, non-empty list of card collections grouped by
	 *         {@link FaceValue}, which will contain one or more non-null
	 *         {@link Card}s. The collections of cards will be sorted with the
	 *         largest collection first, down to the smallest collection. After
	 *         being sorted by size the collections will be sorted by
	 *         {@link FaceValue} (for instance, if there are two pairs the higher
	 *         {@link FaceValue} pair will come first).
	 */
	public final List<List<Card>> getCardCollectionsGroupedByFaceValue() {

		return this.cardCollectionsGroupedByFaceValue;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		return new StringBuilder("HandOfCards[numberOfCards=").append(this.cards.size()).append(",cards=")
				.append(this.cards).append(",sequentialCardCollections=").append(this.sequentialCardCollections)
				.append(",cardCollectionsGroupedByFaceValue=").append(this.cardCollectionsGroupedByFaceValue)
				.append("]").toString();
	}
}