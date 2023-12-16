package com.bugtrackingsystem.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class ProjectDTO {

	private Integer projId;
	@NotBlank(message="Project Name canot be empty")
	private String projName;
	@NotBlank(message="Description cannot be empty")
	private String projectDescription;
	@NotBlank(message="Status cannot be empty")
	private String projStatus;
	private List<DeveloperDTO> devList;
	private List<TestEngineerDTO> testEngList;
	

}
