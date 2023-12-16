package com.bugtrackingsystem.serviceimplementation;

import org.jetbrains.annotations.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugtrackingsystem.dto.BugDTO;
import com.bugtrackingsystem.dto.SearchDTO;
import com.bugtrackingsystem.dto.UpdateBugDTO;
import com.bugtrackingsystem.entity.*;
import com.bugtrackingsystem.exceptions.ResourceNotFoundException;
import com.bugtrackingsystem.repository.*;
import com.bugtrackingsystem.service.IBugService;
import com.bugtrackingsystem.util.BugStatusEnum;
import com.bugtrackingsystem.util.SeverityEnum;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IBugServiceImplementation  implements IBugService{

    @Autowired
    private BugRepository bugRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BugHistoryRepository bugHistoryRepository;
    @Autowired
    private DeveloperRepository developerRepository;
    @Transactional
    public Bug createBug(@NotNull BugDTO bugDto)throws ResourceNotFoundException{
        var bug = bugDto.toBugObject();
        User createdBy = userRepository.findById(bugDto.getCreatedByUserId()).orElseThrow(ResourceNotFoundException::new);
        User assignTo = userRepository.findById(bugDto.getAssignToId()).orElseThrow(ResourceNotFoundException::new);
        Project project = projectRepository.findById(bugDto.getProjectId()).orElseThrow(ResourceNotFoundException::new);
        bug.setCreatedBy(createdBy);
        bug.setProject(project);
        bug.setAssignTo(assignTo);
        bugRepository.save(bug);
        bugHistoryRepository.save(new BugHistory(null, bug, bug.getBugStatus(), bug.getComments(), bug.getCreatedOn()));
        return bug;
    }

    @Transactional
    public Bug updateBug(@NotNull UpdateBugDTO updateBugDTO){
        Bug bug = updateBugDTO.toBugObject(this);
        bugRepository.save(bug);
        bugHistoryRepository.save(new BugHistory(null, bug, bug.getBugStatus(), bug.getComments(), bug.getLastUpdatedOn()));
        return bug;
    }

    public Bug findBugById(Long bugId)throws ResourceNotFoundException{
        return bugRepository.findById(bugId).orElseThrow(ResourceNotFoundException::new);
    }

    public List<BugHistory> findBugHistoryById(Long bugId) throws ResourceNotFoundException{
        return bugHistoryRepository.findByBug_Id(bugId);
    }

    public List<Bug> myAssignedBugs(Long userId) throws ResourceNotFoundException{
        return bugRepository.findByAssignTo_Id(userId);
    }
    public List<Bug> myCreatedBugs(Long userId) throws ResourceNotFoundException{
        return bugRepository.findByCreatedBy_Id(userId);
    }

    public List<Bug> findAllBugsByProjectId(Long projectId)throws ResourceNotFoundException{
        return bugRepository.findByProject_Id(projectId);
    }

    @Override
    public List<Bug> findBugsAssignedToDeveloper(Long devId) throws ResourceNotFoundException{
        Developer developer = developerRepository.findById(devId).orElseThrow(ResourceNotFoundException::new);
        return bugRepository.findByAssignTo_Id(developer.getUser().getId());
    }

    @Override
    public List<Bug> findBugsAssignedToDeveloperByStatus(Long devId, BugStatusEnum status)throws ResourceNotFoundException {
        Developer developer = developerRepository.findById(devId).orElseThrow(ResourceNotFoundException::new);
        return bugRepository.findByAssignToAndBugStatus(developer.getUser(),status);
    }

    @Override
    public List<Bug> findBugsByStatus(BugStatusEnum status) throws ResourceNotFoundException {
        return bugRepository.findByBugStatus(status);
    }

    @Override
    public List<Bug> findBugsBySeverity(SeverityEnum severity) throws ResourceNotFoundException{
        return bugRepository.findBySeverity(severity);
    }

    @Override
    public List<Bug> findBugsByDate(LocalDate date) throws ResourceNotFoundException{
        return bugRepository.findAll().stream().filter(item -> item.getCreatedOn().toLocalDate().isEqual(date)).collect(Collectors.toList());

    }

    @Override
    public List<Bug> findBugsByDevIdAndDate(Long devId, LocalDate startDate, LocalDate endDate) throws ResourceNotFoundException{
        Developer developer = developerRepository.findById(devId).orElseThrow(ResourceNotFoundException::new);
        return bugRepository.findAll().stream()
                .filter(item -> {
                    return item.getAssignTo().equals(developer.getUser()) &&
                            item.getCreatedOn().toLocalDate().isAfter(startDate.minusDays(1L)) &&
                            item.getCreatedOn().toLocalDate().isBefore(endDate.plusDays(1L));
                }).collect(Collectors.toList());
    }

    @Override
    public List<Bug> findBugsCreatedByTestEngineer(Long testEngId) throws ResourceNotFoundException {
        return bugRepository.findByCreatedBy_Id(testEngId);
    }

    @Override
    public List<Bug> findBugsByProjectIdAndDevId(Long projId, Long devId)throws ResourceNotFoundException {
        return bugRepository.findByProject_IdAndAssignTo_Id(projId,devId);
    }

    @Override
    public List<Bug> findBugsByProjectIdAndTestEngId(Long projId, Long testEngId) throws ResourceNotFoundException {
        return bugRepository.findByProject_IdAndCreatedBy_Id(projId,testEngId );
    }

    public List<Bug> findAllBugs() throws ResourceNotFoundException{
    	if(bugRepository.findAll().isEmpty()) {
    		throw new ResourceNotFoundException("No Bugs found");
    	}
        return bugRepository.findAll();
    }

    public List<Bug> searchBug(SearchDTO searchDTO)  {
        return bugRepository.findByAssignTo_IdAndProject_IdAndBugStatusAndSeverity(
                searchDTO.getUserId(),
                searchDTO.getProjectId(),
                searchDTO.getBugStatus(),
                searchDTO.getSeverity()
        );
    }
    
    public String deleteById(Long id) {
		Bug bug = bugRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        projectRepository.deleteById(id);
		return "Bug Deleted Succesfully";
	}
}
