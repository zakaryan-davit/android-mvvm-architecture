package com.example.davit_zakaryan.mvvmapp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Davit_Zakaryan on 12/19/2017.
 */
public class ItemsResponse {

	@SerializedName("_id")
	public String id;

	@SerializedName("title")
	public String title;

	@SerializedName("item_type")
	public String itemType;

	@SerializedName("level")
	public int level;

	@SerializedName("short_desc")
	public String shortDesc;

	@SerializedName("long_desc")
	public String longDesc;
}
