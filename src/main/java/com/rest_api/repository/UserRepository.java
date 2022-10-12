package com.rest_api.repository;

import com.rest_api.service.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> {
        //bezieht sich verbindung mit dem datenbank
Optional<User> findByEmailAndPassword(String email, String password);
Optional<User> findBySecret(String secret);
    }

