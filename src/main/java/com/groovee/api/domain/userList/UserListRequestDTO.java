package com.groovee.api.domain.userList;

import java.util.UUID;

public record UserListRequestDTO(UUID userId, String name, String description) {
}
