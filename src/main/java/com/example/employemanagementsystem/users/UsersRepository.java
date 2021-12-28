package com.example.employemanagementsystem.users;

import org.springframework.data.jpa.repository.JpaRepository;
public interface UsersRepository  extends JpaRepository<User, Integer> {
}
