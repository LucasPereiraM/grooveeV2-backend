package com.groovee.api.services;

import com.groovee.api.domain.album.Album;
import com.groovee.api.domain.album.AlbumRequestDTO;
import com.groovee.api.domain.artist.Artist;
import com.groovee.api.repositories.AlbumRepository;
import com.groovee.api.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository){
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    public Album CreateAlbum(AlbumRequestDTO data){

        Artist artist = artistRepository.findById(data.artistId()).orElseThrow(() -> new RuntimeException("Artista não encontrado"));
        Album album = new Album();

        album.setTitle(data.title());
        album.setExternalId(data.externalId());
        album.setReleaseDate(data.releaseDate());
        album.setArtist(artist);
        album.setSource(data.source());
        album.setGenre(data.genre());

        return albumRepository.save(album);
    }
}
