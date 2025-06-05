package com.groovee.api.controller;

import com.groovee.api.domain.seeker.Seeker;
import com.groovee.api.domain.seeker.SeekerRequestDTO;
import com.groovee.api.domain.seeker.SeekerResponseDTO;
import com.groovee.api.services.SeekerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seeker")
public class SeekerController {

    private final SeekerService seekerService;

    public SeekerController(SeekerService seekerService){
        this.seekerService = seekerService;
    }

    @PostMapping
    public ResponseEntity<SeekerResponseDTO> create(@RequestBody @Valid SeekerRequestDTO request){
        Seeker seeker = seekerService.CreateSeeker(request);
        return ResponseEntity.ok(new SeekerResponseDTO(seeker));
    }
}
