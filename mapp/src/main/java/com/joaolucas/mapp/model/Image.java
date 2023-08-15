package com.joaolucas.mapp.model;

import java.io.Serializable;
import java.util.Base64;
import java.util.Objects;

import org.bson.types.Binary;

public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	private Binary image;
	
	public Image() {

	}

	public Image(Binary image) {
		this.image = image;
	}

	public Binary getImage() {
		return image;
	}

	public void setImage(Binary image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		return Objects.hash(image);
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
		return Objects.equals(image, other.image);
	}

	public String getImagemBinaryStr() {
		return Base64.getEncoder().encodeToString(image.getData());
	}

}
