package com.groovee.api.domain.review;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewResponseDTO(
        UUID id,
        Long entityId,
        String entityType,
        Integer rating,
        String comment,
        UUID userId,
        LocalDateTime createdAt
) {
    public ReviewResponseDTO(Review review) {
        this(
                review.getId(),
                review.getEntityId(),
                review.getEntityType(),
                review.getRating(),
                review.getComment(),
                review.getUser().getId(),
                review.getCreatedAt()
        );
    }
}
