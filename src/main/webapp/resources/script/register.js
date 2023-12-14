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

verifyButton.addEventListener('click', function() {
    if (isSend)
        alert("잘못된 접근입니다.");
    var email = document.getElementById('email').value;
    console.log(email);

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
            alert("인증 메일이 발송되었습니다.");
            verifiedBlock.style.display = 'flex';
            verifyButton.disabled = 'true';
            verifyButton.style.cursor = 'default';
            verifyButton.style.backgroundColor = "#888";
            startTimer();
        })
        .catch((error) => {
            // 에러 메시지 출력
            alert('메일이 이미 존재하거나, 올바르지 않습니다.' + error);
        });
});

verifyEmailButton.addEventListener('click', function() {
    if (isVerified)
        alert("잘못된 접근입니다.");
    let email = document.getElementById('email').value;
    let key = document.getElementById("verifiedKey").value;

    fetch('/register/mailCertify', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            email: email,
            verify_key: key
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
            verifyEmailButton.style.backgroundColor = "#888";
        })
        .catch((error) => {
            // 에러 메시지 출력
            alert('인증번호가 올바르지 않습니다. 다시 시도해주세요.' + error);
        });
});


function startTimer() {
    // 이미 타이머가 실행 중이면 중지
    if (intervalId !== null) {
        clearInterval(intervalId);
    }

    var totalSeconds = 5 * 60;  // 5분을 초로 변환

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
            verifyButton.textContent = '인증번호 발급';
            intervalId = null;
            verifyButton.disabled = 'false';
            verifyEmailButton.style.display = 'block';
            verifiedBlock.style.display = 'none';
            verifyButton.style.cursor = 'pointer';
            verifyButton.style.backgroundColor = "#9664FF";
        }
    }, 1000);
}
