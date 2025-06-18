package com.groovee.api.services;

import com.groovee.api.domain.album.Album;
import com.groovee.api.domain.album.AlbumRequestDTO;
import com.groovee.api.domain.album.AlbumResponseDTO;
import com.groovee.api.domain.album.AlbumUpdateDTO;
import com.groovee.api.domain.artist.Artist;
import com.groovee.api.repositories.AlbumRepository;
import com.groovee.api.repositories.ArtistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public AlbumResponseDTO updateAlbum(UUID id, AlbumUpdateDTO dto){
        Album album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));

        if(dto.getArtistId() != null){
            Artist artist = artistRepository.findById(dto.getArtistId()).orElseThrow(() -> new RuntimeException("Artist not found"));
            album.setArtist(artist);
        }
        if(dto.getTitle() != null){
            album.setTitle(dto.getTitle());
        }
        if (dto.getTitle() != null) album.setTitle(dto.getTitle());
        if (dto.getGenre() != null) album.setGenre(dto.getGenre());
        if (dto.getSource() != null) album.setSource(dto.getSource());
        if (dto.getExternalId() != null) album.setExternalId(dto.getExternalId());
        if (dto.getReleaseDate() != null) album.setReleaseDate(dto.getReleaseDate());

        Album updatedAlbum = albumRepository.save(album);
        return new AlbumResponseDTO(updatedAlbum);
    }

    public void deleteAlbum(UUID id){
        Album album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));
        albumRepository.delete(album);
    }
}
