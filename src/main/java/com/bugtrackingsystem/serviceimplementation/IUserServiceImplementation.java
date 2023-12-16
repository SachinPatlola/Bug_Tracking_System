package com.bugtrackingsystem.serviceimplementation;


import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.stereotype.Service;

import com.bugtrackingsystem.entity.Developer;
import com.bugtrackingsystem.entity.TestEngineer;
import com.bugtrackingsystem.entity.User;
import com.bugtrackingsystem.exceptions.ResourceAlreadyExistException;
import com.bugtrackingsystem.exceptions.ResourceNotFoundException;
import com.bugtrackingsystem.repository.DeveloperRepository;
import com.bugtrackingsystem.repository.TestEngineerRepository;
import com.bugtrackingsystem.repository.UserRepository;
import com.bugtrackingsystem.service.IUserService;
import com.bugtrackingsystem.util.UserRoleEnum;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j

public class IUserServiceImplementation implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private TestEngineerRepository testEngineerRepository;
    @Autowired
    private DeveloperRepository developerRepository;
    // Register user
	@Transactional
	public User registerUser(@Valid User user) throws ResourceAlreadyExistException {
		user.setId(null);
		// checking name is empty or not
		if (user.getFirstName()==""|| user.getLastName()=="")
			throw new ResourceNotFoundException("First name is empty");
		// checking email is present in database
		else if (userRepository.existsByEmail(user.getEmail()))
			throw new ResourceAlreadyExistException("User already exists");
		userRepository.save(user);
		// Saving user details based on role
		 if (UserRoleEnum.TESTER.equals(user.getRole())) {
			testEngineerRepository.save(new TestEngineer(null, user.getFirstName() + " " + user.getLastName(),
					user.getSkills(), null, user));
		} else if (UserRoleEnum.DEVELOPER.equals(user.getRole())) {
			developerRepository.save(
					new Developer(null, user.getFirstName() + " " + user.getLastName(), user.getSkills(), null, user));
		}
		return user;
	}
	// sign in

    public User signIn(@Valid User user)throws ResourceNotFoundException{

        return userRepository.findByEmailIgnoreCaseAndPassword(user.getEmail(), user.getPassword()).orElseThrow(
                () -> new ResourceNotFoundException("Wrong credentials, Please try again")
        );
    }
    // sign out

    @Override
    public String signOut() {
        return "Sign Out Successfull";
    }
    // User details using id
    public Optional<User> userDetails(Long userId)throws ResourceNotFoundException{
    	if(userRepository.findById(userId).isEmpty()) {
    		throw new ResourceNotFoundException("No details found for given userId");
    	}
    	return userRepository.findById(userId);
    	
    }


    public List<User> findOtherUsers(Long userId)throws ResourceNotFoundException{
    	List<User>otherUsers= userRepository.findByIdNot(userId);
    	if(otherUsers.isEmpty()) throw new ResourceNotFoundException("No users were found");
        return otherUsers;
    }

    public List<User> findAllByRole(UserRoleEnum role)throws ResourceNotFoundException{
    	
       List<User>userList= userRepository.findByRole(role);
       if(userList.isEmpty()) 
    	   throw new ResourceNotFoundException("No users found for given Role");
       return userList;
    }
    public List<User> findAll() throws ResourceNotFoundException{
    	if(userRepository.findAll().isEmpty()) {
    		throw new ResourceNotFoundException("No users found");
    	}
        return userRepository.findAll();
    }

    public String greet(){
        return "greeting";
    }
}
