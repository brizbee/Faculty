<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ended</title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
</head>
<body>
<c:import url="/navbar"/>
<div class="container">
    <div class="row">
        <ul class="collapsible" data-collapsible="accordion">
            <c:forEach var="faculty" items="${endedFac}">
                <c:set var="idFac" value="${faculty.getId()}"/>
                <c:set var="course" value="${courseMap[idFac]}"/>
                <c:set var="users" value="${userMap[idFac]}"/>
                <li>
                    <div class="collapsible-header">${course.getTitle()}</div>
                    <div class="collapsible-body">
                        <ul>
                            <c:forEach var="u" items="${users}">
                                <div class="row" style="padding: 5px 20px 5px 20px">
                                    <li class="col s4">${u.getName()}</li>
                                    <a class="waves-effect waves-light btn blue lighten-2 col s4 blue lighten-1" href="#" >Оценить</a>
                                </div>

                            </c:forEach>
                        </ul>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<jsp:include page="/footer"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
</body>
</html>
