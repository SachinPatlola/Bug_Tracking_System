package com.bugtrackingsystem.dto;

import com.bugtrackingsystem.entity.Bug;
import com.bugtrackingsystem.util.BugStatusEnum;
import com.bugtrackingsystem.util.SeverityEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BugDTO {
	@NotBlank(message="Bug Title cannot be empty")
    private String title;
	@NotBlank(message="describe the bug")
    private String description;
	@NotBlank(message="Given role is invalid")
    private SeverityEnum severity;
	@NotBlank(message=" comments for additional info")
    private String comments;
    private Long createdByUserId;
	@NotNull(message="Project Id is null")
    private Long projectId;
	@NotNull(message="Assign to id cannot be null")
    private Long assignToId;

    @JsonIgnore
    public Bug toBugObject(){
        return new Bug(null,
                title,description,severity,comments,null,null,null, BugStatusEnum.NEW, LocalDateTime.now(),LocalDateTime.now());
    }
}
