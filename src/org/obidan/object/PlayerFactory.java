package org.obidan.object;

import org.obidan.handrank.PokerHandRankEvaluator;
import org.obidan.utility.CardFaceValueGrouper;
import org.obidan.utility.SequentialCardFinder;

/**
 * Factory for creating {@link Player} objects.
 * 
 * @author Obi-Dan
 */
public class PlayerFactory {

	private final SequentialCardFinder sequentialCardFinder;
	private final CardFaceValueGrouper cardFaceValueGrouper;
	private final PokerHandRankEvaluator pokerHandRankEvaluator;

	/**
	 * Constructs a {@link PlayerFactory}.
	 * 
	 * @param sequentialCardFinder   A {@link SequentialCardFinder}. This must never
	 *                               be null.
	 * @param cardFaceValueGrouper   A {@link CardFaceValueGrouper}. This must never
	 *                               be null.
	 * @param pokerHandRankEvaluator A {@link PokerHandRankEvaluator}. This must
	 *                               never be null.
	 */
	public PlayerFactory(final SequentialCardFinder sequentialCardFinder,
			final CardFaceValueGrouper cardFaceValueGrouper, final PokerHandRankEvaluator pokerHandRankEvaluator) {

		this.sequentialCardFinder = sequentialCardFinder;
		this.cardFaceValueGrouper = cardFaceValueGrouper;
		this.pokerHandRankEvaluator = pokerHandRankEvaluator;
	}

	/**
	 * Creates a {@link Player}.
	 * 
	 * @param name           The player name. This must never be blank.
	 * @param deck           The {@link Deck}. This must never be null or empty.
	 * @param cardsPerPlayer The number of cards per player. This must be positive.
	 * @return Non-null {@link Player}.
	 */
	public Player create(final String name, final Deck deck, final int cardsPerPlayer) {

		return Player.create(name,
				HandOfCards.create(deck.remove(cardsPerPlayer), this.sequentialCardFinder, this.cardFaceValueGrouper),
				this.pokerHandRankEvaluator);
	}
}