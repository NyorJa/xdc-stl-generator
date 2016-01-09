package com.xdc.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xdc.rest.entity.Person;
import com.xdc.rest.model.PersonInfo;
import com.xdc.rest.model.StatusListInfo;
import com.xdc.rest.model.StatusSingleInfo;
import com.xdc.rest.security.RestCorsHttpServletResponse;
import com.xdc.rest.service.PersonService;
import com.xdc.rest.utils.SystemMessage;

@RestController
@RequestMapping("/rest")
public class PersonCtrl {
	
	@Autowired
	PersonService personService;
	
	@RequestMapping(value = "/person/list", method = RequestMethod.GET, produces = "application/json")
	public StatusListInfo getAll(HttpServletRequest request, HttpServletResponse response){
		RestCorsHttpServletResponse.setResponse(response);
		StatusListInfo statusListInfo = new StatusListInfo();
		
		List<Person> persons = personService.getAll(request);
		List<PersonInfo> personInfos = new ArrayList<>();
		
		for (Person person : persons) {
			
			PersonInfo personInfo = new PersonInfo();
			
			personInfo.setPersonId(person.getId());
			personInfo.setFirstName(person.getFirstName());
			personInfo.setLastName(person.getLastName());
			personInfo.setAddress(person.getAddress());
			personInfo.setDateCreated(person.getDateCreated());
			personInfo.setDateModified(person.getDateModified());
			
			personInfos.add(personInfo);
			
		}
		
		statusListInfo.setList(personInfos);
		statusListInfo.setMessage(SystemMessage.SUCCESS);
		statusListInfo.setStatus(true);
		statusListInfo.setTotal(personService.getTotal(request));
		
		return statusListInfo;
	}
	
	@RequestMapping(value = "/person/dlist", method = RequestMethod.GET, produces = "application/json")
	public StatusListInfo getDefinedList(HttpServletRequest request, HttpServletResponse response){
		RestCorsHttpServletResponse.setResponse(response);
		StatusListInfo statusListInfo = new StatusListInfo();	
		
		List<PersonInfo> personInfos = personService.getDefinedList(request);
		
		statusListInfo.setList(personInfos);
		statusListInfo.setStatus(true);
		statusListInfo.setMessage(SystemMessage.SUCCESS);
		statusListInfo.setTotal(personService.getTotal(request));
		
		return statusListInfo;
	}
	
	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public StatusSingleInfo add(@RequestBody PersonInfo personInfo, HttpServletResponse  response){
		RestCorsHttpServletResponse.setResponse(response);
		StatusSingleInfo statusSingleInfo = new StatusSingleInfo();
		
		Person person = new Person();
		
		person.setAddress(personInfo.getAddress());
		person.setFirstName(personInfo.getFirstName());
		person.setLastName(personInfo.getLastName());
		person.setAddress(personInfo.getAddress());
		person.setDateCreated(new Date());
		
		person = personService.add(person);
		
		statusSingleInfo.setId(person.getId());
		statusSingleInfo.setMessage(SystemMessage.SUCCESS);
		statusSingleInfo.setStatus(true);
		
		return statusSingleInfo;
	}
	
	public StatusSingleInfo update(@PathVariable int id, @RequestBody PersonInfo personInfo,
			HttpServletResponse response){
		RestCorsHttpServletResponse.setResponse(response);
		StatusSingleInfo statusSingleInfo = new StatusSingleInfo();
		
		Person person = personService.get(id);
		
		person.setAddress(personInfo.getAddress());
		person.setDateModified(null);
		person.setFirstName(personInfo.getFirstName());
		person.setLastName(personInfo.getLastName());
		
		person = personService.update(person);
		
		statusSingleInfo.setId(person.getId());
		statusSingleInfo.setMessage(SystemMessage.SUCCESS);
		statusSingleInfo.setStatus(true);
		
		return statusSingleInfo;
	}
}
