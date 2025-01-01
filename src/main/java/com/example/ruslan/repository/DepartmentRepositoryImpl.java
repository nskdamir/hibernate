package com.example.ruslan.repository;

import com.example.ruslan.model.Department;
import com.example.ruslan.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Department> getDepartment() {

        List<Department> departments = entityManager.createQuery(
                "SELECT d FROM Department d LEFT JOIN FETCH d.employees e order by e.name", Department.class)
                .setFirstResult(1)
                .setMaxResults(2)
                .getResultList();


        for (Department department : departments) {
            List<Employee> employees = department.getEmployees(); // Triggers a query for each department
            System.out.println("Department: " + department.getName() + ", Employees: " + employees.size());

            employees.forEach(employee ->
                    System.out.println("Employee: " + employee.getName())
            );
        }
        return departments;
    }
}
