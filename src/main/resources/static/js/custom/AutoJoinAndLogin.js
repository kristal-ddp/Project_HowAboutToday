const autoJoin = () => {
    //alert("조인")

    const joinForm = document.querySelector("#joinForm");

    const nick = document.querySelector("#inputNickname");
    const email = document.querySelector("#inputEmail");
    const pwd = document.querySelector("#inputPassword");

    nick.value = '불사조';
    email.value = 'how@naver.com';
    pwd.value = '1111111a!';

    // event.preventDefault();
    joinForm.submit();
}

const autoLogin = (event) => {
    const loginForm = document.querySelector("#loginForm");
    const email = document.querySelector("#email");
    const pwd = document.querySelector("#pwd");

    email.value = 'how@naver.com';
    pwd.value = '1111111a!';

    //event.preventDefault();
    //alert("잘 들어오나?")
    loginForm.submit()
}
