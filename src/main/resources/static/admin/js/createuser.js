let form_save = document.querySelector("#form-save");


async function saveUser(e) {
    e.preventDefault();

    let formData = new FormData(form_save);

    let response = await fetch('/api/user/save', {
        method: 'POST',
        body: formData
    });

    let data = await response.json();

    console.log(data);

    modal.style.display = 'grid';
    malla_modal.style.display = 'block';
    
    document.querySelector(".id-user").innerHTML = data.body.idUser;
    document.querySelector(".username").innerHTML = data.body.username;
    document.querySelector(".birthday").innerHTML = data.body.birthday;
    document.querySelector(".creation-date").innerHTML = data.body.creationDate;
    document.querySelector("#photo").setAttribute("src", "data:image/png;base64, " +data.body.photo);

    form_save.reset();
}

form_save.addEventListener("submit", (e) => saveUser(e));


let fa_close = document.querySelector(".fa-close");
let malla_modal = document.querySelector(".malla-modal");
let modal = document.querySelector(".modal");

function closeModal() {
    malla_modal.style.display = 'none';
    modal.style.display = 'none';
}


fa_close.addEventListener("click", (e) => closeModal());