package org.obidan.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Extracts player names from a file.
 * 
 * @author Obi-Dan
 */
public class PlayerNamesExtractor {

	private static final short MAXIMUM_NAME_LENGTH = 50;
	private static final String USE_UNICODE = "(?U)";
	private static final String BEGIN_FIND = "[";
	private static final String END_FIND = "]";
	private static final String NOT = "^";
	private static final String UNICODE_LETTER = "\\p{L}";
	private static final String UNICODE_DIGIT = "\\p{N}";
	private static final String WHITESPACE = "\\s";
	private static final String PERMITTED_PUNCTUATION = ".,-";
	private static final String FIND_ONE_OR_MORE = "+";
	private static final String BEGIN_GROUP = "(";
	private static final String END_GROUP = ")";
	private static final String FIRST_GROUP = "$1";
	private static final String PLAYER_NAME_CHARACTERS_NOT_PERMITTED = new StringBuilder(USE_UNICODE).append(BEGIN_FIND)
			.append(NOT).append(UNICODE_LETTER).append(UNICODE_DIGIT).append(WHITESPACE).append(PERMITTED_PUNCTUATION)
			.append(END_FIND).append(FIND_ONE_OR_MORE).toString();
	private static final String DUPLICATE_WHITESPACE = new StringBuilder(USE_UNICODE).append(BEGIN_GROUP)
			.append(WHITESPACE).append(END_GROUP).append(WHITESPACE).append(FIND_ONE_OR_MORE).toString();

	/**
	 * Gets the player names from a file.
	 * 
	 * @param fileName The file name. This must never be null.
	 * @return Non-null, possibly empty list of non-blank player names. Note that
	 *         the player names will only contain unicode characters, digits, and
	 *         whitespace with the exception of a period, a comma, and a hyphen.
	 * @throws FileNotFoundException When the file for the given file name cannot be
	 *                               found.
	 * @throws IOException           When the file cannot be closed properly.
	 */
	public List<String> getPlayerNames(final String fileName) throws FileNotFoundException, IOException {

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {

			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);

			final List<String> playerNames = bufferedReader.lines()
					.map(line -> line.replaceAll(PLAYER_NAME_CHARACTERS_NOT_PERMITTED, "")
							.replaceAll(DUPLICATE_WHITESPACE, FIRST_GROUP).trim())
					.filter(line -> !line.isEmpty())
					.map(line -> line.length() <= MAXIMUM_NAME_LENGTH ? line
							: line.substring(0, MAXIMUM_NAME_LENGTH).trim())
					.filter(line -> !line.isEmpty()).collect(Collectors.toList());

			return playerNames;
		} catch (final FileNotFoundException e) {

			throw e;
		} finally {

			if (bufferedReader != null) {
				try {

					bufferedReader.close();
				} catch (final IOException e) {

					throw e;
				}
			}

			if (fileReader != null) {
				try {

					fileReader.close();
				} catch (final IOException e) {

					throw e;
				}
			}
		}
	}
}