const SERVER_URL = "http://133.186.241.167:8100";

// TODO #1 - DomContentLoaded 모든 HTML 문서가 로드된 상태 - DOM element에 접근 가능
window.addEventListener("DOMContentLoaded",function(){
    // TODO #2 - strict mode 설정
    'use strict';

    const loginForm = document.getElementById("login-form");

    // TODO #5 - login form validation check
    // 아이디, 비밀번호 공백 체크 및 focus 처리
    const validateLoginForm = function (form) {
        if (form['userId'].value.trim() === '') {
            alert('userId empty!');
            form['userId'].focus();
            return false;
        }

        if (form['userPassword'].value.trim() === '') {
            alert('userPassword empty!');
            form['userPassword'].focus();
            return false;
        }
    }

    // TODO #3 - loginForm submit event 등록
    // submit 이벤트는 로그인 버튼을 클릭했을 때 발생
    // 단, 로그인 버튼의 button type = 'submit' 이어야 함. (type = 'button'은 동작 X)
    loginForm.addEventListener("submit", async function (event) {
        event.preventDefault();

        // TODO #4 - loginForm validation check
        // event.target = form 자체를 의미
        if (validateLoginForm(event.target) === false) {
            return;
        }

        // 로그인 api 호출
        const userId = event.target['userId'].value;
        const userPassword = event.target['userPassword'].value;

        // TODO #5 - 로그인 처리
        let user = null;
        try {
            user = await doLogin(userId, userPassword);
            console.log(user);

            // TODO #6 - 로그인 정보 표시
            loginSuccess(user);
        } catch (e) {
            alert(e);
            return;
        }

        try {
            // TODO #7 - 장바구니 조회
            const items = await getCartItems(user.userId, user.cartId);

            // TODO #8 - 장바구니 표시
            displayItems(items);
        } catch(e) {
            alert(e);
            return;
        }


    });
});

function loginSuccess(user) {
    const loginWrapper = document.getElementById("login-wrapper");
    loginWrapper.setAttribute("style", "display: none;");

    const loginSuccess = document.getElementById("login-success");
    loginSuccess.setAttribute("style", "display: block;");

    const loginUserId = document.getElementById("login-userId");
    const loginUserName = document.getElementById("login-userName");
    const loginCartId = document.getElementById("login-cartId");

    loginUserId.innerText = user.userId;
    loginUserName.innerText = user.userName;
    loginCartId.innerText = user.cartId;
}

function displayItems(items) {
    const cartTable = document.getElementById("cart-table");
    const body = cartTable.getElementsByTagName("tbody")[0];
    const intl = new Intl.NumberFormat();

    for (const item of items) {
        const tr = document.createElement("tr");
        const td1 = document.createElement("td");
        const td2 = document.createElement("td");
        const td3 = document.createElement("td");
        const td4 = document.createElement("td");
        const td5 = document.createElement("td");

        td1.innerText = item.productId;
        td2.innerText = item.name;
        td3.innerText = intl.format(item.price);
        td4.innerText = intl.format(item.amount);
        td5.innerText = intl.format(item.totalPrice);

        tr.append(td1, td2, td3, td4, td5);
        body.append(tr);
    }
}

async function doLogin(userId, userPassword) {
    const url = SERVER_URL + "/api/users/login";

    const data = {
        userId: userId,
        userPassword: userPassword
    }

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }

    const response = await fetch(url, options);

    if (!response.ok) {
        throw new Error('login error');
    }

    return await response.json();
}

async function getCartItems(userId, cartId) {
    const url = SERVER_URL + "/api/nhnmart/shopping-cart/" + cartId;

    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'X-USER-ID': userId
        }
    }

    const response = await fetch(url, options);
    if (!response.ok) {
        throw new Error("cart-api Error");
    }

    return await response.json();
}