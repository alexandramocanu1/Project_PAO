package model;

public class Discount {
    private String discountId;
    private String eventName;
    private double discountPercentage;

    public Discount(String discountId, String eventName, double discountPercentage) {
        this.discountId = discountId;
        this.eventName = eventName;
        this.discountPercentage = discountPercentage;
    }

    public String getDiscountId() {
        return discountId;
    }

    public String getEventName() {
        return eventName;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void displayDiscountInfo() {
        System.out.println("Discount Details:");
        System.out.println("Discount ID: " + discountId);
        System.out.println("Event Name: " + eventName);
        System.out.println("Discount Percentage: " + discountPercentage + "%");
    }

}
