package com.nanotechmoz.learning.automatedtests.student;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository underTest;
	
	@AfterEach
	void tearDown() {
		underTest.deleteAll();
	}
	
	@Test
	void itShouldCheckIfExistsEmail() {
		
		//arrange
		String email = "jamila@gmail.com";
		Student student = new Student(
				"Jamila",
				email,
				Gender.FEMALE
				);
		
		underTest.save(student);
		
		//Act 
		boolean expected = underTest.selectExistsEmail(email);
		
		//Assert
		assertThat(expected).isTrue();
		
	}
	
	@Test
	void itShouldCheckIfEmailDoesNotExist() {
		
		//arrange
		String email = "jamila@gmail.com";
		
		//Act 
		boolean expected = underTest.selectExistsEmail(email);
		
		//Assert
		assertThat(expected).isFalse();
		
	}
	

}
