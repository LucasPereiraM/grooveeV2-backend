package com.groovee.api.domain.userList;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserListResponseDTO(
        UUID id,
        UUID userId,
        String name,
        String description,
        LocalDateTime createdAt
) {
    public UserListResponseDTO(UserList userList) {
        this(
                userList.getId(),
                userList.getUser().getId(),
                userList.getName(),
                userList.getDescription(),
                userList.getCreatedAt()
        );
    }
}
