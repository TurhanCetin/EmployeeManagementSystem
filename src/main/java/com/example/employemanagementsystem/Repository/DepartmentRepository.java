package com.example.employemanagementsystem.Repository;

import com.example.employemanagementsystem.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
