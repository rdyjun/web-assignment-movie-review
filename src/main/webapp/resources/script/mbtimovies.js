function mbtipage () {
    fir = document.getElementById("first");
    sec = document.getElementById("second");
    thi = document.getElementById("third");
    fou = document.getElementById("fourth");
    location.href="/mbti-movies?mbti=" + fir.value + sec.value + thi.value + fou.value;
}