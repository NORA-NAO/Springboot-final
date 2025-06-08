package com.ProyectoFinal.api_rest.security.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ProyectoFinal.api_rest.entities.user;
import com.ProyectoFinal.api_rest.repositories.userRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private userRepository userR;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user usuario = userR.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        SimpleGrantedAuthority roles = new SimpleGrantedAuthority(usuario.getRol().getName());
        return new org.springframework.security.core.userdetails.User(
            usuario.getEmail(),
            usuario.getPassword(),
            List.of(roles)
            
        );
    }
}