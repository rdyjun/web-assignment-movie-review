<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/login.css">
    <link rel="stylesheet" type="text/css" href="../resources/wrap.css">
    <link href='https://fonts.googleapis.com/css?family=Preahvihear' rel='stylesheet'>
</head>
<body>
    <form id="loginBox" action="/login-validate" method="post">
        <div id="loginWelcome">
            <p id="printWelcome"><a href="/">TALK FILM</a></p>
            <div id="hintRegister">
                <p>회원이 아니신가요?</p>
                <a id="linkRegister" href="/register">회원가입</a>
            </div>
        </div>

        <p id="hintOfEmail" class="hintMessage">가입한 이메일 주소를 입력해주세요.</p>
        <input name="id" class="loginInputButton" type="email" placeholder="username of email address" required>
        <p id="hintOfPassword" class="hintMessage">당신의 비밀번호를 입력해주세요.</p>
        <input name="pw" class="loginInputButton" type="password" placeholder="Password" required>
        <input id="loginSubmitButton" type="submit" value="로그인">
    </form>
</body>
</html>