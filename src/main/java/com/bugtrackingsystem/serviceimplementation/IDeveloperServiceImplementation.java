package com.bugtrackingsystem.serviceimplementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugtrackingsystem.dto.DeveloperDTO;
import com.bugtrackingsystem.entity.Developer;
import com.bugtrackingsystem.entity.Project;
import com.bugtrackingsystem.entity.User;
import com.bugtrackingsystem.exceptions.ResourceNotFoundException;
import com.bugtrackingsystem.repository.DeveloperRepository;
import com.bugtrackingsystem.repository.ProjectRepository;
import com.bugtrackingsystem.repository.UserRepository;
import com.bugtrackingsystem.service.IDeveloperService;

import java.util.List;
import java.util.Optional;
@Service
public class IDeveloperServiceImplementation implements IDeveloperService{
    @Autowired
    DeveloperRepository developerRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Developer addDeveloper(DeveloperDTO developer) {
        Project project = projectRepository.findById(developer.getProjectId()).orElseThrow(ResourceNotFoundException::new);
        User user = userRepository.findById(developer.getUserId()).orElseThrow(ResourceNotFoundException::new);

        Developer dev = new Developer(null, developer.getName(), developer.getSkill(),project,user );
        developerRepository.save(dev);
        return dev;
    }

    @Override
    public Developer updateDeveloper(DeveloperDTO developer, Long developerId) {
        Project project = projectRepository.findById(developer.getProjectId()).orElseThrow(ResourceNotFoundException::new);
        User user = userRepository.findById(developer.getUserId()).orElseThrow(ResourceNotFoundException::new);
        Developer dev = new Developer(developerId, developer.getName(), developer.getSkill(),project,user );
        developerRepository.save(dev);
        return dev;
    }

    @Override
    public Developer getDeveloperById(Long devId) {
        return developerRepository.findById(devId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return developerRepository.findAll();
    }

    @Override
    public Project getProjectByDevId(Long devId) {
        return getDeveloperById(devId).getProject();
    }
   // public String deleteProject(Long projectId) {
	// Project project = projectRepository.findById(projectId).orElseThrow(ResourceNotFoundException::new);
      //  projectRepository.delete(project);
		//return "Project Deleted Succesfully";
// }
	@Override
	public String deleteDeveloper(Long devId) {
		Developer dev=developerRepository.findById(devId).orElseThrow(ResourceNotFoundException::new);
		developerRepository.delete(dev);
		
		return "Deleted Developer";
	}
}
