package com.example.ruslan.service;

import com.example.ruslan.application_error.DepartmentWithEmployeesException;
import com.example.ruslan.model.Department;
import com.example.ruslan.model.Employee;
import com.example.ruslan.repository.DepartmentRepository;
import com.example.ruslan.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> getDepartmentService(String position) {

        List<Department> departments = departmentRepository.getDepartment(position);
        for (Department department : departments) {
            List<Employee> employees = department.getEmployees();
            System.out.println("Department: " + department.getName() + ", Employees: " + employees.size());

            employees.forEach(employee ->
                    System.out.println("Employee: " + employee.getName())
            );
        }
        return departments;
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

    @Transactional
    public void removeDepartment(String name) {
        try {
            Department departmentByName = departmentRepository.getDepartmentByName(name);

            if (departmentByName.getEmployees().isEmpty()) {
                departmentRepository.delete(departmentByName);
            } else {
                throw new DepartmentWithEmployeesException("Не можем удалить деепартамент, т.к. в нем есть сотрудники");
            }
        } catch (Exception e) {
            throw new RuntimeException("Исключение, откат изменений", e);
        }
    }

    @Transactional(readOnly = true)
    public Department getDepartmentWithEmployees(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Департамент не найден"));

        department.getEmployees().forEach(employee ->
                System.out.println("Employee: " + employee.getName())
        );

        return department;
    }
}