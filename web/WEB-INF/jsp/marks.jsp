<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
</head>
<body class="grey lighten-4">
<c:import url="/navbar"/>

<div class="container" style="margin-bottom: 200px;">
    <div class="center-align">
        <h5>Оценки:</h5>
    </div>
    <div class="row white">
        <div class="col s12">
            <table>
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Кафедра</th>
                    <th>Оценка</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="facultyList" items="${facultyList}">
                    <c:set var="idFac" value="${facultyList.getId()}"/>
                    <tr>
                        <td>${coursesMap[idFac].getTitle()}</td>
                        <td>${coursesMap[idFac].getKafedra()}</td>
                        <td>${marksMap[idFac].getMark()}</td>
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
