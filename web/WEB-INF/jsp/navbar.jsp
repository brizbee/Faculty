<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<nav>
    <div class="nav-wrapper grey darken-4">
        <a class="brand-logo white-text" style="padding-left: 15px;">Факультатив</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <c:if test="${isSignedIn == true}">
                <li class="white-text" style="padding: 0px 15px 0px 15px">Пользователь: ${login}</li>
                <c:if test="${role == 'student'}">
                    <li><a href="controller?command=updateFac">Мои факультативы</a></li>
                    <li><a href="controller?command=getMyMarks">Мои оценки</a></li>
                </c:if>
                <c:if test="${role == 'teacher'}">
                    <li><a href="controller?command=getFacTeach">Мои факультативы</a></li>
                    <li><a href="controller?command=getEndTeach">Законченные факультативы</a></li>
                </c:if>
                <li><a href="controller?command=logout">Выйти</a></li>
            </c:if>
        </ul>
    </div>
</nav>
</body>
</html>
