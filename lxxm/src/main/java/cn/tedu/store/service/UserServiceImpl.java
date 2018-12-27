package cn.tedu.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired //配置哪里
	private UserMapper userMapper;

	public User reg(User user) {
		
		return user;
	}
}





