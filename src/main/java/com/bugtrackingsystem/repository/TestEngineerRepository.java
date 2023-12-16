package com.bugtrackingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugtrackingsystem.entity.Developer;
import com.bugtrackingsystem.entity.TestEngineer;

import java.util.List;

@Repository
public interface TestEngineerRepository extends JpaRepository<TestEngineer,Long> {
    List<TestEngineer> findByProject_Id(Long id);

}
