package com.bugtrackingsystem.dto;

import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private boolean isSuccess;
    private String message;

    public ErrorDTO(String message) {
        this.message = message;
    }
}
