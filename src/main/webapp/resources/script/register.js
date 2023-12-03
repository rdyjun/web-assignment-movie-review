let pwHint = document.getElementById("hintOfConfirmPassword")
let pw = document.getElementById("password")
let pwConfirm = document.getElementById("confirmPassword")
let form = document.getElementById("registerBox")
let submitButton = document.getElementById("loginSubmitButton");
function togglePasswordVisibility(id) {
    var passwordInput = document.getElementById(id);
    passwordInput.type = "text";
}

function togglePasswordInvisibility(id) {
    var passwordInput = document.getElementById(id);
    passwordInput.type = "password";
}

function validatePassword () {
    console.log("111");
    if (pw.value == pwConfirm.value) {
        pwHint.style.color = "green";
        pwConfirm.style.borderColor = "green";
        pwConfirm.style.color = "green";
    }
    if (pw.value != pwConfirm.value) {
        pwHint.style.color = "red";
        pwConfirm.style.borderColor = "red";
        pwConfirm.style.color = "red";
    }
}
form.addEventListener('input', validateInputValue)
function validateInputValue () {
    if (form.checkValidity() && pw.value === pwConfirm.value) {
        submitButton.disabled = false;
    } else {
        submitButton.disabled = true;
    }
}