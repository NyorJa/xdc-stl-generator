package com.xdc.rest.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xdc.rest.entity.User;

public interface UserDao extends GenericDao<User> {
	List<Object[]> getDefinedList(HttpServletRequest request) throws IllegalArgumentException;
}
