package com.groovee.api.controller;

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
