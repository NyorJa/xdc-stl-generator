package com.xdc.rest.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.xdc.rest.dao.UserDao;
import com.xdc.rest.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	private Integer limit = 0;
	private Integer page = 1;
	
	@Override
	public User add(User entity) throws IllegalArgumentException {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public User get(Object key) throws IllegalArgumentException {
		return entityManager.find(User.class, key);
	}

	@Override
	public User update(User entity) throws IllegalArgumentException {
		return entityManager.merge(entity);
	}

	@Override
	public boolean remove(Object key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getAll() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll(HttpServletRequest request) throws IllegalArgumentException {
		String qryCondition = setQueryCondition(request);
		Query query = entityManager.createQuery("select u from User u "
				+ qryCondition
				+ " order by u.id desc");
		
		if (this.limit > 0) {
			query = query.setMaxResults(this.limit);
		}
		
		if(this.page > 0){
			query = query.setFirstResult((this.page - 1) * this.limit);
		}
		
		return query.getResultList();
	}

	@Override
	public boolean exists(User entity) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String generateDocNo(Integer entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCount(HttpServletRequest request) {
		String qryCondition = setQueryCondition(request);
		Query query = entityManager.createQuery("select count(*) from User u "
				+ qryCondition);
		return (Long) query.getSingleResult();
	}
	
	private void setRequestParam(HttpServletRequest request){
		
		if(request.getParameter("limit") != null){
			this.limit = Integer.valueOf(request.getParameter("limit"));
		}else{
			this.limit = 0;
		}
		
		if(request.getParameter("page") != null){
			this.page = Integer.valueOf(request.getParameter("page"));
		}else{
			this.page = 1;
		}
	}

	private String setQueryCondition(HttpServletRequest request){
		
		setRequestParam(request);
		
		String qryCondition = "";
		
		if(!qryCondition.equals("")){
			qryCondition = " where " + qryCondition;
		}
		
		return qryCondition;
	}

}
