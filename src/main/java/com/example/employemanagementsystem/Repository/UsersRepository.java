package com.example.employemanagementsystem.Repository;

import com.example.employemanagementsystem.Model.Department;
import com.example.employemanagementsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository  extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);

    List<User> getUsersByDepartment(Department department);
}
