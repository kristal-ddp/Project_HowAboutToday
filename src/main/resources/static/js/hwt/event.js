// 데이터 전송 객체 생성
const comment = {

    // 이벤트 등록
    init : function() {

        const _this = this;
        const comments =

        // 댓글 등록
        $('#btn-comment-add').on('click', () => {
            _this.commentSave();
        });

        // 댓글 수정
        document.querySelectorAll('#edit-comment').forEach(function (item) {
            item.addEventListener('click', function () { // 버튼 클릭 이벤트 발생 시
                const form = this.closest('form'); // 버튼의 가장 가까운 부모의 Element(form)를 반환하고
                _this.commentEdit(form); // 해당 form으로 업데이트를 수행
            });
        });

    },
    
     // 댓글 등록
    commentSave : function () {

        // 데이터
        const data = {
            eventNum: $('#eventNum').val(),
            commentContent: $('#commentContent').val()
        }
 
        // 공백 체크
        if (!data.commentContent) {

            Swal.fire({
                icon: 'error',
                title: '댓글을 입력해주세요.',
                confirmButtonText: '확인',
            });
            return false;

        } else {

            $.ajax({

                type: 'POST',
                url: `/event/${data.eventNum}/comments`,
                dataType: 'text',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)

            }).done(function () {

                Swal.fire({
                    icon: 'success',
                    title: '댓글이 등록되었습니다.',
                confirmButtonText: '확인',
                }).then(function(result) {
                    if(result) {
                        window.location.reload();
                    }
                });

            }).fail(function (error) {

                Swal.fire({
                    icon: 'error',
                    title: JSON.stringify(error),
                });
            });
        }
    },

    // 댓글 수정
    commentEdit : function (form) {

        // 데이터
        const data = {
            eventNum: form.querySelector('#edit-eventNum').value,
            commentNum: form.querySelector('#edit-commentNum').value,
            commentContent: form.querySelector('#edit-commentContent').value,
        }

        // 공백 체크
        if (!data.commentContent) {

            Swal.fire({
                icon: 'error',
                title: '댓글을 입력해주세요.',
            });
            return false;
        }

        $.ajax({
            type: 'PUT',
            url: `/event/${data.eventNum}/comments/${data.commentNum}`,
            dataType: 'text',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)

        }).done(function () {

            Swal.fire({
                icon: 'success',
                title: '댓글이 수정되었습니다.',
            }).then(function(result) {
                if(result) {
                    window.location.reload();
                }
            });

        }).fail(function (error) {

            Swal.fire({
                icon: 'error',
                title: JSON.stringify(error),
            });
        });
    },

    //  댓글 삭제
    commentDelete : function (eventNum, commentNum) {

        $.ajax({

            type: 'DELETE',
            url: `/event/${eventNum}/comments/${commentNum}`,
            dataType: 'text',

        }).done(function () {

            Swal.fire({
                icon: 'success',
                title: '댓글이 삭제되었습니다.',
                confirmButtonText: '확인',
            }).then((result) => {
                if(result) {
                    window.location.reload();
                }
            });

        }).fail(function (error) {

            Swal.fire({
                icon: 'error',
                title: JSON.stringify(error),
            });
        });
    }
};

// 객체 초기화
comment.init();