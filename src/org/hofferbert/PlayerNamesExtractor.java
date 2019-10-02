package org.hofferbert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Extracts player names from a file.
 * 
 * @author Daniel Hofferbert
 */
class PlayerNamesExtractor {
	/**
	 * Gets the player names from a file.
	 * 
	 * @param fileName The file name. This must never be null.
	 * @return Non-null, possibly empty list of non-blank player names.
	 */
	List<String> getPlayerNames(String fileName) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);

			return bufferedReader.lines().map(line -> line.trim()).filter(line -> !line.isEmpty())
					.collect(Collectors.toList());
		} catch (FileNotFoundException e) {
			System.out.println(new StringBuilder("The given file name \"").append(fileName).append("\" does not exist.")
					.toString());
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					System.out.println(new StringBuilder("The buffered reader for the given file name \"")
							.append(fileName).append("\" cannot be closed. Please try again.").toString());
				}
			}

			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					System.out.println(new StringBuilder("The file reader for the given file name \"").append(fileName)
							.append("\" cannot be closed. Please try again.").toString());
				}
			}
		}

		return Collections.emptyList();
	}
}