package co.edu.unbosque.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.unbosque.biginteger.Modelo;

class ValidExpresionTest {

	private Modelo model;
	
	public ValidExpresionTest() {
		model = new Modelo();
	}
	
	
	@Test
	void numberOneToNineTest() {
		assertEquals(true, model.isValidNumber("2"));
	}
	
	@Test
	void numberOneToNineVersionTwoTest() {
		assertEquals(false, model.isValidNumber("-2"));
	}
	
	@Test
	void numberNineToOneHundredVersionTest() {
		assertEquals(true, model.isValidNumber("10"));
	}
	
	@Test
	void numberNineToOneHundredVersionTwoTest() {
		assertEquals(true, model.isValidNumber("99"));
	}
	
	@Test
	void numberNineToOneHundredVersionThreeTest() {
		assertEquals(true, model.isValidNumber("90"));
	}
	
	@Test
	void numberNineToOneHundredVersionFourTest() {
		assertEquals(true, model.isValidNumber("56"));
	}
	
	
	@Test
	void numberNineToOneHundredVersionFiveTest() {
		assertEquals(true, model.isValidNumber("99"));
	}
	
	@Test
	void numberOneHundredToOneThousandVersionTest() {
		assertEquals(true, model.isValidNumber("100"));
	}
	
	@Test
	void numberOneHundredToOneThousandVersionTwoTest() {
		assertEquals(true, model.isValidNumber("999"));
	}
	
	@Test
	void numberOneHundredToOneThousandVersionThreeTest() {
		assertEquals(true, model.isValidNumber("823"));
	}
	
	@Test
	void numberOneHundredToOneThousandVersionFourTest() {
		assertEquals(true, model.isValidNumber("283"));
	}
	
	@Test
	void numberOneHundredToOneThousandVersionFiveTest() {
		assertEquals(true, model.isValidNumber("893"));
	}
	
	@Test
	void numberOneThousandToTenThousandVersionTest() {
		assertEquals(true, model.isValidNumber("1000"));
	}
	
	@Test
	void numberOneThousandToTenThousandVersionTwoTest() {
		assertEquals(true, model.isValidNumber("9999"));
	}
	
	@Test
	void numberOneThousandToTenThousandVersionThreeTest() {
		assertEquals(true, model.isValidNumber("2893"));
	}
	
	@Test
	void numberOneThousandToTenThousandVersionFourTest() {
		assertEquals(true, model.isValidNumber("9900"));
	}
	
	@Test
	void numberOneThousandToTenThousandVersionFiveTest() {
		assertEquals(true, model.isValidNumber("4785"));
	}
	
	@Test
	void numberTenThousandToFifteenThousandVersionTest() {
		assertEquals(true, model.isValidNumber("10000"));
	}
	
	@Test
	void numberTenThousandToFifteenThousandVersionTwoTest() {
		assertEquals(true, model.isValidNumber("50000"));
	}
	
	@Test
	void numberTenThousandToFifteenThousandVersionThreeTest() {
		assertEquals(true, model.isValidNumber("49999"));
	}
	
	@Test
	void numberTenThousandToFifteenThousandVersionFourTest() {
		assertEquals(false, model.isValidNumber("50001"));
	}
}