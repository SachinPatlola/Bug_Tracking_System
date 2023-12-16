package com.bugtrackingsystem.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugtrackingsystem.dto.DeveloperDTO;
import com.bugtrackingsystem.dto.TestEngineerDTO;
import com.bugtrackingsystem.entity.Developer;
import com.bugtrackingsystem.entity.Project;
import com.bugtrackingsystem.entity.TestEngineer;
import com.bugtrackingsystem.entity.User;
import com.bugtrackingsystem.exceptions.ResourceNotFoundException;
import com.bugtrackingsystem.repository.ProjectRepository;
import com.bugtrackingsystem.repository.TestEngineerRepository;
import com.bugtrackingsystem.repository.UserRepository;
import com.bugtrackingsystem.service.ITestEngineerService;

import java.util.List;
import java.util.Optional;
@Service
public class ITestEngineerServiceImplementation implements ITestEngineerService{
    @Autowired
    TestEngineerRepository testEngineerRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;
    
//    public Developer addDeveloper(DeveloperDTO developer) {
//        Project project = projectRepository.findById(developer.getProjectId()).orElseThrow(ResourceNotFoundException::new);
//        User user = userRepository.findById(developer.getUserId()).orElseThrow(ResourceNotFoundException::new);
//
//        Developer dev = new Developer(null, developer.getName(), developer.getSkill(),project,user );
//        developerRepository.save(dev);
//        return dev;
//    }

    @Override
    public TestEngineer addTestEngineer(TestEngineerDTO testEngineer)throws ResourceNotFoundException {
    	Project project=projectRepository.findById(testEngineer.getProjectId()).orElseThrow(ResourceNotFoundException::new);
    	User user=userRepository.findById(testEngineer.getUserId()).orElseThrow(ResourceNotFoundException::new);
    	TestEngineer test= new TestEngineer(null,testEngineer.getTesterName(),testEngineer.getTesterSkill(),project,user);
    	testEngineerRepository.save(test);
    	return test;
    }
//    public Developer updateDeveloper(DeveloperDTO developer, Long developerId) {
//        Project project = projectRepository.findById(developer.getProjectId()).orElseThrow(ResourceNotFoundException::new);
//        User user = userRepository.findById(developer.getUserId()).orElseThrow(ResourceNotFoundException::new);
//        Developer dev = new Developer(developerId, developer.getName(), developer.getSkill(),project,user );
//        developerRepository.save(dev);
//        return dev;
//    }
    @Override
    public TestEngineer updateTestEngineer(TestEngineerDTO tester, Long testerId) throws ResourceNotFoundException {
        Project project = projectRepository.findById(tester.getProjectId()).orElseThrow(ResourceNotFoundException::new);
       User user = userRepository.findById(tester.getUserId()).orElseThrow(ResourceNotFoundException::new);
        TestEngineer tester1 = new TestEngineer(testerId, tester.getTesterName(), tester.getTesterSkill(),project,user);
        testEngineerRepository.save(tester1);
        return tester1;
        
    }

    @Override
    public TestEngineer getTestEngById(Long testerId)throws ResourceNotFoundException {
        return testEngineerRepository.findById(testerId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<TestEngineer> getAllTesters() throws ResourceNotFoundException{
    	if(testEngineerRepository.findAll().isEmpty()) throw new ResourceNotFoundException("No testers found");
    	else
    		return testEngineerRepository.findAll();
    }

    @Override
    public Project getProjectByTestEngId(Long testEngId) throws ResourceNotFoundException{
    	
        return getTestEngById(testEngId).getProject();
    }
}
