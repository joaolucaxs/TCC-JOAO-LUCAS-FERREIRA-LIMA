updateArtistDetails();

function updateArtistDetails() {

	const editButtonLink = document.getElementById("editButtonLink");
	const select = document.getElementById("form-artesao");
	const nomeInput = document.getElementById("validationServerNomeArtesao");
	const apelidoInput = document.getElementById("validationServerApelido");
	const telefoneInput = document.getElementById("validationServerTelefone");
	const emailInput = document.getElementById("validationServerEmail");
	const cidadeInput = document.getElementById("validationServerCidade");
	const formulario = document.getElementById("formArtista")
	const idInput = document.getElementById("validationServerId");

	const selectedOption = select.options[select.selectedIndex];
	const isArtistaSelected = selectedOption.value !== "0";

	nomeInput.readOnly = isArtistaSelected;
	apelidoInput.readOnly = isArtistaSelected;
	telefoneInput.readOnly = isArtistaSelected;
	emailInput.readOnly = isArtistaSelected;
	cidadeInput.readOnly = isArtistaSelected;
	idInput.readOnly = true;

	if (isArtistaSelected) {
		const nome = selectedOption.getAttribute("data-nome");
		const apelido = selectedOption.getAttribute("data-apelido");
		const telefone = selectedOption.getAttribute("data-telefone");
		const email = selectedOption.getAttribute("data-email");
		const cidade = selectedOption.getAttribute("data-cidade");
		const id = selectedOption.getAttribute("data-id");

		nomeInput.value = nome;
		apelidoInput.value = apelido;
		telefoneInput.value = telefone;
		emailInput.value = email;
		cidadeInput.value = cidade;
		idInput.value = id;
		editButtonLink.style.display = "block";
		formulario.classList.add('was-validated')
		editButtonLink.href = `/pecas/novaObra/associarArtista/artista/editarArtista/${id}`;

	} else {
		nomeInput.value = "";
		apelidoInput.value = "";
		telefoneInput.value = "";
		emailInput.value = "";
		cidadeInput.value = "";
		idInput.value = null;
		formulario.classList.remove('was-validated')
		editButtonLink.style.display = "none";
		editButtonLink.href = `/pecas`;

	}

}

updateEditArtistDetails();

function updateEditArtistDetails() {

	const editButtonLink = document.getElementById("editButtonLink");
	const select = document.getElementById("form-artesao");
	const nomeInput = document.getElementById("validationServerNomeArtesao");
	const apelidoInput = document.getElementById("validationServerApelido");
	const telefoneInput = document.getElementById("validationServerTelefone");
	const emailInput = document.getElementById("validationServerEmail");
	const cidadeInput = document.getElementById("validationServerCidade");
	const idObra = document.getElementById("formArtista").getAttribute("data-idObra");
	const formulario = document.getElementById("formArtista")
	const idInput = document.getElementById("validationServerId");

	const selectedOption = select.options[select.selectedIndex];
	const isArtistaSelected = selectedOption.value !== "0";

	nomeInput.readOnly = isArtistaSelected;
	apelidoInput.readOnly = isArtistaSelected;
	telefoneInput.readOnly = isArtistaSelected;
	emailInput.readOnly = isArtistaSelected;
	cidadeInput.readOnly = isArtistaSelected;
	idInput.readOnly = true;

	if (isArtistaSelected) {
		const nome = selectedOption.getAttribute("data-nome");
		const apelido = selectedOption.getAttribute("data-apelido");
		const telefone = selectedOption.getAttribute("data-telefone");
		const email = selectedOption.getAttribute("data-email");
		const cidade = selectedOption.getAttribute("data-cidade");
		const id = selectedOption.getAttribute("data-id");

		nomeInput.value = nome;
		apelidoInput.value = apelido;
		telefoneInput.value = telefone;
		emailInput.value = email;
		cidadeInput.value = cidade;
		idInput.value = id;
		editButtonLink.style.display = "block";
		formulario.classList.add('was-validated')
		editButtonLink.href = `/pecas/editarObra/${idObra}/associarArtista/artista/editarArtista/${id}`;

	} else {
		nomeInput.value = "";
		apelidoInput.value = "";
		telefoneInput.value = "";
		emailInput.value = "";
		cidadeInput.value = "";
		idInput.value = null;
		editButtonLink.style.display = "none";
		formulario.classList.remove('was-validated')
		editButtonLink.href = `/pecas`;
	}
}

function resetValidation(form) {
	form.classList.remove('was-validated');
}

function enableValidation(form) {
	form.classList.add('was-validated');
	if (!form.checkValidity()) {
		event.preventDefault();
		event.stopPropagation();
	}
}

document.getElementById('salvarOBra').addEventListener('click', function() {
	enableValidation(document.getElementById('formArtista'));
});

document.getElementById('formArtista').addEventListener('reset', function() {
	resetValidation(this);
});