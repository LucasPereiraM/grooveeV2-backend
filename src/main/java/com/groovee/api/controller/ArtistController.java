package com.groovee.api.controller;

import com.groovee.api.domain.artist.Artist;
import com.groovee.api.domain.artist.ArtistRequestDTO;
import com.groovee.api.domain.artist.ArtistResponseDTO;
import com.groovee.api.domain.artist.ArtistUpdateDTO;
import com.groovee.api.services.ArtistService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<List<ArtistResponseDTO>> getArtists(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<ArtistResponseDTO> allArtists = this.artistService.getArtists(page, size);
        return ResponseEntity.ok(allArtists);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> updateArtist(@PathVariable UUID id, @RequestBody ArtistUpdateDTO dto) {
        ArtistResponseDTO updateArtist = artistService.updateArtist(id, dto);
        return ResponseEntity.ok(updateArtist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> deleteArtist(@PathVariable UUID id){
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}