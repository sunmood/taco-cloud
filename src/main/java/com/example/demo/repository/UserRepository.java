package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sunmood on 2019/3/13.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
