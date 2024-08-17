package com.in28minutes.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.Size;


public class Report {
	private int id;

	@Size(min = 10, message = "Enter atleast 10 Characters.")
	private String name;
	private String description;
	private String createdAt;
	private String updatedAt;

	private String reportedBy;

	public Report(int id, String name, String description, String reportedBy) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.reportedBy = reportedBy;
		this.createdAt = this.convertDateTimeToString();
	}

	public Report() {
		super();
	}

	public void update(String name, String description, String reportedBy) {
		boolean isUpdate = false;

		if (name != null && name.length() >= 10 && !name.trim().isEmpty()) {
			this.name = name;
			isUpdate = true;
		}
		if (description != null) {
			this.description = description;
			isUpdate = true;
		}
		if (reportedBy != null && !reportedBy.trim().isEmpty()) {
			this.reportedBy = reportedBy;
			isUpdate = true;
		}

		if (isUpdate) {
			this.updatedAt = this.convertDateTimeToString();
		}

	}

	private String convertDateTimeToString() {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date now = new Date();
		return dateTimeFormat.format(now);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}
	
	@Override
	public String toString() {
		return "Report [createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		return id == other.id && Objects.equals(name, other.name) && Objects.equals(reportedBy, other.reportedBy);
	}

}