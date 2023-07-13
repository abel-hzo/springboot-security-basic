let fa_close = document.querySelector(".fa-close");
let malla_modal = document.querySelector(".malla-modal");
let modal_form = document.querySelector(".modal-form");

function closeModal(e) {
    malla_modal.style.display = 'none';
    modal_form.style.display = 'none';

    formModifyUser.reset();
}

fa_close.addEventListener("click", (e) => closeModal(e));

/*============================ END CLOSE MODAL ============================= */

let formModifyUser = document.querySelector("#formModifyUser");

async function openModal(e) {
    malla_modal.style.display = 'block';
    modal_form.style.display = 'block';

    formModifyUser.idUser.value = e.currentTarget.dataset.id;

    let response = await fetch('/api/user/id/'+formModifyUser.idUser.value);

    let data = await response.json();

    formModifyUser.username.value = data.body.username;
    formModifyUser.password.value = data.body.password;
    formModifyUser.birthday.value = data.body.birthday;
    data.body.userRolsDTO.forEach(roles => {
        if(roles.rolDTO.idRol === 1)
            formModifyUser.user.checked = true;
        if(roles.rolDTO.idRol === 2)    
            formModifyUser.admin.checked = true;
    });

}   

/*============================ END OPEN MODAL ============================= */

let idRolUser = document.querySelector("#idRolUser");
let idRolAdmin = document.querySelector("#idRolAdmin");

async function sendRoles(e) {
    
    let formData = new FormData();
    formData.append("userRolsDTO[0].userDTO.idUser", formModifyUser.idUser.value);
    formData.append("userRolsDTO[0].rolDTO.idRol", e.currentTarget.value);

    alert("PERMISOS MODIFICADOS: " + formData.get("userRolsDTO[0].userDTO.idUser") +" - "+ formData.get("userRolsDTO[0].rolDTO.idRol"));

    if(e.currentTarget.checked) {

        let response = await fetch('/api/user/rol/add', {
            method: 'POST',
            body: formData
        });

        let data = await response.json();

        console.log(data);
    } else {

        let response = await fetch('/api/user/rol/remove', {
            method: 'POST',
            body: formData
        });

        let data = await response.json();

        console.log(data);      

    }
}

idRolUser.addEventListener("change", (e) => sendRoles(e));
idRolAdmin.addEventListener("change", (e) => sendRoles(e));

/*=============================== END CHANGE CHECKBOX ============================= */

 let tbody = document.querySelector("tbody");

async function loadTable(e) {
    let response = await fetch('/api/user/all');

    let data = await response.json();

    console.log(data);

    let row = "<tr>";
    data.body.forEach(user => {
        row += "<td>" + user.idUser + "</td>";
        row += "<td>" + user.username + "</td>";
        row += "<td>" + user.birthday + "</td>";
        row += "<td>" + user.creationDate + "</td>";
        row += "<td><span data-id=" + user.idUser + " class='fa-solid fa-edit' onclick='openModal(event)'></span></td>";
        row += "</tr>";
    });

    tbody.innerHTML += row;

}

window.addEventListener("load", (e) => loadTable(e));

/*================================ END LOAD TABLE ============================== */

async function modifyUser(e) {
    e.preventDefault();

    let formData = new FormData(formModifyUser);

    formData.delete('user');
    formData.delete('admin');

    let response = await fetch('/api/user/modify', {
        method: 'PUT',
        body: formData
    });

    let data = await response.json();

    console.log(data);


    formModifyUser.reset();
    malla_modal.style.display = 'none';
    modal_form.style.display = 'none';

}

formModifyUser.addEventListener("submit", (e) => modifyUser(e));