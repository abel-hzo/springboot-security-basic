let body = document.querySelector("body");

let id_span = document.querySelector(".id-span");
let username_span = document.querySelector(".username-span");
let birthday_span = document.querySelector(".birthday-span");
let created_span = document.querySelector(".created-span");
let photo = document.querySelector("#photo");

async function getProfile() {
	/*fetch('/user/profile')
	.then(response => response.json())
	.then(data => console.log(data));*/

	let response = await fetch('/api/user/profile');

	let data = await response.json();

	console.log(data);

	id_span.innerHTML = "ID: " + data.body.idUser;
	username_span.innerHTML = "Username: " + data.currentUser;
	birthday_span.innerHTML = "Birthday: " + data.body.birthday;
	created_span.innerHTML = "Created: " + data.body.creationDate;

	photo.src = "data:image/png;base64, " + data.body.photo;
}

body.addEventListener("load", getProfile());

/*=========================== LOAD IMAGE ============================*/

let fa_image = document.querySelector(".fa-image");
let file = document.querySelector("#file");
let file_name = document.querySelector(".file-name");

function loadImage() {

	let value = file.files[0].name;
	file_name.innerHTML = value;

	const url = URL.createObjectURL(file.files[0]);
	photo.src = url;
}

file.addEventListener("change", (e) => loadImage(e));

/*============================ UPLOAD IMAGE ========================== */

let fa_upload = document.querySelector(".fa-upload");
let formUploadFile = document.querySelector("#formUploadFile");

async function uploadFile(e) {
	e.preventDefault();

	let formData = new FormData(formUploadFile);
	formData.append("idUser", id_span.innerHTML.substring(4));

	let response = await fetch('/api/user/photo', {
		method: 'PUT',
		body: formData
	});

	let data = await response.json();

	console.log(data);

	location.reload();
}

formUploadFile.addEventListener("submit", (e) => uploadFile(e));