package com.groovee.api.services;

import com.groovee.api.domain.album.Album;
import com.groovee.api.domain.track.Track;
import com.groovee.api.domain.track.TrackRequestDTO;
import com.groovee.api.domain.track.TrackResponseDTO;
import com.groovee.api.domain.track.TrackUpdateDTO;
import com.groovee.api.repositories.AlbumRepository;
import com.groovee.api.repositories.TrackRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrackService {

    private final TrackRepository trackRepository;
    private final AlbumRepository albumRepository;

    public TrackService(TrackRepository trackRepository, AlbumRepository albumRepository){
        this.trackRepository = trackRepository;
        this.albumRepository = albumRepository;
    }

    public Track createTrack(TrackRequestDTO data) {
        Album album = albumRepository.findById(data.albumId()).orElseThrow(() -> new EntityNotFoundException("Álbum não encontrado"));

        Track newTrack = new Track();

        newTrack.setTitle(data.title());
        newTrack.setAlbum(album);
        newTrack.setDuration(data.duration());
        newTrack.setSource(data.source());
        newTrack.setExternalId(data.externalId());

        return trackRepository.save(newTrack);
    }

    public List<TrackResponseDTO> getTracks(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Track> tracksPage = this.trackRepository.findAll(pageable);
        return tracksPage.map(TrackResponseDTO::new).getContent();
    }

    public TrackResponseDTO updateTrack(UUID id, TrackUpdateDTO dto) {
        Track track = trackRepository.findById(id).orElseThrow(() -> new RuntimeException("Track not found"));

        if(dto.getTitle() != null) track.setTitle(dto.getTitle());
        if(dto.getDuration() != null) track.setDuration(dto.getDuration());
        if(dto.getSource() != null) track.setSource(dto.getSource());
        if(dto.getExternalId() != null) track.setExternalId(dto.getExternalId());

        if(dto.getAlbumId() != null) {
            Album album = albumRepository.findById(dto.getAlbumId())
                    .orElseThrow(() -> new RuntimeException("Album not found"));
            track.setAlbum(album);
        }

        trackRepository.save(track);
        return new  TrackResponseDTO(track);
    }

    public void deleteTrack(UUID id){
        Track track = trackRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Track not found"));
        trackRepository.delete(track);
    }
}
