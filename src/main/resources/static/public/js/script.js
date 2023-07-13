let icon_eye = document.querySelector('.fa-eye');
let password = document.querySelector('#password');

function showPassword(e) {
    if(e.currentTarget.classList.contains("fa-eye-slash")) {
        icon_eye.classList.replace("fa-eye-slash", "fa-eye");
        password.setAttribute("type", "password");
    } else {
        icon_eye.classList.replace("fa-eye", "fa-eye-slash");
        password.setAttribute("type", "text");
    }
}

icon_eye.addEventListener("click", (e) => showPassword(e));