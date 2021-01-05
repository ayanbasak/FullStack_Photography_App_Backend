package com.ayan.tiles.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Tile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String tileCode;
	private String imgLink;
	private Integer size;
	private Float price;

	private Date create_At;
	private Date update_At;

	@PrePersist
	protected void onCreate() {
		this.create_At = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.update_At = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTileCode() {
		return tileCode;
	}

	public void setTileCode(String tileCode) {
		this.tileCode = tileCode;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Float getPrice() {
		return price;
	}
	

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Date getCreate_At() {
		return create_At;
	}

	public void setCreate_At(Date create_At) {
		this.create_At = create_At;
	}

	public Date getUpdate_At() {
		return update_At;
	}

	public void setUpdate_At(Date update_At) {
		this.update_At = update_At;
	}

}
