<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
</head>
<body>
<c:import url="/navbar"/>
<div class="container">
    <div class="row center-align" style="margin: 15% 0% 15% 0%">
        <h5>Заполните поля формы</h5>
        <form name="LoginForm" method="POST" action="controller" class="col s8 offset-l2">
            <input name="command" type="hidden" value="login">
            <div class="row center-align">
                <div class="input-field col s4 offset-l2">
                    <input name="login" type="text">
                    <label>Login</label>
                </div>
                <div class="input-field col s4 ">
                    <input name="password" type="password" length="15">
                    <label>Password</label>
                </div>
                <div class="text-accent-4">${errorLoginOrPassMessage}</div>
            </div>
            ${wrongAction}
            ${nullPage}
            <div class="row center-align">
                <button class="btn waves-effect waves-light col s4 offset-l2 blue lighten-1" type="submit" style="margin-right: 5px;">Войти</button>
                <a class="waves-effect waves-light btn blue lighten-2 col s4 blue lighten-1" href="/index.jsp" style="margin-left: 5px;">На главную</a>
            </div>
        </form>
    </div>
</div>
<jsp:include page="/footer"/>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
</body>
</html>
