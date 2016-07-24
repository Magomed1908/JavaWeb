<%@ page import="java.util.Locale" %><%--
  Created by IntelliJ IDEA.
  User: bo0mka
  Date: 09.05.16
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<html>
<head>
    <title><%= session.getAttribute("signin")%></title>
</head>
<style>
    body {
        height: 2000px;
        /* подсказка должна работать независимо от прокрутки */
    }

    .tooltip {
        position: fixed;
        padding: 10px 20px;
        /* красивости... */

        border: 1px solid #b3c9ce;
        border-radius: 4px;
        text-align: center;
        font: italic 14px/1.3 arial, sans-serif;
        color: #333;
        background: #fff;
        box-shadow: 3px 3px 3px rgba(0, 0, 0, .3);
    }
</style>
<body>
<form action="/SignIn", method="post">
    <label><%= session.getAttribute("login")%></label>
    <input data-tooltip="Попробуйте ввести login = admin и password = admin" type="text" name="login">
    <label><%= session.getAttribute("password")%></label>
    <input type="password" name="password">
    <input type="submit" value="<%= session.getAttribute("signin")%>">
</form>
<form action="Register.jsp", method="get">
    <input type="submit" value="<%= session.getAttribute("register")%>">
</form>
<script>
    var showingTooltip;

    document.onmouseover = function(e) {
        var target = e.target;

        var tooltip = target.getAttribute('data-tooltip');
        if (!tooltip) return;

        var tooltipElem = document.createElement('div');
        tooltipElem.className = 'tooltip';
        tooltipElem.innerHTML = tooltip;
        document.body.appendChild(tooltipElem);

        var coords = target.getBoundingClientRect();

        var left = coords.left + (target.offsetWidth - tooltipElem.offsetWidth) / 2;
        if (left < 0) left = 0; // не вылезать за левую границу окна

        var top = coords.top - tooltipElem.offsetHeight - 5;
        if (top < 0) { // не вылезать за верхнюю границу окна
            top = coords.top + target.offsetHeight + 5;
        }

        tooltipElem.style.left = left + 'px';
        tooltipElem.style.top = top + 'px';

        showingTooltip = tooltipElem;
    };

    document.onmouseout = function(e) {

        if (showingTooltip) {
            document.body.removeChild(showingTooltip);
            showingTooltip = null;
        }

    };
</script>

</body>
</html>
