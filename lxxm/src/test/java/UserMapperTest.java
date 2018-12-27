import java.util.Date;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;

public class UserMapperTest {

	@Test
	public void insert() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-dao.xml");
		
		UserMapper userMapper
			= ac.getBean("userMapper", UserMapper.class);
		
		User user = new User();
		user.setUsername("root");
		user.setPassword("1234");
		userMapper.insert(user);
		System.out.println(user);
		
		ac.close();
	}
	
	
	
	@Test
	public void findUserByUsername() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-dao.xml");
		
		UserMapper userMapper
			= ac.getBean("userMapper", UserMapper.class);
		
		User user
			= userMapper.findUserByUsername("root");
		System.out.println(user);
		
		ac.close();
	}
	
	@Test
	public void findUserById() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-dao.xml");
		
		UserMapper userMapper
			= ac.getBean("userMapper", UserMapper.class);
		
		User user
			= userMapper.findUserById(1);
		System.out.println(user);
		
		ac.close();
	}
	@Test
	public void changePassword() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-dao.xml");
		
		UserMapper userMapper
			= ac.getBean("userMapper", UserMapper.class);
		
		Integer uid = 1;
		String password = "123456";
		Integer rows
			= userMapper.changePassword(uid, password, null, null);
		System.out.println("rows=" + rows);
		
		ac.close();
	}
	
	@Test
	public void changeInfo() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-dao.xml");
		
		UserMapper userMapper
			= ac.getBean("userMapper", UserMapper.class);
		
		User user = new User();
		user.setId(5);
		user.setUsername("spring-mvc");
		// user.setGender(1);
		// user.setPhone("13800138005");
		user.setEmail("spring-mvc@tedu.cn");
		user.setModifiedUser(user.getUsername());
		user.setModifiedTime(new Date());
		Integer rows
			= userMapper.changeInfo(user);
		System.out.println("rows=" + rows);
		
		ac.close();
	}
	
}
