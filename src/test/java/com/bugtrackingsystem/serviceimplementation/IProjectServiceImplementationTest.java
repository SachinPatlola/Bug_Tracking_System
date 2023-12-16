package com.bugtrackingsystem.serviceimplementation;
import com.bugtrackingsystem.entity.Project;
import com.bugtrackingsystem.exceptions.ResourceNotFoundException;
import com.bugtrackingsystem.repository.DeveloperRepository;
import com.bugtrackingsystem.repository.ProjectRepository;
import com.bugtrackingsystem.repository.TestEngineerRepository;
import com.bugtrackingsystem.serviceimplementation.IProjectServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
class IProjectServiceImplementationTest {
 
    @InjectMocks
    private IProjectServiceImplementation projectService;
 
    @Mock
    private ProjectRepository projectRepository;
 
    @Mock
    private DeveloperRepository developerRepository;
 
    @Mock
    private TestEngineerRepository testEngineerRepository;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testCreateProject() {
        // Arrange
        Project projectToCreate = new Project(null, "ProjectName", "InProgress", "ProjectDescription");
        when(projectRepository.save(projectToCreate)).thenReturn(new Project(1L, "ProjectName", "InProgress", "ProjectDescription"));
 
        // Act
        Project createdProject = projectService.createProject(projectToCreate);
 
        // Assert
        //assertNotNull(createdProject.getId());
        assertEquals("ProjectName", createdProject.getName());
        assertEquals("InProgress", createdProject.getStatus());
        assertEquals("ProjectDescription", createdProject.getDescription());
 
        // Verify that projectRepository.save method was called once with the correct argument
        verify(projectRepository, times(1)).save(projectToCreate);
        verifyNoMoreInteractions(projectRepository, developerRepository, testEngineerRepository);
    }
 
    @Test
    void testGetProjectById() {
        // Arrange
        Long projectId = 1L;
        Project project = new Project(projectId, "ProjectName", "InProgress", "ProjectDescription");
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
 
        // Act
        Project retrievedProject = projectService.getProjectById(projectId);
 
        // Assert
        assertNotNull(retrievedProject);
        assertEquals(projectId, retrievedProject.getId());
        assertEquals("ProjectName", retrievedProject.getName());
        assertEquals("InProgress", retrievedProject.getStatus());
        assertEquals("ProjectDescription", retrievedProject.getDescription());
 
        // Verify that projectRepository.findById method was called once with the correct argument
        verify(projectRepository, times(1)).findById(projectId);
        verifyNoMoreInteractions(projectRepository, developerRepository, testEngineerRepository);
    }
 
    @Test
    void testGetProjectById_ResourceNotFoundException() {
        // Arrange
        Long projectId = 1L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());
 
        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> projectService.getProjectById(projectId));
 
        // Verify that projectRepository.findById method was called once with the correct argument
        verify(projectRepository, times(1)).findById(projectId);
        verifyNoMoreInteractions(projectRepository, developerRepository, testEngineerRepository);
    }

	
	  @Test
	  void testGetAllProjects() { 
		  // Arrange 
		  List<Project> projects =
	  Arrays.asList( new Project(1L, "Project1", "InProgress", "Description1"), new
	  Project(2L, "Project2", "Completed", "Description2") );
	  when(projectRepository.findAll()).thenReturn(projects);
	  
	  // Act
	  List<Project> retrievedProjects = projectService.getAllProjects();
	  
	  // Assert 
	  assertEquals(projects.size(), retrievedProjects.size());
	  assertEquals(projects, retrievedProjects);
	  
	  // Verify that projectRepository.findAll method was called once
	  verify(projectRepository, times(2)).findAll();
	  verifyNoMoreInteractions(projectRepository, developerRepository,
	  testEngineerRepository); }
	 
    @Test
    void testUpdateProject() {
        // Arrange
        Project projectToUpdate = new Project(1L, "UpdatedProject", "InProgress", "UpdatedDescription");
        when(projectRepository.save(projectToUpdate)).thenReturn(projectToUpdate);
 
        // Act
        Project updatedProject = projectService.updateProject(projectToUpdate);
 
        // Assert
        assertEquals(1L, updatedProject.getId());
        assertEquals("UpdatedProject", updatedProject.getName());
        assertEquals("InProgress", updatedProject.getStatus());
        assertEquals("UpdatedDescription", updatedProject.getDescription());
 
        // Verify that projectRepository.save method was called once with the correct argument
        verify(projectRepository, times(1)).save(projectToUpdate);
        verifyNoMoreInteractions(projectRepository, developerRepository, testEngineerRepository);
    }
	
	@Test
	void testDeleteProject() {
		// Arrange
		Long projectId = 1L;
		Project projectToDelete = new Project(projectId, "ProjectToDelete", "InProgress", "DescriptionToDelete");
		when(projectRepository.findById(projectId)).thenReturn(Optional.of(projectToDelete));

		// Act
		String result = projectService.deleteProject(projectId);

		// Assert
		assertEquals("Project Deleted Successfully", result);

		// Verify that projectRepository.findById and projectRepository.delete methods
		// were called once with the correct arguments
		verify(projectRepository, times(1)).findById(projectId);
		verify(projectRepository, times(1)).delete(projectToDelete);
		verifyNoMoreInteractions(projectRepository, developerRepository, testEngineerRepository);
	}
 
    @Test
    void testDeleteProject_ResourceNotFoundException() {
        // Arrange
        Long projectId = 1L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());
 
        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> projectService.deleteProject(projectId));
 
        // Verify that projectRepository.findById method was called once with the correct argument
        verify(projectRepository, times(1)).findById(projectId);
        verifyNoMoreInteractions(projectRepository, developerRepository, testEngineerRepository);
    }
}