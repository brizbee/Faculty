function validate(){
    var result = true;

    var FILL_FIELD = "*заполните поле",
        INCORRECT_EMAIL = "*Неправильный формат ввода адреса электронной почты",
        INCORRECT_F_NAME = "*Неправильный формат ввода имени",
        INCORRECT_L_NAME = "*Неправильный формат ввода фамилии",
        INCORRECT_LOGIN = "*Минимум 5 символов. Может состоять из латинских букв, цифр и символа '_' . Первый символ - латинская буква",
        INCORRECT_PASSWORD = "*Минимум 6 символов. Не менее одной буквы в каждом регистре и не менее одной цифры",
        PWD_NOT_EQUAL = "*не совпадают значения паролей";

    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var nameRe = /^[A-Z]{1}[a-z]{0,20}$/;
    var loginRe = /^[a-zA-Z]{1}[a-zA-Z0-9_]{4,14}$/;
    var passwordRe = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{6,}$/;


    var erName = document.getElementById("err-firstname"),
        erLastName = document.getElementById("err-lastname"),
        erLogin = document.getElementById("err-login"),
        erPassword = document.getElementById("err-password"),
        erRpassword = document.getElementById("err-rpassword"),
        erEmail = document.getElementById("err-email");

    erName.innerHTML= "";
    erLastName.innerHTML= "";
    erLogin.innerHTML= "";
    erEmail.innerHTML= "";
    erPassword.innerHTML= "";
    erRpassword.innerHTML= "";

    var f_name = document.registration.firstName.value,
        l_name = document.registration.lastName.value,
        usr = document.registration.login.value,
        pwd1 = document.registration.password.value,
        pwd2 = document.registration.rpassword.value,
        eml = document.registration.eMail.value;

    //first name
    if(!f_name){
        erName.innerHTML = FILL_FIELD;
        result = false;
    }
    if(f_name && !nameRe.test(f_name)){
        erName.innerHTML = INCORRECT_F_NAME;
        result = false;
    }

    //last_name
    if(!l_name){
        erLastName.innerHTML = FILL_FIELD;
        result = false;
    }
    if(l_name && !nameRe.test(l_name)){
        erLastName.innerHTML = INCORRECT_L_NAME;
        result = false;
    }

    //login
    if (!usr) {
        erLogin.innerHTML = FILL_FIELD;
        result = false;
    }
    if (usr && !loginRe.test(usr)) {
        erLogin.innerHTML = INCORRECT_LOGIN;
        result = false;
    }

    //email
    if(!eml){
        erEmail.innerHTML = FILL_FIELD;
        result = false;
    }
    if(eml && !re.test(eml)){
        erEmail.innerHTML = INCORRECT_EMAIL;
        result = false;
    }

    //password & repeat password
    if (!pwd1) {
        erPassword.innerHTML = FILL_FIELD;
        result = false;
    }
    if (!pwd2) {
        erRpassword.innerHTML = FILL_FIELD;
        result = false;
    }
    if(pwd1 && !passwordRe.test(pwd1)){
        erPassword.innerHTML = INCORRECT_PASSWORD;
        result = false;
    }
    if (pwd2 && passwordRe.test(pwd1) && pwd1 !== pwd2) {
        erPassword.innerHTML = PWD_NOT_EQUAL;
        erRpassword.innerHTML = PWD_NOT_EQUAL;
        result = false;
    }

    return result;

}
