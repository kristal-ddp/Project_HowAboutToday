const btnOpenPopup = document.querySelector('#couponSelect');
const couponApply = document.querySelectorAll("button[name='couponApply']");


btnOpenPopup.addEventListener('click', (event) => {
    event.preventDefault();

    //최대한 jquery안쓰고 싶지만 부트스트랩 자체가 jquery로 되어있으니... 방법이 없다!
    $('#exampleModalCenteredScrollable').modal("show");
});

couponApply.forEach((button) => {
    button.addEventListener('click', (event) => {
        const couponNum = event.target.value;


        fetch("/couponUsed", {
            method: 'POST', // *GET, POST, PUT, DELETE 등
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
            },
            body: JSON.stringify({
                couponNum: couponNum,   //pk값
                originPrice: originPrice,

            }), // body의 데이터 유형은 반드시 "Content-Type" 헤더와 일치해야 함
        })
            .then((response) => {
                console.log("성공")
                return response.json()
            })
            .then((result) => {
                console.log(result.isPossible)
                console.log(result.resultPrice)
                console.log(result.discountPrice)
                console.log(result.useCouponNum)

                const {isPossible, resultPrice, discountPrice, useCouponNum} = result;

                if (isPossible){
                    amount = resultPrice;
                    document.querySelector("#discountPrice").innerText = `쿠폰 할인: ${discountPrice == 0 ? '':'-'}${discountPrice.toLocaleString('ko-KR')}원`;
                    document.querySelector("#discountValue").value = discountPrice;
                    console.log(document.querySelector("#discountValue"))
                    document.querySelector("#totalPrice").innerText = (resultPrice).toLocaleString('ko-KR') + '원';
                    document.querySelector("#useCouponNum").value = useCouponNum;
                    $('#exampleModalCenteredScrollable').modal("hide");
                }
                else{
                    alert(result.failReason);
                }
            })
            .catch((error) => {
                alert(error)
            })

        // console.log("어마운트" + amount);
        // amount = 10000;
        // console.log("바꾼 어마운트" + amount);
        // document.querySelector("#totalPrice").innerText = (10000).toLocaleString('ko-KR') + '원';

    })
})



