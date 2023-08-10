package com.joaolucas.mapp.model;

import java.io.Serializable;
import java.util.Objects;

public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fileName;
	private String fileType;
	private String fileDownloadURI;
	private long size;

	public Image() {

	}

	public Image(String fileName, String fileType, String fileDownloadURI, long size) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileDownloadURI = fileDownloadURI;
		this.size = size;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileDownloadURI() {
		return fileDownloadURI;
	}

	public void setFileDownloadURI(String fileDownloadURI) {
		this.fileDownloadURI = fileDownloadURI;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fileDownloadURI, fileName, fileType, size);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return Objects.equals(fileDownloadURI, other.fileDownloadURI) && Objects.equals(fileName, other.fileName)
				&& Objects.equals(fileType, other.fileType) && size == other.size;
	}

	
}
