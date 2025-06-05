package com.groovee.api.domain.track;

import java.util.UUID;

public record TrackRequestDTO(String title, UUID albumId, Integer duration, String source, String externalId) {
}
