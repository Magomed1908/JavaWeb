<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.time.Clock" %>
<%@ page import="java.time.LocalTime" %><%--
  Created by IntelliJ IDEA.
  User: bo0mka
  Date: 09.05.16
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<html>
<head>
    <title>HomePage</title>
</head>
<body>
        <%SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            PrintWriter writer = response.getWriter();
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            writer.println(dateFormat.format(date.getTime()));
            writer.println();
            calendar.set(Calendar.YEAR, 1970);
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            if(calendar.getTime().getTime() >= dateFormat.parse("18:00").getTime() && calendar.getTime().getTime() < dateFormat.parse("23:00").getTime()){
                writer.println(session.getAttribute("day.evening")+ " " + request.getAttribute("name"));
            }
            else  if(calendar.getTime().getTime()  >= dateFormat.parse("23:00").getTime() && calendar.getTime().getTime() < dateFormat.parse("6:00").getTime()) {
                writer.println(session.getAttribute("day.night") + " " +  request.getAttribute("name"));
            }
            else  if(calendar.getTime().getTime()  >= dateFormat.parse("6:00").getTime() && calendar.getTime().getTime() < dateFormat.parse("12:00").getTime()) {
                writer.println(session.getAttribute("day.morning") + " "  +  request.getAttribute("name"));
            }
            else {
                writer.println(session.getAttribute("day.day")+ " " +  request.getAttribute("name"));
            }

        %>

<form action="/out" method="post">
    <input type="submit" value="<%=session.getAttribute("exit")%>">
</form>
</body>
</html>
