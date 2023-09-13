package com.example.hng_internship_backend_stage_2_task.repository;

import com.example.hng_internship_backend_stage_2_task.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByName(String personName);
}
