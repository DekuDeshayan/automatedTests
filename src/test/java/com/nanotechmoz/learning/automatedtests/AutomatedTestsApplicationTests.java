package com.nanotechmoz.learning.automatedtests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;


class AutomatedTestsApplicationTests {
	
	Calculator underTest = new Calculator();

	@Test
	void itShouldAddNumbers() {
		
		//Arrange
		int numberOne = 80;
		int numberTwo = 30;
		int val = 50;
		
		//Act
		int result = underTest.add(numberOne, numberTwo);
		
		//Assert
		assertThat(result).isEqualTo(val);
	}
	
	class Calculator{
		
		int add(int a, int b) {
			return a + b;
		}
		
	}

}
