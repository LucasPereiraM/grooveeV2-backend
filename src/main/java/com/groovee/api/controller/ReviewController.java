package com.groovee.api.controller;

import com.groovee.api.domain.review.Review;
import com.groovee.api.domain.review.ReviewRequestDTO;
import com.groovee.api.domain.review.ReviewResponseDTO;
import com.groovee.api.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    public ResponseEntity<ReviewResponseDTO> create(@RequestBody @Valid ReviewRequestDTO request, @AuthenticationPrincipal Jwt jwt){
        UUID userId = UUID.fromString(jwt.getSubject());
        Review review = reviewService.createReview(request, userId);
        return ResponseEntity.ok(new ReviewResponseDTO(review));
    }
}
