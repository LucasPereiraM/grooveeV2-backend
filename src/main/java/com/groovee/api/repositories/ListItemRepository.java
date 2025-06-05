package com.groovee.api.repositories;

import com.groovee.api.domain.userList.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ListItemRepository extends JpaRepository<ListItem, UUID> {
}
