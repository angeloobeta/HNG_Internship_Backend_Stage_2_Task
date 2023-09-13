package com.example.hng_internship_backend_stage_2_task.error;

import com.example.hng_internship_backend_stage_2_task.entity.entity.ErrorMessage;
import com.example.hng_internship_backend_stage_2_task.error.PersonErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityException extends  ResponseEntityExceptionHandler{

    @ExceptionHandler(PersonErrorException.class)
    public ResponseEntity<ErrorMessage> nameNotFoundException(PersonErrorException personErrorException, WebRequest webRequest){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.FORBIDDEN, personErrorException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}