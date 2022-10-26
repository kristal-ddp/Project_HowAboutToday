/* iamport 결제 요청 */
const requestPay = () => {

    const buyer_name = document.querySelector(`#name`).value;
    const buyer_tel = document.querySelector(`#tel`).value;

    console.log(buyer_name)
    console.log(buyer_tel)

    window.IMP.init('imp73826618');
    window.IMP.request_pay({
        pg: "kcp",
        pay_method: pay_method,
        merchant_uid: merchant_uid,
        name: name,
        amount: amount,
        // amount : 1,
        buyer_email: buyer_email,
        buyer_name: buyer_name,
        buyer_tel: buyer_tel,
        buyer_addr: buyer_addr,
        buyer_postcode: buyer_postcode,
    }, function (rsp) { // callback
        if (rsp.success) {
            successRequest(rsp.imp_uid, rsp.merchant_uid);

        } else {
            alert("실패!!!!!!!!!!!!!!!!!!!")
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
    confirm("정말 취소하시겠습니까") && runCancel();
}

const runCancel = () => {
    const merchantId = document.querySelector("#merchantId").value;
    const totalPrice = document.querySelector("#totalPrice").value;
    // const imp_uid = document.querySelector("#imp_uid").value;

    alert(merchantId);
    /** 위 데이터 3개는 ordersNum으로 서버에서 데이터베이스에서 읽는 방식이 더 올바른 방식일듯 **/

    const header = $("meta[name='_csrf_header']").attr('content');
    const token = $("meta[name='_csrf']").attr('content');

    $.ajax({
        type: "POST",
        url: "/orders/deleteorders",
        contentType: 'application/json; charset=utf-8',
        dataType: "json",
        data: JSON.stringify({
            merchant_uid: merchantId,
            cancel_request_amount: totalPrice,
            reason: "테스트 결제 환불",
        }),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
    }).done((response) => {
        alert(response.merchant_uid);
        alert("성공")
    }).fail(function (error) {
        alert("실패")
        alert(JSON.stringify(error));
    });
}


function cancelPay() {


}


