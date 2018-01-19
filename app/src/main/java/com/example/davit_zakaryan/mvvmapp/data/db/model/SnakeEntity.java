package com.example.davit_zakaryan.mvvmapp.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(nameInDb = "snakes")
public class SnakeEntity {

	@Id(autoincrement = true)
	private Long id;

	@Property(nameInDb = "server_id")
	private String serverId;

	@Property(nameInDb = "title")
	private String title;

	@Property(nameInDb = "level")
	private int level;

	@Property(nameInDb = "short_desc")
	private String shortDesc;

	@Property(nameInDb = "description")
	private String description;

	@Property(nameInDb = "image_url")
	private String url;

	@Generated(hash = 439179929)
	public SnakeEntity(Long id, String serverId, String title, int level,
			String shortDesc, String description, String url) {
		this.id = id;
		this.serverId = serverId;
		this.title = title;
		this.level = level;
		this.shortDesc = shortDesc;
		this.description = description;
		this.url = url;
	}

	@Generated(hash = 494727732)
	public SnakeEntity() {
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
