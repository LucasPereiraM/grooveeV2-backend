package com.groovee.api.domain.list;

import java.time.LocalDateTime;
import java.util.UUID;

public record ListItemResponseDTO(
        UUID id,
        UUID listId,
        String entityType,
        Long entityId,
        LocalDateTime addedAt
) {
    public ListItemResponseDTO(ListItem listItem) {
        this(
                listItem.getId(),
                listItem.getList().getId(),
                listItem.getEntityType(),
                listItem.getEntityId(),
                listItem.getAddedAt()
        );
    }
}
