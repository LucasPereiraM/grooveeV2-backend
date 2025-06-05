package com.groovee.api.domain.userList;

import java.util.UUID;

public record ListItemRequestDTO(UUID listId, String entityType, Long entityId) {
}
