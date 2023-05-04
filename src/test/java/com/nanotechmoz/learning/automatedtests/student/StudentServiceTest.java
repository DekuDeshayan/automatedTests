package com.nanotechmoz.learning.automatedtests.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nanotechmoz.learning.automatedtests.student.exception.BadRequestException;


@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
	
	@Mock
	private StudentRepository repository;
	private StudentService underTest;	
	
	@BeforeEach
	void setUp() {
		underTest = new StudentService(repository);
	}		
	
	@Test
	void canGetAllStudents() {
		//When
		underTest.getAllStudents();
		
		//Then
		verify(repository).findAll();		
	}
	
	@Test
	void CanAddStudent() {
		
		//arrange
		Student student = new Student(
				"Jamila",
				"Jamila@gmail.com",
				Gender.FEMALE
			);
		
		//when		
		underTest.addStudent(student);
		
		//Then		
		ArgumentCaptor<Student> studentArgumentCaptor = 
				ArgumentCaptor.forClass(Student.class);
		
		verify(repository)
		.save(studentArgumentCaptor.capture());
		
		//Arrange
		Student capturedStudent = studentArgumentCaptor.getValue();
		
		//Assert
		assertThat(capturedStudent).isEqualTo(student);
		
	}
	
	@Test
	void willThrowWhenEmailIsTaken() {
		
		//arrange
		Student student = new Student(
				"Jamila",
				"Jamila@gmail.com",
				Gender.FEMALE
			);
		
		
		given(repository.selectExistsEmail(student.getEmail()))
		.willReturn(true);
		
		
		//when		
		//Then		
		assertThatThrownBy(() -> underTest.addStudent(student))
				.isInstanceOf(BadRequestException.class)
				.hasMessageContaining("Email "+ student.getEmail() + " taken");
		
		verify(repository, never()).save(any());
		
	
	}
	
	@Test
	void deleteStudent() {
		
		
	}

}
