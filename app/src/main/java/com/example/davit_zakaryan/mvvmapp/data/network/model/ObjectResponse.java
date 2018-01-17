package com.example.davit_zakaryan.mvvmapp.data.network.model;

import com.google.gson.annotations.SerializedName;

public class ObjectResponse<T> {

	@SerializedName("message")
	public String message;

	@SerializedName("data")
	public T data = null;
}
