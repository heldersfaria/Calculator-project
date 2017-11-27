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
		boolean[] arrayStringNegativo = new boolean[arrrayString.length];

		Double result = null;
		int numerosNegativos = 0;

		for (int i = 0; i < arrrayString.length; i++) {

			String numero = arrrayString[i].trim();

			if (!isEmpty(numero)) {

				try {
					Double numeroConvertido = Double.parseDouble(numero);

					if (numeroConvertido < 0) {
						numerosNegativos++;
						arrayStringNegativo[i] = true;
					}

					if (numeroConvertido <= 1000) {

						if (result != null) {
							result = function.apply(result, numeroConvertido).doubleValue();
						} else {
							result = numeroConvertido;
						}
					}
				} catch (NumberFormatException e) {
					throw new NumberFormatException("It was not possible to parse " + numero);
				}
			}
		}

		if (numerosNegativos == 1) {
			throw new NegativeNumberException("negatives not allowed");
		} else if (numerosNegativos > 1) {

			StringBuilder sb = new StringBuilder("negatives not allowed ");

			for (int i = 0; i < arrayStringNegativo.length; i++) {

				if (arrayStringNegativo[i]) {
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
