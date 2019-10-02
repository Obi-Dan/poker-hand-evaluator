package org.hofferbert;

/**
 * Extracts arguments passed from the consumer.
 * 
 * @author Daniel Hofferbert
 */
class ArgumentExtractor {
	/**
	 * Gets the first argument from an array of arguments.
	 * 
	 * @param arguments The array of arguments. This may be null and can contain
	 *                  zero or more null, empty, or blank strings.
	 * @return Possibly null first argument. If non-null then it will also be
	 *         non-blank.
	 */
	String getFirstArgument(String[] arguments) {
		if (arguments != null && arguments.length > 0) {
			String firstArgument = arguments[0];
			if (firstArgument != null && !firstArgument.trim().isEmpty()) {
				return firstArgument;
			}
		}

		return null;
	}
}