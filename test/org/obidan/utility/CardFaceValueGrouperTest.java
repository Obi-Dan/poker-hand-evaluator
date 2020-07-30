package org.obidan.utility;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.obidan.object.Card.create;
import static org.obidan.type.FaceValue.ACE;
import static org.obidan.type.FaceValue.FIVE;
import static org.obidan.type.FaceValue.TWO;
import static org.obidan.type.Suit.CLUBS;
import static org.obidan.type.Suit.DIAMONDS;
import static org.obidan.type.Suit.HEARTS;
import static org.obidan.type.Suit.SPADES;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.obidan.object.Card;

/**
 * Unit tests for the {@link CardFaceValueGrouper} class.
 * 
 * @author Obi-Dan
 */
class CardFaceValueGrouperTest {

	private final CardFaceValueGrouper cardFaceValueGrouper = new CardFaceValueGrouper();

	/**
	 * Tests {@link CardFaceValueGrouper#getCardCollectionsGroupedByFaceValue(List)}
	 * when the cards rank as a full house.
	 */
	@Test
	void test_getCardCollectionsGroupedByFaceValue_FullHouse() {
		final List<Card> originalCards = asList(create(CLUBS, TWO), create(DIAMONDS, FIVE), create(HEARTS, TWO),
				create(SPADES, FIVE), create(CLUBS, FIVE));

		final List<List<Card>> expectedCardCollections = asList(
				asList(create(DIAMONDS, FIVE), create(SPADES, FIVE), create(CLUBS, FIVE)),
				asList(create(CLUBS, TWO), create(HEARTS, TWO)));

		assertEquals(expectedCardCollections,
				this.cardFaceValueGrouper.getCardCollectionsGroupedByFaceValue(originalCards));
	}

	/**
	 * Tests {@link CardFaceValueGrouper#getCardCollectionsGroupedByFaceValue(List)}
	 * when the cards rank as a two pair.
	 */
	@Test
	void test_getSequentialCardCollections_TwoPair() {
		final List<Card> originalCards = asList(create(CLUBS, TWO), create(DIAMONDS, ACE), create(HEARTS, TWO),
				create(SPADES, FIVE), create(CLUBS, ACE));

		final List<List<Card>> expectedCardCollections = asList(asList(create(DIAMONDS, ACE), create(CLUBS, ACE)),
				asList(create(CLUBS, TWO), create(HEARTS, TWO)), asList(create(SPADES, FIVE)));

		assertEquals(expectedCardCollections,
				this.cardFaceValueGrouper.getCardCollectionsGroupedByFaceValue(originalCards));
	}
}