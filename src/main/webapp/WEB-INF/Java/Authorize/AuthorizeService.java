package Authorize;

import Model.User;

import dbService.UserDAO;
import dbService.UserDAOImpl;
import org.apache.commons.codec.digest.DigestUtils;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Created by bo0mka on 09.05.16.
 */
public class AuthorizeService {

    private final Logger logger = Logger.getLogger(AuthorizeService.class.getName());
    private final UserDAO userDAO;

    private static volatile AuthorizeService instance;

    public AuthorizeService() {
        userDAO = new UserDAOImpl();
    }


    public static AuthorizeService getInstance() {
        AuthorizeService localInstance = instance;
        if (localInstance == null) {
            synchronized (AuthorizeService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AuthorizeService();
                }
            }
        }
        return localInstance;
    }

    private final Map<String, User> authorizeUsers = new ConcurrentHashMap<>();

    private boolean isAuthorize(String sessionID)
    {

        return authorizeUsers.containsKey(sessionID);
    }

    public User authorize(String sessionID, String login, String passwordHash)  {

        String password = DigestUtils.sha256Hex(passwordHash);

        User user = userDAO.getUser(login, password);

        if(user != null )
            authorizeUsers.put(sessionID, user);

        return user;

    }

    public User unAuthorize(String sessionID)
    {
        if(isAuthorize(sessionID)) {
            User user = authorizeUsers.get(sessionID);
            authorizeUsers.remove(sessionID);
            return user;
        }
        return null;
    }

    public boolean register(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        password = DigestUtils.sha256Hex(password);

        return userDAO.addUser(login, name, email, password);

    }


    public boolean unRegister(String login) {

       if (userDAO.deleteUser(login)) {
           for (Map.Entry<String, User> userEntry : authorizeUsers.entrySet()) {
               if (userEntry.getValue().getLogin().equals(login))
                   authorizeUsers.remove(userEntry.getKey());
           }
           return true;
       }
        return false;

    }

}
