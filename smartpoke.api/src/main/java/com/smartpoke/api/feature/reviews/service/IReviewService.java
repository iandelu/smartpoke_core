package com.smartpoke.api.feature.reviews.service;

import com.smartpoke.api.feature.reviews.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IReviewService {
    List<Review> findByRecipeId(Long id);

}
