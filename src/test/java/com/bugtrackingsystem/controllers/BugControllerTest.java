package com.bugtrackingsystem.controllers;
 
import com.bugtrackingsystem.controllers.BugController;
import com.bugtrackingsystem.dto.BasicDTO;
import com.bugtrackingsystem.dto.BugDTO;
import com.bugtrackingsystem.entity.Bug;
import com.bugtrackingsystem.serviceimplementation.IBugServiceImplementation;
import com.bugtrackingsystem.util.SeverityEnum;
 
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
 
class BugControllerTest {
 
    @Mock
    private IBugServiceImplementation bugService;
 
    @InjectMocks
    private BugController bugController;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void createBugTest() {
        // Mock data
        BugDTO bugDTO = new BugDTO("Test Bug", "Description", SeverityEnum.MEDIUM,
                "Test comments", 1L, 2L, 3L);
        Bug mockBug = new Bug();  // You need to create a mock Bug object according to your requirement
 
        // Mock service behavior
        when(bugService.createBug(any(BugDTO.class))).thenReturn(mockBug);
 
        // Perform the test
        ResponseEntity<BasicDTO<Bug>> responseEntity = bugController.createBug(bugDTO);
 
        // Verify the result
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockBug, responseEntity.getBody().getData());
 
        // Verify that the service method was called with the correct parameters
        verify(bugService, times(1)).createBug(bugDTO);
    }
 
    
}