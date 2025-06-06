package com.groovee.api.controller;

import com.groovee.api.domain.review.Review;
import com.groovee.api.domain.review.ReviewRequestDTO;
import com.groovee.api.domain.review.ReviewResponseDTO;
import com.groovee.api.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> getAlbums(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<ReviewResponseDTO> allReviews = this.reviewService.getReviews(page, size);
        return ResponseEntity.ok(allReviews);
    }
}
