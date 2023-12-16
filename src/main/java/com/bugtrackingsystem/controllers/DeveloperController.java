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
import org.springframework.web.bind.annotation.RestController;

import com.bugtrackingsystem.dto.BasicDTO;
import com.bugtrackingsystem.dto.DeveloperDTO;
import com.bugtrackingsystem.entity.Developer;
import com.bugtrackingsystem.entity.Project;
import com.bugtrackingsystem.serviceimplementation.IDeveloperServiceImplementation;

@RestController
public class DeveloperController {
 
	@Autowired
	IDeveloperServiceImplementation developerServiceimplementation;
	
	
	@PostMapping("/add")
   public ResponseEntity<BasicDTO<Developer>> addDeveloper(@RequestBody DeveloperDTO developer) {
     return new ResponseEntity(new BasicDTO<>(developerServiceimplementation.addDeveloper(developer)), HttpStatus.CREATED);
 }

	@GetMapping("/getById/{devId}")
    public ResponseEntity<BasicDTO<Developer>> getDeveloperById(@PathVariable Long developerId) {
        return new ResponseEntity(new BasicDTO<>(developerServiceimplementation.getDeveloperById(developerId)),HttpStatus.OK);
    }
	
	 @PatchMapping("/update")
	    public ResponseEntity<BasicDTO<Developer>> updateDeveloper(@RequestBody DeveloperDTO DevDTO,Long developerId){
	        return ResponseEntity.ok(new BasicDTO<>(developerServiceimplementation.updateDeveloper(DevDTO, developerId)));
	    }
	
	
	 @GetMapping("/all")
	    public ResponseEntity<BasicDTO<List<Developer>>> allDevelopers(){
	        return ResponseEntity.ok(new BasicDTO<>(developerServiceimplementation.getAllDevelopers()));
	    }
	 @DeleteMapping("/delete/{devId}")
	    public  ResponseEntity<Object> deleteDeveloper(@PathVariable Long devId) {
	        return ResponseEntity.ok(developerServiceimplementation.deleteDeveloper(devId));
	        
	    }
	
}
