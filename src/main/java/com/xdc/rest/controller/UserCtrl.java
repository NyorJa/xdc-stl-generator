package com.xdc.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xdc.rest.entity.Person;
import com.xdc.rest.entity.User;
import com.xdc.rest.model.StatusListInfo;
import com.xdc.rest.model.StatusSingleInfo;
import com.xdc.rest.model.UserInfo;
import com.xdc.rest.security.RestCorsHttpServletResponse;
import com.xdc.rest.service.UserService;
import com.xdc.rest.utils.SystemMessage;

@RequestMapping("/rest")
@RestController
public class UserCtrl {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/user/list", method = RequestMethod.GET, produces = "application/json")
	public StatusListInfo getAll(HttpServletRequest request, HttpServletResponse response){
		RestCorsHttpServletResponse.setResponse(response);
		StatusListInfo statusListInfo = new StatusListInfo();
		
		List<UserInfo> userInfos = new ArrayList<>();
		List<User> users = userService.getAll(request);
		
		for (User user : users) {
			
			UserInfo userInfo = new UserInfo();
			
			userInfo.setUserId(user.getId());
			userInfo.setPersonId(user.getPerson().getId());
			userInfo.setUsername(user.getUsername());
			userInfo.setPassword(user.getPassword());
			userInfo.setPersonName(user.getPerson().getFirstName() +  " " + user.getPerson().getLastName());
			userInfo.setPersonAddress(user.getPerson().getAddress());
			userInfo.setDateCreated(user.getDateCreated());
			userInfo.setDateModified(user.getDateModified());
			
			userInfos.add(userInfo);
		}
		
		statusListInfo.setList(userInfos);
		statusListInfo.setMessage(SystemMessage.SUCCESS);
		statusListInfo.setStatus(true);
		statusListInfo.setTotal(userService.getTotal(request));
		
		return statusListInfo;
	}
	
	
	@RequestMapping(value = "/user/dlist", method = RequestMethod.GET, produces = "application/json")
	public StatusListInfo getDefinedList(HttpServletRequest request, HttpServletResponse response){
		RestCorsHttpServletResponse.setResponse(response);
		StatusListInfo statusListInfo = new StatusListInfo();
		List<UserInfo> userInfos = userService.getDefinedList(request);
		
		statusListInfo.setList(userInfos);
		statusListInfo.setMessage(SystemMessage.SUCCESS);
		statusListInfo.setStatus(true);
		statusListInfo.setTotal(userService.getTotal(request));
		
		return statusListInfo;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public StatusSingleInfo add(@RequestBody UserInfo userInfo, HttpServletResponse response){
		RestCorsHttpServletResponse.setResponse(response);
		StatusSingleInfo statusSingleInfo = new StatusSingleInfo();
		
		statusSingleInfo.setId(0);
		statusSingleInfo.setStatus(false);
		
		if (userInfo.getPersonId() == null || 
				userInfo.getPersonId() <= 0) {
			statusSingleInfo.setMessage(SystemMessage.ERROR);
			return statusSingleInfo;
		}
		
		User user = new User();
		
		Person person = new Person();
		person.setId(userInfo.getPersonId());
		
		user.setPerson(person);
		user.setPassword(userInfo.getPassword());
		user.setDateCreated(new Date());
		
		user = userService.add(user);
		
		statusSingleInfo.setId(user.getId());
		statusSingleInfo.setMessage(SystemMessage.SUCCESS);
		statusSingleInfo.setStatus(true);
		
		return statusSingleInfo;
		
	}
}
