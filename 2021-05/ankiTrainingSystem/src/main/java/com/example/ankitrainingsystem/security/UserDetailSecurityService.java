package com.example.ankitrainingsystem.security;

import com.example.ankitrainingsystem.model.User;
import com.example.ankitrainingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("user %s not found", username));
        }
        return new UserSecurity(user);
    }
}
