package com.groovee.api.controller;

import com.groovee.api.domain.user.User;
import com.groovee.api.domain.user.UserRequestDTO;
import com.groovee.api.domain.user.UserResponseDTO;
import com.groovee.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

   @GetMapping
    public ResponseEntity<String> getUser(){
       return ResponseEntity.ok("sucesso");
   }

}
