const ratingStar = document.querySelectorAll("input[name='ratingStar']");
const reviewWrite = document.querySelector("#reviewWrite");
let clickRate = 5;

ratingStar.forEach((star) => {
    star.addEventListener('click', (event) => {
        clickRate = event.target.value;
        console.log(clickRate);
        console.log(star.checked);

        const innerRatingStar = document.querySelectorAll("input[name='ratingStar']");
        innerRatingStar.forEach((star) => {
            if(star.value <= clickRate){
                star.checked = true;
            }
            else {
                star.checked = false;
            }
        });
    })
})

reviewWrite.addEventListener('click', (event) => {
    const roomNum = document.querySelector("#roomNum").value;
    const reviewMemberName = document.querySelector("#reviewMemberName");
    const ordersDetailNum = document.querySelector("#ordersDetailNum");

    const reviewContent = document.querySelectorAll("textarea[name='reviewContent']")[0];

    const odNum = ordersDetailNum.value;
    const name = reviewMemberName.value;
    const content = reviewContent.value;

    if(name === "" || content === ""){
        alert("성함과 내용을 작성해주세요.")
        return;
    }
    const data = {
        ordersDetailNum:odNum,
        roomNum : roomNum,
        name: name,
        content: content,
        rating: clickRate
    }

    fetch("/roomReview/save", {
        method: 'post',
        headers : {
            'Content-Type' : 'application/json; charset=utf-8'
        },
        body:JSON.stringify(data)
    })
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data.code);
            console.log(typeof data.code);
            checkReview(data.code);
            window.location.href = window.location.href;
        })
        .catch(() => {
            alert("리뷰 저장 실패");
        })
})

const REVIEW_RESPONSE_CODE = {
    NOT_MEMBER : "NOT_MEMBER",
    NOT_RESERVE:"NOT_RESERVE",
    OVER_TWO_WEEKS:"OVER_TWO_WEEKS",
    WRITE_POSSIBLE:"WRITE_POSSIBLE"
}


const checkReview = (code) => {
    let message = '작성 완료.';

    switch (code){
        case REVIEW_RESPONSE_CODE.NOT_MEMBER:
            message = '회원가입이 필요합니다.'
            break;
        case REVIEW_RESPONSE_CODE.NOT_RESERVE:
            message = '예약하지 않은 객실입니다.'
            break;
        case REVIEW_RESPONSE_CODE.OVER_TWO_WEEKS:
            message = '리뷰 작성 기간은 이용 후 2주 이내입니다.'
            break;
        default:
        // REVIEW_RESPONSE_CODE.WRITE_POSSIBLE
    }

    return message;
}