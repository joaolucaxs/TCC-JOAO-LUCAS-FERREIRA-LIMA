(() => {
	'use strict'

	const forms = document.querySelectorAll('.needs-validation')

	Array.from(forms).forEach(form => {
		form.addEventListener('submit', event => {
			if (!form.checkValidity()) {
				event.preventDefault()
				event.stopPropagation()
			}

			form.classList.add('was-validated')
		}, false)
	})
})()

document.addEventListener("DOMContentLoaded", function() {
	const imageInput = document.getElementById("validationServerImagemObra");
	const previewImage = document.getElementById("imagem-capa");
	const oldImageUrl = previewImage.src;

	imageInput.addEventListener("change", function() {
		const file = imageInput.files[0];

		if (file) {
			const reader = new FileReader();
			reader.onload = function(event) {
				previewImage.src = event.target.result;
				previewImage.style.display = "block";
			};
			reader.readAsDataURL(file);
		} else {
			previewImage.src = oldImageUrl;
			previewImage.style.display = "block";
		}
	});

	const uploadForm = document.getElementById("uploadForm");
	uploadForm.addEventListener("submit", function(event) {
		event.preventDefault();
	});
});

function formatDate(dateString) {
	const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
	return new Date(dateString).toLocaleDateString('pt-BR', options);
}

const dateInput = document.getElementById('validationServerDataAquisicao');

dateInput.value = formatDate(dateInput.value);

document.addEventListener("DOMContentLoaded", function() {
	const imageInput = document.getElementById("validationServerImagemObra");
	const previewImage = document.getElementById("imagem-capa");
	const oldImageUrl = previewImage.src;

	imageInput.addEventListener("change", function() {
		const file = imageInput.files[0];

		if (file) {
			const reader = new FileReader();
			reader.onload = function(event) {
				previewImage.src = event.target.result;
				previewImage.style.display = "block";
			};
			reader.readAsDataURL(file);
		} else {
			previewImage.src = oldImageUrl;
			previewImage.style.display = "block";
		}
	});

	const uploadForm = document.getElementById("uploadForm");
	uploadForm.addEventListener("submit", function(event) {
		event.preventDefault();

	});
});