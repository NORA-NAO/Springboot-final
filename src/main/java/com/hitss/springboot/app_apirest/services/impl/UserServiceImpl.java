package com.hitss.springboot.app_apirest.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.springboot.app_apirest.entities.Role;
import com.hitss.springboot.app_apirest.entities.User;
import com.hitss.springboot.app_apirest.repositories.RoleRepository;
import com.hitss.springboot.app_apirest.repositories.UserRepository;
import com.hitss.springboot.app_apirest.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
    private UserRepository usuarios;
    @Autowired
    private RoleRepository roles;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return usuarios.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return usuarios.findById(id);
    }

    @Override
    @Transactional
    public User save(User usuario) {
		List<Role> user_roles = new ArrayList<>();
        if (usuario.isAdmin()) {
            Optional<Role> roleOptional = roles.findByName("ROLE_ADMIN");
            roleOptional.ifPresent(user_roles::add);
        }
        if (usuario.isProfessor()) {
            Optional<Role> roleOptional = roles.findByName("ROLE_PROFESSOR");
            roleOptional.ifPresent(user_roles::add);
        } 
        if(usuario.isStudent()){
            Optional<Role> roleOptional = roles.findByName("ROLE_STUDENT");
            roleOptional.ifPresent(user_roles::add);
        }

        usuario.setUsername(usuario.getUsername());
        usuario.setEmail(usuario.getEmail());
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuario.setRoles(user_roles);
        return usuarios.save(usuario);
    }

    @Override
    @Transactional
    public Optional<User> update(Long id, User usuario) {
        Optional<User> optionalUser = usuarios.findById(id);
        if (optionalUser.isPresent()) {
            User usuarioDB = optionalUser.orElseThrow();
            usuarioDB.setUsername(usuario.getUsername());
            usuarioDB.setEmail(usuario.getEmail());
            usuarioDB.setPassword(usuario.getPassword());
            List<Role> user_roles = new ArrayList<>();
            if (usuario.isAdmin()) {
                Optional<Role> roleOptional = roles.findByName("ROLE_ADMIN");
                roleOptional.ifPresent(user_roles::add);
            } else if (usuario.isProfessor()) {
                Optional<Role> roleOptional = roles.findByName("ROLE_PROFESSOR");
                roleOptional.ifPresent(user_roles::add);
            } else if(usuario.isStudent()){
                Optional<Role> roleOptional = roles.findByName("ROLE_STUDENT");
                roleOptional.ifPresent(user_roles::add);
            }
            usuarioDB.setRoles(user_roles);
            return Optional.of(usuarios.save(usuarioDB));
        }
        return optionalUser;
    }

    @Override
    @Transactional
    public Optional<User> delete(Long id) {
        Optional<User> optionalUser = usuarios.findById(id);
        optionalUser.ifPresent(usuarios::delete);
        return optionalUser;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return usuarios.findByEmail(email);

    }

}
