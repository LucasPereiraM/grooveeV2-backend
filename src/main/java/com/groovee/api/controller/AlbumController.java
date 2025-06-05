package com.groovee.api.controller;

import com.groovee.api.domain.album.Album;
import com.groovee.api.domain.album.AlbumRequestDTO;
import com.groovee.api.domain.album.AlbumResponseDTO;
import com.groovee.api.services.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

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
}
