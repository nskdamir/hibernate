package com.example.ruslan.repository;

import com.example.ruslan.model.Department;
import com.example.ruslan.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value =  "SELECT DISTINCT d FROM Department d JOIN FETCH d.employees e WHERE e.position = :position")
    List<Department> getDepartment(String position);

}
