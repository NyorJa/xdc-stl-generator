package com.xdc.rest.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.xdc.rest.dao.PersonDao;
import com.xdc.rest.entity.Person;

@Repository
public class PersonDaoImpl implements PersonDao {

	@PersistenceContext
	private EntityManager entityManager;
	private Integer limit = 0;
	private Integer page = 1;
	
	@Override
	public Person add(Person entity) throws IllegalArgumentException {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Person get(Object key) throws IllegalArgumentException {
		return entityManager.find(Person.class, key);
	}

	@Override
	public Person update(Person entity) throws IllegalArgumentException {
		return entityManager.merge(entity);
	}

	@Override
	public boolean remove(Object key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Person> getAll() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAll(HttpServletRequest request) throws IllegalArgumentException {
		String qryCondition = setQueryCondition(request);
		Query query = entityManager.createQuery("select p from Person p "
				+ qryCondition
				+ " order by p.id desc");
		
		if (this.limit > 0) {
			query = query.setMaxResults(this.limit);
		}
		
		if(this.page > 0){
			query = query.setFirstResult((this.page - 1) * this.limit);
		}

		return query.getResultList();
	}

	@Override
	public boolean exists(Person entity) throws IllegalArgumentException {
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
		Query query = entityManager.createQuery("select count(*) from Person p "
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDefinedList(HttpServletRequest request) throws IllegalArgumentException {
		String qryCondition = setQueryCondition(request);
		Query query = entityManager.createQuery("select "
				+ " p.id,"
				+ " p.firstName, "
				+ " p.lastName, "
				+ " p.address,"
				+ " p.dateCreated, "
				+ " p.dateModified "
				+ " from Person p "
				+ qryCondition
				+ " order by p.id desc ");
		
		if (this.limit > 0) {
			query = query.setMaxResults(this.limit);
		}
		
		if(this.page > 0){
			query = query.setFirstResult((this.page - 1) * this.limit);
		}
		
		return query.getResultList();
	}

}
