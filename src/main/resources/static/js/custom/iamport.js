
function requestPay() {

    window.IMP.init('imp73826618');
    window.IMP.request_pay({
        pg: "kcp",
        pay_method: "card",
        merchant_uid : 'merchant_'+new Date().getTime(),
        name : '결제테스트',
        amount : 140,
        buyer_email : 'iamport@siot.do',
        buyer_name : '구매자',
        buyer_tel : '010-4502-0614',
        buyer_addr : '서울특별시 강남구 삼성동',
        buyer_postcode : '123-456'
    }, function (rsp) { // callback
        if (rsp.success) {
            alert("결제 성공")
        } else {
            alert("실패!!!!!!!!!!!!!!!!!!!")
        }
    });
}