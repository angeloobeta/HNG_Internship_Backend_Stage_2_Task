package com.example.hng_internship_backend_stage_2_task.service;

import com.example.hng_internship_backend_stage_2_task.entity.Person;

public interface PersonService {
    Person findPersonByName(Person name);
}
