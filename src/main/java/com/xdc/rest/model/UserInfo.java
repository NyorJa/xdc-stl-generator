package com.xdc.rest.model;

import java.sql.Timestamp;
import java.util.Date;

public class UserInfo {
	private Integer userId;
	private Integer personId;
	private String username;
	private String personName;
	private String personAddress;
	private Date dateCreated;
	private Timestamp dateModified;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonAddress() {
		return personAddress;
	}
	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Timestamp getDateModified() {
		return dateModified;
	}
	public void setDateModified(Timestamp dateModified) {
		this.dateModified = dateModified;
	}
}
