const revInnerBox = document.querySelector('.rev-inner-box');
const revInnerItem = revInnerBox.querySelectorAll('.rev-inner-item');

 // 각 게시글에 클릭 이벤트 리스너 추가
 for (let i = 0; i < revInnerItem.length; i++) {
   revInnerItem[i].addEventListener('click', function(e) {
     console.log(this.getAttribute('data-link'));
     const link = this.getAttribute('data-link');
     location.href= link;
   });
 } /* end table listener */

 //---------------------------프로필 이미지---------------------------//
        function uploadFile() {
            event.preventDefault(); //기본 동작 방지

            var uploadImgFile = $("#uploadImgFile").val(); //값 들고오기
            if(uploadImgFile != null) { //파일 존재시
                var ext = uploadImgFile.split('.').pop().toLowerCase(); //확장자 분리 및 소문자 변환
                if($.inArray(ext, ['jpg','jpeg','gif','png']) == -1) { //확장자가 배열에 존재하는데 확인
                  alert('이미지 파일만 업로드 할수 있습니다.');
                  return;
                }
            }
            document.forms['myPageForm'].submit(); //폼 제출
        }


        document.addEventListener('DOMContentLoaded', function() {
            const profileModal = new bootstrap.Modal(document.querySelector('.profileModal'));
            const secessionModal = new bootstrap.Modal(document.querySelector('.secessionModal'));
            const uploadModal = new bootstrap.Modal(document.querySelector(".uploadModal"));

            const modifyForm = document.querySelector('#modifyForm');
            const deleteForm = document.querySelector('#deleteForm');
            const inputPassword = document.querySelector("#inputPassword");

            // 프로필
            document.querySelector(".addFilesBtn").addEventListener('click', function(e) {
               console.log("test");
               uploadModal.show();
            });
            document.querySelector(".addFilesCloseBtn").addEventListener('click', function(e) {
               uploadModal.hide();
            });

            // 탈퇴
            document.querySelector(".secessionBtn").addEventListener('click', function(e) {
               console.log("test");
               secessionModal.show();
            });
            document.querySelector(".secCloseBtn").addEventListener('click', function(e) {
               secessionModal.hide();
            });

            // 열기
            document.querySelector('.profileBtn').addEventListener('click', function(e) {
                console.log("test");
               profileModal.show();
            });
            // 닫기
            document.querySelector('.closeBtn').addEventListener('click', function(e) {
               profileModal.hide();
            });

            // 수정
            document.querySelector('.modBtn').addEventListener('click', function(e) {
                e.preventDefault();
                e.stopPropagation();

                console.log("test 시작");

                fetch('/mypage/check', {
                   method: 'POST',
                   body: new FormData(modifyForm)
                }).then(response => {
                   console.log("response received");

                   if (response.ok) {
                       return response.text(); // 응답 텍스트를 반환
                   } else {
                       throw new Error("서버 응답에 문제가 있습니다.");
                   }
                }).then(responsetext => {
                   console.log("responsetext:", responsetext);

                   const modifyError = document.querySelector('#modify-error');

                   if (responsetext.length != 0) {
                       modifyError.innerHTML = `<div class="alert alert-danger">${responsetext}</div>`
                   }

                   // 여기서 errorMessage의 값을 확인하고 후속 로직을 실행할 수 있습니다.
                   if (responsetext.length === 0) {
                       //console.log("submit")
                       // 유효성 검사에 문제가 없을 경우 폼을 제출
                       modifyForm.submit();
                   }

                   }).catch(error => {
                       console.error("Error: ", error);
                    });
                });
            });



            document.querySelector(".secBtn").addEventListener('click', function(e) {
            e.preventDefault();
            e.stopPropagation();

            console.log("test 시작");
            fetch('/mypage/check', {
               method: 'POST',
               body: new FormData(deleteForm)

            }).then(response => {
               console.log("response received");

               if (response.ok) {
                   return response.text(); // 응답 텍스트를 반환
               } else {
                   throw new Error("서버 응답에 문제가 있습니다.");
               }
            }).then(responsetext => {
               console.log("responsetext:", responsetext);

               const deleteError = document.querySelector('#delete-error');

               if (responsetext.length != 0) {
                   deleteError.innerHTML = `<div class="alert alert-danger">${responsetext}</div>`
               }

               // 여기서 errorMessage의 값을 확인하고 후속 로직을 실행할 수 있습니다.
               if (responsetext.length === 0) {
                   //console.log("submit")
                   // 유효성 검사에 문제가 없을 경우 폼을 제출
                   deleteForm.submit();
               }

           }).catch(error => {
               console.error("Error: ", error);
           });
        });