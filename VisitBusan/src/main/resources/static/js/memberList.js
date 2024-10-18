/* 게시글 링크 동작 */
const table = document.querySelector('.table');
const rows = table.getElementsByTagName('tr');

const informationModal = new bootstrap.Modal(document.querySelector('.informationModal'));

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
                return response.json();  // 응답 데이터를 JSON으로 변환
            })
            .then(data => {
                // 성공적으로 데이터를 받았을 때 처리
                console.log("데이터 : ", data);
                document.getElementById('inputUserId').value = data.userId;
                document.getElementById('inputName').value = data.name;
                document.getElementById('inputEmail').value = data.email;
                document.getElementById('inputAddress').value = data.address;

                informationModal.show();
            })
            .catch(error => {
                console.error('Error fetching member data:', error);
                 alert('회원 정보를 불러오는데 문제가 발생했습니다.');
            });
        }
    });
};

// 수정
document.querySelector('.modBtn').addEventListener('click', function(e) {
    e.stopPropagation();
    console.log("test 시작");

    const memberData = {
        userId: document.getElementById('inputUserId').value,
        name: document.getElementById('inputName').value,
        email: document.getElementById('inputEmail').value,
        address: document.getElementById('inputAddress').value
    };

    console.log(memberData)

    // 서버에 POST 요청을 보냅니다.
    fetch('/admin/member/list/modify', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(memberData)
    })
    .then(response => {
        console.log("3");
        if (response.ok) {
            window.location.href = "/admin/member/list"; // 응답 텍스트를 반환
        } else {
            throw new Error("서버 응답에 문제가 있습니다.");
        }
    })
    .then(data => {
        console.log("4");
        console.log("회원 정보가 수정되었습니다");
        informationModal.hide(); // 모달 닫기
    })
    .catch(error => {
        console.log("5");
        console.error("Error: ", error);
    });
});

// 삭제
document.querySelector('.secessionBtn').addEventListener('click', function(e) {
    e.stopPropagation();
    console.log("test 시작");

    const userId = document.getElementById('inputUserId').value;
    console.log("삭제 ID : ", userId);

    // 서버에 POST 요청을 보냅니다.
    fetch(`/admin/member/list/delete/${userId}`, {
        method: 'DELETE',
    })
    .then(response => {
        console.log("서버 응답 상태:", response.status);  // 상태 코드 출력

        if (response.ok) {
            window.location.href = "/admin/member/list"; // 응답 텍스트를 반환
        } else {
            throw new Error("서버 응답에 문제가 있습니다.");
        }
    })
    .catch(error => {
        console.log("5");
        console.error("Error: ", error);
    });
});