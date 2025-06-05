package com.groovee.api.domain.track;

import java.util.UUID;

public record TrackResponseDTO(
        UUID id,
        String title,
        int duration,
        String source,
        String externalId,
        UUID albumId
) {
    public TrackResponseDTO(Track track) {
        this(
                track.getId(),
                track.getTitle(),
                track.getDuration(),
                track.getSource(),
                track.getExternalId(),
                track.getAlbum().getId()
        );
    }
}
