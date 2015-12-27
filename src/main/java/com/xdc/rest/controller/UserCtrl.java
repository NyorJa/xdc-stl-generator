package com.xdc.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xdc.rest.entity.User;
import com.xdc.rest.model.StatusListInfo;
import com.xdc.rest.model.UserInfo;
import com.xdc.rest.service.UserService;
import com.xdc.rest.utils.SystemMessage;

@RequestMapping("/rest")
@RestController
public class UserCtrl {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/user/list", method = RequestMethod.GET, produces = "application/json")
	public StatusListInfo getAll(HttpServletRequest request, HttpServletResponse response){
		StatusListInfo statusListInfo = new StatusListInfo();
		
		List<UserInfo> userInfos = new ArrayList<>();
		List<User> users = userService.getAll(request);
		
		for (User user : users) {
			
			UserInfo userInfo = new UserInfo();
			
			userInfo.setUserId(user.getId());
			userInfo.setPersonId(user.getPerson().getId());
			userInfo.setUsername(user.getUsername());
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
}
