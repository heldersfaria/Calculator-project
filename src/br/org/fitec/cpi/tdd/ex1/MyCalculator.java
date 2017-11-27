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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.BiFunction;

/**
 * 
 * 
 * @author helder
 *
 */
public class MyCalculator implements Calculator {

	private Number basicOperation(String string, BiFunction<Double, Double, Number> function) {

		Double result = null;

		if (!isEmpty(string)) {

			String[] arrrayString = string.split(",");

			List<String> listaNegativos = new ArrayList<>();

			for (String number : arrrayString) {

				number = number.trim();

				if (!isEmpty(number)) {

					try {

						Double parsedNumber = Double.parseDouble(number);

						if (parsedNumber < 0) {
							listaNegativos.add(number);
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

			if (listaNegativos.size() == 1) {
				throw new NegativeNumberException("negatives not allowed");
			} else if (listaNegativos.size() > 1) {

				StringJoiner joiner = new StringJoiner(" ");
				joiner.add("negatives not allowed");

				for (String negativos : listaNegativos) {
					joiner.add(negativos);
				}

				throw new NegativeNumberException(joiner.toString());
			}
		}

		return result == null ? 0.0 : result;
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
		return aproximate(basicOperation(string, (Double a, Double b) -> a * b).doubleValue());
	}

	@Override
	public Double divide(String string) {

		BiFunction<Double, Double, Number> function = (Double a, Double b) -> {

			if (b == 0.0) {
				throw new DivisionByZero("division by zero");
			}

			return a / b;
		};

		Number number = basicOperation(string, function);

		return aproximate(number.doubleValue());
	}

	private boolean isEmpty(String string) {
		return "".equals(string) || string == null;
	}

	private Double aproximate(Double value) {
		return Double.parseDouble(value.toString().substring(0, value.toString().indexOf(".") + 2));
	}

}
