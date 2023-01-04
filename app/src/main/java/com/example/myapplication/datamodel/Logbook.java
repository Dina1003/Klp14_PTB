package com.example.myapplication.datamodel;

import com.google.gson.annotations.SerializedName;

public class Logbook{

	@SerializedName("date")
	private String date;

	@SerializedName("thesis_id")
	private String thesisId;

	@SerializedName("problem")
	private String problem;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("supervisor_id")
	private String supervisorId;

	@SerializedName("progress")
	private String progress;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private int status;

	public String getDate(){
		return date;
	}

	public String getThesisId(){
		return thesisId;
	}

	public String getProblem(){
		return problem;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getSupervisorId(){
		return supervisorId;
	}

	public String getProgress(){
		return progress;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public int getStatus(){
		return status;
	}
}