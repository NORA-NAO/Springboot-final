package com.hitss.springboot.app_apirest.services;

import java.util.List;
import java.util.Optional;

import com.hitss.springboot.app_apirest.entities.User;

public interface UserService {
	List<User> findAll();
    Optional<User> findById(Long id);
    User save(User usuario);
    Optional<User> update(Long id, User usuario);
    Optional<User> delete(Long id);
    Optional<User> findByEmail(String email);
}
