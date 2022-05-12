package com.example.ankitrainingsystem.repository;


import com.example.ankitrainingsystem.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);

    List<User> findAll();

}
