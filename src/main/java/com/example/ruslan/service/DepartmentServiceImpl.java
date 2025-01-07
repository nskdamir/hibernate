package com.example.ruslan.service;

import com.example.ruslan.model.Department;
import com.example.ruslan.repository.DepartmentRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepositoryImpl departmentRepository;

    public List<Department> getDepartmentService(String position) {

        return departmentRepository.getDepartment(position);
    }
}
