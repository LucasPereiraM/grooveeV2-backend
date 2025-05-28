package com.groovee.api.repositories;

import com.groovee.api.domain.list.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ListRepository extends JpaRepository <List, UUID> {
}
