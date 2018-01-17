package com.example.davit_zakaryan.mvvmapp.data.db.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;


@Entity(nameInDb = "elements")
public class ElementEntity {

	@Id(autoincrement = true)
	private Long id;

	@Property(nameInDb = "server_id")
	private String serverId;

	@Property(nameInDb = "title")
	private String title;

	@Property(nameInDb = "item_type")
	private String itemType;

	@Property(nameInDb = "level")
	private int level;

	@Property(nameInDb = "short_desc")
	private String shortDesc;

	@Property(nameInDb = "description")
	private String description;

	@Property(nameInDb = "do_date")
	private Date doDate;

	@Property(nameInDb = "image_url")
	public String url;

	@Generated(hash = 423545585)
	public ElementEntity(Long id, String serverId, String title, String itemType,
			int level, String shortDesc, String description, Date doDate, String url) {
		this.id = id;
		this.serverId = serverId;
		this.title = title;
		this.itemType = itemType;
		this.level = level;
		this.shortDesc = shortDesc;
		this.description = description;
		this.doDate = doDate;
		this.url = url;
	}

	@Generated(hash = 33487496)
	public ElementEntity() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServerId() {
		return this.serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getShortDesc() {
		return this.shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDoDate() {
		return this.doDate;
	}

	public void setDoDate(Date doDate) {
		this.doDate = doDate;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

}
