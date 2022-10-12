package com.rest_api.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest_api.service.Todo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY ) //ID wird automatisch um 1 erhöht
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    @OneToMany
    @JoinColumn(name = "userId")
    private Set<Todo> todos;

    private String secret;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Todo> getTodos() {
        return todos;
    }

    public void setTodos(Set<Todo> todos) {
        this.todos = todos;
    }

    public String getSecret() {
        return secret;
    }
    @JsonIgnore
    public void setSecret(String secret) {
        this.secret = secret;
    }
}
