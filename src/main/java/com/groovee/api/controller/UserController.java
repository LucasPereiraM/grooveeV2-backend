package com.groovee.api.controller;

import com.groovee.api.domain.user.User;
import com.groovee.api.domain.user.UserRequestDTO;
import com.groovee.api.domain.user.UserResponseDTO;
import com.groovee.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO body){
        User user = this.userService.createUser(body);
        return ResponseEntity.ok(new UserResponseDTO(user));
    }
}
