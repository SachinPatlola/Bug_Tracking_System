package com.bugtrackingsystem.entity;

import com.bugtrackingsystem.util.UserRoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity @NoArgsConstructor @AllArgsConstructor @Data
@Table(name="users_table")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="First Name cannot be blank")
    private String firstName;
	@NotBlank (message="Last Name cannot be blank")
    private String lastName;
	@Email(message="Invalid email format")
    private String email;
	@NotBlank(message="Password cannot be empty")
	@Size(min=8, message="Password length cannot be less than 8 characters")
	@JsonIgnore
    private String password;
	@NotNull(message="Role cannot be empty")
    private UserRoleEnum role;
	@NotBlank(message="Skills cannot be empty")
    private String skills;
}
