package org.obidan.utility;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.obidan.object.Card;
import org.obidan.type.FaceValue;

/**
 * Finds cards grouped by {@link FaceValue} in a collection of cards.
 * 
 * @author Obi-Dan
 */
public class CardFaceValueGrouper {

	/**
	 * Gets a collection of the lists of cards grouped by {@link FaceValue}
	 * (ignoring suit). The collections of cards will be sorted with the largest
	 * collection first, down to the smallest collection. After being sorted by size
	 * the collections will be sorted by {@link FaceValue} (for instance, if there
	 * are two pairs the higher {@link FaceValue} pair will come first).
	 * 
	 * @param cards The {@link List} of player {@link Card}s. This must never be
	 *              null or contain null values.
	 * @return Non-null, non-empty list of card collections grouped by
	 *         {@link FaceValue}, which will contain one or more non-null
	 *         {@link Card}s. The collections of cards will be sorted with the
	 *         largest collection first, down to the smallest collection. After
	 *         being sorted by size the collections will be sorted by
	 *         {@link FaceValue} (for instance, if there are two pairs the higher
	 *         {@link FaceValue} pair will come first).
	 */
	public List<List<Card>> getCardCollectionsGroupedByFaceValue(final List<Card> cards) {

		final Map<FaceValue, List<Card>> cardsByFaceValue = cards.stream()
				.collect(Collectors.groupingBy(Card::getFaceValue));

		final Comparator<Entry<FaceValue, List<Card>>> listSizeDescendingComparator = Comparator
				.comparing((final Map.Entry<FaceValue, List<Card>> entry) -> entry.getValue().size()).reversed();

		final Comparator<Entry<FaceValue, List<Card>>> faceValueDescendingComparator = Comparator
				.comparing((final Map.Entry<FaceValue, List<Card>> entry) -> entry.getKey().getNumericValue())
				.reversed();

		final List<List<Card>> rankCardCollections = cardsByFaceValue.entrySet().stream()
				.sorted(listSizeDescendingComparator.thenComparing(faceValueDescendingComparator))
				.map(Map.Entry::getValue).collect(Collectors.toList());

		return rankCardCollections;
	}
}