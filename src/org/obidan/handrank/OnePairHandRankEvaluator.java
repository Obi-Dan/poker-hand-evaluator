/**
 * 
 */
package org.obidan.handrank;

import org.obidan.object.HandOfCards;
import org.obidan.object.Player;
import org.obidan.type.HandRank;

/**
 * Evaluates a {@link Player}'s poker hand to determine if the hand is a two
 * pair.
 * 
 * @author Obi-Dan
 */
public class OnePairHandRankEvaluator implements PokerHandRankEvaluator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HandRank evaluateHandRank(final HandOfCards handOfCards, final HandRank previousHandRank) {

		if (previousHandRank != null) {
			return previousHandRank;
		}

		// Since one pair is a lower rank than two pair simply checking the size of the
		// first grouped card collection as having 2 cards is sufficient to determine
		// this rank.
		if (handOfCards.getCardCollectionsGroupedByFaceValue().get(0).size() == 2) {
			return HandRank.ONE_PAIR;
		}

		return null;
	}
}