package com.example.ruslan.service;

import com.example.ruslan.model.Department;
import com.example.ruslan.model.Employee;

import java.util.List;

public interface DepartmentService {
    List<Department> getDepartmentService(String position);

    Department createDepartmentWithEmployees(String departmentName,  List<Employee> employees);

    void removeDepartment(String name);

    Department getDepartmentWithEmployees(Long departmentId);
}
