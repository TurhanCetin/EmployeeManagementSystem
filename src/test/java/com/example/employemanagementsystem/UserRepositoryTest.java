package com.example.employemanagementsystem;


import com.example.employemanagementsystem.Model.Role;
import com.example.employemanagementsystem.Model.User;
import com.example.employemanagementsystem.Repository.RoleRepository;
import com.example.employemanagementsystem.Repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UsersRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void TestCreateUser(){
        User user = new User();
        user.setEmail("turhan@example.com");
        user.setName("Turhan");
        user.setPassword("1234");

        User savedUser = userRepo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());

    }

    @Test
    public void testAddRoleToNewUser(){
        User user = new User();
        user.setEmail("turhan1@example.com");
        user.setName("Turhan1");
        user.setPassword("12345");

        Role roleUser = roleRepo.findByName("User");
        user.addRole(roleUser);

        User savedUser = userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(1);


    }


}
