<%--
  Created by IntelliJ IDEA.
  User: bo0mka
  Date: 06.05.16
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"   pageEncoding="UTF-8"%>
<html>
<head>
    <title>Register</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <style>.or{
        text-align: center;
    }</style>
</head>
<body bgcolor="#0ff">
    <%request.setCharacterEncoding("UTF-8");%>
    <form method="post" action="/SignUp">
        <label><%= session.getAttribute("name")%></label>
        <input type="text" name="name"  placeholder="Ivan Ivanov" >
        <br>
        <label><%= session.getAttribute("login")%></label>
        <input type="text" name="login"  placeholder="shrek228" >
        <br>
        <label><%= session.getAttribute("password")%></label>
        <input type="password" name="password"  placeholder="123456" >
        <br>
        <label><%= session.getAttribute("rePassword")%></label>
        <input type="password" name="password2"  placeholder="123456" >
        <br>
        <label><%= session.getAttribute("email")%></label>
        <input type="text" name="email"  placeholder="example@mail.com" >
        <br>
        <input type="submit" value="<%= session.getAttribute("register")%>">
    </form>
    <hr>
                                   <div class = "or"><%=session.getAttribute("or")%></div>
    <hr>
<form action="SignIn.jsp" method="get">
    <input type="submit" value="<%= session.getAttribute("signin")%>">
</form>
</body>
</html>
