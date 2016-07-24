package Servlets;

import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by bo0mka on 09.05.16.
 */
@WebServlet(name = "SignOutServlet", urlPatterns = "/out")
public class SignOutServlet extends SignAbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        ResourceBundle bundle = ResourceBundle.getBundle("Locale", (Locale)req.getSession().getAttribute("myLocale"));
        HttpSession session = req.getSession();
        for (Enumeration e = bundle.getKeys(); e.hasMoreElements();) {
            String key = (String)e.nextElement();
            String s =  new String(bundle.getString(key).getBytes("ISO8859-1"));
            session.setAttribute(key,s);
        }
        User user = authorizeService.unAuthorize(req.getSession().getId());
        logger.info("user " + user.getName()+" has been unauthorized");
        resp.getWriter().println(session.getAttribute("out"));
        writer.println("<form action=\"Locale.jsp\" method =\"get\">");
        writer.println("<input type=\"submit\" value=\" "+ session.getAttribute("back") +"\"/>");
        writer.println("</form>");
        session.invalidate();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Register.jsp").forward(req,resp);
    }
}
