package com.xdc.rest.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xdc.rest.entity.Person;

public interface PersonDao extends GenericDao<Person> {
	List<Object[]> getDefinedList(HttpServletRequest request) throws IllegalArgumentException;
}
