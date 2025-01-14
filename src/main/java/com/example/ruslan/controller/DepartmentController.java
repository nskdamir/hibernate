package com.example.ruslan.controller;

import com.example.ruslan.model.Department;
import com.example.ruslan.model.Employee;
import com.example.ruslan.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/api/getDepartment")
    public List<Department> getDepartmentList(@RequestParam String position) {
       return departmentService.getDepartmentService(position);
    }

    @GetMapping("/api/getDepartmentWithEmployees")
    public Department getDepartmentWithEmployees(@RequestParam Long departmentId) {
        return departmentService.getDepartmentWithEmployees(departmentId);
    }

    @PostMapping("/api/createDepartment")
    public Department createDepartmentWithEmployees(@RequestParam String departmentName, @RequestBody List<Employee> employees) {
        return departmentService.createDepartmentWithEmployees(departmentName, employees);
    }
    @PostMapping("/api/removeDepartment")
    public void removeDepartment(@RequestParam String departmentName) {
        departmentService.removeDepartment(departmentName);
    }

}
