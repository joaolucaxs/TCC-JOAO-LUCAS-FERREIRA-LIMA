package com.joaolucas.mapp.resources;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joaolucas.mapp.model.File;
import com.joaolucas.mapp.services.FileService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/arquivos")
public class FileResource {

	@Autowired
	private FileService fileService;

	@GetMapping("/download/{id}")
	public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String id, HttpServletRequest request, BindingResult result) {
		
		File file = fileService.getFileById(id);
		String contentType = "";

		try {
			contentType = file.getContentType();
		} catch (Exception e) {
			e.getMessage();
		}

		if (contentType.isBlank())
			contentType = "application/octet-stream";

		ByteArrayInputStream inputStream = new ByteArrayInputStream(file.getContent().getData());
		InputStreamResource resource = new InputStreamResource(inputStream);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getOriginalFileName() + "\"")
				.body(resource);
	}

	@GetMapping("/visualizar/{id}")
	public void visualizarArquivo(@PathVariable String id, HttpServletResponse response) throws IOException {

		File file = fileService.getFileById(id);

		response.setContentType(file.getContentType());

		response.setHeader("Content-Disposition", "inline; filename=" + file.getOriginalFileName());
		response.setHeader("Content-Length", String.valueOf(file.getSize()));

		response.getOutputStream().write(file.getContent().getData());
		response.flushBuffer();
	}

}
