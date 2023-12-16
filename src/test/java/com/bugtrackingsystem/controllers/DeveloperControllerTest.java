package com.bugtrackingsystem.controllers;

import com.bugtrackingsystem.controllers.DeveloperController;
import com.bugtrackingsystem.dto.BasicDTO;
import com.bugtrackingsystem.dto.DeveloperDTO;
import com.bugtrackingsystem.entity.Developer;
import com.bugtrackingsystem.serviceimplementation.IDeveloperServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeveloperControllerTest {

	@Mock
	private IDeveloperServiceImplementation developerService;

	@InjectMocks
	private DeveloperController developerController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addDeveloperTest() {
		// Mock data
		DeveloperDTO developerDTO = new DeveloperDTO("John Doe", "Java", 1L, 2L);
		Developer mockDeveloper = new Developer(); // You need to create a mock Developer object according to your
													// requirement

		// Mock service behavior
		when(developerService.addDeveloper(any(DeveloperDTO.class))).thenReturn(mockDeveloper);

		// Perform the test
		ResponseEntity<BasicDTO<Developer>> responseEntity = developerController.addDeveloper(developerDTO);

		// Verify the result
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(mockDeveloper, responseEntity.getBody().getData());

		// Verify that the service method was called with the correct parameters
		verify(developerService, times(1)).addDeveloper(developerDTO);
	}

	@Test
	void getDeveloperByIdTest() {
		// Mock data
		Long developerId = 1L;
		Developer mockDeveloper = new Developer(); // You need to create a mock Developer object according to your
													// requirement

		// Mock service behavior
		when(developerService.getDeveloperById(developerId)).thenReturn(mockDeveloper);

		// Perform the test
		ResponseEntity<BasicDTO<Developer>> responseEntity = developerController.getDeveloperById(developerId);

		// Verify the result
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockDeveloper, responseEntity.getBody().getData());

		// Verify that the service method was called with the correct parameters
		verify(developerService, times(1)).getDeveloperById(developerId);
	}

	@Test
	void updateDeveloperTest() {
		// Mock data
		DeveloperDTO developerDTO = new DeveloperDTO("John Doe", "Java", 1L, 2L);
		Long developerId = 1L;
		Developer mockDeveloper = new Developer(); // You need to create a mock Developer object according to your
													// requirement

		// Mock service behavior
		when(developerService.updateDeveloper(developerDTO, developerId)).thenReturn(mockDeveloper);

		// Perform the test
		ResponseEntity<BasicDTO<Developer>> responseEntity = developerController.updateDeveloper(developerDTO,
				developerId);

		// Verify the result
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockDeveloper, responseEntity.getBody().getData());

		// Verify that the service method was called with the correct parameters
		verify(developerService, times(1)).updateDeveloper(developerDTO, developerId);
	}

	@Test
	void allDevelopersTest() {
		// Mock data
		List<Developer> mockDevelopers = Collections.singletonList(new Developer()); // You need to create mock
																						// Developer objects according
																						// to your requirement

		// Mock service behavior
		when(developerService.getAllDevelopers()).thenReturn(mockDevelopers);

		// Perform the test
		ResponseEntity<BasicDTO<List<Developer>>> responseEntity = developerController.allDevelopers();

		// Verify the result
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockDevelopers, responseEntity.getBody().getData());

		// Verify that the service method was called
		verify(developerService, times(1)).getAllDevelopers();
	}

}