//메뉴 반응형
const menuBox = document.querySelector(".menu-box");
//열기
document.querySelector('.menu_open').addEventListener('click', function(e) {
    e.preventDefault();
    e.stopPropagation();

    menuBox.classList.add("active");
});
//닫기
document.querySelector('.menu_close').addEventListener('click', function(e) {
    e.preventDefault();
    e.stopPropagation();

    menuBox.classList.remove("active");
});

//1200이상 자동 닫기
$(window).resize(function() {
    var innerWidth = window.outerWidth;

    if(innerWidth >= 1200) {
        menuBox.classList.remove("active");
    }
})
