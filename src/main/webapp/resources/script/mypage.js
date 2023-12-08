const modifyBox = document.getElementById("modifyForm");
const modifyInput = document.getElementById("modifyInput");
const name = document.getElementById("movieTitle");
function openModify () {
    modifyInput.value = name.textContent;
    modifyBox.style.display = "block";
    name.style.display = "none";
}
function closeModify () {
    name.style.display = "block";
    modifyInput.value = "";
    modifyBox.style.display = "none";
}