/* iamport 결제 요청 */
const requestPay = () => {
    const buyer_name = document.querySelector(`#name`).value;
    const buyer_tel = document.querySelector(`#tel`).value;

    if(!checkName(buyer_name) || !checkTel(buyer_tel) ){
        return;
    }

    window.IMP.init('imp73826618');
    window.IMP.request_pay({
        pg: "kcp",
        pay_method: pay_method,
        merchant_uid: merchant_uid,
        name: name,
        amount: amount,
        buyer_email: buyer_email,
        buyer_name: buyer_name,
        buyer_tel: buyer_tel,
        buyer_addr: buyer_addr,
        buyer_postcode: buyer_postcode,
    }, function (rsp) { // callback
        if (rsp.success) {
            Swal.fire({
                icon:'success',
                title:'결제 성공 ',

            }).then(result => {
                successRequest(rsp.imp_uid, rsp.merchant_uid);
            });


        } else {
            swalAlert("결제 실패")
        }
    });
}

//성공시 실행할 함수
const successRequest = (imp_uid, merchant_uid) => {
//    조금 더 보안을 지켜서 만들면 아임포트 webhook을 사용해서 이중?체크로
//    결제 정보 서버로 요청

    const successForm = document.querySelector("form[name='myForm']");

    const hiddenImp = document.createElement('input');
    const hiddenMerchant = document.createElement('input');

    hiddenImp.setAttribute('type', 'hidden'); //값 입력
    hiddenImp.setAttribute('name', 'imp_uid');
    hiddenImp.setAttribute('value', imp_uid);
    successForm.appendChild(hiddenImp);

    hiddenMerchant.setAttribute('type', 'hidden'); //값 입력
    hiddenMerchant.setAttribute('name', 'merchantId');
    hiddenMerchant.setAttribute('value', merchant_uid);
    successForm.appendChild(hiddenMerchant);

    successForm.submit();
}

const cancelConfirm = () => {
    confirm("정말 취소하시겠습니까?") && runCancel();
}

const runCancel = () => {
    LoadingWithMask();
    const merchantId = document.querySelector("#merchantId").value;
    const totalPrice = document.querySelector("#totalPrice").value;
    // const imp_uid = document.querySelector("#imp_uid").value;

    // alert(merchantId);
    /** csrf가 활성화 되어 있으면 넣어줘야하는 코드 **/
    /** 이 코드와 더불어 header부분에도 넣어줘야한다. **/

    // const header = $("meta[name='_csrf_header']").attr('content');
    // const token = $("meta[name='_csrf']").attr('content');

    $.ajax({
        type: "POST",
        url: "/orders/cancelorders",
        contentType: 'application/json; charset=utf-8',
        dataType: "json",
        data: JSON.stringify({
            merchant_uid: merchantId,
            cancel_request_amount: totalPrice,
            reason: "단순 변심",
        }),
        // beforeSend: function (xhr) {
        //     xhr.setRequestHeader(header, token);
        // },
    }).done((response) => {
        alert("예약을 취소했습니다.")
        closeLoadingWithMask();
        window.location.href = '/user-dashboard-booking';
    }).fail(function (error) {
        alert("실패")
        closeLoadingWithMask();
        alert(JSON.stringify(error));
    });
}

const checkName = (inputName) => {

    if (inputName.length < 2) {
        swalAlert("이름은 두글자 이상 입력해주세요.");
        return false;
    }
    return true;
}

const checkTel = (inputTel) => {
    const EMPTY_STRING = "";
    let phoneNumber = inputTel;

    phoneNumber = phoneNumber.replaceAll(/[-\s]/gi, "");
    const regex = /^01(0[\d]{4}|[16789][\d]{3,4})[\d]{4}$/;

    if (phoneNumber == EMPTY_STRING) {
        swalAlert("전화번호를 입력해주세요.");
        return false;
    } else if (!regex.test(phoneNumber)) {
        swalAlert("올바르지 않은 전화번호입니다.");
        return false;
    }
    return true;
}

const swalAlert = (message) => {
    Swal.fire({
        icon : 'error',
        title: message
    })
}

const swalWarning = (message) => {
    Swal.fire({
        icon : 'warning',
        title: message
    })
}
