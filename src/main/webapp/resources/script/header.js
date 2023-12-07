function isConfirm () {
    let isConfirm = confirm('정말 회원탈퇴를 진행하시겠습니까?');
    return isConfirm;
}
function showMyApps () {
    let myApps = document.getElementById("myApps");
    if (myApps.style.display == "") {
        myApps.style.display = "flex";
        return;
    }
    if (myApps.style.display == "flex")
        myApps.style.display = "";
}