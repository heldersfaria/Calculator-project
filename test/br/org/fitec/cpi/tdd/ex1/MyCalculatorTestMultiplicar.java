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

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MyCalculatorTestMultiplicar {

	Calculator myCalculator;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myCalculator = new MyCalculator();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		myCalculator = null;
	}

	@Test
	public void multiplyWithEmptyParametersWithResult() {
		Assert.assertThat(0.0, equalTo(myCalculator.multiply("")));
		Assert.assertThat(0.0, equalTo(myCalculator.multiply(",")));
		Assert.assertThat(0.0, equalTo(myCalculator.multiply(",,,")));
		Assert.assertThat(0.0, equalTo(myCalculator.multiply(", , , ")));
		Assert.assertThat(0.0, equalTo(myCalculator.multiply(null)));
	}

	@Test
	public void multiplyStringUniqueValueUsedWithResult() {
		Assert.assertThat(0.0, equalTo(myCalculator.multiply("0")));
		Assert.assertThat(1.0, equalTo(myCalculator.multiply("1")));
		Assert.assertThat(2.0, equalTo(myCalculator.multiply("2")));
		Assert.assertThat(3.0, equalTo(myCalculator.multiply("3")));
	}
	
	@Test
	public void multiply2ItensWithResult() {
		Assert.assertThat(0.0, equalTo(myCalculator.multiply("0,1")));
		Assert.assertThat(1.0, equalTo(myCalculator.multiply("1,1")));
		Assert.assertThat(36.0, equalTo(myCalculator.multiply("12,3")));
		Assert.assertThat(2.1, equalTo(myCalculator.multiply("1,2.1")));
		Assert.assertThat(2.4, equalTo(myCalculator.multiply("1,2.39878")));
		Assert.assertThat(0.0, equalTo(myCalculator.multiply("0,1")));
		Assert.assertThat(1.7, equalTo(myCalculator.multiply("1,1.7")));
		Assert.assertThat(2.8, equalTo(myCalculator.multiply("1,2.8")));
		Assert.assertThat(11.2, equalTo(myCalculator.multiply("4.7,2.39")));
	}

	@Test
	public void multiplyNotSimileOperation() {
		thrown.expect(NumberFormatException.class);
		thrown.expectMessage(is("It was not possible to parse -asdf1"));
		myCalculator.multiply("-asdf1,asdfsdf");
	}

	@Test
	public void multiplySimileOperation() {
		Assert.assertEquals(myCalculator.multiply("1,2"), myCalculator.multiply("2,1"));
	}

	@Test
	public void multiply3itensWithResult() {
		Assert.assertThat(0.0, equalTo(myCalculator.multiply("0,1,2")));
		Assert.assertThat(2.0, equalTo(myCalculator.multiply("1,1,2")));
		Assert.assertThat(7.8, equalTo(myCalculator.multiply("1,2,3.89778")));
	}

	@Test
	public void multiplyWithEmptyValuesWithResult() {
		Assert.assertThat(1.0, equalTo(myCalculator.multiply(",1")));
		Assert.assertThat(1.0, equalTo(myCalculator.multiply(",1")));
		Assert.assertThat(1.0, equalTo(myCalculator.multiply("1, ")));
		Assert.assertThat(1.0, equalTo(myCalculator.multiply("1,")));
		Assert.assertThat(2.0, equalTo(myCalculator.multiply("1,2, ")));
		Assert.assertThat(2.1, equalTo(myCalculator.multiply("1, , 2.1")));
		Assert.assertThat(2.0, equalTo(myCalculator.multiply(" , 1 , 2")));
		Assert.assertThat(2.1, equalTo(myCalculator.multiply(" , 1 , 2.1")));
		Assert.assertThat(2400.0, equalTo(myCalculator.multiply("100 , 2 , 3, 4")));
		Assert.assertThat(1200.0, equalTo(myCalculator.multiply("100 , , 3, 4")));
		Assert.assertThat(24.0, equalTo(myCalculator.multiply("1 , , 3, 4, 2")));
		Assert.assertThat(25.2, equalTo(myCalculator.multiply("1 , , 3, 4, 2.1")));
	}

	@Test
	public void multiplyWithEmptyValueAndInvalidValueWithException() {
		thrown.expectMessage(is("negatives not allowed"));
		myCalculator.divide(", -1 , ");
	}

	@Test
	public void divideWithInvalidValueWithException() {
		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage(is("negatives not allowed"));
		myCalculator.multiply("-1,3");
		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage(containsString("-1"));
		thrown.expectMessage(containsString("-3"));
		thrown.expectMessage(is("negatives not allowed -1 -3"));
		myCalculator.multiply("-1,-3");
		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage(containsString("-1"));
		thrown.expectMessage(containsString("-3"));
		thrown.expectMessage(containsString("-5"));
		thrown.expectMessage(is("negatives not allowed -1 -3 -5"));
		myCalculator.multiply("-1,-3, -5");
	}

	@Test
	public void divideWithUnparsedWithException() {
		thrown.expect(NumberFormatException.class);
		thrown.expectMessage(is("It was not possible to parse " + "asdfsdf"));
		myCalculator.multiply("-1,asdfsdf");
	}

	@Test
	public void divideBiggerThan1000WithResult() {
		Assert.assertThat(1.0, equalTo(myCalculator.multiply("1,1002")));
		Assert.assertThat(1.0, equalTo(myCalculator.multiply("1,1001")));
		Assert.assertThat(1000.0, equalTo(myCalculator.multiply("1,1000")));
	}
}