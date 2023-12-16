package com.bugtrackingsystem.service;


import org.springframework.stereotype.Service;

import com.bugtrackingsystem.dto.TestEngineerDTO;
import com.bugtrackingsystem.entity.Project;
import com.bugtrackingsystem.entity.TestEngineer;

import java.util.List;
@Service
public interface ITestEngineerService {

	TestEngineer addTestEngineer(TestEngineerDTO testEngineer);

	TestEngineer updateTestEngineer(TestEngineerDTO testEngineer, Long testerId);

	TestEngineer getTestEngById(Long testerId);

	List<TestEngineer> getAllTesters();

	Project getProjectByTestEngId(Long testEngId);

}
