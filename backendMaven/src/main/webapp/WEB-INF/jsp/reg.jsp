<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Регистрация</title>
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
                <h2>Регистрация</h2>
                <form:form method="POST" modelAttribute="userForm">
                    <div>
                        <form:input type="text" path="name" placeholder="Your name" autofocus="true"></form:input>
                        </div>
                        <div>
                        <form:input type="text" path="username" placeholder="Username"></form:input>
                        <form:errors path="username"></form:errors>
                        ${usernameError}
                    </div>
                    <div>
                        <form:input type="password" path="password" placeholder="Password"></form:input>
                        <form:input type="password" path="passwordConfirm"
                                    placeholder="Confirm your password"></form:input>
                        <form:errors path="password"></form:errors>
                        ${passwordError}
                    </div>
                    <div>
                        <button class="btn" type="submit">Зарегистрироваться</button>
                    </div>
                </form:form>
            </section>
        </div>
    </body>
</html>

