package cn.tedu.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	public static final long MAX_AVATAR_SIZE = 50;
	
	@RequestMapping("/reg.do")
	public String showReg() {
		return "user_reg";
	}
}









