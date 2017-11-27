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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyCalculatorTest {

	Calculator myCalculator;

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
	public void addStringVaziaResultado0() {
		Assert.assertThat(0, equalTo(myCalculator.add("")));
	}

	@Test
	public void addStringNullaResultado0() {
		Assert.assertThat(0, equalTo(myCalculator.add(null)));
	}

	@Test
	public void addStringUmUnicaEntradaResultadoRecebido() {
		Assert.assertThat(0, equalTo(myCalculator.add("0")));
		Assert.assertThat(1, equalTo(myCalculator.add("1")));
		Assert.assertThat(2, equalTo(myCalculator.add("2")));
		Assert.assertThat(3, equalTo(myCalculator.add("3")));
	}

	@Test
	public void addStringUmUnicaEntradaComValoresFracionadosResultadoRecebido() {
		Assert.assertThat(0, equalTo(myCalculator.add("0")));
		Assert.assertThat(1, equalTo(myCalculator.add("1")));
		Assert.assertThat(2, equalTo(myCalculator.add("2")));
		Assert.assertThat(3, equalTo(myCalculator.add("3")));
	}

	@Test
	public void addStringDiferenteResultado() {
		Assert.assertNotEquals(1, equalTo(myCalculator.add("0")));
		Assert.assertNotEquals(2, equalTo(myCalculator.add("1")));
		Assert.assertNotEquals(3, equalTo(myCalculator.add("2")));
		Assert.assertNotEquals(4, equalTo(myCalculator.add("3")));
	}

	@Test
	public void addSoma2itensResultadoSomaDosDois() {
		Assert.assertThat(1, equalTo(myCalculator.add("0,1")));
		Assert.assertThat(1, equalTo(myCalculator.add("1,0")));
		Assert.assertThat(2, equalTo(myCalculator.add("1,1")));
		Assert.assertThat(3, equalTo(myCalculator.add("1,2")));
	}

	@Test
	public void addSoma3itensResultadoDiferente() {
		Assert.assertNotEquals(0, equalTo(myCalculator.add("0,1")));
		Assert.assertNotEquals(2, equalTo(myCalculator.add("0,1")));
		Assert.assertNotEquals(0, equalTo(myCalculator.add("1,0")));
		Assert.assertNotEquals(2, equalTo(myCalculator.add("1,0")));
		Assert.assertNotEquals(3, equalTo(myCalculator.add("1,1")));
		Assert.assertNotEquals(4, equalTo(myCalculator.add("1,2")));
	}

	@Test
	public void addSoma3itensResultadoSomaDosDois() {
		Assert.assertThat(3, equalTo(myCalculator.add("0,1,2")));
		Assert.assertThat(3, equalTo(myCalculator.add("1,0,2")));
		Assert.assertThat(4, equalTo(myCalculator.add("1,1,2")));
		Assert.assertThat(6, equalTo(myCalculator.add("1,2,3")));
	}

	@Test
	public void addStringVazia2ResultadoUnicoValor() {
		Assert.assertThat(1, equalTo(myCalculator.add(",1")));
		Assert.assertThat(1, equalTo(myCalculator.add(",1")));
		Assert.assertThat(1, equalTo(myCalculator.add("1, ")));
		Assert.assertThat(1, equalTo(myCalculator.add("1,")));
		Assert.assertThat(3, equalTo(myCalculator.add("1,2, ")));
		Assert.assertThat(3, equalTo(myCalculator.add("1, , 2")));
		Assert.assertThat(3, equalTo(myCalculator.add(" , 1 , 2")));
	}

	@Test
	public void add4valores() {
		Assert.assertThat(10, equalTo(myCalculator.add("1 , 2 , 3, 4")));
		Assert.assertThat(8, equalTo(myCalculator.add("1 ,  , 3, 4")));
		Assert.assertThat(10, equalTo(myCalculator.add("1 ,  , 3, 4, 2")));
	}

	@Test
	public void notAllowed() {
		try {
			myCalculator.add("-1,3");
		} catch (NegativeNumberException e) {
			Assert.assertEquals("negatives not allowed", e.getMessage());
		}
	}

	@Test
	public void notAllowed2() {
		try {
			myCalculator.add("-1,-3");
		} catch (NegativeNumberException e) {
			Assert.assertTrue(e.getMessage().contains("-1") && e.getMessage().contains("-3")
					&& e.getMessage().contains("negatives not allowed"));
			Assert.assertTrue("negatives not allowed -1 -3 ".equals(e.getMessage()));
		}
	}

	@Test
	public void notAllowed3() {
		try {
			myCalculator.add("-1,-3, -5");
		} catch (NegativeNumberException e) {
			Assert.assertTrue(e.getMessage().contains("-1") && e.getMessage().contains("-3")
					&& e.getMessage().contains("-5") && "negatives not allowed -1 -3  -5 ".equals(e.getMessage()));
		}
	}

	@Test
	public void addBiggerThan1000() {
		Assert.assertThat(1, equalTo(myCalculator.add("1,1002")));
		Assert.assertThat(1, equalTo(myCalculator.add("1,1001")));
		Assert.assertThat(1001, equalTo(myCalculator.add("1,1000")));
	}

	@Test
	public void addErroParse() {
		try {
			myCalculator.add("1, 3, 400000000000000000");
		} catch (NumberFormatException e) {
			Assert.assertTrue(e.getMessage().contains("It was not possible to parse 400000000000000000"));
		}
	}
}