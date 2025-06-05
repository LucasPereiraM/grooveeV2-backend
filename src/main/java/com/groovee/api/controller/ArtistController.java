package com.groovee.api.controller;

import com.groovee.api.domain.artist.Artist;
import com.groovee.api.domain.artist.ArtistRequestDTO;
import com.groovee.api.domain.artist.ArtistResponseDTO;
import com.groovee.api.services.ArtistService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ArtistResponseDTO>> getAlbums(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<ArtistResponseDTO> allAlbums = this.artistService.getArtists(page, size);
        return ResponseEntity.ok(allAlbums);
    }
}
