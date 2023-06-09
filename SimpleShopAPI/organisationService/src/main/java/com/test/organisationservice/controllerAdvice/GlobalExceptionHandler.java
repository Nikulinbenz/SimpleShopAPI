package com.test.organisationservice.controllerAdvice;

import com.test.userservice.dto.ErrorDTO;
import com.test.userservice.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(RuntimeException exception) {
        final ErrorDTO errorDTO = new ErrorDTO(
                LocalDateTime.now(),
                exception.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
