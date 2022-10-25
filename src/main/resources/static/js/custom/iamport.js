
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
        merchant_uid : merchant_uid,
        name : name,
        amount : amount,
        // amount : 1,
        buyer_email : buyer_email,
        buyer_name : buyer_name,
        buyer_tel : buyer_tel,
        buyer_addr : buyer_addr,
        buyer_postcode : buyer_postcode,
    }, function (rsp) { // callback
        if (rsp.success) {
            successRequest(rsp.merchant_uid);

        } else {
            alert("실패!!!!!!!!!!!!!!!!!!!")
        }
    });
}

//성공시 실행할 함수
const successRequest = (merchant_uid) => {
//    조금 더 보안을 지켜서 만들면 아임포트 webhook을 사용해서 이중?체크로
//    결제 정보 서버로 요청

    const successForm = document.querySelector('form');

    const hiddenField = document.createElement('input');

    hiddenField.setAttribute('type', 'hidden'); //값 입력
    hiddenField.setAttribute('name', 'merchantId');
    hiddenField.setAttribute('value', merchant_uid);
    successForm.appendChild(hiddenField);

    successForm.action = '/orders/paymentSuccess';
    successForm.submit();
}


const cancelConfirm = (ordersNum) => {

    confirm("정말 삭제하시겠습니까?" + ordersNum + "pk") && runCancel(ordersNum);


}

const runCancel = (ordersNum) =>{
    alert("삭제되었습니다.")
    location.href="/orders/deleteorders/" + ordersNum;

    const form = document.createElement('form');

    form.setAttribute('method', 'post'); //POST 메서드 적용
    form.setAttribute('action', "/orders/deleteorders/");	// 데이터를 전송할 url
    const hiddenField = document.createElement('input');
    hiddenField.setAttribute('type', 'hidden'); //값 입력
    hiddenField.setAttribute('name', 'ordersNum');
    hiddenField.setAttribute('value', ordersNum);
    form.appendChild(hiddenField);
    document.body.appendChild(form);
    form.submit();	// 전송~

}



