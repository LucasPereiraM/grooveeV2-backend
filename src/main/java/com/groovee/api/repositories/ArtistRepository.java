package com.groovee.api.repositories;

import com.groovee.api.domain.artist.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtistRepository extends JpaRepository <Artist, UUID> {
}
