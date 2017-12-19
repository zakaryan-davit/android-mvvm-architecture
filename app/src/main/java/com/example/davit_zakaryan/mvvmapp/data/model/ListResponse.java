package com.example.davit_zakaryan.mvvmapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListResponse<T> {

	@SerializedName("message")
	public String message;

	@SerializedName("data")
	public List<T> data = null;
}
