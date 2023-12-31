package com.example.hng_internship_backend_stage_2_task.controller;

import com.example.hng_internship_backend_stage_2_task.Dto.ApiResponse;
import com.example.hng_internship_backend_stage_2_task.entity.Person;
import com.example.hng_internship_backend_stage_2_task.error.PersonErrorException;
import com.example.hng_internship_backend_stage_2_task.repository.PersonRepository;
import com.example.hng_internship_backend_stage_2_task.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
//    private PersonRepository personRepository;
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @PostMapping
    public ResponseEntity<ApiResponse> createPerson(@Valid @RequestBody Person person){

        // check if the name already exist
if(personService.findPersonByName(person) == null){
    // If the name doesn't exist save it to the database
    Person createdPerson = personRepository.save(person);
} else if(personService.findPersonByName(person).getName().equals(person.getName())) {
           throw new PersonErrorException("Name already exits");}

        ApiResponse response = new ApiResponse();
        response.setMessage("User created successfully");
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setTime(LocalDateTime.now());
//        person = personRepository.findById(id).orElse(null);
        response.setId(personService.findPersonByName(person).getId());



        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    };

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getPersonById(@PathVariable long user_id) throws PersonErrorException {
        Person person = personRepository.findById(user_id).orElse(null);

        if(person != null){
            ApiResponse response = new  ApiResponse();
            response.setMessage(person.getName());
            response.setTime(LocalDateTime.now());
            response.setId(user_id);
            response.setStatusCode(HttpStatus.OK.value());
            ;
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            throw new PersonErrorException("User doesn't exist by that Id");
//            ApiResponse response = new ApiResponse();
//            response.setMessage("User not found");
//            response.setStatusCode(HttpStatus.NOT_FOUND.value());
//            response.setTime(LocalDateTime.now());
//
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @PutMapping("/{user_id}")
    public  ResponseEntity<ApiResponse> updatePerson(@PathVariable Long user_id, @RequestBody Person updatePerson){
        if(personRepository.existsById(user_id)){
            updatePerson.setId(user_id);
            Person updatedPerson = personRepository.save(updatePerson);
            ApiResponse response = new ApiResponse();
            response.setMessage("User updated successfully");
            response.setStatusCode(HttpStatus.CREATED.value());
            response.setTime(LocalDateTime.now());
            response.setId(updatedPerson.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            throw new PersonErrorException("User doesn't exist by that Id");
//            ApiResponse response = new ApiResponse();
//            response.setMessage("User doesn't exist");
//            response.setStatusCode(HttpStatus.NO_CONTENT.value());
//            response.setTime(LocalDateTime.now());
////            response.setUserId(id);
//            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<ApiResponse> deletePerson(@PathVariable Long user_id){
        if(personRepository.existsById(user_id)){
            personRepository.deleteById(user_id);
            ApiResponse response = new ApiResponse();
            response.setMessage("User deleted successfully");
            response.setStatusCode(HttpStatus.OK.value());
            response.setTime(LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            throw new PersonErrorException("User doesn't exist by that Id");
        }
    }

}
