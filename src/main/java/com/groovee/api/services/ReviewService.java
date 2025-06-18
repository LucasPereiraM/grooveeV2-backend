package com.groovee.api.services;

import com.groovee.api.domain.review.Review;
import com.groovee.api.domain.review.ReviewRequestDTO;
import com.groovee.api.domain.review.ReviewResponseDTO;
import com.groovee.api.domain.review.ReviewUpdateDTO;
import com.groovee.api.domain.user.User;
import com.groovee.api.repositories.ReviewRepository;
import com.groovee.api.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        newReview.setUser(user);
        newReview.setComment(data.comment());
        newReview.setRating(data.rating());
        newReview.setEntityType(data.entityType());
        newReview.setEntityId(data.entityId());

        return reviewRepository.save(newReview);
    }

    public List<ReviewResponseDTO> getReviews(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviewsPage = this.reviewRepository.findAll(pageable);
        return reviewsPage.map(ReviewResponseDTO::new).getContent();
    }

    public ReviewResponseDTO updateReview(UUID id, ReviewUpdateDTO dto){
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if(dto.getComment()!= null) review.setComment(dto.getComment());
        if(dto.getRating()!=null) review.setRating(dto.getRating());
        if(dto.getEntityId()!=null) review.setEntityId(dto.getEntityId());
        if(dto.getEntityType()!=null) review.setEntityType(dto.getEntityType());

        Review updatedReview = reviewRepository.save(review);
        return new ReviewResponseDTO(updatedReview);
    }

    public void deleteReview(UUID id){
        Review review = reviewRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Review not found"));
        reviewRepository.delete(review);
    }
}
