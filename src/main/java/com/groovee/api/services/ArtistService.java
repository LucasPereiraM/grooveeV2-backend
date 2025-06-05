package com.groovee.api.services;

import com.groovee.api.domain.artist.Artist;
import com.groovee.api.domain.artist.ArtistRequestDTO;
import com.groovee.api.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

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
}
