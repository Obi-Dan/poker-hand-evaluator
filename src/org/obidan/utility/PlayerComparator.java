package org.obidan.utility;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.obidan.object.Card;
import org.obidan.object.Player;

/**
 * {@link Comparator} for comparing {@link Player}s.
 * 
 * @author Obi-Dan
 */
public class PlayerComparator implements Comparator<Player> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compare(final Player player1, final Player player2) {

		final int handRankValueComparison = Integer.compare(player1.getHandRank().getNumericValue(),
				player2.getHandRank().getNumericValue());
		// Return if the hand rank is unequal.
		if (handRankValueComparison != 0) {
			return handRankValueComparison;
		}

		// The players hand ranks are assumed to be equal here.
		switch (player1.getHandRank()) {
		case FOUR_OF_A_KIND:
		case FULL_HOUSE:
		case THREE_OF_A_KIND:
		case TWO_PAIR:
		case ONE_PAIR:
			return compare(player1.getHandOfCards().getCardCollectionsGroupedByFaceValue(),
					player2.getHandOfCards().getCardCollectionsGroupedByFaceValue());

		case STRAIGHT_FLUSH:
		case FLUSH:
		case STRAIGHT:
		case HIGH_CARD:
		default:
			return compare(player1.getHandOfCards().getSequentialCardCollections(),
					player2.getHandOfCards().getSequentialCardCollections());
		}
	}

	/**
	 * Compares the {@link Card} collections of player 1 and player 2.
	 * 
	 * @param player1CardCollections The first player's card collections. This must
	 *                               never be null, empty, or contain null
	 *                               collections. The contained collections must
	 *                               also be non-empty and contain non-null
	 *                               {@link Card}.
	 * @param player2CardCollections The second player's card collections. This must
	 *                               never be null, empty, or contain null
	 *                               collections. The contained collections must
	 *                               also be non-empty and contain non-null
	 *                               {@link Card}.
	 * @return A negative integer, zero, or a positive integer as player 1's
	 *         {@link Card} collections are less than, equal to, or greater than
	 *         player 2's {@link Card} collections.
	 */
	private int compare(final List<List<Card>> player1CardCollections, final List<List<Card>> player2CardCollections) {

		final List<Card> player1Cards = player1CardCollections.stream().flatMap(List::stream)
				.collect(Collectors.toList());
		final List<Card> player2Cards = player2CardCollections.stream().flatMap(List::stream)
				.collect(Collectors.toList());

		for (int index = 0; index < player1Cards.size(); index++) {
			final Card player1Card = player1Cards.get(index);
			final Card player2Card = player2Cards.get(index);

			// The only case in which this comparison will be incorrect will be a straight
			// flush from 5 to an ace, in which case this comparison will be unnecessary.
			final int faceValueComparison = Integer.compare(player1Card.getFaceValue().getNumericValue(),
					player2Card.getFaceValue().getNumericValue());
			if (faceValueComparison != 0) {
				return -faceValueComparison;
			}
		}

		return 0;
	}
}