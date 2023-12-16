package com.bugtrackingsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugtrackingsystem.dto.BasicDTO;
import com.bugtrackingsystem.dto.DeveloperDTO;
import com.bugtrackingsystem.dto.TestEngineerDTO;
import com.bugtrackingsystem.entity.Developer;
import com.bugtrackingsystem.entity.TestEngineer;
import com.bugtrackingsystem.serviceimplementation.IDeveloperServiceImplementation;
import com.bugtrackingsystem.serviceimplementation.ITestEngineerServiceImplementation;

@RestController
@RequestMapping("/tester")
public class TesterController {
 
	@Autowired
	ITestEngineerServiceImplementation testEngineeerServiceimplementation;
	
	
	@PostMapping("/add")
   public ResponseEntity<BasicDTO<TestEngineer>> addTester(@RequestBody TestEngineerDTO tester) {
     return ResponseEntity.ok(new BasicDTO<>(testEngineeerServiceimplementation.addTestEngineer(tester)));
 }

	@GetMapping("/getById/{testerId}")
    public ResponseEntity<BasicDTO<TestEngineer>> getTesterById(@PathVariable Long testererId) {
        return new ResponseEntity(new BasicDTO<>(testEngineeerServiceimplementation.getTestEngById(testererId)),HttpStatus.OK);
    }
	
	 @PatchMapping("/update")
	    public ResponseEntity<BasicDTO<TestEngineer>> updateTester(@RequestBody TestEngineerDTO testDTO,Long testerId){
	        return ResponseEntity.ok(new BasicDTO<>(testEngineeerServiceimplementation.updateTestEngineer(testDTO,testerId)));
	    }
	
	
	 @GetMapping("/all")
	    public ResponseEntity<BasicDTO<List<TestEngineer>>> allTesters(){
	        return ResponseEntity.ok(new BasicDTO<>(testEngineeerServiceimplementation.getAllTesters()));
	    }
	
}
