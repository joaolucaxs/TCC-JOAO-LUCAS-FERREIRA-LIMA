package com.joaolucas.mapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Objects;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "arquivos")
public class File implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Max(value = 50, message = "O nome do arquivo não pode ultrapassar 50 caracteres")
	private String name;

	@NotBlank(message = "O tipo de conteúdo não pode estar em branco.")
	private String contentType;

	@NotNull(message = "O tamanho do arquivo não pode ser nulo.")
	private long size;

	@NotNull(message = "A data de upload não pode ser nula.")
	private LocalDate uploadDate;

	@NotBlank(message = "O MD5 não pode estar em branco.")
	private String md5;

	@NotNull(message = "O conteúdo do arquivo não pode ser nulo.")
	private Binary content;

	@NotBlank(message = "O caminho do arquivo não pode estar em branco.")
	private String path;

	private String idObra;

	@NotBlank(message = "O nome original do arquivo não pode estar em branco.")
	private String originalFileName;

	public File() {

	}

	public File(String originalFileName, String contentType, long size, Binary content) {
		this.originalFileName = originalFileName;
		this.contentType = contentType;
		this.size = size;
		this.content = content;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof File))
			return false;
		File fileModel = (File) o;
		return size == fileModel.size && Objects.equals(id, fileModel.id)
				&& Objects.equals(originalFileName, fileModel.originalFileName)
				&& Objects.equals(contentType, fileModel.contentType)
				&& Objects.equals(uploadDate, fileModel.uploadDate) && Objects.equals(md5, fileModel.md5)
				&& Objects.equals(content, fileModel.content) && Objects.equals(path, fileModel.path);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, originalFileName, contentType, size, uploadDate, md5, content, path);
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

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public LocalDate getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Binary getContent() {
		return content;
	}

	public void setContent(Binary content) {
		this.content = content;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIdObra() {
		return idObra;
	}

	public void setIdObra(String idObra) {
		this.idObra = idObra;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getFileExtension(String originalFilename) {
		if (originalFilename != null && !originalFilename.isEmpty()) {
			int lastDotIndex = originalFilename.lastIndexOf('.');
			if (lastDotIndex > 0) {
				return originalFilename.substring(lastDotIndex + 1);
			}
		}
		return "";
	}

	public String getBinaryAsStr() {
		return Base64.getEncoder().encodeToString(content.getData());
	}

}