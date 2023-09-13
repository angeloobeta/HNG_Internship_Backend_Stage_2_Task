package com.example.hng_internship_backend_stage_2_task.repository;

import com.example.hng_internship_backend_stage_2_task.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
