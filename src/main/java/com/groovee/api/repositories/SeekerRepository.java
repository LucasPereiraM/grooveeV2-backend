package com.groovee.api.repositories;

import com.groovee.api.domain.seeker.Seeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeekerRepository extends JpaRepository <Seeker, UUID> {
}
