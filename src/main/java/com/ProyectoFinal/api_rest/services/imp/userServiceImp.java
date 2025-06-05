package com.ProyectoFinal.api_rest.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProyectoFinal.api_rest.entities.user;
import com.ProyectoFinal.api_rest.repositories.userRepository;
import com.ProyectoFinal.api_rest.services.userService;

@Service
public class userServiceImp implements userService {

    @Autowired
    private userRepository usuarios;

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
        return usuarios.save(usuario);
    }

    @Override
    @Transactional
    public Optional<user> update(Long id, user usuario) {
        Optional<user> optionalUser = usuarios.findById(id);
        if (optionalUser.isPresent()){
            user usuarioDB = optionalUser.orElseThrow();
            usuarioDB.setName(usuario.getName());
            usuarioDB.setEmail(usuario.getEmail());
            usuarioDB.setPassword(usuario.getPassword());
            usuarioDB.setRol(usuario.getRol());
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
}
