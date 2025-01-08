package com.example.ruslan.repository;

import com.example.ruslan.model.Department;
import com.example.ruslan.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Department> getDepartment(String position) {
        List<Department> departments = entityManager.createQuery(
                        "SELECT DISTINCT d FROM Department d JOIN FETCH d.employees e WHERE e.position = :position", Department.class)
                .setParameter("position", position)
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

    public Department save(Department entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return entity;
    }
}
