package com.yaitskiy.citymanagement.exceptionHandler;

//import com.yaitskiy.citymanagement.exeption.PersonNotFoundException;
import com.yaitskiy.citymanagement.exeption.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handle (EntityNotFoundException personNotFoundException){
        return ResponseEntity.status(400).body(personNotFoundException.getMessage());
    }
}
