package carldini.utils;

import org.junit.Before;

import carldini.utils.BasicStringCalculator;

public class BasicStringCalculatorTest extends StringCalculatorTests<BasicStringCalculator> {

	@Before
	public void setupCalculatorImplementation() {
		stringCalculator = new BasicStringCalculator();
	}
	
}
