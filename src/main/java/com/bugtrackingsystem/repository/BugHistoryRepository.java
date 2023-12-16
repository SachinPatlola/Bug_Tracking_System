package com.bugtrackingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugtrackingsystem.entity.BugHistory;

import java.util.List;

@Repository
public interface BugHistoryRepository extends JpaRepository<BugHistory, Long> {
    List<BugHistory> findByBug_Id(Long id);
}
