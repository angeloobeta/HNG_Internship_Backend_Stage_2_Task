package com.example.hng_internship_backend_stage_2_task.error;

public class PersonErrorException extends RuntimeException{
    private String message;
    public PersonErrorException(String message){super(message);}
}
