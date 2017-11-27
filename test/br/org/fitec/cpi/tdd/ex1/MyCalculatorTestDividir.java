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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MyCalculatorTestDividir {

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
	public void divideStringVaziaResultado0() {
		Assert.assertThat(0.0, equalTo(myCalculator.divide("")));
	}

	@Test
	public void divideStringNullaResultado0() {
		Assert.assertThat(0.0, equalTo(myCalculator.divide(null)));
	}

	@Test
	public void divideStringUmUnicaEntradaResultadoRecebido() {
		Assert.assertThat(0.0, equalTo(myCalculator.divide("0")));
		Assert.assertThat(1.0, equalTo(myCalculator.divide("1")));
		Assert.assertThat(2.0, equalTo(myCalculator.divide("2")));
		Assert.assertThat(3.0, equalTo(myCalculator.divide("3")));
	}

	@Test
	public void divideStringUmUnicaEntradaComValoresFracionadosResultadoRecebido() {
		Assert.assertThat(0.0, equalTo(myCalculator.divide("0")));
		Assert.assertThat(1.0, equalTo(myCalculator.divide("1")));
		Assert.assertThat(2.0, equalTo(myCalculator.divide("2")));
		Assert.assertThat(3.0, equalTo(myCalculator.divide("3")));
	}

	@Test
	public void divideStringDiferenteResultado() {
		Assert.assertNotEquals(1.0, equalTo(myCalculator.divide("0")));
		Assert.assertNotEquals(2.0, equalTo(myCalculator.divide("1")));
		Assert.assertNotEquals(3.0, equalTo(myCalculator.divide("2")));
		Assert.assertNotEquals(4.0, equalTo(myCalculator.divide("3")));
	}

	@Test
	public void divide2itensResultadoSomaDosDois() {
		Assert.assertThat(0.0, equalTo(myCalculator.divide("0,1")));
		Assert.assertThat(1.0, equalTo(myCalculator.divide("1,1")));
		Assert.assertThat(0.5, equalTo(myCalculator.divide("1,2")));
	}

	@Test
	public void divideByZeroTest1() {
		thrown.expect(DivisionByZero.class);
		myCalculator.divide("1,0");
	}

	@Test
	public void divideByZeroTest2() {
		thrown.expect(DivisionByZero.class);
		myCalculator.divide("1,0,2");
	}

	@Test
	public void divideNumberFormatException2() {
		thrown.expect(NumberFormatException.class);
		thrown.expectMessage(is("It was not possible to parse -asdf1"));
		myCalculator.divide("-asdf1,asdfsdf");
	}

	@Test
	public void divideSimetriaResultadoDiferente() {
		Assert.assertNotEquals(myCalculator.divide("1,2"), myCalculator.divide("2,1"));
	}

	@Test
	public void divideSoma3itensResultadoSomaDosDois() {
		Assert.assertThat(0.0, equalTo(myCalculator.divide("0,1,2")));
		Assert.assertThat(0.5, equalTo(myCalculator.divide("1,1,2")));
		Assert.assertThat(0.2, equalTo(myCalculator.divide("1,2,3")));
	}

	@Test
	public void addStringVazia2ResultadoUnicoValor() {
		Assert.assertThat(1.0, equalTo(myCalculator.divide(",1")));
		Assert.assertThat(1.0, equalTo(myCalculator.divide(",1")));
		Assert.assertThat(1.0, equalTo(myCalculator.divide("1, ")));
		Assert.assertThat(1.0, equalTo(myCalculator.divide("1,")));
		Assert.assertThat(0.5, equalTo(myCalculator.divide("1,2, ")));
		Assert.assertThat(0.5, equalTo(myCalculator.divide("1, , 2")));
		Assert.assertThat(0.5, equalTo(myCalculator.divide(" , 1 , 2")));
	}

	@Test
	public void addStringVazia2ResultadoUnicoValos() {
		thrown.expectMessage(is("negatives not allowed"));
		myCalculator.divide(", -1 , ");
	}

	@Test
	public void add4valores() {
		Assert.assertThat(4.2, equalTo(myCalculator.divide("100 , 2 , 3, 4")));
		Assert.assertThat(8.3, equalTo(myCalculator.divide("100 , , 3, 4")));
		Assert.assertThat(0.0, equalTo(myCalculator.divide("1 , , 3, 4, 2")));
	}
	//
	// @Test
	// public void notAllowed(){
	// thrown.expect(NegativeNumberException.class);
	// thrown.expectMessage(is("negatives not allowed"));
	// myCalculator.add("-1,3");
	// }
	//
	// @Test
	// public void notAllowed2() {
	// thrown.expect(NegativeNumberException.class);
	// thrown.expectMessage(containsString("-1"));
	// thrown.expectMessage(containsString("-3"));
	// thrown.expectMessage(is("negatives not allowed -1 -3"));
	// myCalculator.add("-1,-3");
	// }
	//
	// @Test
	// public void notAllowed3() {
	// thrown.expect(NegativeNumberException.class);
	// thrown.expectMessage(containsString("-1"));
	// thrown.expectMessage(containsString("-3"));
	// thrown.expectMessage(containsString("-5"));
	// thrown.expectMessage(is("negatives not allowed -1 -3 -5"));
	// myCalculator.add("-1,-3, -5");
	// }
	//
	// @Test
	// public void substractNumberFormatException(){
	// thrown.expect(NumberFormatException.class);
	// thrown.expectMessage(is("It was not possible to parse " + "asdfsdf"));
	// myCalculator.add("-1,asdfsdf");
	// }
	//
	// @Test
	// public void addBiggerThan1000() {
	// Assert.assertThat(1, equalTo(myCalculator.add("1,1002")));
	// Assert.assertThat(1, equalTo(myCalculator.add("1,1001")));
	// Assert.assertThat(1001, equalTo(myCalculator.add("1,1000")));
	// }
}