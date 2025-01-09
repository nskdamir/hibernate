package com.example.ruslan.repository;

import com.example.ruslan.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "Select e from Employee e where e.name = :name")
    Employee findEmployeeByName(String name);
}
