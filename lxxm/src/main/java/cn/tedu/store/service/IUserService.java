package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameConflictException;

public interface IUserService {
	//好样的
	User reg(User user) 
		throws UsernameConflictException;
	
}




