
	//비밀번호 확인
$(function(){
    $("#alert-success").hide();
    $("#alert-danger").hide();
    $("input").keyup(function(){
        var pwd=$("#pwd").val();
        var pwdCheck=$("#pwdCheck").val();
        if(pwd != "" || pwdCheck != ""){
            if(pwd == pwdCheck){
                $("#alert-success").show();
                $("#alert-danger").hide();
                $("#btn-member-modify").removeAttr("disabled");
            }else{
                $("#alert-success").hide();
                $("#alert-danger").show();
                $("#btn-member-modify").attr("disabled", "disabled");
            }
        }
    });
});



const main = {
    init : function() {
        const _this = this;
        ...
        $('#btn-modify').on('click', function () {
            _this.modify();
        });
    },
    
    ...
    modify : function () {
        const data = {
            memberNum: $('#memberNum').val(),
            memberTel: $('#memberTel').val(),
            nickname: $('#nickname').val(),
            pwd: $('#pwd').val()
        }
        if(!data.nickname || data.nickname.trim() === "" || !data.pwd || data.pwd.trim() === "" || !data.memberTel || data.memberTel.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        } else if(?=.*[0-9])(?=.*[a-z])(?=.*\W)(?=\S+$).{6,12}/.test(data.password)) {
            alert("영문자와 숫자, 특수문자가 1개 이상 포함된 6자~12자의 비밀번호여야 합니다.");
            $('#pwd').focus();
            return false;
        } else if(!/^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$/.test(data.nickname)) {
            alert("이름은 특수문자를 제외한 2~10자리여야 합니다.");
            $('#nickname').focus();
            return false;
        }
        const con_check = confirm("수정하시겠습니까?");
        if (con_check === true) {
            $.ajax({
                type: "PUT",
                url: "/api/member",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
 
            }).done(function () {
                alert("회원수정이 완료되었습니다.");
                window.location.href = "/";
 
//            }).fail(function (error) {
//                if (error.status === 500) {
//                    alert("이미 사용중인 닉네임 입니다.");
//                    $('#nickname').focus();
                } else {
                    alert(JSON.stringify(error));
                }
            });
        }
    }
};
 
main.init();