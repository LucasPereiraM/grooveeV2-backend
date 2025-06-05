package com.groovee.api.controller;

import com.groovee.api.domain.artist.Artist;
import com.groovee.api.domain.artist.ArtistRequestDTO;
import com.groovee.api.domain.artist.ArtistResponseDTO;
import com.groovee.api.services.ArtistService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping
    public ResponseEntity<ArtistResponseDTO> create(@RequestBody @Valid ArtistRequestDTO request) {
        Artist artist = artistService.createArtist(request);
        return ResponseEntity.ok(new ArtistResponseDTO(artist));
    }
}
