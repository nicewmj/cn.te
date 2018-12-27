import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.service.IGoodsService;

public class GoodsServiceTest {
	
	@Test
	public void getHotGoodsList() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
					"spring-dao.xml", "spring-service.xml");
		
		IGoodsService goodsService
			= ac.getBean("goodsService", 
					IGoodsService.class);
		
		Long categoryId = 163L;
		Integer count = 5;
		List<Goods> goodsList
			= goodsService.getHotGoodsList(
					categoryId, count);
		
		System.out.println("¡¾Goods List¡¿");
		for (Goods goods : goodsList) {
			System.out.println(goods);
		}
		
		ac.close();
	}
	
	@Test
	public void getListByCategoryId() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-dao.xml", "spring-service.xml");
		
		IGoodsService goodsService
			= ac.getBean("goodsService", 
				IGoodsService.class);
		
		Integer page = null;
		Long categoryId = 163L;
		List<Goods> goodsList
			= goodsService.getListByCategoryId(
					categoryId, page);
		
		System.out.println("¡¾Goods List¡¿");
		for (Goods goods : goodsList) {
			System.out.println(goods);
		}
		
		ac.close();
	}

}
