package com.example.myapplication.datamodel;

import com.google.gson.annotations.SerializedName;

public class TambahLogbookResponse{

	@SerializedName("logbook")
	private Logbook logbook;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public Logbook getLogbook(){
		return logbook;
	}

	public String getMessage(){
		return message;
	}

	public String getStatus(){
		return status;
	}
}