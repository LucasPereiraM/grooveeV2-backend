package com.groovee.api.domain.album;

import java.time.LocalDate;
import java.util.UUID;

public record AlbumResponseDTO(
        UUID id,
        String title,
        String genre,
        String source,
        String externalId,
        LocalDate releaseDate,
        UUID artistId,
        String artistName
) {
    public AlbumResponseDTO(Album album) {
        this(
                album.getId(),
                album.getTitle(),
                album.getGenre(),
                album.getSource(),
                album.getExternalId(),
                album.getReleaseDate(),
                album.getArtist().getId(),
                album.getArtist().getName()
        );
    }
}
