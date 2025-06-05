package com.groovee.api.domain.list;

import java.util.UUID;

public record ListRequestDTO(UUID userId, String name, String description) {
}
