<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tutor page</title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
</head>
<body class="grey lighten-4">
<c:import url="/navbar"/>
<div class="container">
    <div class="center-align">
        <h5>Начатые курсы</h5>
    </div>
    <div class="row white">
        <div>

            <table>
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Кафедра</th>
                    <th>Описание</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="startedFacult" items="${started}">
                    <c:set var="facId" value="${startedFacult.getId()}"/>
                    <tr>
                        <td>${courseMap[facId].getTitle()}</td>
                        <td>${courseMap[facId].getKafedra()}</td>
                        <td>${courseMap[facId].getDescription()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="center-align">
        <h5>Неактивные курсы</h5>
    </div>
    <div class="row white">
        <div>
            <table>
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Кафедра</th>
                    <th>Описание</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="endedFacult" items="${ended}">
                    <c:set var="facId" value="${endedFacult.getId()}"/>
                    <tr>
                        <td>${courseMap[facId].getTitle()}</td>
                        <td>${courseMap[facId].getKafedra()}</td>
                        <td>${courseMap[facId].getDescription()}</td>
                        <td class="center-align"><a class="waves-effect waves-light btn indigo darken-4" href="controller?command=startFac${facId}">Начать</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
<c:import url="/footer"/>
</body>
</html>
