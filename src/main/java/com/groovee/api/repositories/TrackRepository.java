package com.groovee.api.repositories;

import com.groovee.api.domain.track.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrackRepository extends JpaRepository <Track, UUID> {
}
