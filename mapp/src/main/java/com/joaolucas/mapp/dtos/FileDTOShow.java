package com.joaolucas.mapp.dtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.joaolucas.mapp.model.File;

public class FileDTOShow {

	private String id;
	private String name;
	private String extensao;
	private LocalDate uploadDate;

	public FileDTOShow() {
	}

	public FileDTOShow(File obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.uploadDate = obj.getUploadDate();
		this.extensao = obj.getFileExtension(obj.getOriginalFileName());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public LocalDate getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	public String getDataUploadAsStr() {
		return formatDateToString(getUploadDate());
	}
	
	private static String formatDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

}
