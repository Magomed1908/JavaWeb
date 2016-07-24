package Servlets;

import Model.User;
import dbService.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

/**
 * Created by bo0mka on 09.05.16.
 */
@WebServlet(name = "SignInServlet", urlPatterns = "/SignIn")
public class SignInServlet extends SignAbstractServlet {


    public static void M(){
        throw new NullPointerException();
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        String sessionID = req.getSession().getId();
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        resp.setContentType(CONTENT_TYPE);
        //Locale s = (Locale) session.getAttribute("myLocale");
        if(session.getAttribute("myLocale") == null)
        {
            req.getRequestDispatcher("Locale.jsp");
        }
        ResourceBundle bundle = ResourceBundle.getBundle("Locale", (Locale)session.getAttribute("myLocale"));

            User user = authorizeService.authorize(sessionID,login,password);



//
            if(user == null || user.getLogin() == null)
            {
                resp.getWriter().println(UNAUTHORIZED);
                resp.setStatus(SC_UNAUTHORIZED);
                resp.getWriter().println(SC_UNAUTHORIZED);
                PrintWriter writer = resp.getWriter();
                writer.println(""+session.getAttribute("incorrect")+"");
                writer.println("<form action=\"SignIn.jsp\" method =\"get\">");
                writer.println("<input type=\"submit\" value=\""+session.getAttribute("back")+"\"/>");
                writer.println("</form>");
            }
            else {
                if(user.getLogin().equals("admin")) {
                    List<User> users = UserDAOImpl.printUsers();
                    req.setAttribute("users", users);
                    req.getRequestDispatcher("Admin.jsp").forward(req, resp);
                }
                logger.info("User " + user.getName() + " has been authorized");
                resp.setStatus(SC_OK);
                req.setAttribute("name", user.getName());
                req.getRequestDispatcher("homePage.jsp").forward(req,resp);
            }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Register.jsp").forward(req, resp);
    }
}
