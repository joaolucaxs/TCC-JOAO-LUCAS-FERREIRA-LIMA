function addMediaContainer() {
	var mediaContainers = document.getElementById('mediaContainers');
	var newMediaContainer = document.createElement('div');
	newMediaContainer.classList.add('media-container', 'droppable');
	newMediaContainer.setAttribute('id', 'dropArea');
	newMediaContainer.ondrop = function(event) { drop(event) };
	newMediaContainer.ondragover = function(event) { allowDrop(event) };
	newMediaContainer.ondragenter = function(event) { dragEnter(event) };
	newMediaContainer.ondragleave = function(event) { dragLeave(event) };
	newMediaContainer.innerHTML = '<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas"' +
		'data-bs-target="#offcanvasMidia" aria-controls="offcanvasMidia" >' +
		'Adicionar Mídia </button >';
	mediaContainers.appendChild(newMediaContainer);
}

function allowDrop(event) {
	event.preventDefault();
}

function drag(event) {
	var draggedElement = event.target;
	var clone = draggedElement.cloneNode(true);
	event.dataTransfer.setDragImage(clone, 50, 50);
	event.dataTransfer.setData("text", draggedElement.outerHTML);
}

function drop(event) {
	event.preventDefault();
	var data = event.dataTransfer.getData("text");
	var dropArea = event.target;

	if (dropArea.id === 'dropArea') {

		var updateButton = '<button class="btn btn-warning" id="btnClearCont" onclick = "clearContainer(this.parentNode)"><i class="bi bi-pen"></i></button></div>'
		var deleteButton = '<button class="btn btn-danger" id = "btnDelMidia" onclick = "deleteMedia(this.parentNode)"><i class="bi bi-x-lg"></i></button >'
		dropArea.innerHTML = updateButton + deleteButton + data;
		makeEditable(dropArea);
	}

	var linkElement = dropArea.querySelector('#linkTexto');

	if (linkElement) {
		// Se o elemento for encontrado, faça alguma coisa
		var urlButton = '<button class="btn btn-info url-button" id="btnAddUrl" type="button" data-bs-toggle="modal" data-bs-target="#addUrlModal" data-dropzone-id="' + dropArea.id + '"><i class="bi bi-link"></i></button>';
		dropArea.innerHTML += urlButton;

		// Adiciona um ouvinte de evento ao botão de URL recém-criado
		var newUrlButton = dropArea.querySelector('.url-button');
		newUrlButton.addEventListener('click', function() {
			window.currentDropzone = dropArea;
		});
	}


	// Ajuste dinâmico da altura com base na altura da mídia adicionada
	var mediaHeight = dropArea.querySelector('img, video, audio, h1, h2, h3, p, b, i, big, em, blockquote, q, del, u').clientHeight;
	dropArea.style.minHeight = mediaHeight + 'px';
}

document.getElementById('addUrlModal').addEventListener('show.bs.modal', function(event) {
	var relatedTarget = event.relatedTarget;
	if (relatedTarget && relatedTarget.dataset.dropzoneId) {
		// Armazene a referência à dropzone associada em uma variável global ou em um elemento HTML oculto
		var currentDropzone = document.getElementById(relatedTarget.dataset.dropzoneId);
		window.currentDropzone = currentDropzone;
	}
});

function makeEditable(dropArea) {
	// Encontrar os elementos editáveis dentro da dropArea e torná-los editáveis
	var editableElements = dropArea.querySelectorAll('.editable');
	editableElements.forEach(function(element) {
		element.contentEditable = true;
	});
}

function dragEnter(event) {
	var dropArea = event.target;
	if (dropArea.classList.contains('droppable') && !hasMedia(dropArea)) {
		dropArea.classList.add('drag-over');
	}
}

function dragLeave(event) {
	var dropArea = event.target;
	if (dropArea.classList.contains('droppable') && !hasMedia(dropArea)) {
		dropArea.classList.remove('drag-over');
	}
}

function hasMedia(dropArea) {
	// Verifica se a dropArea já contém algum elemento de mídia
	return dropArea.querySelector('img, video, audio, h1, h2, h3, p, b, big, em, blockquote, q, del, u') !== null;
}

function deleteMedia(mediaContainer) {
	// Remove o elemento de mídia e seu contêiner pai
	mediaContainer.parentNode.removeChild(mediaContainer);
	mediaContainer.style.minHeight = '250';
}


function clearContainer(clearMediaContainer) {
	// Remove todos os elementos filhos do mediaContainer
	while (clearMediaContainer.firstChild) {
		clearMediaContainer.removeChild(clearMediaContainer.firstChild);
	}

	// Restaura o mediaContainer ao estado original, adicionando o botão "Adicionar Mídia"
	clearMediaContainer.innerHTML = '<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasMidia" aria-controls="offcanvasMidia">Adicionar Mídia</button>';

	// Resetar a altura do mediaContainer
	clearMediaContainer.style.minHeight = '250px';
	clearMediaContainer.classList.remove('drag-over');
}

function filtrarMidias(tipoMidia) {
	var selectedObra = document.getElementById('obraSelect' + tipoMidia).value;

	var medias = document.querySelectorAll('.' + tipoMidia);

	medias.forEach(function(media) {
		var obraDaMidia = media.querySelector('.card-title.mb-4').innerText.trim();
		if (obraDaMidia === selectedObra || selectedObra === '') {
			media.style.display = 'block';
		} else {
			media.style.display = 'none';
		}
	});
}

function preencherConteudo() {
	var phoneContent = document.getElementById('phoneContent').innerHTML;
	document.getElementById('phoneContentInput').value = phoneContent;
}

document.getElementById('addUrlModal').addEventListener('show.bs.modal', function(event) {
	var relatedTarget = event.relatedTarget;
	if (relatedTarget && relatedTarget.dataset.dropzoneId) {
		// Armazene a referência à dropzone associada em uma variável global ou em um elemento HTML oculto
		var currentDropzone = document.getElementById(relatedTarget.dataset.dropzoneId);
		window.currentDropzone = currentDropzone;
	}
});

function updateLink() {
	var newUrl = document.getElementById('message-text').value;

	if (window.currentDropzone) {
		var linkInDropzone = window.currentDropzone.querySelector('a#linkTexto');
		if (linkInDropzone) {
			linkInDropzone.href = newUrl;
		}
	}
	var modal = new bootstrap.Modal(document.getElementById('addUrlModal'));
	modal.hide();

	document.getElementById('message-text').value = '';

}
