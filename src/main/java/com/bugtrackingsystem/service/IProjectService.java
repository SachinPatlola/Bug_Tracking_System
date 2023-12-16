package com.bugtrackingsystem.service;

import org.springframework.stereotype.Service;
import com.bugtrackingsystem.entity.Developer;
import com.bugtrackingsystem.entity.Project;
import com.bugtrackingsystem.entity.TestEngineer;

import java.util.List;
@Service
public interface IProjectService {

	Project createProject(Project projDTO);

	Project getProjectById(Long projId);

	List<Project> getAllProjects();

	Project updateProject(Project projDTO);
	
	String deleteProject(Long projectId);
}
