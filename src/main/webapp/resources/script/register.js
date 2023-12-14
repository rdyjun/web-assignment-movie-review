let pwHint = document.getElementById("hintOfConfirmPassword")
let pw = document.getElementById("password")
let pwConfirm = document.getElementById("confirmPassword")
let form = document.getElementById("registerBox")
let submitButton = document.getElementById("loginSubmitButton");
let verifyButton = document.getElementById('verifyButton');
let verifyEmailButton = document.getElementById('verifyEmailButton');
let verifiedBlock = document.getElementById('verifiedBlock');
let intervalId = null;
let isSend = false;
let isVerified = false;
let email = document.getElementById('email');
let key = document.getElementById("verifiedKey");

function togglePasswordVisibility(id) {
    var passwordInput = document.getElementById(id);
    passwordInput.type = "text";
}

function togglePasswordInvisibility(id) {
    var passwordInput = document.getElementById(id);
    passwordInput.type = "password";
}

function validatePassword () {
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
    if (form.checkValidity() && pw.value === pwConfirm.value && isVerified) {
        submitButton.disabled = false;
    } else {
        submitButton.disabled = true;
    }
}

verifyButton.addEventListener('click', function() {
    alert("인증 메일이 발송되었습니다.");
    validateDuring()
    startTimer();
    if (isSend)
        alert("잘못된 접근입니다.");
    var email = document.getElementById('email').value;

    fetch('/register/mailConfirm', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email: email }),
    })
        .then(response => {
            if (!response.ok)
                throw new Error('Network response was not ok');
        })
        .then(data => {

        })
        .catch((error) => {
            validateBefore()
            // 에러 메시지 출력
            alert('메일이 이미 존재하거나, 올바르지 않습니다.' + error);
            clearInterval(intervalId);
        });
});

verifyEmailButton.addEventListener('click', function() {
    if (isVerified)
        alert("잘못된 접근입니다.");

    fetch('/register/mailCertify', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            email: email.value,
            key: key.value
        }),
    })
        .then(response => {
            if (!response.ok)
                throw new Error('Network response was not ok');
        })
        .then(data => {
            alert("인증되었습니다.");
            clearInterval(intervalId);
            verifyButton.disabled = true;
            verifyEmailButton.disabled = true;
            verifyEmailButton.style.backgroundColor = "#a7a7a7";
            key.readOnly = true;
            isVerified = true;
            verifyButton.textContent = '인증 완료';
            validateInputValue();
        })
        .catch((error) => {
            // 에러 메시지 출력
            alert('인증번호가 올바르지 않습니다. 다시 시도해주세요.\n' + error);
        });
});


function startTimer() {
    // 이미 타이머가 실행 중이면 중지
    if (intervalId !== null) {
        clearInterval(intervalId);
    }

    var totalSeconds = 5 * 60;

    intervalId = setInterval(function() {
        var minutes = Math.floor(totalSeconds / 60);
        var seconds = totalSeconds % 60;

        // 두 자리 숫자로 표시
        if (minutes < 10) minutes = '0' + minutes;
        if (seconds < 10) seconds = '0' + seconds;

        verifyButton.textContent = minutes + ':' + seconds;

        totalSeconds--;

        // 00:00이 되면 타이머 중지
        if (totalSeconds < 0) {
            clearInterval(intervalId);
            validateBefore();
            intervalId = null;
            verifyButton.style.cursor = 'pointer';
            verifyButton.style.backgroundColor = "#9664FF";
        }
    }, 1000);
}

function validateDuring () {
    verifiedBlock.style.display = 'flex';
    verifyButton.disabled = true;
    verifyButton.style.cursor = 'default';
    verifyButton.style.backgroundColor = "#a7a7a7";
    email.style.backgroundColor = '#a7a7a7';
    email.readOnly = true;
}
function validateBefore () {
    verifyButton.textContent = '인증번호 발급';
    verifiedBlock.style.display = 'none';
    verifyButton.disabled = false;
    verifyButton.style.cursor = 'pointer';
    verifyButton.style.backgroundColor = "#9664FF";
    email.style.backgroundColor = '#fff';
    email.readOnly = false;
}