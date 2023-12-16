package com.bugtrackingsystem.service;


import org.springframework.stereotype.Service;

import com.bugtrackingsystem.dto.UserDTO;
import com.bugtrackingsystem.entity.User;

@Service
public interface IUserService {
	User registerUser(User user);

	User signIn(User user);

	String signOut();
}
