/**
 * 
 */
package org.obidan.handrank;

import java.util.List;

import org.obidan.object.Card;
import org.obidan.object.HandOfCards;
import org.obidan.object.Player;
import org.obidan.type.HandRank;

/**
 * Evaluates a {@link Player}'s poker hand to determine if the hand is a
 * straight.
 * 
 * @author Obi-Dan
 */
public class StraightHandRankEvaluator implements PokerHandRankEvaluator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HandRank evaluateHandRank(final HandOfCards handOfCards, final HandRank previousHandRank) {

		if (previousHandRank != null) {
			return previousHandRank;
		}

		final List<List<Card>> sequentialCardCollections = handOfCards.getSequentialCardCollections();

		// Since a straight is a lower rank than a straight flush simply checking that
		// all the cards are sequential is sufficient to determine this rank.
		if (sequentialCardCollections.size() == 1 && sequentialCardCollections.get(0).size() == 5) {
			return HandRank.STRAIGHT;
		}

		return null;
	}
}