/**
 * 
 */
package org.obidan.handrank;

import org.obidan.object.HandOfCards;
import org.obidan.object.Player;
import org.obidan.type.HandRank;

/**
 * Evaluates a {@link Player}'s poker hand to determine if the hand is a
 * three-of-a-kind.
 * 
 * @author Obi-Dan
 */
public class ThreeOfAKindHandRankEvaluator implements PokerHandRankEvaluator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HandRank evaluateHandRank(final HandOfCards handOfCards, final HandRank previousHandRank) {

		if (previousHandRank != null) {
			return previousHandRank;
		}

		// Since three of a kind is a lower rank than full house simply checking the
		// size of the first grouped card collection as having 3 cards is sufficient to
		// determine this rank.
		if (handOfCards.getCardCollectionsGroupedByFaceValue().get(0).size() == 3) {
			return HandRank.THREE_OF_A_KIND;
		}

		return null;
	}
}