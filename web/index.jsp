<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello page</title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
</head>
<body>
<c:import url="/navbar"/>

<div class="container">
    <div class="row" style="min-height: 100px; margin: 130px 0px 180px 0px">
        <div class="col s6 offset-l3">
            <div class="row center-align">
                <h5>Пожалуйста, выберите действие</h5>
                <a class="waves-effect waves-light btn blue lighten-2 col s4 offset-l2" href="/login" style="margin-right: 5px;">Войти</a>
                <a class="waves-effect waves-light btn blue lighten-1 col s4" href="/signin" style="margin-left: 5px;">Регистрация</a>
            </div>
        </div>
    </div>
</div>
<c:import url="/footer"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
</body>
</html>
