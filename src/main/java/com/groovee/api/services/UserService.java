package com.groovee.api.services;

import com.groovee.api.domain.user.User;
import com.groovee.api.domain.user.UserRequestDTO;
import com.groovee.api.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(UserRequestDTO data){
        User newUser = new User();

        newUser.setUsername(data.username());
        newUser.setEmail(data.email());
        newUser.setPasswordHash(passwordEncoder.encode(data.password()));

        return userRepository.save(newUser);
    }
}
