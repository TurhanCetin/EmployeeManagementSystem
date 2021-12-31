package com.example.employemanagementsystem.Service;

import com.example.employemanagementsystem.Model.Department;
import com.example.employemanagementsystem.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepo;

    public List<Department> listAll(){
        return departmentRepo.findAll();
    }
}
