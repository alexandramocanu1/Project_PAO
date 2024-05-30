package model;

public class RegularUser extends User {
    private String subscriptionType;

    public RegularUser(int id, String username, String password, String email, String fullName, String role, String subscriptionType) {
        super(id, username, password, email, fullName, role);
        this.subscriptionType = subscriptionType;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
