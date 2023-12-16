package com.bugtrackingsystem.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bugtrackingsystem.entity.User;
import com.bugtrackingsystem.util.UserRoleEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
	@NotBlank(message="FirstName cannot be blank")
    private String firstName;
	@NotNull(message="Last Name cannot be null")
    private String lastName;
	@Email(message="Invalid  email format")
    private String email;
    @NotNull
    @Size(min=8, message="password length should be greater than 8")
    private String password;
    private UserRoleEnum role;
    @NotBlank(message="skills cannot be empty")
    private String skills;

    public User toUserObject(){
        return new User(null, firstName, lastName,email, password,role,skills);
    }
}
