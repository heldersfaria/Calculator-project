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

public class MyCalculatorTest {

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
	public void addStringInvalidResult0() {
		Assert.assertThat(0, equalTo(myCalculator.add("")));
		Assert.assertThat(0, equalTo(myCalculator.add(",")));
		Assert.assertThat(0, equalTo(myCalculator.add(",,,")));
		Assert.assertThat(0, equalTo(myCalculator.add(", , , ")));
		Assert.assertThat(0, equalTo(myCalculator.add(null)));
	}

	@Test
	public void addStringUniqueValueUsedWithResult() {
		Assert.assertThat(0, equalTo(myCalculator.add("0")));
		Assert.assertThat(1, equalTo(myCalculator.add("1")));
		Assert.assertThat(2, equalTo(myCalculator.add("2")));
		Assert.assertThat(3, equalTo(myCalculator.add("3")));
	}

	@Test
	public void addSum2ItensWithResult() {
		Assert.assertThat(1, equalTo(myCalculator.add("0,1")));
		Assert.assertThat(1, equalTo(myCalculator.add("1,0")));
		Assert.assertThat(2, equalTo(myCalculator.add("1,1")));
		Assert.assertThat(3, equalTo(myCalculator.add("1,2")));
		Assert.assertNotEquals(0, equalTo(myCalculator.add("0,1")));
		Assert.assertNotEquals(2, equalTo(myCalculator.add("0,1")));
		Assert.assertNotEquals(0, equalTo(myCalculator.add("1,0")));
		Assert.assertNotEquals(2, equalTo(myCalculator.add("1,0")));
		Assert.assertNotEquals(3, equalTo(myCalculator.add("1,1")));
		Assert.assertNotEquals(4, equalTo(myCalculator.add("1,2")));
	}

	@Test
	public void addSun3itensWithResult() {
		Assert.assertThat(3, equalTo(myCalculator.add("0,1,2")));
		Assert.assertThat(3, equalTo(myCalculator.add("1,0,2")));
		Assert.assertThat(4, equalTo(myCalculator.add("1,1,2")));
		Assert.assertThat(6, equalTo(myCalculator.add("1,2,3")));
	}

	@Test
	public void addWithEmptyValuesWithResult() {
		Assert.assertThat(1, equalTo(myCalculator.add(",1")));
		Assert.assertThat(1, equalTo(myCalculator.add(",1")));
		Assert.assertThat(1, equalTo(myCalculator.add("1, ")));
		Assert.assertThat(1, equalTo(myCalculator.add("1,")));
		Assert.assertThat(3, equalTo(myCalculator.add("1,2, ")));
		Assert.assertThat(3, equalTo(myCalculator.add("1, , 2")));
		Assert.assertThat(3, equalTo(myCalculator.add(" , 1 , 2")));
		Assert.assertThat(10, equalTo(myCalculator.add("1 , 2 , 3, 4")));
		Assert.assertThat(8, equalTo(myCalculator.add("1 ,  , 3, 4")));
		Assert.assertThat(10, equalTo(myCalculator.add("1 ,  , 3, 4, 2")));
	}

	@Test
	public void addWithEmptyValueAndInvalidValueWithException() {
		thrown.expectMessage(is("negatives not allowed"));
		myCalculator.add(", -1 , ");
	}
	
	@Test
	public void addWithInvalidValueWithException(){
		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage(is("negatives not allowed"));
		myCalculator.add("-1,3");
	
		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage(containsString("-1"));
		thrown.expectMessage(containsString("-3"));
		thrown.expectMessage(is("negatives not allowed -1 -3"));
		myCalculator.add("-1,-3");

		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage(containsString("-1"));
		thrown.expectMessage(containsString("-3"));
		thrown.expectMessage(containsString("-5"));
		thrown.expectMessage(is("negatives not allowed -1 -3 -5"));
		myCalculator.add("-1,-3, -5");
	}
	 
	@Test
	public void addWithUnparsedWithException(){
		thrown.expect(NumberFormatException.class);
		thrown.expectMessage(is("It was not possible to parse " + "asdfsdf"));
		myCalculator.add("-1,asdfsdf");
		
		thrown.expect(NumberFormatException.class);
		thrown.expectMessage(is("It was not possible to parse -asdf1"));
		myCalculator.add("-asdf1,asdfsdf");
	}
	
	@Test
	public void addWithTestSimileOperation() {
		Assert.assertEquals(myCalculator.add("1,2"), myCalculator.add("2,1"));
	}
	
	@Test
	public void addBiggerThan1000WithResult() {
		Assert.assertThat(1, equalTo(myCalculator.add("1,1002")));
		Assert.assertThat(1, equalTo(myCalculator.add("1,1001")));
		Assert.assertThat(1001, equalTo(myCalculator.add("1,1000")));
	}
}