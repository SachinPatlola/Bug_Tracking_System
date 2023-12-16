package com.bugtrackingsystem.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor @AllArgsConstructor
public class DeveloperDTO {
	@NotBlank(message="Dev name is empty")
	private String name;
	@NotBlank(message="skill is empty")
	private String skill;
	@NotNull(message="project id cannot be null")
	private Long projectId;
	@NotNull(message="user id cannot be null")
	private Long userId;

}