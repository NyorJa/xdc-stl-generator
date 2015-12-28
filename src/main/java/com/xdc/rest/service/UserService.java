package com.xdc.rest.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdc.rest.dao.UserDao;
import com.xdc.rest.entity.User;
import com.xdc.rest.model.UserInfo;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public User add(User user){return userDao.add(user);}
	public User get(int id){return userDao.get(id);}
	public User update(User user){return userDao.update(user);}
	public List<User> getAll(HttpServletRequest request){return userDao.getAll(request);}
	public Long getTotal(HttpServletRequest request){return userDao.getCount(request);}
	public List<UserInfo> getDefinedList(HttpServletRequest request){
		List<UserInfo> userInfos = new ArrayList<>();
		
		List<Object[]> results = userDao.getDefinedList(request);
		
		for (Object[] res : results) {
			
			UserInfo userInfo = new UserInfo();
			
			userInfo.setUserId((Integer) res[0]);
			userInfo.setPersonId((Integer) res[1]);
			userInfo.setUsername((String) res[2]);
			userInfo.setPassword((String) res[3]);
			userInfo.setPersonName((String) res[4]);
			userInfo.setPersonAddress((String) res[5]);
			userInfo.setDateCreated((Date) res[6]);
			userInfo.setDateModified((Timestamp) res[7]);
			
			userInfos.add(userInfo);
		}
		
		return userInfos;
	}
}
