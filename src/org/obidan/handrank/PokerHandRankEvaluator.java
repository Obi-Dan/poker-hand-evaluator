package org.obidan.handrank;

import org.obidan.object.Card;
import org.obidan.object.HandOfCards;
import org.obidan.type.HandRank;

/**
 * Evaluates poker hand ranks.
 * 
 * @author Obi-Dan
 */
@FunctionalInterface
public interface PokerHandRankEvaluator {

	/**
	 * Evaluates the {@link HandRank} of the given hand of {@link Card}.
	 * 
	 * @param handOfCards The player's {@link HandOfCards}. This must never be null.
	 * @return Null if the {@link HandRank} for the poker hand cannot be determined.
	 *         Otherwise, a non-null @link HandRank}.
	 */
	HandRank evaluateHandRank(final HandOfCards handOfCards, HandRank previousHandRank);

	/**
	 * Determines the next {@link PokerHandRankEvaluator}.
	 * 
	 * @param nextPokerHandEvaluator The next {@link PokerHandRankEvaluator}. This
	 *                               must never be null.
	 * @return Non-null {@link PokerHandRankEvaluator}.
	 */
	default PokerHandRankEvaluator then(final PokerHandRankEvaluator nextPokerHandRankEvaluator) {

		return (handOfCards, previousHandRank) -> {
			final HandRank handRank = evaluateHandRank(handOfCards, previousHandRank);
			return nextPokerHandRankEvaluator.evaluateHandRank(handOfCards, handRank);
		};
	}
}