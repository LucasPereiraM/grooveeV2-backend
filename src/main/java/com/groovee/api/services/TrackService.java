package com.groovee.api.services;

import com.groovee.api.domain.album.Album;
import com.groovee.api.domain.track.Track;
import com.groovee.api.domain.track.TrackRequestDTO;
import com.groovee.api.domain.track.TrackResponseDTO;
import com.groovee.api.repositories.AlbumRepository;
import com.groovee.api.repositories.TrackRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
