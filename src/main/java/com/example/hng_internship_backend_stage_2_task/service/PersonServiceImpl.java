package com.example.hng_internship_backend_stage_2_task.service;

import com.example.hng_internship_backend_stage_2_task.entity.Person;
import com.example.hng_internship_backend_stage_2_task.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person findPersonByName(Person name) {
        return personRepository.findByName(name.getName());
    }
}
