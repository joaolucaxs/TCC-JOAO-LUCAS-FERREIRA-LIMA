package com.joaolucas.mapp.resources.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

public class BinaryToMultiPartFile implements MultipartFile {
	private final Binary image;

	public BinaryToMultiPartFile(Binary image) {
        this.image = image;
    }

	@Override
	public String getName() {
		return "image"; // Nome do campo do formulário
	}

	@Override
	public String getOriginalFilename() {
		return "image.jpg"; // Nome original do arquivo
	}

	@Override
	public String getContentType() {
		// Determinar o tipo de conteúdo da imagem com base nos dados
		return "image/jpeg"; // Exemplo: imagem JPEG
	}

	@Override
	public boolean isEmpty() {
		return image.getData() == null || image.getData().length == 0;
	}

	@Override
	public long getSize() {
		return image.getData() != null ? image.getData().length : 0;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return image.getData();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(image.getData());
	}

	@Override
	public void transferTo(java.io.File dest) throws IOException, IllegalStateException {
		throw new UnsupportedOperationException("Not implemented");
	}

}
