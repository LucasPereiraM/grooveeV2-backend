package com.groovee.api.domain.review;

import java.util.UUID;

public record ReviewRequestDTO(String entityType, Long entityId, Integer rating, String comment) {
}
