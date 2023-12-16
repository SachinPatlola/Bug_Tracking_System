package com.bugtrackingsystem.service;

import org.springframework.stereotype.Service;

import com.bugtrackingsystem.dto.BugDTO;
import com.bugtrackingsystem.dto.UpdateBugDTO;
import com.bugtrackingsystem.entity.Bug;
import com.bugtrackingsystem.util.BugStatusEnum;
import com.bugtrackingsystem.util.SeverityEnum;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IBugService {
	Bug createBug(BugDTO bugDTO);

	Bug updateBug(UpdateBugDTO bugDTO);

	Bug findBugById(Long bugId);

	List<Bug> findAllBugs();

	List<Bug> findAllBugsByProjectId(Long projId);

	List<Bug> findBugsAssignedToDeveloper(Long devId);

	List<Bug> findBugsAssignedToDeveloperByStatus(Long devId, BugStatusEnum status);

	List<Bug> findBugsByStatus(BugStatusEnum status);

	List<Bug> findBugsBySeverity(SeverityEnum severity);

	List<Bug> findBugsByDate(LocalDate date);

	List<Bug> findBugsByDevIdAndDate(Long devId, LocalDate startDate, LocalDate endDate);

	List<Bug> findBugsCreatedByTestEngineer(Long testEngId);
	
	List<Bug> findBugsByProjectIdAndDevId(Long projId,Long devId);
	
	List<Bug> findBugsByProjectIdAndTestEngId(Long projId,Long testEngId);
	
	String deleteById(Long id);

}
