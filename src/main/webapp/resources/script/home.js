// function switchMovie() {
//     // 모든 포스터에서 focus 클래스를 제거합니다.
//     document.querySelectorAll('.moviePosterContainer').forEach(el => el.classList.remove('focus'));
//
//     // 현재 영화에 focus 클래스를 추가합니다.
//     document.querySelector(`.${movies[index]}`).classList.add('focus');
//
//     // 감독, 장르, 등급 정보를 업데이트합니다.
//     document.querySelector('#movieData p:nth-child(1)').innerHTML = '감독 : ' + movieData[index].director;
//     document.querySelector('#movieData p:nth-child(2)').innerHTML = '장르 : ' + movieData[index].genre;
//     document.querySelector('#movieData p:nth-child(3)').innerHTML = '등급 : ' + movieData[index].rating;
//
//     // 다음 영화의 인덱스로 이동하거나, 배열의 끝에 도달한 경우 인덱스를 0으로 재설정합니다.
//     index = (index + 1) % movies.length;
// }

// 2초마다 switchMovie 함수를 호출합니다.
// setInterval(switchMovie, 2000);

let index = 1;
const movies = document.querySelectorAll('.moviePosterContainer').forEach(el => el.classList.remove('focus'));
const f = document.getElementById("first");
const s = document.getElementById("second");
const t = document.getElementById("third");
let className = ['animate-first', 'animate-focus', 'animate-second'];

function switchMovie() {
    f.classList.remove(className[index % 3]);
    s.classList.remove(className[(index + 2) % 3]);
    t.classList.remove(className[(index + 1) % 3]);
    f.classList.add(className[(index + 1) % 3]);
    s.classList.add(className[(index + 3) % 3]);
    t.classList.add(className[(index + 2) % 3]);
    index += 1;
}

// 5초마다 switchMovie 함수를 호출합니다.
setInterval(switchMovie, 5000);