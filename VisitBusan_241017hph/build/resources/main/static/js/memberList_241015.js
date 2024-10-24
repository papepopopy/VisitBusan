console.log("스크립트 실행2! :b");

/* 게시글 링크 동작 */
const table = document.querySelector('.table');
const rows = table.getElementsByTagName('tr');

// 각 행에 클릭 이벤트 리스너 추가
for (let i = 1; i < rows.length; i++) { // 헤더를 제외하기 위해 i를 1로 시작
    rows[i].addEventListener('click', function(e) {
        const userId = this.getAttribute('data-userId');
        if (userId) {
            // 필요한 데이터를 URL 쿼리 파라미터로 전달
            const params = new URLSearchParams({
                userId: document.getElementById('inputUserId').value,
                name: document.getElementById('inputName').value,
                email: document.getElementById('inputEmail').value,
                address: document.getElementById('inputAddress').value
            });

            fetch(`/admin/member/${userId}?${params.toString()}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                }
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // 성공적으로 데이터를 받았을 때 처리
                console.log(data);
                document.getElementById('inputName').value = data.name;
                document.getElementById('inputEmail').value = data.email;
                document.getElementById('inputAddress').value = data.address;
            })
            .catch(error => {
                console.error('Error fetching member data:', error);
            });
        }
    });
};

const informationModal = new bootstrap.Modal(document.querySelector('.informationModal'));
const PostBox = document.querySelector('.PostBox');
const informationBtns = PostBox.querySelectorAll('.informationBtn');

document.addEventListener('click', function(e) {
    if (e.target.classList.contains('informationBtn')) {
        console.log("click")
        const userId = e.target.getAttribute('onclick').match(/\('(.+?)'\)/)[1];
        fetchMemberData(userId)
            .then(() => {
                console.log("1");
                informationModal.show();
            })
            .catch(error => {
                console.log("2");
                console.error('Error fetching member data:', error);
            });
    }
})

// 수정
document.querySelector('.modBtn').addEventListener('click', function(e) {
    e.preventDefault();
    e.stopPropagation();

    console.log("test 시작");

    fetch('/admin/member/modify', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            // 필요한 데이터 추가
            userId: document.getElementById('inputUserId').value,
            name: document.getElementById('inputName').value,
            email: document.getElementById('inputEmail').value,
            address: document.getElementById('inputAddress').value
        })
    })
    .then(response => {
        console.log("3");
        if (response.ok) {
            return response.text(); // 응답 텍스트를 반환
        } else {
            throw new Error("서버 응답에 문제가 있습니다.");
        }
    })
    .then(data => {
        console.log("4");
        console.log("회원 정보가 수정되었습니다:", data);
        informationModal.hide(); // 모달 닫기
    })
    .catch(error => {
        console.log("5");
        console.error("Error: ", error);
    });
});

function fetchMemberData(userId) {
    return fetch(`/admin/member/read/${userId}`, {
        method: 'GET'
    })
    .then(response => {
        console.log("6");
        if (!response.ok) {
            throw new Error('네트워크 응답이 좋지 않습니다.');
        }
        return response.json();
    })
    .then(data => {
        console.log("7");
        // 가져온 데이터를 모달 내부에 채우기
        document.getElementById('inputName').value = data.name;
        document.getElementById('inputEmail').value = data.email;
        document.getElementById('inputAddress').value = data.address;
        document.getElementById('inputDate').value = data.regDate;
    })
    .catch(error => {
        console.log("8");
        console.error('Error fetching member data:', error)
    });
};