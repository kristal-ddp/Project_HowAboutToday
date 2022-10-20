
const requestPay = () => {

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
            successRequest();
        } else {
            alert("실패!!!!!!!!!!!!!!!!!!!")
        }
    });
}

const successRequest = () => {
//    조금 더 보안을 지켜서 만들면 아임포트 webhook을 사용해서 이중?체크로
//    결제 정보 서버로 요청


//    필요한 정보


    console.log("리다이렉트")
    location.replace("/paymentSuccess");

}