package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;
import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * Created by bo0mka on 09.05.16.
 */
@WebServlet(name = "SignUpServlet", urlPatterns = "/SignUp")
public class SignUpServlet extends SignAbstractServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        PrintWriter writer = resp.getWriter();
        if (req.getSession().getAttribute("myLocale") == null) {
            req.getRequestDispatcher("Locale.jsp");
        } else {
            ResourceBundle bundle = ResourceBundle.getBundle("Locale", (Locale) req.getSession().getAttribute("myLocale"));


            if (!req.getParameter("password").equals(req.getParameter("password2")) || req.getParameter("password2") == null) {
                writer.println("" + req.getSession().getAttribute("notEquals") + "");
                writer.println("<form action=\"Register.jsp\" method =\"get\">");
                writer.println("<input type=\"submit\" value=\"" + req.getSession().getAttribute("backRegister") + "\"/>");
                writer.println("</form>");
                return;

            } else if (name.equals("") || login.equals("") || email.equals("") || password.equals("")) {
                writer.println(req.getSession().getAttribute("empty"));
                writer.println("<form action=\"Register.jsp\" method =\"get\">");
                writer.println("<input type=\"submit\" value=\" " + req.getSession().getAttribute("backRegister") + "\"/>");
                writer.println("</form>");
                return;

            }

            boolean register = authorizeService.register(req, resp);

            if (!register) {
                resp.setStatus(SC_CONFLICT);
                writer.println(UNSUCCESS);
                writer.println("" + bundle.getString("taken") + "");
                writer.println("<form action=\"Register.jsp\" method =\"get\">");
                writer.println("<input type=\"submit\" value=\" " + req.getSession().getAttribute("backRegister") + "\"/>");
                writer.println("</form>");

            } else {
                logger.info("User " + req.getAttribute("login") + " has been register");
                resp.setStatus(SC_OK);
                writer.println(SUCCESS);
                writer.println("<html>");
                writer.println("<body>");
                writer.println(req.getSession().getAttribute("wish"));
                writer.println("<form action=\"SignIn.jsp\" method =\"get\">");
                writer.println("<input type=\"submit\" value=\"" + req.getSession().getAttribute("yes") + "\"/>");
                writer.println("</form>");
                writer.println("<form action=\"Register.jsp\" method =\"get\">");
                writer.println("<input type=\"submit\" value=\"" + req.getSession().getAttribute("no") + "\"/>");
                writer.println("</form>");
                writer.println("</body>");
                writer.println("</html>");

            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Register.jsp").forward(req,resp);
    }
}
