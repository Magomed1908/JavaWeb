package dbService;

import Model.User;

/**
 * Created by bo0mka on 28.05.16.
 */
public interface UserDAO {

    public User getUser(String login, String password);

    public boolean addUser(String login, String name, String email, String password);

    public boolean deleteUser(String login);

    public boolean isRegister(String login);


}
