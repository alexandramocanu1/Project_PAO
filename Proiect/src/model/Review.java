package model;

import service.AuditService;

public class Review {
    private String reviewId;
    private String eventId;
    private String userId;
    private int rating;
    private String comment;

    public Review(String reviewId, String eventId, String userId, int rating, String comment) {
        this.reviewId = reviewId;
        this.eventId = eventId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        AuditService.logAction("ReviewCreated");
    }

    public String getReviewId() {
        return reviewId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getUserId() {
        return userId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
        AuditService.logAction("setReviewId");
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
        AuditService.logAction("setEventId");
    }

    public void setUserId(String userId) {
        this.userId = userId;
        AuditService.logAction("setUserId");
    }

    public void setRating(int rating) {
        this.rating = rating;
        AuditService.logAction("setRating");
    }

    public void setComment(String comment) {
        this.comment = comment;
        AuditService.logAction("setComment");
    }

    public void displayReviewInfo() {
        System.out.println("Review Details:");
        System.out.println("Review ID: " + reviewId);
        System.out.println("Event ID: " + eventId);
        System.out.println("User ID: " + userId);
        System.out.println("Rating: " + rating);
        System.out.println("Comment: " + comment);
        AuditService.logAction("displayReviewInfo");
    }
}
