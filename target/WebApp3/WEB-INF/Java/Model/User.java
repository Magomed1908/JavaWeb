package Model;

/**
 * Created by bo0mka on 09.05.16.
 */
public class User {

    private final String name;
    private final String login;
    private final String passwordHash;
    private final String email;

    public User(String name, String login, String passwordHash, String email) {
        this.name = name;
        this.login = login;
        this.passwordHash = passwordHash;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmail() {
        return email;
    }
}
