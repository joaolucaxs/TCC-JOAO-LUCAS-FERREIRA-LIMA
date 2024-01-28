package com.joaolucas.mapp.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.joaolucas.mapp.dtos.FileApresentacaoDTOShow;
import com.joaolucas.mapp.model.File;
import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.repositories.FileRepository;
import com.joaolucas.mapp.resources.util.MD5Util;
import com.joaolucas.mapp.services.exceptions.DataBaseException;
import com.joaolucas.mapp.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class FileService {

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private PecaService pecaService;

	public File save(File file) {
		return fileRepository.save(file);
	}

	public void delete(String id) {
		try {
			findById(id);
			fileRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Arquivo com ID " + id + " não foi encontrado.");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public File findById(String id) {
		Optional<File> obj = fileRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Arquivo com ID " + id + " não foi encontrado."));
	}

	public List<File> findAllByIdObra(String idObra) {
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
		List<FileApresentacaoDTOShow> videosFilesDTO = allVideos.stream().map(file -> new FileApresentacaoDTOShow(file))
				.collect(Collectors.toList());

		for (FileApresentacaoDTOShow fileApresentacaoDTOShow : videosFilesDTO) {
			Peca obraAssociada = new Peca(pecaService.findById(fileApresentacaoDTOShow.getIdObra()));
			fileApresentacaoDTOShow.setPeca(obraAssociada);
		}
		return videosFilesDTO;
	}

	public List<FileApresentacaoDTOShow> listAllAudiosDTO() {
		List<File> allAudios = fileRepository.findByContentTypeContaining("audio/");
		List<FileApresentacaoDTOShow> audiosFilesDTO = allAudios.stream().map(file -> new FileApresentacaoDTOShow(file))
				.collect(Collectors.toList());

		for (FileApresentacaoDTOShow fileApresentacaoDTOShow : audiosFilesDTO) {
			Peca obraAssociada = new Peca(pecaService.findById(fileApresentacaoDTOShow.getIdObra()));
			fileApresentacaoDTOShow.setPeca(obraAssociada);
		}
		return audiosFilesDTO;
	}

	public void visualizarArquivo(HttpServletResponse response, File file) throws IOException {
		response.setContentType(file.getContentType());

		response.setHeader("Content-Disposition", "inline; filename=" + file.getOriginalFileName());
		response.setHeader("Content-Length", String.valueOf(file.getSize()));

		response.getOutputStream().write(file.getContent().getData());
		response.flushBuffer();
	}
	
	public File buildFile(MultipartFile file, String idObra, String nomeArquivo)
			throws IOException, NoSuchAlgorithmException {
		File f = new File(file.getOriginalFilename(), file.getContentType(), file.getSize(),
				new Binary(file.getBytes()));
		f.setMd5(MD5Util.getMD5(file.getInputStream()));
		f.setIdObra(idObra);
		f.setName(nomeArquivo);
		f.setUploadDate(LocalDate.now());
		return f;
	}

}
