//**********************************************************************
// Copyright (c) 2017 Telefonaktiebolaget LM Ericsson, Sweden.
// All rights reserved.
// The Copyright to the computer program(s) herein is the property of
// Telefonaktiebolaget LM Ericsson, Sweden.
// The program(s) may be used and/or copied with the written permission
// from Telefonaktiebolaget LM Ericsson or in accordance with the terms
// and conditions stipulated in the agreement/contract under which the
// program(s) have been supplied.
// **********************************************************************
package br.org.fitec.cpi.tdd.ex1;

import java.util.function.BiFunction;

/**
 * 
 * Numbers bigger than 1000 should be ignored, so adding 2 + 1001 = 2
 * 
 * @author helder
 *
 */
public class MyCalculator implements Calculator {

	private static final String SEPARATOR = ",";

	private boolean isEmpty(String string) {
		return "".equals(string) || string == null;
	}

	private Number basicOperation(String string, BiFunction<Double, Double, Number> function) {
		if (isEmpty(string)) {
			return 0.0;
		}

		String[] arrrayString = string.split(SEPARATOR);
		boolean[] NegativeArrayString = new boolean[arrrayString.length];

		Double result = null;
		int negativeNumbers = 0;

		for (int i = 0; i < arrrayString.length; i++) {

			String number = arrrayString[i].trim();

			if (!isEmpty(number)) {

				try {
					Double parsedNumber = Double.parseDouble(number);

					if (parsedNumber < 0) {
						negativeNumbers++;
						NegativeArrayString[i] = true;
					}

					if (parsedNumber <= 1000) {

						if (result != null) {
							result = function.apply(result, parsedNumber).doubleValue();
						} else {
							result = parsedNumber;
						}
					}
				} catch (NumberFormatException e) {
					throw new NumberFormatException("It was not possible to parse " + number);
				}
			}
		}

		if (negativeNumbers == 1) {
			throw new NegativeNumberException("negatives not allowed");
		} else if (negativeNumbers > 1) {

			StringBuilder sb = new StringBuilder("negatives not allowed ");

			for (int i = 0; i < NegativeArrayString.length; i++) {

				if (NegativeArrayString[i]) {
					sb.append(arrrayString[i] + " ");
				}
			}

			throw new NegativeNumberException(sb.toString());

		}
		return result;
	}

	@Override
	public Integer add(String string) {
		return basicOperation(string, (Double a, Double b) -> a + b).intValue();
	}

	@Override
	public Integer substract(String string) {
		return basicOperation(string, (Double a, Double b) -> a - b).intValue();
	}

	@Override
	public Double multiply(String string) {
		return basicOperation(string, (Double a, Double b) -> a * b).doubleValue();
	}

	@Override
	public Double divide(String string) {
		//
		// BiFunction<Double, Double, Number> function = (Double a, Double b) ->
		// {
		// if (b == 0.0) {
		// throw new DivisionByZero("division by zero");
		// }
		//
		// return ;
		// };
		//
		// return basicOperation(string, function).doubleValue();

		return basicOperation(string, (Double a, Double b) -> a / b).doubleValue();
	}
}
