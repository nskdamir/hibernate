package com.example.ruslan.service;

import com.example.ruslan.model.Employee;
import com.example.ruslan.repository.EmployeeRepository;
import jakarta.persistence.OptimisticLockException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeServiceImplTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Test
    void updateEmployeeTest() throws InterruptedException {
        Employee employee = new Employee();
        employee.setName("Alina");
        employee.setStatus("ACTIVE");
        employee.setPosition("Java developer");

        employeeRepository.save(employee);

        Thread thread1 = new Thread(() -> {
           employeeService.updateEmployee(employeeRepository.findEmployeeByName("Alina"));
            System.out.println(employee.getStatus() + employee.getVersion());
        });

        Thread thread2 = new Thread(() -> {
            employeeService.updateEmployee(employeeRepository.findEmployeeByName("Alina"));
            System.out.println(employee.getStatus() + employee.getVersion());
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final status: " + employee.getName() + " " + employee.getStatus());
    }
}