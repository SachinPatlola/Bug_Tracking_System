package com.bugtrackingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class TestEngineerDTO {
	
	private String testerName;
	private String testerSkill;
	private Long userId;
	private Long projectId;
}
