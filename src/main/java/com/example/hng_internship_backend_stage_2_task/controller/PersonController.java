package com.example.hng_internship_backend_stage_2_task.controller;

import com.example.hng_internship_backend_stage_2_task.Dto.ApiResponse;
import com.example.hng_internship_backend_stage_2_task.entity.Person;
import com.example.hng_internship_backend_stage_2_task.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @PostMapping
    public ResponseEntity<ApiResponse> createPerson(@RequestBody Person person){
        Person createdPerson = personRepository.save(person);
        ApiResponse response = new ApiResponse();
        response.setMessage("User created successfully");
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setTime(LocalDateTime.now());
        response.setUserId(createdPerson.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    };

    @GetMapping("/{id}")
    public ResponseEntity<?> getPerson(@PathVariable long id){
        Person person = personRepository.findById(id).orElse(null);

        if(person != null){
            return ResponseEntity.ok(person);
        }else{
            ApiResponse response = new ApiResponse();
            response.setMessage("User not found");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setTime(LocalDateTime.now());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/{id}")
    public  ResponseEntity<ApiResponse> updatePerson(@PathVariable Long id, @RequestBody Person updatePerson){
        if(personRepository.existsById(id)){
            updatePerson.setId(id);
            Person updatedPerson = personRepository.save(updatePerson);
            ApiResponse response = new ApiResponse();
            response.setMessage("User updated successfully");
            response.setStatusCode(HttpStatus.CREATED.value());
            response.setTime(LocalDateTime.now());
            response.setUserId(updatedPerson.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            ApiResponse response = new ApiResponse();
            response.setMessage("User doesn't exist");
            response.setStatusCode(HttpStatus.NO_CONTENT.value());
            response.setTime(LocalDateTime.now());
//            response.setUserId(id);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePerson(@PathVariable Long id){
        if(personRepository.existsById(id)){
            personRepository.deleteById(id);
            ApiResponse response = new ApiResponse();
            response.setMessage("User deleted successfully");
            response.setStatusCode(HttpStatus.OK.value());
            response.setTime(LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            ApiResponse response = new ApiResponse();
            response.setMessage("User doesn't exist");
            response.setStatusCode(HttpStatus.NO_CONTENT.value());
            response.setTime(LocalDateTime.now());
            return ResponseEntity.badRequest().body(response);
        }
    }

}
