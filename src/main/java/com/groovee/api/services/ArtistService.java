package com.groovee.api.services;

import com.groovee.api.domain.artist.Artist;
import com.groovee.api.domain.artist.ArtistRequestDTO;
import com.groovee.api.domain.artist.ArtistResponseDTO;
import com.groovee.api.repositories.ArtistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist createArtist(ArtistRequestDTO data) {

        Artist newArtist = new Artist();

        newArtist.setName(data.name());
        newArtist.setBio(data.bio());
        newArtist.setGenre(data.genre());
        newArtist.setSource(data.source());
        newArtist.setExternalId(data.externalId());

        return artistRepository.save(newArtist);
    }

    public List<ArtistResponseDTO> getArtists(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Artist> artistsPage = this.artistRepository.findAll(pageable);
        return artistsPage.map(ArtistResponseDTO::new).getContent();
    }
}
