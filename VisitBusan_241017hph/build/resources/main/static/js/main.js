console.log("123123123123123")
let currentIndex = 0;
let slides = document.querySelectorAll('.slider .f-sliderBox');
let totalSlides = slides.length;
// const totalSlides = 2;
console.log("789789789798")

function showSlide(index) {
    const slider = document.querySelector('.slider');
    slider.style.transform = `translateX(${-index * 20}%)`;
}

function nextSlide() {
    console.log("next")
    console.log(totalSlides)
    currentIndex = (currentIndex + 1) % totalSlides;
    showSlide(currentIndex);
}

function prevSlide() {
    console.log("prev")
    console.log(totalSlides)
    currentIndex = (currentIndex - 1 + totalSlides) % totalSlides;
    showSlide(currentIndex);
}
// let currentIndex = 0;
// let slides = document.querySelectorAll('.slider .f-sliderBox');
// let totalSlides = slides.length;
// const slider = document.querySelector('.slider');

// function showSlide() {
//     // 슬라이더 이동
//     slider.style.transform = `translateX(${-currentIndex * 20}%)`;
// }

// function nextSlide() {
//     currentIndex++;
//     showSlide();

//     // 마지막 슬라이드에서 첫 슬라이드를 맨 뒤로 이동
//     if (currentIndex === totalSlides) {
//         setTimeout(() => {
//             slider.style.transition = 'none';
//             currentIndex = 0;
//             slider.style.transform = `translateX(0)`;
//             slider.appendChild(slides[0]); // 첫 슬라이드를 맨 뒤로 이동
//             slider.style.transition = 'transform 0.5s ease';
//         }, 500); // 슬라이드 이동 후 transition 시간과 일치하도록
//     }
// }

// function prevSlide() {
//     currentIndex--;
//     showSlide();

//     // 첫 번째 슬라이드에서 마지막 슬라이드를 맨 앞으로 이동
//     if (currentIndex < 0) {
//         setTimeout(() => {
//             slider.style.transition = 'none';
//             currentIndex = totalSlides - 1;
//             slider.style.transform = `translateX(${-currentIndex * 20}%)`;
//             slider.insertBefore(slides[totalSlides - 1], slides[0]); // 마지막 슬라이드를 맨 앞으로 이동
//             slider.style.transition = 'transform 0.5s ease';
//         }, 500); // 슬라이드 이동 후 transition 시간과 일치하도록
//     }
// }

// // 초기 슬라이드 설정
// showSlide();


const pageRequests = [
    {bCategory:'festival', size:5},
    {bCategory:'place', size:8},
    {bCategory:'review', size:2},
    {bCategory:'themeTravel', size:6}
];

const f_sliderList = document.querySelector('.f-sliderList');
f_sliderList.innerHTML = ''; 
const g_imgBox = document.querySelector('.g-imgBox');
g_imgBox.innerHTML = '';
const t_imgBox = document.querySelector('.t-imgBox');
t_imgBox.innerHTML = '';
const ord_imgSlide = document.querySelector('.ord-imgSlide');
ord_imgSlide.innerHTML = '';

pageRequests.forEach(request => {
    // 서버로부터 카테고리별로 그룹화된 데이터를 가져옴
    fetch(`/board/main/list?size=${request.size}&bCategory=${request.bCategory}`)
    .then(response => response.json())
    .then(data => {

        for(i=0; i<data.length; i++) {
            console.log("data[i]: ",data[i])
            console.log("data[i].id: ",data[i].id)
            console.log("data[i].category: ",data[i].category)
            console.log("data[i].title: ",data[i].title)
            console.log("data[i].boardFiles: ",data[i].boardFiles)

            if(request.bCategory == "festival") {
                const f_sliderBox = document.createElement("div");
                f_sliderBox.classList = "f-sliderBox"
                
                if (data[i].boardFiles.length == 0 ) {
                    f_sliderBox.style.backgroundImage= "url('../images/mainpage/f_img2.gif')";
                }
                if (data[i].boardFiles.length > 0 ) {
                    f_sliderBox.style.backgroundImage=`url('http://localhost:9089/view/${data[i].boardFiles[0].uuid}==vb==${data[i].boardFiles[0].fileName}')`;
                }

                f_sliderBox.innerHTML += 
                `<div class="f-slider-title">
                    <h2>${data[i].title}</h2>
                    <span class="date">기간 ${data[i].startDate} ~ ${data[i].endDate}</span>
                </div>`

                f_sliderList.appendChild(f_sliderBox);
                
                slides = document.querySelectorAll('.slider .f-sliderBox');
                totalSlides = slides.length;

            } else if (request.bCategory == "place") {
                // const g_item = document.createElement("div");
                // g_item.classList = "g-item"
                // if (data[i].boardFiles.length == 0 ) {
                //     g_item.style.backgroundImage= "url('../images/samples/busan1.jpg')";
                // }
                // if (data[i].boardFiles.length > 0 ) {
                //     g_item.style.backgroundImage=`url('http://localhost:9089/view/${data[i].boardFiles[0].uuid}==vb==${data[i].boardFiles[0].fileName}')`;
                // }
                // g_imgBox.appendChild(g_item);
                const g_imgBox_child = document.createElement("div");
                g_imgBox_child.classList = "g-item"
                if (data[i].boardFiles.length == 0 ) {
                    g_imgBox_child.style.backgroundImage= "url('../images/samples/busan1.jpg')";
                }
                if (data[i].boardFiles.length > 0 ) {
                    g_imgBox_child.style.backgroundImage=`url('http://localhost:9089/view/${data[i].boardFiles[0].uuid}==vb==${data[i].boardFiles[0].fileName}')`;
                }
                g_imgBox.appendChild(g_imgBox_child);

            } else if (request.bCategory == "review") {
                let htmlTag = '';
                if (data[i].boardFiles.length == 0 ) {
                    htmlTag +=
                    `<div class="t-item">
                        <div class="image-container">
                            <img class="img-tag" src="../images/samples/busan1.jpg">
                        </div>`
                }
                if (data[i].boardFiles.length > 0 ) {
                    htmlTag +=
                    `<div class="t-item">
                        <div class="image-container">
                            <img class="img-tag" src="/view/${data[i].boardFiles[0].uuid}==vb==${data[i].boardFiles[0].fileName}">
                        </div>`
                }
                htmlTag +=
                `   <div class="t-tag">#광안리해수욕장</div>
                    <div class="t-title">${data[i].title}</div>
                    <div class="t-date">${data[i].regDate}</div>
                </div>`
                t_imgBox.innerHTML += htmlTag;

            } else if (request.bCategory == "themeTravel") {
                const ord_imgSlide_child = document.createElement("li");
                if (data[i].boardFiles.length == 0 ) {
                    console.log("case1")
                    ord_imgSlide_child.style.backgroundImage= "url('../images/samples/busan1.jpg')";
                }
                if (data[i].boardFiles.length > 0 ) {
                    console.log("case2")
                    ord_imgSlide_child.style.backgroundImage=`url('http://localhost:9089/view/${data[i].boardFiles[0].uuid}==vb==${data[i].boardFiles[0].fileName}')`;
                }
                ord_imgSlide.appendChild(ord_imgSlide_child);

            }

        }

    })

})

