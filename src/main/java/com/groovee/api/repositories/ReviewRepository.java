package com.groovee.api.repositories;

import com.groovee.api.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository <Review, UUID> {
}
