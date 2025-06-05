package com.groovee.api.domain.album;

import java.time.LocalDate;
import java.util.UUID;

public record AlbumRequestDTO(UUID artistId, LocalDate releaseDate, String title, String genre, String source, String externalId) {
}
