package com.example.employemanagementsystem.Repository;

import com.example.employemanagementsystem.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository  extends JpaRepository<Role, Integer> {
    @Query("SELECT r from Role r WHERE r.name = ?1")
    public Role findByName(String name);
}
