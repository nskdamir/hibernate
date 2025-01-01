package com.example.ruslan.controller;

import com.example.ruslan.model.Department;
import com.example.ruslan.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/api/getDepartment")
    public List<Department> getDepartmentList() {
       return departmentService.getDepartmentService();
    }
}
