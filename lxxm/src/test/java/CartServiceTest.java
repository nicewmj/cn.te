import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.ex.ServiceException;

public class CartServiceTest {
	
	@Test
	public void addToCart() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-dao.xml", "spring-service.xml");
		
		ICartService cartService
			= ac.getBean("cartService", ICartService.class);
		
		try {
			Integer uid = 3;
			Long goodsId = 10000042L;
			Integer num = -1;
			cartService.addToCart(uid, goodsId, num);
			System.out.println("成功将商品添加到购物车！");
		} catch(ServiceException e) {
			System.out.println(e.getMessage());
		}
	
		ac.close();
	}

}
