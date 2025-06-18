package com.groovee.api.controller;

import com.groovee.api.domain.album.Album;
import com.groovee.api.domain.album.AlbumRequestDTO;
import com.groovee.api.domain.album.AlbumResponseDTO;
import com.groovee.api.domain.album.AlbumUpdateDTO;
import com.groovee.api.services.AlbumService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/album")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService){
        this.albumService = albumService;
    }

    @PostMapping
    public ResponseEntity<AlbumResponseDTO> create(@RequestBody @Valid AlbumRequestDTO request) {
        Album album = albumService.CreateAlbum(request);
        return ResponseEntity.ok(new AlbumResponseDTO(album));
    }

    @GetMapping
    public ResponseEntity<List<AlbumResponseDTO>> getAlbums(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<AlbumResponseDTO> allAlbums = this.albumService.getAlbums(page, size);
        return ResponseEntity.ok(allAlbums);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> updateAlbum(@PathVariable UUID id, @RequestBody AlbumUpdateDTO dto) {
        AlbumResponseDTO updatedAlbum = albumService.updateAlbum(id, dto);
        return ResponseEntity.ok(updatedAlbum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> deleteAlbum(@PathVariable UUID id){
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }
}
