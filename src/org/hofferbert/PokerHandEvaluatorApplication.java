package org.hofferbert;

public class PokerHandEvaluatorApplication {

	public static void main(String[] args) {
		PokerHandEvaluator pokerHandEvaluator = new PokerHandEvaluator(new ArgumentExtractor(),
				new PlayerNamesExtractor());
		pokerHandEvaluator.evaluate(args);
	}
}