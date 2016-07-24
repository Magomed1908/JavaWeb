<%--
  Created by IntelliJ IDEA.
  User: bo0mka
  Date: 22.05.16
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"   pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
    String lang = request.getParameter("lang");
    if (lang == null) {
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>
        Registration
    </title>
</head>
<body bgcolor="#33CCFF">
<font face="Times New Roman,Times" size=+3>
    Registration
</font>
<hr>
   <p> Please select a language:</p>
    <form action="Locale.jsp" method="post">
        English <input type="radio" name="lang" value="English" checked>
        <br>
        Deutsch <input type="radio" name="lang" value="German">
        <br>
        Russian <input type="radio" name="lang" value="Russian">
        <br>
    <input type="submit" value="Continue">
    </form>
</body>
</html>
<%
} else {
    Locale locale= Locale.getDefault();
    if (lang.equals("German")) {
        locale=Locale.GERMANY;
    } else if (lang.equals("Russian")) {
        locale= new Locale("ru", "RU");
    } else {
        locale=Locale.US;
    }

    ResourceBundle bundle =
            ResourceBundle.getBundle("Locale",locale);
    session.setAttribute("myLocale", locale);

    for (Enumeration e = bundle.getKeys();e.hasMoreElements();) {
        String key = (String)e.nextElement();
        String s =  new String(bundle.getString(key).getBytes("ISO8859-1"));
        session.setAttribute(key,s);
    }
%>
<%
        request.getRequestDispatcher("Register.jsp").forward(request,response);
    }
%>
