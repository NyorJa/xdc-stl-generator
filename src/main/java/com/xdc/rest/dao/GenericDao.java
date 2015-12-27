package com.xdc.rest.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

interface GenericDao<E> {
	int FIRST_INDEX = 0;
    /**
     * Use this field as count value to disregard count parameter and return all
     */
    int ALL = -1;

    E add(E entity) throws IllegalArgumentException;

    E get(Object key) throws IllegalArgumentException;

    E update(E entity) throws IllegalArgumentException;

    boolean remove(Object key) throws IllegalArgumentException;

    List<E> getAll() throws IllegalArgumentException;
    
    List<E> getAll(HttpServletRequest request) throws IllegalArgumentException;

    boolean exists(E entity) throws IllegalArgumentException;
    
    String generateDocNo(Integer entityId);
    
    Long getCount(HttpServletRequest request);
}
