<%-- 
    Document   : add_audio_correct
    Created on : 23 апр. 2020 г., 14:49:49
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
                <h2>Скорректировать мета-данные файла</h2>
                <form:form method="POST" modelAttribute="audioForm">
                    <div>
                        <label>
                            Название композиции
                            <input type="text" name="name" value="${audioForm.getName()}" autofocus="true">
                        </label>
                    </div>
                    <div>
                        <label>
                            Исполнитель
                            <input type="text" name="artists" value="${audioForm.getArtists()}">
                        </label>
                    </div>
                    <div>
                        <label>
                            Жанр
                            <input type="text" name="genres" value="${audioForm.getGenres()}">
                        </label>
                    </div>
                    <div>
                        <button class="btn" type="submit">Сохранить</button>
                    </div>
                    <p>${msg}</p>
                </form:form>
            </section>
        </div>
    </body>
</html>
