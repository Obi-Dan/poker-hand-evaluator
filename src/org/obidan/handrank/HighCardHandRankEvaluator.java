/**
 * 
 */
package org.obidan.handrank;

import org.obidan.object.HandOfCards;
import org.obidan.object.Player;
import org.obidan.type.HandRank;

/**
 * Evaluates a {@link Player}'s poker hand to determine if the hand is a high
 * card hand.
 * 
 * @author Obi-Dan
 */
public class HighCardHandRankEvaluator implements PokerHandRankEvaluator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HandRank evaluateHandRank(final HandOfCards handOfCards, final HandRank previousHandRank) {

		if (previousHandRank != null) {
			return previousHandRank;
		}

		// Since high card is the lowest rank of hand and will be evaluated last, no
		// logic need be performed to determine this rank.
		return HandRank.HIGH_CARD;
	}
}