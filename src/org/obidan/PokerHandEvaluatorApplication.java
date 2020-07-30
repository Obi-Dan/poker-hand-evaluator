package org.obidan;

import org.obidan.handrank.FlushHandRankEvaluator;
import org.obidan.handrank.FourOfAKindHandRankEvaluator;
import org.obidan.handrank.FullHouseHandRankEvaluator;
import org.obidan.handrank.HighCardHandRankEvaluator;
import org.obidan.handrank.OnePairHandRankEvaluator;
import org.obidan.handrank.StraightFlushHandRankEvaluator;
import org.obidan.handrank.StraightHandRankEvaluator;
import org.obidan.handrank.ThreeOfAKindHandRankEvaluator;
import org.obidan.handrank.TwoPairHandRankEvaluator;
import org.obidan.object.PlayerFactory;
import org.obidan.utility.ArgumentExtractor;
import org.obidan.utility.CardFaceValueGrouper;
import org.obidan.utility.PlayerComparator;
import org.obidan.utility.PlayerNamesExtractor;
import org.obidan.utility.SequentialCardFinder;

/**
 * Main entry point for the poker hand evaluator.
 * 
 * @author Obi-Dan
 */
public class PokerHandEvaluatorApplication {

	/**
	 * Evaluates poker hands for the players whose names are in the file given as
	 * input for the first argument.
	 * 
	 * @param arguments The array of arguments. This may be null and can contain
	 *                  zero or more null, empty, or blank strings.
	 */
	public static void main(final String[] args) {

		final PokerHandEvaluator pokerHandEvaluator = new PokerHandEvaluator(new ArgumentExtractor(),
				new PlayerNamesExtractor(),
				new PlayerFactory(new SequentialCardFinder(), new CardFaceValueGrouper(),
						new StraightFlushHandRankEvaluator().then(new FourOfAKindHandRankEvaluator())
								.then(new FullHouseHandRankEvaluator()).then(new FlushHandRankEvaluator())
								.then(new StraightHandRankEvaluator()).then(new ThreeOfAKindHandRankEvaluator())
								.then(new TwoPairHandRankEvaluator()).then(new OnePairHandRankEvaluator())
								.then(new HighCardHandRankEvaluator())),
				new PlayerComparator());

		pokerHandEvaluator.evaluate(args);
	}
}