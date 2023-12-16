package com.bugtrackingsystem.exceptions;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Validation;
import javax.validation.ValidationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bugtrackingsystem.dto.ErrorDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundException ex){
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    protected ResponseEntity<ErrorDTO> handleResourceAlreadyExistException(ResourceAlreadyExistException ex){
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.CONFLICT);
        
    }
   @ExceptionHandler(MethodArgumentNotValidException.class)
   	public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
   		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(error->error.getDefaultMessage())
   				.collect(Collectors.toList());
   		Map<String, List<String>> errorResponse = new HashMap<>();
   		errorResponse.put("errors", errors);
   		return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
   	}
   
}
