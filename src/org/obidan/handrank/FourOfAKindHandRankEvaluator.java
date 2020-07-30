/**
 * 
 */
package org.obidan.handrank;

import org.obidan.object.HandOfCards;
import org.obidan.object.Player;
import org.obidan.type.HandRank;

/**
 * Evaluates a {@link Player}'s poker hand to determine if the hand is a
 * four-of-a-kind.
 * 
 * @author Obi-Dan
 */
public class FourOfAKindHandRankEvaluator implements PokerHandRankEvaluator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HandRank evaluateHandRank(final HandOfCards handOfCards, final HandRank previousHandRank) {

		if (previousHandRank != null) {
			return previousHandRank;
		}

		if (handOfCards.getCardCollectionsGroupedByFaceValue().get(0).size() == 4) {
			return HandRank.FOUR_OF_A_KIND;
		}

		return null;
	}
}