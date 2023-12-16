package com.bugtrackingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.bugtrackingsystem.util.BugStatusEnum;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BugHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Bug bug;
    private BugStatusEnum bugStatus;
    private String comment;
    private LocalDateTime updatedOn;
}
