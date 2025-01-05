package com.example.ruslan.repository;

import com.example.ruslan.model.Department;
import com.example.ruslan.model.Employee;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.QueryHint;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Department> getDepartment() {
        EntityGraph<?> withDepartmentsAndEmployees = entityManager.getEntityGraph("WithDepartmentsAndEmployees");
        List<Department> departments = entityManager.createQuery(
                        "SELECT d FROM Department d", Department.class)
                .setHint(GraphSemantic.LOAD.getJakartaHintName(), withDepartmentsAndEmployees)
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
