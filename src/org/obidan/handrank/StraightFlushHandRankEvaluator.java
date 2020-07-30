/**
 * 
 */
package org.obidan.handrank;

import java.util.List;

import org.obidan.object.Card;
import org.obidan.object.HandOfCards;
import org.obidan.object.Player;
import org.obidan.type.HandRank;
import org.obidan.type.Suit;

/**
 * Evaluates a {@link Player}'s poker hand to determine if the hand is a
 * straight flush.
 * 
 * @author Obi-Dan
 */
public class StraightFlushHandRankEvaluator implements PokerHandRankEvaluator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HandRank evaluateHandRank(final HandOfCards handOfCards, final HandRank previousHandRank) {

		if (previousHandRank != null) {
			return previousHandRank;
		}

		final List<Card> cards = handOfCards.getCards();
		final Suit firstCardSuit = cards.get(0).getSuit();

		final boolean allCardsAreSameSuit = cards.stream().allMatch(card -> card.getSuit() == firstCardSuit);

		if (allCardsAreSameSuit) {
			final List<List<Card>> sequentialCardCollections = handOfCards.getSequentialCardCollections();

			if (sequentialCardCollections.size() == 1 && sequentialCardCollections.get(0).size() == 5) {
				return HandRank.STRAIGHT_FLUSH;
			}
		}

		return null;
	}
}