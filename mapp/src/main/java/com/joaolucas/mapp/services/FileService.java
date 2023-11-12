package com.joaolucas.mapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.joaolucas.mapp.model.File;
import com.joaolucas.mapp.repositories.FileRepository;
import com.joaolucas.mapp.services.exceptions.DataBaseException;
import com.joaolucas.mapp.services.exceptions.ResourceNotFoundException;

@Service
public class FileService {

	@Autowired
	private FileRepository fileRepository;

	public File saveFile(File file) {
		return fileRepository.save(file);
	}

	public void deleteFile(String id) {
		try {
			getFileById(id);
			fileRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Arquivo com ID " + id + " não foi encontrado.");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public File getFileById(String id) {
		Optional<File> obj = fileRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Arquivo com ID " + id + " não foi encontrado."));
	}

	public List<File> listAllFilesByIdObra(String idObra) {
		if (idObra == null || idObra.isEmpty()) {
			throw new ResourceNotFoundException("Obra com ID " + idObra + " não foi encontrado.");
		}
		return fileRepository.findByIdObra(idObra);
	}
	
	public List<File> listAllImages() {
		return fileRepository.findByContentTypeContaining("image/");
	}

	public List<File> listAllVideos() {
		return fileRepository.findByContentTypeContaining("video/");
	}
	
	public List<File> listAllAudios() {
		return fileRepository.findByContentTypeContaining("audio/");
	}
}
