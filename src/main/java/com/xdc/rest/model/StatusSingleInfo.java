package com.xdc.rest.model;

/*
 * @Author confidential company
 */
public class StatusSingleInfo {
	private boolean status;
	private String message;
	private int id;
		
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
