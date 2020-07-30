package org.obidan;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.obidan.object.Deck;
import org.obidan.object.Player;
import org.obidan.object.PlayerFactory;
import org.obidan.type.HandRank;
import org.obidan.type.WinningStatus;
import org.obidan.utility.ArgumentExtractor;
import org.obidan.utility.PlayerComparator;
import org.obidan.utility.PlayerNamesExtractor;

/**
 * Evaluates poker hands.
 * 
 * @author Obi-Dan
 */
public class PokerHandEvaluator {

	private static final short MAXIMUM_NUMBER_OF_PLAYERS = 10;
	private static final int CARDS_PER_PLAYER = 5;

	private final ArgumentExtractor argumentExtractor;
	private final PlayerNamesExtractor playerNamesExtractor;
	private final PlayerFactory playerFactory;
	private final PlayerComparator playerComparator;

	/**
	 * Constructs a {@link PokerHandEvaluator}.
	 * 
	 * @param argumentExtractor    An {@link ArgumentExtractor}. This must never be
	 *                             null.
	 * @param playerNamesExtractor A {@link PlayerNamesExtractor}. This must never
	 *                             be null.
	 * @param playerFactory        A {@link PlayerFactory}. This must never be null.
	 * @param playerComparator     A {@link PlayerComparator}. This must never be
	 *                             null.
	 */
	public PokerHandEvaluator(final ArgumentExtractor argumentExtractor,
			final PlayerNamesExtractor playerNamesExtractor, final PlayerFactory playerFactory,
			final PlayerComparator playerComparator) {

		this.argumentExtractor = argumentExtractor;
		this.playerNamesExtractor = playerNamesExtractor;
		this.playerFactory = playerFactory;
		this.playerComparator = playerComparator;
	}

	/**
	 * Evaluates poker hands for the players whose names are in the file given as
	 * input for the first argument.
	 * 
	 * @param arguments The array of arguments. This may be null and can contain
	 *                  zero or more null, empty, or blank strings.
	 */
	public void evaluate(final String[] arguments) {

		final String fileName = this.argumentExtractor.getFirstArgument(arguments);
		if (fileName == null) {

			System.out.println(
					"A file name must be provided as a single argument. For example \"java -jar poker-hand-evaluator.jar ./Input1.txt\"");
			return;
		}

		List<String> playerNames = null;
		try {

			playerNames = this.playerNamesExtractor.getPlayerNames(fileName);
		} catch (final FileNotFoundException e) {

			System.out.println(new StringBuilder("The given file name \"").append(fileName)
					.append("\" cannot be found.").toString());
			return;
		} catch (final IOException e) {

			System.out.println(new StringBuilder("An error occurred when attempting to close the given file name \"")
					.append(fileName).append("\". Please try running the application again.").toString());
			return;
		}

		if (playerNames.isEmpty()) {

			System.out.println(new StringBuilder("No valid player names were found in the file (fileName=\"")
					.append(fileName)
					.append("\"). Valid player names consist of unicode letters, numbers, and whitespace (they may not, however, be all whitespace) along with commas, periods, and hyphens.")
					.toString());
			return;
		}

		final List<String> playingPlayersNames = playerNames.stream().limit(MAXIMUM_NUMBER_OF_PLAYERS)
				.collect(Collectors.toList());
		final List<String> eliminatedPlayerNames = playerNames.stream().skip(MAXIMUM_NUMBER_OF_PLAYERS)
				.collect(Collectors.toList());

		if (!eliminatedPlayerNames.isEmpty()) {

			System.out.println(new StringBuilder("The maximum allowed number of players per game is 10 and ")
					.append(playerNames.size())
					.append(" players were in the game. Only the first 10 players will be in the game.").toString());
		}

		final Deck deck = Deck.createSingleDeck();
		deck.shuffle();

		final List<Player> players = playingPlayersNames.stream()
				.map(name -> this.playerFactory.create(name, deck, CARDS_PER_PLAYER)).collect(Collectors.toList());

		determineWinningPlayers(players);

		players.stream().forEachOrdered(player -> System.out.println(player.getDisplay()));
	}

	/**
	 * This method will evaluate a {@link List} of {@link Player} to determine the
	 * winner(s). The result will be that the {@link Player} objects in the given
	 * {@link List} will be mutated and will have the appropriate
	 * {@link WinningStatus}.
	 * 
	 * @param sortedPlayers The {@link List} of {@link Player}. This must never be
	 *                      null or contain null values.
	 */
	private void determineWinningPlayers(final List<Player> players) {

		final Map<HandRank, List<Player>> playerByHandRank = players.stream()
				.collect(Collectors.groupingBy(Player::getHandRank));

		final LinkedHashMap<HandRank, List<Player>> sortedPlayerBySortedHandRank = playerByHandRank.entrySet().stream()
				.map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(),
						entry.getValue().stream().sorted(this.playerComparator).collect(Collectors.toList())))
				.sorted(Map.Entry.comparingByKey(Comparator.comparingInt(HandRank::getNumericValue)))
				.collect(Collectors.toMap(Map.Entry::getKey,
						entry -> entry.getValue().stream().sorted(this.playerComparator).collect(Collectors.toList()),
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));

		final List<Player> sortedPlayers = sortedPlayerBySortedHandRank.entrySet().stream().map(Map.Entry::getValue)
				.flatMap(List::stream).collect(Collectors.toList());

		assignWinningStatusToPlayers(sortedPlayers);
	}

	/**
	 * This method will evaluate a {@link List} of {@link Player} sorted by known
	 * winner to loser and determine if there is more than one winner. The result
	 * will be that the {@link Player} objects in the given {@link List} will be
	 * mutated and will have the appropriate {@link WinningStatus}.
	 * 
	 * @param sortedPlayers The {@link List} of {@link Player} sorted by known
	 *                      winner to loser. This must never be null or contain null
	 *                      values.
	 */
	private void assignWinningStatusToPlayers(final List<Player> sortedPlayers) {

		Player firstWinningPlayer = null;
		for (int index = 0; index < sortedPlayers.size(); index++) {
			final Player player = sortedPlayers.get(index);

			if (firstWinningPlayer == null) {
				firstWinningPlayer = player;
				player.setWinningStatus(WinningStatus.WON);
			} else if (this.playerComparator.compare(firstWinningPlayer, player) == 0) {
				firstWinningPlayer.setWinningStatus(WinningStatus.TIED);
				player.setWinningStatus(WinningStatus.TIED);
			}
		}
	}
}