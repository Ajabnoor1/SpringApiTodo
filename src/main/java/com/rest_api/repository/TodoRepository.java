package com.rest_api.repository;

import com.rest_api.service.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TodoRepository extends CrudRepository<Todo, Integer>{
    //bezieht sich verbindung mit dem datenbank
Set<Todo> findAllByUserId(int userId);
}
