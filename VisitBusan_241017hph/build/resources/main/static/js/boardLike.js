
console.log("boardLike.js start");
console.log('board_id2',board_id);

async function getBoardLike(board_id) {
    console.log("==> getBoardLike board_id: ",board_id);

    const response = await axios.get(`/boardLike/list/${board_id}`);
    console.log("==> getBoardLike response: ",response); // 서버로부터 응답받은 객체
    console.log();
    return response.data;
}

// 1. 게시글에 대한 댓글 List, 인자값이 여러개 전달 받을 경우 => {데이터1, 데이터2, ...}
async function getList(board_id, page, size, goLast) {
    console.log("==> getList board_id: ",board_id);

    const result = await axios.get(`/boardLike/list/${board_id}`, { params: {page, size} });
    console.log("==> getList result: ",result); // 서버로부터 응답받은 객체

    if (goLast) {
        // 댓글 총 개수
        const total = result.data.total;
        // 댓글 마지막 페이지 계산 = 댓글 총 개수 / 페이지 사이즈 => 자리올림
        const lastPage = parseInt(Math.ceil(total/size));
        return getList(board_id, lastPage, size);
    }
    return result.data;
}

// 2. 게시글에 대한 댓글 등록
async function addReply(boardLikeObj) {
    const response = await axios.post(`/boardLike/`, boardLikeObj);

    console.log("addReply response: ", response.data);
    return response.data;
}

// 3. 게시글에 대한 댓글 조회
async function getBoardLike(id) {
    const response = await axios.get(`/boardLike/${id}`);
    return response.data;
}

// 4. 게시글에 대한 댓글 수정
async function modifyReply(boardLikeObj) {
    const response = await axios.put(`/boardLike/${boardLikeObj.id}`,boardLikeObj);
    return response.data;
}

// 5. 게시글에 대한 댓글 삭제
async function removeReply(id) {
    const response = await axios.delete(`/boardLike/${id}`);
    return response.data;
}
