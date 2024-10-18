async function validateStep2() {
    console.log("validateStep2");

    const userId = document.querySelector('#floatingId');
    const password = document.querySelector('#floatingPassword');
    const name = document.querySelector('#floatingName');
    const email = document.querySelector('#floatingEmail');
    const address = document.querySelector('#floatingAddress');

    if (userId.value.trim() === "") {
        alert("아이디 입력은 필수입니다.");
        userId.focus();
        return false;
    }
    if (password.value.trim() === "") {
        alert("비밀번호 입력은 필수입니다.");
        password.focus();
        return false;
    }
    if (password.value.length < 4 || password.value.length > 16) {
        alert("비밀번호는 4자 이상 16자 이내로 입력해주세요.");
        password.focus();
        return false;
    }
    if (name.value.trim() === "") {
        alert("이름 입력은 필수입니다.");
        name.focus();
        return false;
    }
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; //이메일 주소 형식
    if (email.value.trim() === "") {
        alert("이메일 입력은 필수입니다.");
        email.focus();
        return false;
    } else if (!emailPattern.test(email.value.trim())) {
        alert("이메일 형식이 올바르지 않습니다.");
        email.focus();
        return false;
    }
    if (address.value.trim() === "") {
        alert("주소 입력은 필수입니다.");
        address.focus();
        return false;
    }
    try {
        // ID 또는 이메일이 이미 존재하는지 확인 API 호출
        const response = await fetch("/check-existing", {
            method : "POST", //데이터가 서버로 전송
            headers : {"Content-Type":"application/json"}, //JSON 형식
            body : JSON.stringify({userId: userId.value.trim(), email: email.value.trim()}) // db로 보내는 데이터 id, email
        });
        // 응답 상태 코드 확인
        if (!response.ok) {
            throw new Error(`서버 오류: ${response.statusText}`);
        }

        // 응답 결과 처리
        const result = await response.json(); //await: 작업완료 후 response 실행
        console.log("API 응답 결과:", result);

        if (result.exists) { // 중복하는 userId, 이메일이 있을 때
            if(result.type === "userId"){
                alert("이미 존재하는 아이디입니다.");
                userId.focus();
                return false;
            } else if (result.type === "email") {
                alert("이미 존재하는 이메일입니다.");
                email.focus();
                return false;
            }
        }
        console.log("validateStep2 완료");
    } catch (e) {
        console.error("API 호출 오류:", e);
        alert("서버와의 통신 중 오류가 발생했습니다.");
        return false;
    }
    // 모든 유효성 검사를 통과하면 단계 전환
    return true;
}

const formObj = document.querySelector('form')
document.querySelector('#step2-btn').addEventListener('click', function(e) {
    console.log("step2 start")
    formObj.submit();
});