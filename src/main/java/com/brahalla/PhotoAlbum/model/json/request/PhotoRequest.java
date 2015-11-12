package com.brahalla.PhotoAlbum.model.json.request;

import com.brahalla.PhotoAlbum.model.base.ModelBase;

import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class PhotoRequest extends ModelBase {

	private static final long serialVersionUID = 2817453290727069227L;
	private String title;
	private String filePath;
	private Long albumId;

	public PhotoRequest() {
		super();
	}

	public PhotoRequest(String title, String filePath, Long albumId) {
		this.setTitle(title);
		this.setFilePath(filePath);
		this.setAlbumId(albumId);
	}

	@NotEmpty(message = "photo.title.notEmpty")
	@Length(max = 50, message = "photo.title.length")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotEmpty(message = "photo.filePath.notEmpty")
	@Length(max = 100, message = "photo.filePath.length")
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Min(value = 1, message = "photo.albumId.min")
	public Long getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

}
