let listStatus = "user"
function showUnblockUser () {
    let userList = document.getElementById("userList");
    let blockList = document.getElementById("blockList")
    let whiteList = document.getElementById("whitelist");
    let blackList = document.getElementById("blackList");
    userList.style.fontWeight = 700;
    blockList.style.fontWeight = 500;
    whiteList.style.display = "block";
    blackList.style.display = "none";
}
function showblockUser () {
    let userList = document.getElementById("userList");
    let blockList = document.getElementById("blockList")
    let whiteList = document.getElementById("whitelist");
    let blackList = document.getElementById("blackList");
    userList.style.fontWeight = 500;
    blockList.style.fontWeight = 700;
    whiteList.style.display = "none";
    blackList.style.display = "block";
}