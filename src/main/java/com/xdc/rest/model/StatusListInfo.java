package com.xdc.rest.model;

import java.util.List;

/*
 *@Author confidential company 
 */
public class StatusListInfo {
	private boolean status;
	private String message;
	private List<?> list;
	private Long total;

	public StatusListInfo() {
		this.status = false;
		this.message = "";
		this.list = null;
		this.total = new Long(0);
	}

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

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
