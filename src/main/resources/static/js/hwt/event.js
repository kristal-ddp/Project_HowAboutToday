const main = {
    init : function() {
        $('#btn-comment-add').on('click', () => {
            this.commentSave();
        });
    },
    
     // 댓글 저장
    commentSave : function () {

        const data = {
            eventNum: $('#eventNum').val(),
            commentContent: $('#commentContent').val()
        }
 
        // 공백 및 빈 문자열 체크
        if (!data.commentContent) {

            Swal.fire({
                icon: 'error',
                title: '댓글을 입력해주세요.',
            });

            return false;

        } else {

            $.ajax({

                type: 'POST',
                url: '/event/' + data.eventNum + '/comments',
                dataType: 'text',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)

            }).done(function () {

                Swal.fire({
                    icon: 'success',
                    title: '댓글이 등록되었습니다.',
                }).then(function(result) {
                    if(result) {
                        window.location.reload();
                    }
                });
            }).fail(function (error) {

                Swal.fire({
                    icon: 'success',
                    title: JSON.stringify(error),
                });
            });
        }
    }
};
 
main.init();