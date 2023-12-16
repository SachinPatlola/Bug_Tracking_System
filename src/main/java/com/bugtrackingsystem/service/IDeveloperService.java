package com.bugtrackingsystem.service;



import org.springframework.stereotype.Service;

import com.bugtrackingsystem.dto.DeveloperDTO;
import com.bugtrackingsystem.entity.Developer;
import com.bugtrackingsystem.entity.Project;

import java.util.List;

@Service
public interface IDeveloperService {
	Developer addDeveloper(DeveloperDTO developer);

	Developer updateDeveloper(DeveloperDTO developer,Long developerId);

	Developer getDeveloperById(Long devId);

	List<Developer> getAllDevelopers();

	Project getProjectByDevId(Long devId);
	String deleteDeveloper(Long devId);
}
