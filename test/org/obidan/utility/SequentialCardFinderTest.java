package org.obidan.utility;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.obidan.object.Card.create;
import static org.obidan.type.FaceValue.ACE;
import static org.obidan.type.FaceValue.EIGHT;
import static org.obidan.type.FaceValue.FIVE;
import static org.obidan.type.FaceValue.FOUR;
import static org.obidan.type.FaceValue.QUEEN;
import static org.obidan.type.FaceValue.SIX;
import static org.obidan.type.FaceValue.TEN;
import static org.obidan.type.FaceValue.THREE;
import static org.obidan.type.FaceValue.TWO;
import static org.obidan.type.Suit.CLUBS;
import static org.obidan.type.Suit.DIAMONDS;
import static org.obidan.type.Suit.HEARTS;
import static org.obidan.type.Suit.SPADES;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.obidan.object.Card;

/**
 * Unit tests for the {@link SequentialCardFinder} class.
 * 
 * @author Obi-Dan
 */
class SequentialCardFinderTest {

	private final SequentialCardFinder sequentialCardFinder = new SequentialCardFinder();

	/**
	 * Tests that {@link SequentialCardFinder#getSequentialCardCollections(List)}
	 * will ignore the suit of the cards.
	 */
	@Test
	void test_getSequentialCardCollections_SuitIgnored() {
		final List<Card> originalCards = asList(create(CLUBS, TWO), create(DIAMONDS, FOUR), create(HEARTS, SIX),
				create(SPADES, FIVE), create(CLUBS, THREE));

		final List<List<Card>> expectedSequentialCardCollections = asList(asList(create(HEARTS, SIX),
				create(SPADES, FIVE), create(DIAMONDS, FOUR), create(CLUBS, THREE), create(CLUBS, TWO)));

		assertEquals(expectedSequentialCardCollections,
				this.sequentialCardFinder.getSequentialCardCollections(originalCards));
	}

	/**
	 * Tests that {@link SequentialCardFinder#getSequentialCardCollections(List)}
	 * supports an ace as low with a five-high straight.
	 */
	@Test
	void test_getSequentialCardCollections_FiveHighStraight_AceLow() {
		final List<Card> originalCards = asList(create(CLUBS, TWO), create(DIAMONDS, FOUR), create(HEARTS, ACE),
				create(SPADES, FIVE), create(CLUBS, THREE));

		final List<List<Card>> expectedSequentialCardCollections = asList(asList(create(SPADES, FIVE),
				create(DIAMONDS, FOUR), create(CLUBS, THREE), create(CLUBS, TWO), create(HEARTS, ACE)));

		assertEquals(expectedSequentialCardCollections,
				this.sequentialCardFinder.getSequentialCardCollections(originalCards));
	}

	/**
	 * Tests that {@link SequentialCardFinder#getSequentialCardCollections(List)}
	 * will support the ace as high in non-five-high straight.
	 */
	@Test
	void test_getSequentialCardCollections_NotFiveHighStraight_AceHigh() {
		final List<Card> originalCards = asList(create(DIAMONDS, FOUR), create(HEARTS, SIX), create(SPADES, FIVE),
				create(CLUBS, ACE), create(CLUBS, THREE));

		final List<List<Card>> expectedSequentialCardCollections = asList(asList(create(CLUBS, ACE)),
				asList(create(HEARTS, SIX), create(SPADES, FIVE), create(DIAMONDS, FOUR), create(CLUBS, THREE)));

		assertEquals(expectedSequentialCardCollections,
				this.sequentialCardFinder.getSequentialCardCollections(originalCards));
	}

	/**
	 * Tests that {@link SequentialCardFinder#getSequentialCardCollections(List)} if
	 * all cards are not sequential that they will each be returned in their own
	 * card collections.
	 */
	@Test
	void test_getSequentialCardCollections_NoSequentialCards() {
		final List<Card> originalCards = asList(create(DIAMONDS, ACE), create(HEARTS, TEN), create(SPADES, QUEEN),
				create(CLUBS, SIX), create(CLUBS, EIGHT));

		final List<List<Card>> expectedSequentialCardCollections = asList(asList(create(DIAMONDS, ACE)),
				asList(create(SPADES, QUEEN)), asList(create(HEARTS, TEN)), asList(create(CLUBS, EIGHT)),
				asList(create(CLUBS, SIX)));

		assertEquals(expectedSequentialCardCollections,
				this.sequentialCardFinder.getSequentialCardCollections(originalCards));
	}
}