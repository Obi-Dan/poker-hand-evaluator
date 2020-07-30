package org.obidan.object;

import java.util.List;
import java.util.stream.Collectors;

import org.obidan.handrank.PokerHandRankEvaluator;
import org.obidan.type.HandRank;
import org.obidan.type.WinningStatus;

/**
 * Represents a player.
 * 
 * @author Obi-Dan
 */
public class Player {

	private final String name;
	private final HandRank handRank;
	private final HandOfCards handOfCards;
	private WinningStatus winningStatus;

	/**
	 * Constructs a {@link Player}.
	 * 
	 * @param name        The player name. This must never be blank.
	 * @param handRank    The {@link HandRank} of the player's hand. This must never
	 *                    be null.
	 * @param handOfCards The player's {@link HandOfCards}. This must never be null.
	 * @return Non-null {@link Player}.
	 */
	private Player(final String name, final HandRank handRank, final HandOfCards handOfCards) {

		this.name = name;
		this.handRank = handRank;
		this.handOfCards = handOfCards;
		this.winningStatus = WinningStatus.UNKNOWN;
	}

	/**
	 * Creates a {@link Player}.
	 * 
	 * @param name                   The player name. This must never be blank.
	 * @param cards                  The {@link List} of player {@link Card}s. This
	 *                               must never be null, empty, or contain null
	 *                               values. This must never be null.
	 * @param handOfCards            The player's {@link HandOfCards}. This must
	 *                               never be null.
	 * @param pokerHandRankEvaluator The {@link PokerHandRankEvaluator}. This must
	 *                               never be null.
	 * @return Non-null {@link Player}.
	 */
	public static Player create(final String name, final HandOfCards handOfCards,
			final PokerHandRankEvaluator pokerHandRankEvaluator) {

		final HandRank handRank = pokerHandRankEvaluator.evaluateHandRank(handOfCards, null);
		return create(name, handOfCards, handRank);
	}

	/**
	 * Creates a {@link Player}.
	 * 
	 * @param name        The player name. This must never be blank.
	 * @param cards       The {@link List} of player {@link Card}s. This must never
	 *                    be null, empty, or contain null values. This must never be
	 *                    null.
	 * @param handOfCards The player's {@link HandOfCards}. This must never be null.
	 * @param handRank    The {@link HandRank}. This must never be null.
	 * @return Non-null {@link Player}.
	 */
	public static Player create(final String name, final HandOfCards handOfCards, final HandRank handRank) {

		return new Player(name, handRank, handOfCards);
	}

	/**
	 * @return Non-blank player name.
	 */
	public final String getName() {

		return this.name;
	}

	/**
	 * @return Non-null {@link HandRank}.
	 */
	public final HandRank getHandRank() {

		return this.handRank;
	}

	/**
	 * @return Non-null {@link HandOfCards}.
	 */
	public final HandOfCards getHandOfCards() {

		return this.handOfCards;
	}

	/**
	 * @return Non-null {@link WinningStatus}.
	 */
	public final WinningStatus getWinningStatus() {

		return this.winningStatus;
	}

	/**
	 * @param winningStatus The {@link WinningStatus}. This must never be null.
	 */
	public final void setWinningStatus(final WinningStatus winningStatus) {

		this.winningStatus = winningStatus;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		return new StringBuilder("Player[name=").append(this.name).append(",handRank=").append(this.handRank)
				.append(",handOfCards=").append(this.handOfCards).append("]").toString();
	}

	/**
	 * @return Non-blank player display.
	 */
	public String getDisplay() {

		final StringBuilder stringBuilder = new StringBuilder();

		final String cardsDisplay = this.handOfCards.getSequentialCardCollections().stream().flatMap(List::stream)
				.map(card -> card.toString()).collect(Collectors.joining(" "));

		stringBuilder.append(this.name).append("'s cards: ").append(cardsDisplay).append(" (")
				.append(this.handRank.getDisplay()).append(")");

		switch (this.winningStatus) {
		case WON:
			stringBuilder.append(" - $ Winner $");
			break;
		case TIED:
			stringBuilder.append(" - Tie");
			break;
		default:
			break;
		}

		return stringBuilder.toString();
	}
}