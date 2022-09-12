package com.br.auth.services;

import com.br.auth.entity.User;
import com.br.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public User findByUserName(String username){
        var user = repository.findByUsername(username);
        if(user != null){
            return user;
        }else {
            throw new UsernameNotFoundException("Username "  + username + " not found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUserName(username);
    }
}
