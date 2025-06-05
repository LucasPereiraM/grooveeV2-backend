package com.groovee.api.controller;

import com.groovee.api.domain.artist.ArtistResponseDTO;
import com.groovee.api.domain.seeker.Seeker;
import com.groovee.api.domain.seeker.SeekerRequestDTO;
import com.groovee.api.domain.seeker.SeekerResponseDTO;
import com.groovee.api.services.SeekerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<SeekerResponseDTO>> getAlbums(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<SeekerResponseDTO> allSeekers = this.seekerService.getSeekers(page, size);
        return ResponseEntity.ok(allSeekers);
    }
}
