package Servlets;

import Model.User;
import dbService.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by bo0mka on 11.05.16.
 */
@WebServlet(name = "MyServlet", urlPatterns = "/admin")
public class AdminServlet extends  SignAbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login  = req.getParameter("login");
        authorizeService.unRegister(login);
        List<User> users = UserDAOImpl.printUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher("Admin.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Admin.jsp").forward(req, resp);


    }
}
