package Servlets;

import Authorize.AuthorizeService;

import javax.servlet.http.HttpServlet;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created by bo0mka on 09.05.16.
 */
public class SignAbstractServlet extends HttpServlet {
    protected final AuthorizeService authorizeService = AuthorizeService.getInstance();
    protected final Logger logger = Logger.getLogger(SignAbstractServlet.class.getName());


    protected static final String NAME = "name";
    protected static final String EMAIL = "email";
    protected static final String LOGIN = "login";
    protected static final String PASSWORD = "password";
    protected static final String UNAUTHORIZED = "Unauthorized";
    protected static final String AUTHORIZED = "Authorized";
    protected static final String CONTENT_TYPE = "text/html; charset=utf-8";
    protected static final String SUCCESS = "Success";
    protected static final String UNSUCCESS = "Unsuccess";

}
