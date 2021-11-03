package com.example.application.data.service;

import com.example.application.data.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import com.example.application.data.Role;
import javax.persistence.Lob;

@Service
public class UserService extends CrudService<User, Integer> {
    @Autowired
    JdbcTemplate jdbcTemp;

    private UserRepository repository;

    public UserService(@Autowired UserRepository repository) {
        this.repository = repository;
    }

    public void clearUsers(){
        jdbcTemp.update("TRUNCATE `munhelper`.`user`;");
    }

    @Override
    protected UserRepository getRepository() {
        return repository;
    }

}
