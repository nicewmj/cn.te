import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameConflictException;

public class UserServiceTest {
	
	@Test
	public void reg() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-service.xml",
				"spring-dao.xml");
		
		IUserService userService
			= ac.getBean("userService", IUserService.class);
		
		try {
			User user = new User();
			user.setUsername("spring");
			user.setPassword("1234");
			User result = userService.reg(user);
			System.out.println(result);
		} catch(UsernameConflictException e) {
			System.out.println(e.getMessage());
		}
		
		ac.close();
	}
	
	@Test
	public void login() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-service.xml",
				"spring-dao.xml");
		
		IUserService userService
			= ac.getBean("userService", IUserService.class);

		try {
			String username = "mysql";
			String password = "123456";
			User user = userService.login(username, password);
			System.out.println(user);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (PasswordNotMatchException e) {
			System.out.println(e.getMessage());
		}
		
		ac.close();
	}
	
	@Test
	public void changePassword() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-service.xml",
				"spring-dao.xml");
		
		IUserService userService
			= ac.getBean("userService", IUserService.class);
		
		try {
			Integer uid = 5;
			String oldPassword = "123456";
			String newPassword = "456456";
			Integer rows = 
					userService.changePassword(
						uid, oldPassword , newPassword);
			System.out.println(rows);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (PasswordNotMatchException e) {
			System.out.println(e.getMessage());
		}
		
		ac.close();
	}
	@Test
	public void changeInfo() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-service.xml",
				"spring-dao.xml");
		
		IUserService userService
			= ac.getBean("userService", IUserService.class);
		
		try {
			Integer uid = 5;
			String username = "spring-mvc";
			Integer gender = null;
			String phone = null;
			String email = null;
			Integer rows = 
					userService.changeInfo(
						uid, "", username, gender, 
						phone, email);
			System.out.println(rows);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (UsernameConflictException e) {
			System.out.println(e.getMessage());
		}
		
		ac.close();
	}

}
