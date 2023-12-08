
window.onload = function () {
    params = new URLSearchParams(window.location.search);
    rec = document.getElementById("rec");
    sortValue = params.get('sort');
    if (sortValue == "rec")
        document.getElementById("movieSelecter").value = 'rec';
}
function changeSort () {
    slt = document.getElementById("movieSelecter");
    const selectedOption = slt.value;
    const url = new URL(window.location.href);

    // 선택한 옵션 값을 URL의 쿼리 파라미터로 설정
    url.searchParams.set('sort', selectedOption);
    location.href = url.href;
}