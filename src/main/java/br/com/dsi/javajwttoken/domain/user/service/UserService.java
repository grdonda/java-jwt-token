package br.com.dsi.javajwttoken.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.dsi.javajwttoken.domain.user.entities.UserEntity;
import br.com.dsi.javajwttoken.domain.user.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.getUserByUsername(username);
    }

    public UserEntity getUserData() {
        return repository.getUserData();
    }

}
