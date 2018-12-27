import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ex.ServiceException;

public class AddressServiceTest {

	@Test
	public void add() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-dao.xml", "spring-service.xml");
		
		IAddressService addressService 
			= ac.getBean(
				"addressService", 
				IAddressService.class);
		
		Address address = new Address();
		address.setUid(1);
		address.setRecvName("刘老师");
		Address result = addressService.add(
				"mybatis", address);
		System.out.println("result=" + result);
		
		ac.close();
	}
	
	@Test
	public void setDefault() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-dao.xml", "spring-service.xml");
		
		IAddressService addressService 
			= ac.getBean(
				"addressService", 
				IAddressService.class);
		
		try {
			Integer uid = 2;
			Integer id = 2;
			addressService.setDefault(uid, id);
			System.out.println("操作完成");
		} catch(ServiceException e) {
			System.out.println(e.getMessage());
		}
		
		ac.close();
	}
	
}
