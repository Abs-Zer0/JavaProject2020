<%-- 
    Document   : users
    Created on : 11 мая 2020 г., 11:35:59
    Author     : illyasviel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Главная</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
    </head>
    <body>
        <div class="bg">
            <section class="header center">
                <div class="logo">
                    <h1><a href="/">Audio stream</a></h1>
                </div>
            </section>
            <section class="menu center">
                <ul>
                    <li class="block-inline"><a class="btn" href="/">Все треки</a></li>
                    <li class="block-inline current_page_item"><a class="btn" href="/users">Все пользователи</a></li>
                </ul>
                <div class="login-block block-inline">
                    <sec:authorize access="isAuthenticated()">
                        <h3>Привет, ${pageContext.request.userPrincipal.name}</h3>
                        <a class="btn" href="/logout">Выйти</a>
                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
                        <form method="POST" action="/login">
                            <table>
                                <tr>
                                    <td><input name="username" placeholder="Username"></td>
                                    <td><button class="btn" type="submit">Войти</button></td>
                                </tr>
                                <tr>
                                    <td><input name="password" type="password" placeholder="Enter password"></td>
                                    <td><a class="btn" href="/reg">Регистрация</a></td>
                                </tr>
                            </table>
                        </form>
                    </sec:authorize>
                </div>
            </section>
            <section class="page center">
                <div class="content">
                    <h2>${userMsg}</h2>
                    <table>
                        <c:forEach items="${allUsers}" var="user">
                            <tr>
                                <td>${user.getId()}</td>
                                <td>${user.username}</td>
                                <td>${user.password}</td>
                                <td>${user.name}</td>
                                <td>
                                    <c:forEach items="${user.roles}" var="role">
                                        <p>${role.name}</p>
                                    </c:forEach>
                                </td>
                                <td>
                                    <a class="btn" href="/users/${user.getId()}/change_role/admin">
                                        <c:if test="${user.isAdmin()}">Сделать обычным пользователем</c:if>
                                        <c:if test="${!user.isAdmin()}">Сделать администратором</c:if>
                                        </a>
                                    </td>
                                    <td>
                                        <a class="btn" href="/users/${user.getId()}/delete">X</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="sidebar">
                    <div class="search">
                        <h2>Поиск</h2>
                        <form method="GET" action="/search">
                            <input type="text" name="keyword" size="15" placeholder="Enter keyword">
                            <button class="btn" type="submit">&#128269;</button>
                        </form>
                    </div>
                </div>
            </section>
            <section class="footer center">
                <p>
                    &copy; Untitled. All rights reserved. Design by
                    <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.
                </p>
            </section>
        </div>
    </body>
</html>