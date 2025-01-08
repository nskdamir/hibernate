package com.example.ruslan.service;

import com.example.ruslan.model.Department;
import com.example.ruslan.model.Employee;
import com.example.ruslan.repository.DepartmentRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepositoryImpl departmentRepository;

    public List<Department> getDepartmentService(String position) {

        return departmentRepository.getDepartment(position);
    }

    @Transactional
    public Department createDepartmentWithEmployees(String departmentName,  List<Employee> employees) {
        Department department = new Department();
        department.setName(departmentName);

        for (Employee employee : employees) {
            employee.setDepartment(department);
        }

        department.setEmployees(employees);

        return departmentRepository.save(department);
    }
}
