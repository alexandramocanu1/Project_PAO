package model;

public class Admin extends User {
    private String level;

    public Admin(int id, String username, String password, String email, String fullName, String role, String level) {
        super(id, username, password, email, fullName, role);
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
