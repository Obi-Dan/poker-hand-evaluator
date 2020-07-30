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
 * Evaluates a {@link Player}'s poker hand to determine if the hand is a full
 * house.
 * 
 * @author Obi-Dan
 */
public class FullHouseHandRankEvaluator implements PokerHandRankEvaluator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HandRank evaluateHandRank(final HandOfCards handOfCards, final HandRank previousHandRank) {

		if (previousHandRank != null) {
			return previousHandRank;
		}

		final List<List<Card>> cardCollectionsGroupedByFaceValue = handOfCards.getCardCollectionsGroupedByFaceValue();

		if (cardCollectionsGroupedByFaceValue.size() == 2 && cardCollectionsGroupedByFaceValue.get(0).size() == 3
				&& cardCollectionsGroupedByFaceValue.get(1).size() == 2) {
			return HandRank.FULL_HOUSE;
		}

		return null;
	}
}