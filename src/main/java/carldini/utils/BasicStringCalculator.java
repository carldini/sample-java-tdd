package carldini.utils;

import java.text.MessageFormat;

public class BasicStringCalculator implements StringCalculator {
	
	private static final String DELIMITERS = ",|\n";
	private static final String ADHOC_PREFIX = "//";

	public int add(String numbers) {
		String delimiter = DELIMITERS;
		String valueToAddUp = numbers;
		
		if (numbers.startsWith(ADHOC_PREFIX)) {
			int delimiterPosition = numbers.indexOf(ADHOC_PREFIX) + ADHOC_PREFIX.length();
			delimiter = numbers.substring(delimiterPosition, delimiterPosition + 1);
			valueToAddUp = numbers.substring(numbers.indexOf("\n") + 1);	
		}
		
		String[] numberValues = valueToAddUp.split(delimiter);
		return addNumbers(numberValues);
	}
	
	private int addNumbers(String[] numberValues) {
		int totalValue = 0;
		String errorValues = "";
		for (String number : numberValues) {
			if (number.contains("-")) {
				errorValues += number + " ";
			} else if (! "".equals(number)) {
				int value = Integer.parseInt(number);
				if (value <= 1000) {
					totalValue += Integer.parseInt(number);
				}
			}
		}
		
		if (errorValues.length() > 0) {
			String message = MessageFormat.format("There following numbers are invalid %s", errorValues);
			throw new RuntimeException(message);
		}
		
		return totalValue;
	}

}
