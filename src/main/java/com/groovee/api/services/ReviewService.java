package com.groovee.api.services;

import com.groovee.api.domain.review.Review;
import com.groovee.api.domain.review.ReviewRequestDTO;
import com.groovee.api.domain.user.User;
import com.groovee.api.repositories.ArtistRepository;
import com.groovee.api.repositories.ReviewRepository;
import com.groovee.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository){
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public Review createReview(ReviewRequestDTO data, UUID userId ){
        Review newReview = new Review();

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        newReview.setUser(user);
        newReview.setComment(data.comment());
        newReview.setRating(data.rating());
        newReview.setEntityType(data.entityType());
        newReview.setEntityId(data.entityId());

        return reviewRepository.save(newReview);
    }
}
