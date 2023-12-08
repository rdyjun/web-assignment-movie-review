<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/register.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/wrap.css">
    <link href='https://fonts.googleapis.com/css?family=Preahvihear' rel='stylesheet'>
    <script src="../resources/script/register.js" defer="defer"></script>
</head>
<body>
<form id="registerBox" action="/register-validate" method="post">
    <p id="printWelcome" style="cursor: pointer" onclick="location.href='/'">TALK FILM</p>

    <p id="registerTitle">Sign Up</p>
    <p id="registerHint">계정이 이미 있는 경우<br>이곳으로 <a href="/login">로그인</a> 하세요</p>

    <label id="hintOfEmail" class="hintMessage">E-mail
        <input name="id" class="loginInputButton" type="email" placeholder="E-mail" required>
    </label>
    <label class="hintMessage">User name
        <input name="name" class="loginInputButton" type="text" placeholder="User name" maxlength="40" required>
    </label>
    <label id="hintOfPassword" class="hintMessage">Password
        <input id="password" name="pw" class="loginInputButton" type="password" placeholder="Password" onchange="validatePassword()" maxlength="20" required>
        <div id="pwVisionIcon" onmousedown="togglePasswordVisibility('password')" onmouseup="togglePasswordInvisibility('password')" onmouseout="togglePasswordInvisibility('password')"></div>

    </label>
    <label id="hintOfConfirmPassword" class="hintMessage">Confirm Password
        <input id="confirmPassword" class="loginInputButton" type="password" placeholder="Confirm Password" oninput="validatePassword()" maxlength="20" required>
        <div id="pwCheckVisionIcon"  onmousedown="togglePasswordVisibility('confirmPassword')" onmouseup="togglePasswordInvisibility('confirmPassword')" onmouseout="togglePasswordInvisibility('confirmPassword')"></div>
    </label>
    <input id="loginSubmitButton" type="submit" value="회원가입" disabled>
</form>
</body>
</html>
