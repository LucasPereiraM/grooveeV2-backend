package com.groovee.api.domain.artist;

import java.util.UUID;

public record ArtistResponseDTO(
        UUID id,
        String name,
        String bio,
        String genre,
        String source,
        String externalId
) {
    public ArtistResponseDTO(Artist artist) {
        this(
                artist.getId(),
                artist.getName(),
                artist.getBio(),
                artist.getGenre(),
                artist.getSource(),
                artist.getExternalId()
        );
    }
}