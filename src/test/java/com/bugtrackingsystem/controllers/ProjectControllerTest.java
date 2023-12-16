package com.bugtrackingsystem.controllers;


import com.bugtrackingsystem.dto.BasicDTO;
import com.bugtrackingsystem.entity.Project;
import com.bugtrackingsystem.serviceimplementation.IProjectServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectControllerTest {

   @InjectMocks
   private ProjectController projectController;

   @Mock
   private IProjectServiceImplementation projectService;

   @BeforeEach
   void setUp() {
       MockitoAnnotations.openMocks(this);
   }

   @Test
   void testCreateProject() {
       // Arrange
       Project projectToCreate = new Project(null, "ProjectName", "InProgress", "ProjectDescription");
       when(projectService.createProject(projectToCreate)).thenReturn(new Project(1L, "ProjectName", "InProgress", "ProjectDescription"));

       // Act
       ResponseEntity<BasicDTO<Project>> responseEntity = projectController.createProject(projectToCreate);

       // Assert
       assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
       assertNotNull(responseEntity.getBody());
       assertNotNull(responseEntity.getBody().getData().getId());
       assertEquals("ProjectName", responseEntity.getBody().getData().getName());
       assertEquals("InProgress", responseEntity.getBody().getData().getStatus());
       assertEquals("ProjectDescription", responseEntity.getBody().getData().getDescription());

       // Verify that projectService.createProject method was called once with the correct argument
       verify(projectService, times(1)).createProject(projectToCreate);
       verifyNoMoreInteractions(projectService);
   }

   @Test
   void testAllProjects() {
       // Arrange
       List<Project> projects = Arrays.asList(
               new Project(1L, "Project1", "InProgress", "Description1"),
               new Project(2L, "Project2", "Completed", "Description2")
       );
       when(projectService.getAllProjects()).thenReturn(projects);

       // Act
       ResponseEntity<BasicDTO<List<Project>>> responseEntity = projectController.allProjects();

       // Assert
       assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
       assertNotNull(responseEntity.getBody());
       assertEquals(projects.size(), responseEntity.getBody().getData().size());
       assertEquals(projects, responseEntity.getBody().getData());

       // Verify that projectService.getAllProjects method was called once
       verify(projectService, times(1)).getAllProjects();
       verifyNoMoreInteractions(projectService);
   }

   @Test
   void testGetProjectById() {
       // Arrange
       Long projectId = 1L;
       Project project = new Project(projectId, "ProjectName", "InProgress", "ProjectDescription");
       when(projectService.getProjectById(projectId)).thenReturn(project);

       // Act
       ResponseEntity<BasicDTO<Project>> responseEntity = projectController.getProjectById(projectId);

       // Assert
       assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
       assertNotNull(responseEntity.getBody());
       assertNotNull(responseEntity.getBody().getData().getId());
       assertEquals("ProjectName", responseEntity.getBody().getData().getName());
       assertEquals("InProgress", responseEntity.getBody().getData().getStatus());
       assertEquals("ProjectDescription", responseEntity.getBody().getData().getDescription());

       // Verify that projectService.getProjectById method was called once with the correct argument
       verify(projectService, times(1)).getProjectById(projectId);
       verifyNoMoreInteractions(projectService);
   }

   @Test
   void testUpdateProject() {
       // Arrange
       Project projectToUpdate = new Project(1L, "UpdatedProject", "InProgress", "UpdatedDescription");
       when(projectService.updateProject(projectToUpdate)).thenReturn(projectToUpdate);

       // Act
       ResponseEntity<BasicDTO<Project>> responseEntity = projectController.updateProject(projectToUpdate);

       // Assert
       assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
       assertNotNull(responseEntity.getBody());
       assertNotNull(responseEntity.getBody().getData().getId());
       assertEquals("UpdatedProject", responseEntity.getBody().getData().getName());
       assertEquals("InProgress", responseEntity.getBody().getData().getStatus());
       assertEquals("UpdatedDescription", responseEntity.getBody().getData().getDescription());

       // Verify that projectService.updateProject method was called once with the correct argument
       verify(projectService, times(1)).updateProject(projectToUpdate);
       verifyNoMoreInteractions(projectService);
   }

   @Test
   void testDeleteProject() {
       // Arrange
       Long projectId = 1L;
       when(projectService.deleteProject(projectId)).thenReturn("Project Deleted Successfully");

       // Act
       ResponseEntity<Object> responseEntity = projectController.deleteProject(projectId);

       // Assert
       assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
       assertEquals("Project Deleted Successfully", responseEntity.getBody());

       // Verify that projectService.deleteProject method was called once with the correct argument
       verify(projectService, times(1)).deleteProject(projectId);
       verifyNoMoreInteractions(projectService);
   }
}