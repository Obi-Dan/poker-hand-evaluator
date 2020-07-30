package org.obidan.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.obidan.object.Card;
import org.obidan.type.FaceValue;

/**
 * Finds sequential cards in a collection of cards.
 * 
 * @author Obi-Dan
 */
public class SequentialCardFinder {

	/**
	 * Gets a collection of the lists of sequential cards (ignoring suit), each
	 * sorted from highest to lowest. For instance, if a hand has the cards with a
	 * face value 2, 3, 4, 7, 8 then two lists will be returned. One list containing
	 * 4, 3, 2 and another containing 8 and 7. Ace will be considered high and will
	 * only return as low in a five-high straight flush.
	 * 
	 * @param cards The list of cards from which to get the sequential cards. This
	 *              must never be null or empty or contain null values.
	 * @return Non-null, non-empty list of sequential card collections, which will
	 *         contain one or more non-null {@link Card}s. All lists will be sorted
	 *         from highest to lowest.
	 */
	public List<List<Card>> getSequentialCardCollections(final List<Card> cards) {

		final List<Card> sortedCards = cards.stream().sorted((card1, card2) -> Integer
				.compare(card1.getFaceValue().getNumericValue(), card2.getFaceValue().getNumericValue()))
				.collect(Collectors.toList());

		final List<List<Card>> sequentialCardCollections = new ArrayList<>();
		List<Card> sequentialCards = null;

		for (int index = sortedCards.size() - 1; index >= 0; index--) {
			final Card previousCard = index < sortedCards.size() - 1 ? sortedCards.get(index + 1) : null;
			final Card card = sortedCards.get(index);

			if (previousCard != null
					&& previousCard.getFaceValue().getNumericValue() - 1 == card.getFaceValue().getNumericValue()) {
				sequentialCards.add(card);
			} else {
				sequentialCards = new ArrayList<>();
				sequentialCardCollections.add(sequentialCards);
				sequentialCards.add(card);
			}
		}

		return adjustCardCollectionsForLowAce(sequentialCardCollections);
	}

	/**
	 * Adjusts the card collections for cases when the ace should be low (for
	 * instance, a five-high straight with a 5, 4, 3, 2, and an ace). This is
	 * handled as special cases to reduce the complexity of this application.
	 * 
	 * @param sequentialCardCollections The list of {@link Card} lists. This must
	 *                                  never be null or contain null values.
	 * @return Non-null, non-empty list of sequential card collections, which will
	 *         contain one or more non-null {@link Card}s. All lists will be sorted
	 *         from highest to lowest and the cases for a low ace will be taken into
	 *         account.
	 */
	private List<List<Card>> adjustCardCollectionsForLowAce(final List<List<Card>> sequentialCardCollections) {

		// Only a five-high straight is currently handled, which is guaranteed to have
		// exactly two lists of cards.
		if (sequentialCardCollections.size() != 2) {
			return sequentialCardCollections;
		}

		final List<Card> firstCardCollection = sequentialCardCollections.get(0);
		if (firstCardCollection.size() != 1 || firstCardCollection.get(0).getFaceValue() != FaceValue.ACE) {
			return sequentialCardCollections;
		}

		final List<Card> secondCardCollection = sequentialCardCollections.get(1);
		if (secondCardCollection.size() != 4 || secondCardCollection.get(0).getFaceValue() != FaceValue.FIVE) {
			return sequentialCardCollections;
		}

		final List<Card> cardCollection = new ArrayList<>(secondCardCollection);
		cardCollection.addAll(firstCardCollection);

		return Arrays.asList(cardCollection);
	}
}