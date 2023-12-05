var listStatus = "user"
function showUnblockUser () {
    var userList = document.getElementById("userList");
    var blockList = document.getElementById("blockList")
    var whiteList = document.getElementById("whitelist");
    var blackList = document.getElementById("blackList");
    userList.style.fontWeight = 700;
    blockList.style.fontWeight = 500;
    whiteList.style.display = "block";
    blackList.style.display = "none";
}
function showblockUser () {
    var userList = document.getElementById("userList");
    var blockList = document.getElementById("blockList")
    var whiteList = document.getElementById("whitelist");
    var blackList = document.getElementById("blackList");
    userList.style.fontWeight = 500;
    blockList.style.fontWeight = 700;
    whiteList.style.display = "none";
    blackList.style.display = "block";
}