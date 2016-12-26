<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student page</title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
</head>
<body class="grey lighten-4">
<c:import url="/navbar"/>
<div class="container" >
    <div class="row">
        <h5 class="center-align">Факультативы:</h5>
        <div class="center-align">
            <a class="waves-effect waves-light btn blue lighten-1" href="controller?command=getStartedFaculty">Записаться на факультатив</a>
        </div>
        <h5 class="center-align">${accountMessage}</h5>
    </div>
    <div class="white">
        <table>
            <thead>
            <tr>
                <th>Название</th>
                <th>Кафедра</th>
                <th>Описание</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="faculty" items="${facultyList}">
                <c:set var="idCour" value="${faculty.getIdCourse()}"/>
                <c:set var="course" value="${coursesList[idCour]}"/>
                <tr>
                    <td>${course.getTitle()}</td>
                    <td>${course.getKafedra()}</td>
                    <td>${course.getDescription()}</td>
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
