package com.groovee.api.domain.seeker;

import java.time.LocalDateTime;
import java.util.UUID;

public record SeekerResponseDTO(
        UUID id,
        UUID userId,
        String queryText,
        LocalDateTime createdAt
) {
    public SeekerResponseDTO(Seeker seeker) {
        this(
                seeker.getId(),
                seeker.getUser().getId(),
                seeker.getQueryText(),
                seeker.getCreatedAt()
        );
    }
}
