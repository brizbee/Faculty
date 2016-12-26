<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.12.2016
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
</head>
<body class="grey lighten-4">
<c:import url="/navbar"/>

<div class="container" style="margin-bottom: 200px;">
    <div class="row">
        <h5 class="center-align">Начатые факультативы:</h5>
        <h5 class="center-align">${accountMessage}</h5>
    </div>
    <div class="row white">
        <table>
            <thead>
            <tr>
                <th>Название</th>
                <th>Кафедра</th>
                <th>Руководитель</th>
                <th>Описание</th>
                <th>Записаться</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="id" items="${facultId}">
                <c:set var="course" value="${courses[id]}"/>
                <c:set var="tutName" value="${tutorName[id]}"/>
                <tr>
                    <td>${course.getTitle()}</td>
                    <td>${course.getKafedra()}</td>
                    <td>${tutName}</td>
                    <td>${course.getDescription()}</td>
                    <td><a class="waves-effect waves-light btn blue lighten-1" href="controller?command=signToFaculty${id}">Записаться</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
<c:import url="/footer"/>
</body>
</html>
