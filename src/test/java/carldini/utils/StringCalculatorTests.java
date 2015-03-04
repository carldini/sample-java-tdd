package carldini.utils;

import org.junit.Assert;
import org.junit.Test;

import carldini.utils.StringCalculator;

/**
 * If I get time, there will be multiple implementations for {@link StringCalculator}
 * this will allow me to re-use all the tests between both implementations.
 * @param <T> some type of {@link StringCalculator}
 */
public abstract class StringCalculatorTests<T extends StringCalculator> {
	
	protected T stringCalculator;
	
	/**
	 * An empty String should return zero
	 */
	@Test
	public void handleEmptyString() {
		Assert.assertEquals(0, stringCalculator.add(""));
	}
	
	/**
	 * An single value should be returned as it's numeric representation
	 */
	@Test
	public void handleSingleValue() {
		String valueToTest = "1";
		int expectedValue = Integer.parseInt(valueToTest);
		Assert.assertEquals(expectedValue, stringCalculator.add(valueToTest));
	}
	
	/**
	 * An single value should be returned as it's numeric representation
	 */
	@Test
	public void handleTwoCorrectValues() {
		String valueToTest = "1,2";
		int expectedValue = 3;
		Assert.assertEquals(expectedValue, stringCalculator.add(valueToTest));
	}
	
	/**
	 * A single "," should return 0
	 */
	@Test
	public void handleTwoValuesBothBlank() {
		String valueToTest = ",";
		int expectedValue = 0;
		Assert.assertEquals(expectedValue, stringCalculator.add(valueToTest));
	}
	
	/**
	 * ",2" should return 2
	 */
	@Test
	public void handleTwoValuesFirstIsBlank() {
		String valueToTest = ",2";
		int expectedValue = 2;
		Assert.assertEquals(expectedValue, stringCalculator.add(valueToTest));
	}
	
	/**
	 * ",2" should return 2
	 */
	@Test
	public void handleTwoValuesLastIsBlank() {
		String valueToTest = "1,";
		int expectedValue = 1;
		Assert.assertEquals(expectedValue, stringCalculator.add(valueToTest));
	}
	
	/**
	 * An single value should be returned as it's numeric representation
	 */
	@Test
	public void handleThreeCorrectValues() {
		String valueToTest = "1,2,3";
		int expectedValue = 6;
		Assert.assertEquals(expectedValue, stringCalculator.add(valueToTest));
	}
	
	/**
	 * Line feeds are valid delimiters
	 */
	@Test
	public void handleLineFeedSeparators() {
		String valueToTest = "1\n2,3";
		int expectedValue = 6;
		Assert.assertEquals(expectedValue, stringCalculator.add(valueToTest));
	}
	
	/**
	 * test adhoc delimiters
	 */
	@Test
	public void handleAdhocSeparators() {
		String valueToTest = "//;\n1;2";
		int expectedValue = 3;
		Assert.assertEquals(expectedValue, stringCalculator.add(valueToTest));
	}
	
	/**
	 * test that negative numbers are rejected
	 */
	@Test(expected=RuntimeException.class)
	public void handleNegativeNumbers() {
		String valueToTest = "1,-2";
		stringCalculator.add(valueToTest);
	}
	
	/**
	 * test that numbers over 1000 are ignored
	 */
	@Test
	public void handleLargeNumbers() {
		String valueToTest = "2,1001";
		int expectedValue = 2;
		Assert.assertEquals(expectedValue, stringCalculator.add(valueToTest));
	}

}
