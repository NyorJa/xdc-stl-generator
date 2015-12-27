package com.xdc.rest.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdc.rest.dao.PersonDao;
import com.xdc.rest.entity.Person;
import com.xdc.rest.model.PersonInfo;

@Service
@Transactional
public class PersonService {
	
	@Autowired
	PersonDao personDao;
	
	public Person add(Person person){return personDao.add(person);}
	public Person get(int id){return personDao.get(id);}
	public Person update(Person person){return personDao.update(person);}
	public List<Person> getAll(HttpServletRequest request){return personDao.getAll(request);}
	public Long getTotal(HttpServletRequest request){return personDao.getCount(request);}
	public List<PersonInfo> getDefinedList(HttpServletRequest request){
		
		List<PersonInfo> personInfos = new ArrayList<>();
		List<Object[]> results = personDao.getDefinedList(request);
		
		for (Object[] res : results) {
			
			PersonInfo personInfo = new PersonInfo();
			
			personInfo.setPersonId((Integer) res[0]);
			personInfo.setFirstName((String) res[1]);
			personInfo.setLastName((String) res[2]);
			personInfo.setAddress((String) res[3]);
			personInfo.setDateCreated((Date) res[4]);
			personInfo.setDateModified((Timestamp) res[5]);
			
			personInfos.add(personInfo);
		}
		
		return personInfos;
	}
}
