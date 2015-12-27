package com.xdc.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xdc.rest.entity.Person;
import com.xdc.rest.model.PersonInfo;
import com.xdc.rest.model.StatusListInfo;
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
			personInfo.setDateCreated(person.getDateCreated());
			personInfo.setDateModified(person.getDateModified());
			
			personInfos.addAll(personInfos);
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
}
