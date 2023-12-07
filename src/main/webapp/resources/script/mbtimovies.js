function mbtipage () {
    let fir = document.getElementById("first");
    let sec = document.getElementById("second");
    let thi = document.getElementById("third");
    let fou = document.getElementById("fourth");
    location.href="/mbti-movies?mbti=" + fir.value + sec.value + thi.value + fou.value;
}