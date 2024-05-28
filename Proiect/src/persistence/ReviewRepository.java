package persistence;

import model.Review;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
    private List<Review> reviews = new ArrayList<>();

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getAllReviews() {
        return new ArrayList<>(reviews);
    }

    public Review findReviewById(String reviewId) {
        return reviews.stream()
                .filter(review -> review.getReviewId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    public void updateReview(Review review) {
        int index = findIndexById(review.getReviewId());
        if (index >= 0) {
            reviews.set(index, review);
        }
    }

    public void deleteReview(String reviewId) {
        int index = findIndexById(reviewId);
        if (index >= 0) {
            reviews.remove(index);
        }
    }

    private int findIndexById(String reviewId) {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getReviewId().equals(reviewId)) {
                return i;
            }
        }
        return -1;
    }
}
