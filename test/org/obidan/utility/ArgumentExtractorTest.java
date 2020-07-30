package org.obidan.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link ArgumentExtractor} class.
 * 
 * @author Obi-Dan
 */
class ArgumentExtractorTest {

	private final ArgumentExtractor argumentExtractor = new ArgumentExtractor();

	/**
	 * Tests that when {@link ArgumentExtractor#getFirstArgument(String[])} is
	 * called with a null argument array that a null string is returned.
	 */
	@Test
	void test_getFirstArgument_NullArguments() {
		assertEquals(null, this.argumentExtractor.getFirstArgument(null));
	}

	/**
	 * Tests that when {@link ArgumentExtractor#getFirstArgument(String[])} is
	 * called with an argument array containing no strings that a null string is
	 * returned.
	 */
	@Test
	void test_getFirstArgument_EmptyArguments() {
		assertEquals(null, this.argumentExtractor.getFirstArgument(new String[] {}));
	}

	/**
	 * Tests that when {@link ArgumentExtractor#getFirstArgument(String[])} is
	 * called with an argument array containing a single, null string that a null
	 * string is returned.
	 */
	@Test
	void test_getFirstArgument_SingleNullArgument() {
		assertEquals(null, this.argumentExtractor.getFirstArgument(new String[] { null }));
	}

	/**
	 * Tests that when {@link ArgumentExtractor#getFirstArgument(String[])} is
	 * called with an argument array containing a single, empty string that a null
	 * string is returned.
	 */
	@Test
	void test_getFirstArgument_SingleEmptyArgument() {
		assertEquals(null, this.argumentExtractor.getFirstArgument(new String[] { "" }));
	}

	/**
	 * Tests that when {@link ArgumentExtractor#getFirstArgument(String[])} is
	 * called with an argument array containing a single, blank string that a null
	 * string is returned.
	 */
	@Test
	void test_getFirstArgument_SingleBlankArgument() {
		assertEquals(null, this.argumentExtractor.getFirstArgument(new String[] { "\t   \n\t" }));
	}

	/**
	 * Tests that when {@link ArgumentExtractor#getFirstArgument(String[])} is
	 * called with an argument array containing a single, non-blank string that a
	 * non-blank string is returned.
	 */
	@Test
	void test_getFirstArgument_SingleValidArgument() {
		assertEquals("Input1.txt", this.argumentExtractor.getFirstArgument(new String[] { "Input1.txt" }));
	}

	/**
	 * Tests that when {@link ArgumentExtractor#getFirstArgument(String[])} is
	 * called with an argument array containing two, non-blank strings that the
	 * first non-blank string is returned.
	 */
	@Test
	void test_getFirstArgument_TwoValidArguments() {
		assertEquals("FirstArgument",
				this.argumentExtractor.getFirstArgument(new String[] { "FirstArgument", "SecondArgument" }));
	}
}