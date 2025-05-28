package com.groovee.api.repositories;

import com.groovee.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository <User, UUID> {
}
