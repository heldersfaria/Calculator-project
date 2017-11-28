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

public class MyCalculatorTestDivide {

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
	public void divideWithEmptyParametersWithResult() {
		Assert.assertThat(0.0, equalTo(myCalculator.divide("")));
		Assert.assertThat(0.0, equalTo(myCalculator.divide(null)));
		Assert.assertThat(0.0, equalTo(myCalculator.divide(",")));
		Assert.assertThat(0.0, equalTo(myCalculator.divide(",,,")));
		Assert.assertThat(0.0, equalTo(myCalculator.divide(", , , ")));
	}
	
	@Test
	public void divideStringUniqueValueUsedWithResult() {
		Assert.assertThat(0.0, equalTo(myCalculator.divide("0")));
		Assert.assertThat(1.0, equalTo(myCalculator.divide("1")));
		Assert.assertThat(2.0, equalTo(myCalculator.divide("2")));
		Assert.assertThat(3.0, equalTo(myCalculator.divide("3")));
		Assert.assertNotEquals(1.0, equalTo(myCalculator.divide("0")));
		Assert.assertNotEquals(2.0, equalTo(myCalculator.divide("1")));
		Assert.assertNotEquals(3.0, equalTo(myCalculator.divide("2")));
		Assert.assertNotEquals(4.0, equalTo(myCalculator.divide("3")));
	}

	@Test
	public void divide2ItensWithResult() {
		Assert.assertThat(0.0, equalTo(myCalculator.divide("0,1")));
		Assert.assertThat(1.0, equalTo(myCalculator.divide("1,1")));
		Assert.assertThat(0.5, equalTo(myCalculator.divide("1,2")));
		Assert.assertThat(0.0, equalTo(myCalculator.divide("0,1")));
		Assert.assertThat(1.0, equalTo(myCalculator.divide("1,1")));
		Assert.assertThat(0.0, equalTo(myCalculator.divide("1,25")));
		Assert.assertThat(1.3, equalTo(myCalculator.divide("4,3")));
		Assert.assertThat(2.3, equalTo(myCalculator.divide("3.4,1.5")));
		Assert.assertThat(10.2, equalTo(myCalculator.divide("091.667328,9")));
	}

	@Test
	public void divideByZeroTest() {
		thrown.expect(DivisionByZero.class);
		myCalculator.divide("1,0");
		myCalculator.divide("1,0,2");
	}

	@Test
	public void c() {
		thrown.expect(NumberFormatException.class);
		thrown.expectMessage(is("It was not possible to parse -asdf1"));
		myCalculator.divide("-asdf1,asdfsdf");
	}

	@Test
	public void divideNotSimileOperation() {
		Assert.assertNotEquals(myCalculator.divide("1,2"), myCalculator.divide("2,1"));
	}

	@Test
	public void divide3itensWithResult() {
		Assert.assertThat(0.0, equalTo(myCalculator.divide("0,1,2")));
		Assert.assertThat(0.5, equalTo(myCalculator.divide("1,1,2")));
		Assert.assertThat(0.2, equalTo(myCalculator.divide("1,2,3")));
		Assert.assertThat(0.4, equalTo(myCalculator.divide("100,250,0.92")));
		Assert.assertThat(0.5, equalTo(myCalculator.divide("1,1,2")));
		Assert.assertThat(0.2, equalTo(myCalculator.divide("1,2,3")));
	}

	@Test
	public void divideWithEmptyValuesWithResult() {
		Assert.assertThat(1.0, equalTo(myCalculator.divide(",1")));
		Assert.assertThat(1.0, equalTo(myCalculator.divide(",1")));
		Assert.assertThat(1.0, equalTo(myCalculator.divide("1, ")));
		Assert.assertThat(1.0, equalTo(myCalculator.divide("1,")));
		Assert.assertThat(0.5, equalTo(myCalculator.divide("1,2, ")));
		Assert.assertThat(0.5, equalTo(myCalculator.divide("1, , 2")));
		Assert.assertThat(0.5, equalTo(myCalculator.divide(" , 1 , 2")));
		Assert.assertThat(4.2, equalTo(myCalculator.divide("100 , 2 , 3, 4")));
		Assert.assertThat(8.3, equalTo(myCalculator.divide("100 , , 3, 4")));
		Assert.assertThat(0.0, equalTo(myCalculator.divide("1 , , 3, 4, 2")));
	}

	@Test
	public void divideWithEmptyValueAndInvalidValueWithException() {
		thrown.expectMessage(is("negatives not allowed"));
		myCalculator.divide(", -1 , ");
	}

	@Test
	public void divideWithInvalidValueWithException() {
		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage(is("negatives not allowed"));
		myCalculator.divide("-1,3");
		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage(containsString("-1"));
		thrown.expectMessage(containsString("-3"));
		thrown.expectMessage(is("negatives not allowed -1 -3"));
		myCalculator.divide("-1,-3");
		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage(containsString("-1"));
		thrown.expectMessage(containsString("-3"));
		thrown.expectMessage(containsString("-5"));
		thrown.expectMessage(is("negatives not allowed -1 -3 -5"));
		myCalculator.divide("-1,-3, -5");
	}

	@Test
	public void divideWithUnparsedWithException() {
		thrown.expect(NumberFormatException.class);
		thrown.expectMessage(is("It was not possible to parse " + "asdfsdf"));
		myCalculator.divide("-1,asdfsdf");
		
		thrown.expect(NumberFormatException.class);
		thrown.expectMessage(is("It was not possible to parse -asdf1"));
		myCalculator.divide("-asdf1,asdfsdf");
	}

	@Test
	public void divideBiggerThan1000WithResult() {
		Assert.assertThat(1.0, equalTo(myCalculator.divide("1,1002")));
		Assert.assertThat(1.0, equalTo(myCalculator.divide("1,1001")));
		Assert.assertThat(0.0, equalTo(myCalculator.divide("1,1000")));
	}
}