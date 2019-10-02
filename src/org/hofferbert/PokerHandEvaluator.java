package org.hofferbert;

import java.util.List;

class PokerHandEvaluator {
	private final ArgumentExtractor argumentExtractor;
	private final PlayerNamesExtractor playerNamesExtractor;

	/**
	 * Constructs a {@link PokerHandEvaluator}.
	 * 
	 * @param argumentExtractor    An {@link ArgumentExtractor}. This must never be
	 *                             null.
	 * @param playerNamesExtractor An {@link PlayerNamesExtractor}. This must never
	 *                             be null.
	 */
	PokerHandEvaluator(ArgumentExtractor argumentExtractor, final PlayerNamesExtractor playerNamesExtractor) {
		this.argumentExtractor = argumentExtractor;
		this.playerNamesExtractor = playerNamesExtractor;
	}

	/**
	 * Evaluates poker hands for the players whose names are in the file given as
	 * input for the first argument.
	 * 
	 * @param arguments The array of arguments. This may be null and can contain
	 *                  zero or more null, empty, or blank strings.
	 */
	public void evaluate(String[] arguments) {
		String fileName = argumentExtractor.getFirstArgument(arguments);
		if (fileName == null) {
			System.out.println(
					"A file name must be provided as a single argument. For example \"java -jar poker-hand-evaluator.jar ./Input1.txt\"");
			return;
		}

		List<String> playerNames = playerNamesExtractor.getPlayerNames(fileName);
		System.out.println(playerNames.toString());
	}
}