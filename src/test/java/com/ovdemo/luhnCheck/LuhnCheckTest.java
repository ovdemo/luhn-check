package com.ovdemo.luhnCheck;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LuhnCheckTest {

	@ParameterizedTest
	@ValueSource(longs = { 49927398716L, 1234567812345670L })
	void testIntegerPANs(Long number) throws Exception {
		assertTrue(LuhnCheck.validate(number));
	}

	@ParameterizedTest
	@ValueSource(strings = { "49927398716", "1234567812345670" })
	void testValidPANs(String number) throws Exception {
		assertTrue(LuhnCheck.validate(number));
	}

	@ParameterizedTest
	@ValueSource(strings = { "PAY49927398716", "PAN1234567812345670" })
	void testValidPANsWithLetterPrefix(String number) throws Exception {
		assertTrue(LuhnCheck.validate(number));
	}

	@ParameterizedTest
	@ValueSource(strings = { "49927398716PAY", "1234567812345670PAN" })
	void testValidPANsWithLetterSuffix(String number) throws Exception {
		assertTrue(LuhnCheck.validate(number));
	}

	@ParameterizedTest
	@ValueSource(strings = { "4992-7398-716", "1234-5678-1234-5670" })
	void testValidPANsWithHyphens(String number) throws Exception {
		assertTrue(LuhnCheck.validate(number));
	}

	@ParameterizedTest
	@ValueSource(strings = { "49927398717", "1234567812345678", "able was I ere I saw elba" })
	void testNonPANs(String number) throws Exception {
		assertFalse(LuhnCheck.validate(number));
	}
}
