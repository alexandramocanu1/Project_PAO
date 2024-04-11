package model;

public class Review {
    private String eventId;
    private String userId;
    private int rating; // Evaluarea evenimentului (de exemplu, pe o scară de la 1 la 5)
    private String comment; // Comentariul utilizatorului

    public Review(String eventId, String userId, int rating, String comment) {
        this.eventId = eventId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
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

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void displayReviewInfo() {
        System.out.println("Review Details:");
        System.out.println("Event ID: " + eventId);
        System.out.println("User ID: " + userId);
        System.out.println("Rating: " + rating);
        System.out.println("Comment: " + comment);
    }

}
