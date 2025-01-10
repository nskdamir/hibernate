package com.example.ruslan.service;

import com.example.ruslan.model.Department;
import com.example.ruslan.model.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    void updateEmployee(Employee employee);
    void addEmployee(Employee employee, Department department);
}
