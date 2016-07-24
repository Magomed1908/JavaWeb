package dbService;

import Model.User;
import jdk.nashorn.internal.scripts.JD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bo0mka on 28.05.16.
 */
public class UserDAOImpl implements UserDAO {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/usersDB";
    private static final String USER = "root";
    private static final String PASSWORD = "b13b15";

    public UserDAOImpl() {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String login, String password) {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet dataUser = statement.executeQuery("SELECT login, name, email, password FROM users WHERE login='" + login + "'AND  password='" + password + "';");

            String nameU = null;
            String loginU = null;
            String passwordU = null;
            String email = null;
            while (dataUser.next()) {
                nameU = dataUser.getString("name");
                loginU = dataUser.getString("login");
                passwordU = dataUser.getString("password");
                email = dataUser.getString("email");
            }
            return new User(nameU, loginU, passwordU, email);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addUser(String login, String name, String email, String password) {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            if(isRegister(login))
                return false;

            statement.execute("INSERT INTO users(name, date, login, email, password)" +
                    " VALUES ('" + name + "', CURRENT_DATE, '" + login + "', '" + email + "', '" + password + "');");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean deleteUser( String login) {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            if (isRegister(login)) {
                statement.execute("DELETE FROM users WHERE login='" + login + "';");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

        @Override
    public boolean isRegister(String login) {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet rs1 = statement.executeQuery("SELECT login FROM users WHERE login ='" + login + "';");
            if (rs1.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static List<User> printUsers()
    {
        List<User> usersList= new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            try(Statement statement = connection.createStatement()) {
                try(ResultSet userSet = statement.executeQuery("SELECT login, name, email, password FROM users;")) {

                    while (userSet.next())
                    {
                        String login = userSet.getString("login");
                        String name = userSet.getString("name");
                        String email = userSet.getString("email");
                        String password = userSet.getString("password");
                        usersList.add(new User(name,login,password, email));
                    }


                    return usersList;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


