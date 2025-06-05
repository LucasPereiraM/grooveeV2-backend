package com.groovee.api.domain.seeker;

import java.util.UUID;

public record SeekerRequestDTO (UUID userId, String queryText) {
}
