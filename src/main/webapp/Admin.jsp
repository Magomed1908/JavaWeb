<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="Model.User" %><%--
  Created by IntelliJ IDEA.
  User: bo0mka
  Date: 11.05.16
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Admin</title>
    <style type="text/css">
       table {
            border-spacing: 0 10px;
            font-family: 'Open Sans', sans-serif;
            font-weight: bold;
        }
        th {
            padding: 10px 20px;
            background: #56433D;
            color: #F9C941;
            border-right: 2px solid;
            font-size: 0.9em;
        }
        th:first-child {
            text-align: left;
        }
        th:last-child {
            border-right: none;
        }

        td {
            vertical-align: middle;
            padding: 10px;
            font-size: 14px;
            text-align: center;
            border-top: 2px solid #56433D;
            border-bottom: 2px solid #56433D;
            border-right: 2px solid #56433D;
        }
        td:first-child {
            border-left: 2px solid #56433D;
            border-right: none;
        }
        td:nth-child(2){
            text-align: left;
        }


    </style>
</head>
<body>
<h3><%=session.getAttribute("list")%>:</h3>
<table width="100%" cellspacing="0" cellpadding="4" border="1" align="centre">
    <tr>
        <th><%=session.getAttribute("number")%></th>
        <th><%=session.getAttribute("name")%></th>
        <th><%=session.getAttribute("login")%></th>
        <th><%=session.getAttribute("email")%></th>
        <td><%=session.getAttribute("delete")%></td>
    </tr>
    <c:forEach var = "user" items = "${users}" varStatus="count">
    <tr><td>${count.count}</td>
        <td>${user.name}</td>
        <td>${user.login}</td>
        <td>${user.email}</td>
        <td><form action="/admin" method="post" >
            <input type="text" name="login" placeholder="login">
            <input type="submit" value="<%=session.getAttribute("delete")%>" align="centre">
        </form></td>
    </tr>
    </c:forEach>
</table>
<form action="/out" method="post">
    <input type="submit" value="<%=session.getAttribute("exit")%>">
</form>
</body>
</html>
