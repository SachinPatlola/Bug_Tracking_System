package com.bugtrackingsystem.controllers;
import com.bugtrackingsystem.controllers.UserController;
import com.bugtrackingsystem.dto.BasicDTO;
import com.bugtrackingsystem.dto.UserDTO;
import com.bugtrackingsystem.entity.User;
import com.bugtrackingsystem.serviceimplementation.IUserServiceImplementation;
import com.bugtrackingsystem.exceptions.ResourceAlreadyExistException;
import com.bugtrackingsystem.exceptions.ResourceNotFoundException;
import com.bugtrackingsystem.util.UserRoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import java.util.ArrayList;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
class UserControllerTest {
 
    @InjectMocks
    private UserController userController;
 
    @Mock
    private IUserServiceImplementation userService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testRegister() {
        // Arrange
        UserDTO userDTO = new UserDTO(); // Set up your UserDTO as needed
        User userObject = userDTO.toUserObject();

        // Mocking the behavior to throw ResourceAlreadyExistException
        doThrow(ResourceAlreadyExistException.class)
                .when(userService)
                .registerUser(userObject);

        // Act & Assert
        ResourceAlreadyExistException exception = assertThrows(ResourceAlreadyExistException.class, () -> {
            ResponseEntity<BasicDTO<User>> responseEntity = userController.register(userDTO);
        });

        // Verify that the exception message is correct
//        assertEquals("User already exists", exception.getMessage());

        // Verify that the userService.registerUser method was called once with the correct argument
        verify(userService, times(1)).registerUser(userObject);
    }


//    @Test
//    void testRegister_InvalidUserData() {
//        // Arrange
//        UserDTO userDTO = new UserDTO(); // Invalid user data with null fields
//
//        // Act & Assert
//        assertThrows(IllegalArgumentException.class, () -> userController.register(userDTO));
//
//        // Verify that the userService.registerUser method was not called
//        verify(userService, never()).registerUser(any());
//    }
//
//    @Test
//    void testLogin_InvalidCredentials() {
//        // Arrange
//        UserDTO userDTO = new UserDTO("Jane", "Doe", "jane@example.com", "wrongPassword", UserRoleEnum.DEVELOPER, "Python");
//        User userObject = userDTO.toUserObject();
//
//        // Simulate invalid credentials
//        when(userService.signIn(userObject))
//                .thenThrow(new ResourceNotFoundException("Wrong credentials, Please try again"));
//
//        // Act & Assert
//        assertThrows(ResourceNotFoundException.class, () -> userController.login(userDTO));
//
//        // Verify that the userService.signIn method was called once with the correct argument
//        verify(userService, times(1)).signIn(userObject);
//        verifyNoMoreInteractions(userService);
//    }
 
 
 
    @Test
    void testOtherUsers() {
        // Arrange
        Long userId = 1L;
        List<User> otherUsers = new ArrayList<>(); // Set up your list of other users as needed
        when(userService.findOtherUsers(userId)).thenReturn(otherUsers);
 
        // Act
        ResponseEntity<BasicDTO<List<User>>> responseEntity = userController.otherUsers(userId);
 
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(otherUsers, responseEntity.getBody().getData());
 
        // Verify that the userService.findOtherUsers method was called once with the correct argument
        verify(userService, times(1)).findOtherUsers(userId);
        verifyNoMoreInteractions(userService);
    }
 
    @Test
    void testFindAllByRole() {
        // Arrange
        UserRoleEnum role = UserRoleEnum.ADMIN; // Set up your UserRoleEnum as needed
        List<User> usersByRole = new ArrayList<>(); // Set up your list of users by role as needed
        when(userService.findAllByRole(role)).thenReturn(usersByRole);
 
        // Act
        ResponseEntity<BasicDTO<List<User>>> responseEntity = userController.findAllByRole(role);
 
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usersByRole, responseEntity.getBody().getData());
 
        // Verify that the userService.findAllByRole method was called once with the correct argument
        verify(userService, times(1)).findAllByRole(role);
        verifyNoMoreInteractions(userService);
    }
 
    @Test
    void testFindAllUsers() {
        // Arrange
        List<User> allUsers = new ArrayList<>(); // Set up your list of all users as needed
        when(userService.findAll()).thenReturn(allUsers);
 
        // Act
        ResponseEntity<BasicDTO<List<User>>> responseEntity = userController.findAllUsers();
 
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(allUsers, responseEntity.getBody().getData());
 
        // Verify that the userService.findAll method was called once
        verify(userService, times(1)).findAll();
        verifyNoMoreInteractions(userService);
    }
 
    @Test
    void testGreeting() {
        // Arrange
        String greetingMessage = "Hello, Mockito!";
        when(userService.greet()).thenReturn(greetingMessage);
 
        // Act
        String result = userController.greeting();
 
        // Assert
        assertEquals(greetingMessage, result);
 
        // Verify that the userService.greet method was called once
        verify(userService, times(1)).greet();
        verifyNoMoreInteractions(userService);
    }
}