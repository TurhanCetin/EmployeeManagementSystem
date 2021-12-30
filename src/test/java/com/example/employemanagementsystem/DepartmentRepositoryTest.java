package com.example.employemanagementsystem;

import com.example.employemanagementsystem.Model.Department;
import com.example.employemanagementsystem.Repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)

public class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository repo;

   @Test
    public void testCreateDepartment(){
       Department savedDepartment = repo.save(new Department("Java"));

       assertThat(savedDepartment.getId()).isGreaterThan(0);
   }

}
