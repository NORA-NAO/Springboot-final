package com.ProyectoFinal.api_rest.services.imp;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import com.ProyectoFinal.api_rest.repositories.userRepository;
import com.ProyectoFinal.api_rest.entities.*;

public class JpaUserServiceImp implements UserDetailsService {
    @Autowired
    private userRepository user;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<user> userOptional = user.findByEmail(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("El usuario %s no existe en el sistema", username));
        }
        user usuario = userOptional.orElseThrow();
        GrantedAuthority autorizaciones = new SimpleGrantedAuthority(usuario.getRol().getName());

        return new User(
            usuario.getEmail(), usuario.getPassword(), true, true, true, true, List.of(autorizaciones));
    }
    
}
