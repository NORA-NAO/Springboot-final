package com.ProyectoFinal.api_rest.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProyectoFinal.api_rest.entities.Role;
import com.ProyectoFinal.api_rest.entities.user;
import com.ProyectoFinal.api_rest.repositories.roleRepository;
import com.ProyectoFinal.api_rest.repositories.userRepository;
import com.ProyectoFinal.api_rest.services.userService;

import io.jsonwebtoken.security.Jwks.OP;

@Service
public class userServiceImp implements userService {

    @Autowired
    private userRepository usuarios;
    @Autowired
    private roleRepository roles;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    @Transactional(readOnly = true)
    public List<user> findAll() {
        return usuarios.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<user> findById(Long id) {
        return usuarios.findById(id);
    }

    @Override
    @Transactional
    public user save(user usuario) {
        if (usuario.isAdmin()) {
            Optional<Role> roleOptional = roles.findByName("ROLE_ADMIN");
            if (roleOptional.isPresent())
                usuario.setRol(roleOptional.get());
        }
        if (usuario.isProfessor()) {
            Optional<Role> roleOptional = roles.findByName("ROLE_PROFESSOR");
            if (roleOptional.isPresent())
                usuario.setRol(roleOptional.get());
        } 
        if(usuario.isStudent()){
            Optional<Role> roleOptional = roles.findByName("ROLE_STUDENT");
            if (roleOptional.isPresent())
                usuario.setRol(roleOptional.get());
        }

        usuario.setName(usuario.getName());
        usuario.setEmail(usuario.getEmail());
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return usuarios.save(usuario);
    }

    @Override
    @Transactional
    public Optional<user> update(Long id, user usuario) {
        Optional<user> optionalUser = usuarios.findById(id);
        if (optionalUser.isPresent()) {
            user usuarioDB = optionalUser.orElseThrow();
            usuarioDB.setName(usuario.getName());
            usuarioDB.setEmail(usuario.getEmail());
            usuarioDB.setPassword(usuario.getPassword());
            if (usuario.isAdmin()) {
                Optional<Role> roleOptional = roles.findByName("ROLE_ADMIN");
                if (roleOptional.isPresent())
                    usuario.setRol(roleOptional.get());
            } else if (usuario.isProfessor()) {
                Optional<Role> roleOptional = roles.findByName("ROLE_PROFESSOR");
                if (roleOptional.isPresent())
                    usuario.setRol(roleOptional.get());
            }
            Optional<Role> roleOptional = roles.findByName("ROLE_STUDENT");
            if (roleOptional.isPresent())
                usuario.setRol(roleOptional.get());
            return Optional.of(usuarios.save(usuarioDB));
        }
        return optionalUser;
    }

    @Override
    @Transactional
    public Optional<user> delete(Long id) {
        Optional<user> optionalUser = usuarios.findById(id);
        optionalUser.ifPresent(usuarios::delete);
        return optionalUser;
    }

    @Override
    public Optional<user> findByEmail(String email) {
        return usuarios.findByEmail(email);

    }
}
