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
 * Evaluates a {@link Player}'s poker hand to determine if the hand is a flush.
 * 
 * @author Obi-Dan
 */
public class FlushHandRankEvaluator implements PokerHandRankEvaluator {

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

		// Since a flush is a lower rank than a straight flush simply checking that all
		// the cards are in the same suit is sufficient to determine this rank.
		if (allCardsAreSameSuit) {
			return HandRank.FLUSH;
		}

		return null;
	}
}