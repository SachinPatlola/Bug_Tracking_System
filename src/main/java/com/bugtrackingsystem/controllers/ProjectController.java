package com.bugtrackingsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bugtrackingsystem.dto.BasicDTO;
import com.bugtrackingsystem.entity.Project;
import com.bugtrackingsystem.serviceimplementation.IProjectServiceImplementation;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    IProjectServiceImplementation iProjectServiceImplementation;
    // to create a project
	@PostMapping("/create")
    public ResponseEntity<BasicDTO<Project>> createProject(@RequestBody Project project){
        return new ResponseEntity(new BasicDTO<>(iProjectServiceImplementation.createProject(project)), HttpStatus.CREATED);
    }
	// get all projects
    @GetMapping("/all")
    public ResponseEntity<BasicDTO<List<Project>>> allProjects(){
        return ResponseEntity.ok(new BasicDTO<>(iProjectServiceImplementation.getAllProjects()));
    }
    
    // get details of Project using id
    @GetMapping("/getById/{projId}")
    public ResponseEntity<BasicDTO<Project>> getProjectById(@PathVariable Long projId){
        return new ResponseEntity(new BasicDTO<>(iProjectServiceImplementation.getProjectById(projId)),HttpStatus.OK);
    }
    @PatchMapping("/update")
    public ResponseEntity<BasicDTO<Project>> updateProject(@RequestBody Project projDTO){
        return ResponseEntity.ok(new BasicDTO<>(iProjectServiceImplementation.updateProject(projDTO)));
    }
    
    @DeleteMapping("/delete/{projectId}")
    public  ResponseEntity<Object> deleteProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(iProjectServiceImplementation.deleteProject(projectId));
        
    }
    
    
    }
    
