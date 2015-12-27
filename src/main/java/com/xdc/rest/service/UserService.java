package com.xdc.rest.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdc.rest.dao.UserDao;
import com.xdc.rest.entity.User;

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
}
