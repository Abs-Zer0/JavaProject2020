<%-- 
    Document   : add_audio
    Created on : 22 апр. 2020 г., 16:37:10
    Author     : illyasviel
--%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Добавление аудиозаписи</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
    </head>

    <body>
        <sec:authorize access="!isAuthenticated()">
            <% response.sendRedirect("/"); %>
        </sec:authorize>
        <div class="bg">
            <section class="header center">
                <div class="logo">
                    <h1><a href="/">Audio stream</a></h1>
                </div>
            </section>
            <section class="form center">
                <h2>Загрузить файл</h2>
                <form method="POST" action="/add_audio" enctype="multipart/form-data">
                    <div>
                        <p>Максимальный размер файла 15 МБ</p>
                    </div>
                    <div>
                        <input name="file" type="file" accept="audio/*">
                    </div>
                    <p>${fileError}</p>
                    <button class="btn" type="submit">Загрузить</button>
                </form>
            </section>
        </div>
    </body>
</html>
