package com.groovee.api.domain.list;

import com.groovee.api.domain.review.Review;

import java.time.LocalDateTime;
import java.util.UUID;

public record ListResponseDTO(
        UUID id,
        UUID userId,
        String name,
        String description,
        LocalDateTime createdAt
) {
    public ListResponseDTO(List list) {
        this(
                list.getId(),
                list.getUser().getId(),
                list.getName(),
                list.getDescription(),
                list.getCreatedAt()
        );
    }
}
