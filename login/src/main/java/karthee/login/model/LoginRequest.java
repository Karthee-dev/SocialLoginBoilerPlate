package karthee.login.model;

public class LoginRequest {
    private String email = new String();
    private String password = new String();

    public LoginRequest(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
