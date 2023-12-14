package com.joaolucas.mapp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.joaolucas.mapp.dtos.FileApresentacaoDTOShow;
import com.joaolucas.mapp.model.FichaTecnicaObra;
import com.joaolucas.mapp.model.File;
import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.repositories.FileRepository;
import com.joaolucas.mapp.services.exceptions.DataBaseException;
import com.joaolucas.mapp.services.exceptions.ResourceNotFoundException;

@Service
public class FileService {

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private PecaService pecaService;

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


	public List<FileApresentacaoDTOShow> listAllImagesDTO() {
		List<File> allImages = fileRepository.findByContentTypeContaining("image/");
		List<FileApresentacaoDTOShow> imagensFilesDTO = allImages.stream()
				.map(file -> new FileApresentacaoDTOShow(file)).collect(Collectors.toList());

		for (FileApresentacaoDTOShow fileApresentacaoDTOShow : imagensFilesDTO) {
			Peca obraAssociada = new Peca(pecaService.findById(fileApresentacaoDTOShow.getIdObra()));
			fileApresentacaoDTOShow.setPeca(obraAssociada);
		}
		return imagensFilesDTO;
	}
	
	public List<FileApresentacaoDTOShow> listAllVideosDTO() {
		List<File> allVideos = fileRepository.findByContentTypeContaining("video/");
		List<FileApresentacaoDTOShow> videosFilesDTO = allVideos.stream()
				.map(file -> new FileApresentacaoDTOShow(file)).collect(Collectors.toList());

		for (FileApresentacaoDTOShow fileApresentacaoDTOShow : videosFilesDTO) {
			Peca obraAssociada = new Peca(pecaService.findById(fileApresentacaoDTOShow.getIdObra()));
			fileApresentacaoDTOShow.setPeca(obraAssociada);
		}
		return videosFilesDTO;
	}
	
	public List<FileApresentacaoDTOShow> listAllAudiosDTO() {
		List<File> allAudios = fileRepository.findByContentTypeContaining("audio/");
		List<FileApresentacaoDTOShow> audiosFilesDTO = allAudios.stream()
				.map(file -> new FileApresentacaoDTOShow(file)).collect(Collectors.toList());

		for (FileApresentacaoDTOShow fileApresentacaoDTOShow : audiosFilesDTO) {
			Peca obraAssociada = new Peca(pecaService.findById(fileApresentacaoDTOShow.getIdObra()));
			fileApresentacaoDTOShow.setPeca(obraAssociada);
		}
		return audiosFilesDTO;
	}

}
