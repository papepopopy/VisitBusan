console.log("스크립트 실행! :b");

// 페이징 번호 클릭시 처리하는 함수
document.querySelector('.my_pagination').addEventListener('click',function(e) {
    e.preventDefault();  // 기본 이벤트 제거
    e.stopPropagation();  // 버블링(현재 이벤트가 발생한 요소의 상위 요소들에 대해서 이벤트 감지되는 현상) 방지

    console.log('e: ',e);

    const target = e.target;
    if (target.tagName != 'A') {
        console.log("<a>태그가 아니라고!!");
        return;  // <a>태그가 아니면 종료
    }

    const num = target.getAttribute('data-num');  // 현재 클릭된 요소의 data-num을 읽어옴

    // 검색 기능 폼(form)에서 전송

    // document.querySelector('form');  // 폼이 하나면 이렇게 해도 상관없음 // 나중을 위해 비추(까먹고있다가 한참 찾야함)
    const formObj = document.querySelector('.searchForm');

    formObj.innerHTML += `<input type='hidden' name='page' value='${num}'>`
    formObj.submit();  // 전송

    // location.href="/board/list?page="+num  // 클릭한 페이지 번호

}) /* end pagination listener */


/* 게시글 링크 동작 */

const postsBox = document.querySelector('.postsBox');
const posts = postsBox.querySelectorAll('.post');

// 각 게시글에 클릭 이벤트 리스너 추가
for (let i = 0; i < posts.length; i++) {
  posts[i].addEventListener('click', function(e) {
    console.log(this.getAttribute('data-link'));
    const link = this.getAttribute('data-link');
    location.href= link;
  });
} /* end table listener */