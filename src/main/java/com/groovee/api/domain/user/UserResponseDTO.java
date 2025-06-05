package com.groovee.api.domain.user;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String username,
        String email,
        LocalDateTime createdAt
) {
    public UserResponseDTO(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
