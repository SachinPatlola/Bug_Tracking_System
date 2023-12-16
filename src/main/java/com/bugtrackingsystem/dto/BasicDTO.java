package com.bugtrackingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class BasicDTO<T> {
    private boolean isSuccess;
    private String message;
    private T data;

    public BasicDTO(T data) {
        this.data = data;
        this.isSuccess = true;
        this.message = "OK";
    }
}
