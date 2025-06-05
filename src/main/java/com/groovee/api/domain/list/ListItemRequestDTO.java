package com.groovee.api.domain.list;

import java.util.UUID;

public record ListItemRequestDTO(UUID listId, String entityType, Long entityId) {
}
