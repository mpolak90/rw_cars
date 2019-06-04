document.addEventListener("DOMContentLoaded", function () {
    let numbers = document.getElementsByClassName("number");

    for (let i = 0; i < numbers.length; i++) {
        numbers[i].innerHTML = Number(numbers[i].innerHTML).toLocaleString();
    }

    let navItem = document.getElementsByClassName("nav-item");
    let navLink = document.getElementsByClassName("nav-link");
    let current = document.getElementById("current");

    for (let i = 0; i < navItem.length; i++) {
        if (current.innerHTML === navItem[i].dataset['name']) {
            navItem[i].classList.add('active');
            navLink[i].innerHTML = navLink[i].innerHTML + "<span class='sr-only'>(current)</span>";
            navLink[i].removeAttribute('href');
            navLink[i].setAttribute('style','color: #f9f0db');
        } else {
            navLink[i].setAttribute('style','color: #f5e794');
        }
    }

    let logOut = document.getElementsByClassName("logout");
    logOut[0].setAttribute('style','color:red')
});