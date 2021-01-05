package com.ayan.tiles.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Cart {
//  {id: 19, price: 25, src: "https://source.unsplash.com/Dm-qxdynoEc/800x799", tileCode: "1002", qty: 6}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userEmail;
	private Float price;
	private Integer qty;
	private String src;
	private String tileCode;
	
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getTileCode() {
		return tileCode;
	}

	public void setTileCode(String tileCode) {
		this.tileCode = tileCode;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Float getPrice() {
		return price;
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

	@Override
	public String toString() {
		return "Cart [id=" + id + ", userEmail=" + userEmail + ", price=" + price + ", qty=" + qty + ", src=" + src
				+ ", tileCode=" + tileCode + ", create_At=" + create_At + ", update_At=" + update_At + "]";
	}

}
