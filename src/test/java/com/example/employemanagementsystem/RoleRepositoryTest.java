package com.example.employemanagementsystem;

import com.example.employemanagementsystem.Model.Role;
import com.example.employemanagementsystem.Repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {

    @Autowired
    RoleRepository repo;

    @Test
    public void testCreateRole(){
        Role user = repo.save(new Role("User"));
        Role admin = repo.save(new Role("Admin"));
        Role manager = repo.save(new Role("Manager"));

        repo.saveAll(List.of(user ,admin, manager));

       List<Role> listRoles = repo.findAll();
       assertThat(listRoles.size()).isEqualTo(3);
    }

}
