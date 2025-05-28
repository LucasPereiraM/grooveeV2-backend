package com.groovee.api.repositories;

import com.groovee.api.domain.album.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlbumRepository extends JpaRepository <Album, UUID> {
}
