package com.groovee.api.controller;

import com.groovee.api.domain.track.Track;
import com.groovee.api.domain.track.TrackRequestDTO;
import com.groovee.api.domain.track.TrackResponseDTO;
import com.groovee.api.domain.track.TrackUpdateDTO;
import com.groovee.api.services.TrackService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/track")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping
    public ResponseEntity<TrackResponseDTO> create(@RequestBody @Valid TrackRequestDTO request) {
        Track track = trackService.createTrack(request);
        return ResponseEntity.ok(new TrackResponseDTO(track));
    }

    @GetMapping
    public ResponseEntity<List<TrackResponseDTO>> getAlbums(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<TrackResponseDTO> allTracks = this.trackService.getTracks(page, size);
        return ResponseEntity.ok(allTracks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackResponseDTO> updateTrack(@PathVariable UUID id, @RequestBody TrackUpdateDTO dto){
        TrackResponseDTO updateTrack = trackService.updateTrack(id, dto);
        return ResponseEntity.ok(updateTrack);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TrackResponseDTO> deleteTrack(@PathVariable UUID id){
        trackService.deleteTrack(id);
        return ResponseEntity.noContent().build();
    }
}