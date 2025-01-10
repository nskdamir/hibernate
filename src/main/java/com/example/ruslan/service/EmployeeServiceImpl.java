package com.example.ruslan.service;

import com.example.ruslan.model.Department;
import com.example.ruslan.model.Employee;
import com.example.ruslan.repository.DepartmentRepository;
import com.example.ruslan.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    public void updateEmployee(Employee employee) {
        Employee employeeByName = employeeRepository.findEmployeeByName(employee.getName());
        if (employeeByName.getStatus().equals("ACTIVE")) {
            employeeByName.setStatus("INACTIVE");
        }
        employeeRepository.save(employeeByName);
    }

    @Transactional
    public void addEmployee(Employee employee, Department department) {
        try {
            departmentRepository.save(department);
            employee.setDepartment(department);
            employeeRepository.save(employee);
        } catch (Exception e) {
            throw new RuntimeException("Исключение, откат изменений", e);
        }
    }
}

