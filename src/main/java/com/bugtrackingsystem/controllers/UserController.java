package com.bugtrackingsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bugtrackingsystem.dto.BasicDTO;
import com.bugtrackingsystem.dto.UserDTO;
import com.bugtrackingsystem.entity.User;
import com.bugtrackingsystem.exceptions.ResourceAlreadyExistException;
import com.bugtrackingsystem.exceptions.ResourceNotFoundException;
import com.bugtrackingsystem.serviceimplementation.IUserServiceImplementation;
import com.bugtrackingsystem.util.UserRoleEnum;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@RestController
@CrossOrigin

@RequestMapping("/api/user")
public class UserController {
    @Autowired
    IUserServiceImplementation iUserServiceImplementation;
    // for registration of user
    @PostMapping("/register")
    public ResponseEntity<BasicDTO<User>> register(@Valid @RequestBody UserDTO user)throws ResourceAlreadyExistException{
        return new ResponseEntity<>(new BasicDTO<>(iUserServiceImplementation.registerUser(user.toUserObject())), HttpStatus.CREATED);
    }
    //for login
    @PostMapping("/login")
    public ResponseEntity<BasicDTO<User>> login(@RequestBody UserDTO user)throws ResourceNotFoundException{
        return ResponseEntity.ok(new BasicDTO<>(iUserServiceImplementation.signIn(user.toUserObject())));
    }
    // To get all user details other than given id
    @GetMapping("/otherUsers/{userId}")
    public ResponseEntity<BasicDTO<List<User>>> otherUsers( @PathVariable("userId") Long userId)throws ResourceNotFoundException{
        return ResponseEntity.ok(new BasicDTO<>(iUserServiceImplementation.findOtherUsers(userId)));
    }
    //To get user details using id
    @GetMapping("/{userId}")
    public ResponseEntity<BasicDTO<Optional<User>>> UserDetails(@PathVariable("userId") Long userId)throws ResourceNotFoundException{
        return ResponseEntity.ok(new BasicDTO<>(iUserServiceImplementation.userDetails(userId)));
    }
    //To get user details based on role
    @GetMapping("/allByRole/{role}")
    public ResponseEntity<BasicDTO<List<User>>> findAllByRole(@PathVariable("role") UserRoleEnum role)throws ResourceNotFoundException{
        return ResponseEntity.ok(new BasicDTO<>(iUserServiceImplementation.findAllByRole(role)));
    }
    // To get all users
    @GetMapping("/allUsers")
    public ResponseEntity<BasicDTO<List<User>>> findAllUsers()throws ResourceNotFoundException{
        return ResponseEntity.ok(new BasicDTO<>(iUserServiceImplementation.findAll()));
    }
    @GetMapping("/test")
    public String greeting() {
        return iUserServiceImplementation.greet();
    }
}
