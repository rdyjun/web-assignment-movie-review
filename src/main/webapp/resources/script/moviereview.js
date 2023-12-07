function cancel () {
    let reviewTextArea = document.getElementById("reviewTextArea");
    let isOk = true;
    if (reviewTextArea.value.trim() !== "") {
        isOk = confirm("이미 작성하던 내용이 있습니다.\n그래도 취소하시겠습니까?")
    }
    reviewTextArea.value = "";
    if (!isOk)
        return false;
    offWriteBox();
}

function offWriteBox () {
    let writeBox = document.getElementById("reviewWriteBox");
    let reviewTitleBox = document.getElementById("reviewTitleBox");
    writeBox.style.display = "none";
    reviewTitleBox.style.display = "flex";
}

function onWriteBox (userId) {
    if (userId == null || userId == "") {
        alert("로그인 후 다시시도해주세요");
        return;
    }
    let writeBox = document.getElementById("reviewWriteBox");
    let reviewTitleBox = document.getElementById("reviewTitleBox");
    writeBox.style.display = "block";
    reviewTitleBox.style.display = "none";
}

function selectRating (element) {
    let stars = document.querySelectorAll(".writeStar");
    let ipt = document.getElementById("rating");
    stars.forEach(function(stars) {
        stars.style.fill = "#CCC";
    });
    let idx = Array.from(stars).indexOf(element);
    ipt.value = idx + 1;
    let prevStars = Array.from(stars).slice(0, idx + 1);
    prevStars.forEach(function(prevStar) {
        prevStar.style.fill = "#9664FF";
    });
}
function showMenu (element) {
    let reviewMenu = element.nextElementSibling;
    reviewMenu.style.display = "block";
}
document.addEventListener('click', function(event) {
    let reviewMenus = document.querySelectorAll('.reviewMenu');
    reviewMenus.forEach(function(menu) {
        if (event.target !== menu.previousElementSibling && !menu.contains(event.target)) {
            menu.style.display = 'none';
        }
    });
});

function validateForm () {
    let userId = sessionStorage.getItem("userId");
    if (userId == null) {
        alert("로그인 후 시도해주세요.");
        return false;
    }
    return true;
}