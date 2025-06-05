package com.groovee.api.services;

import com.groovee.api.domain.album.Album;
import com.groovee.api.domain.album.AlbumRequestDTO;
import com.groovee.api.domain.album.AlbumResponseDTO;
import com.groovee.api.domain.artist.Artist;
import com.groovee.api.repositories.AlbumRepository;
import com.groovee.api.repositories.ArtistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<AlbumResponseDTO> getAlbums(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Album> albumsPage = this.albumRepository.findAll(pageable);
        return albumsPage.map(AlbumResponseDTO::new).getContent();
    }
}
