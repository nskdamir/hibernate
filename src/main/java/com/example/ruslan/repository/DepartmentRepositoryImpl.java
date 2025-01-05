package com.example.ruslan.repository;

import com.example.ruslan.model.Department;
import com.example.ruslan.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Department> getDepartment() {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("statusFilter").setParameter("status", "ACTIVE");
        List<Department> departments = entityManager.createQuery(
                        "SELECT DISTINCT d FROM Department d LEFT JOIN FETCH d.employees e", Department.class)
                .getResultList();
        session.disableFilter("statusFilter");

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
