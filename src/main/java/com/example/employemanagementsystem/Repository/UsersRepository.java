package com.example.employemanagementsystem.Repository;

import com.example.employemanagementsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsersRepository  extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
