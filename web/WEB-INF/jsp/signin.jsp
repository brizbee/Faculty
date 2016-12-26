<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
</head>
<body class="grey lighten-4">
<c:import url="/navbar"/>
<div class="container">
    <div class="row center-align" style="margin: 10% 0% 5% 0%">
        <h5>Заполните поля формы</h5>
        <div class="center-align red-text">
            <h5>${errorInLogin}</h5>
            <h5>${errorInPass}</h5>
            <h5>${errorSIGNIN}</h5>
        </div>
        <form name="SigninForm" action="controller" method="POST" class="col s8 offset-l2">
            <input name="command" type="hidden" value="signin">
            <div class="row">
                <div class="input-field col s4 offset-l2">
                    <input name="fio" type="text" id="fio">
                    <label for="fio">FIO</label>
                </div>
                <div class="input-field col s4">
                    <input name="login" id="login" type="text">
                    <label for="login">Login</label>
                </div>
                <select required size="1" name="role">
                    <option selected="selected">Выберите роль</option>
                    <option name="role" value="teacher">teacher</option>
                    <option name="role" value="student">student</option>
                </select>
            </div>
            <div class="row">
                <div class="input-field col s4 offset-l2">
                    <input id="password" type="password" name="password" length="15">
                    <label for="password">Password</label>
                </div>
                <div class="input-field col s4">
                    <input name="passwordRepeat" id="passwordRepeat" type="password" length="15">
                    <label for="passwordRepeat">Repeat password</label>
                </div>
            </div>
            <div class="row center-align">
                <button class="btn waves-effect waves-light col s4 offset-l2" type="submit" style="margin-right: 5px;">Зарегистрироваться</button>
                <a class="waves-effect waves-light btn blue lighten-2 col s4" href="/index.jsp" style="margin-left: 5px;">На главную</a>
            </div>



        </form>
    </div>
</div>


<jsp:include page="/footer"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
<script>
    $(document).ready(function() {
        $('select').material_select();
    });
</script>
</body>
</html>
