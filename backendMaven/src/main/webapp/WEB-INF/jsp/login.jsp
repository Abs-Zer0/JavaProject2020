<%-- 
    Document   : login
    Created on : 21 апр. 2020 г., 21:02:40
    Author     : illyasviel
--%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Вход в систему</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
    </head>

    <body>
        <sec:authorize access="isAuthenticated()">
            <% response.sendRedirect("/"); %>
        </sec:authorize>
        <div class="bg">
            <section class="header center">
                <div class="logo">
                    <h1><a href="/">Audio stream</a></h1>
                </div>
            </section>
            <section class="form center">
                <h2>Вход в систему</h2>
                <form method="POST" action="/login">
                    <div>
                        <input name="username" type="text" placeholder="Enter username" autofocus="true">
                        <form:errors path="username"></form:errors>
                        ${usernameError}
                    </div>
                    <div>
                        <input name="password" type="password" placeholder="Enter password">
                        <form:errors path="password"></form:errors>
                        ${passwordError}
                    </div>
                    <div>
                        <button class="btn" type="submit">Войти</button>
                    </div>
                    <div>
                        <a class="btn" href="/reg">Регистрация</a>
                    </div>
                </form>
            </section>
        </div>
    </body>
</html>
