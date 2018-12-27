import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.service.IOrderService;

public class OrderServiceTest {
	
	@Test
	public void createOrder() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-dao.xml", "spring-service.xml");
		
		IOrderService orderService
			= ac.getBean("orderService", IOrderService.class);
		
		Integer uid = 2;
		Integer addressId = 4;
		Integer[] cartIds = {2, 4, 5};
		Integer orderId 
			= orderService.createOrder(
				uid, addressId, cartIds);
		System.out.println("orderId=" + orderId);
	
		ac.close();
	}

}
