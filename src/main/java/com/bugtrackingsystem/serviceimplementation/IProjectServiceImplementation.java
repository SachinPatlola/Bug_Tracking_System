package com.bugtrackingsystem.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bugtrackingsystem.dto.ProjectDTO;
import com.bugtrackingsystem.entity.Developer;
import com.bugtrackingsystem.entity.Project;
import com.bugtrackingsystem.entity.TestEngineer;
import com.bugtrackingsystem.exceptions.ResourceNotFoundException;
import com.bugtrackingsystem.repository.DeveloperRepository;
import com.bugtrackingsystem.repository.ProjectRepository;
import com.bugtrackingsystem.repository.TestEngineerRepository;
import com.bugtrackingsystem.service.IProjectService;

import java.util.List;

@Service
public class IProjectServiceImplementation implements IProjectService {
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	private DeveloperRepository developerRepository;
	@Autowired
	private TestEngineerRepository testEngineerRepository;

	// creating project
	public Project createProject(Project project) {
		project.setId(null);
		projectRepository.save(project);
		return project;
	}
	// get project details using id

	@Override
	public Project getProjectById(Long projId) {
		return projectRepository.findById(projId).orElseThrow(ResourceNotFoundException::new);
	}
	// All projects details
	public List<Project> getAllProjects() {
		if (projectRepository.findAll().isEmpty()) {
			throw new ResourceNotFoundException("No projects found");
		}
		return projectRepository.findAll();
	}
	// Update project
	@Override
	public Project updateProject(Project projDTO) {
		return projectRepository.save(projDTO);
	}

	public String deleteProject(Long projectId) {
		Project project = projectRepository.findById(projectId).orElseThrow(ResourceNotFoundException::new);
		projectRepository.delete(project);
		return "Project Deleted Successfully";
	}

}
