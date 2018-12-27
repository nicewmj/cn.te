import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;

public class AddressMapperTest {

	@Test
	public void insert() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-dao.xml");
		
		AddressMapper addressMapper 
			= ac.getBean(
				"addressMapper", 
				AddressMapper.class);
		
		Address address = new Address();
		address.setUid(1);
		address.setRecvName("¡ı¿œ ¶");
		Integer rows = addressMapper.insert(address);
		System.out.println("rows=" + rows);
		
		ac.close();
	}
	
	@Test
	public void getAddressList() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-dao.xml");
		
		AddressMapper addressMapper 
			= ac.getBean(
				"addressMapper", 
				AddressMapper.class);
		
		Integer uid = 2;
		List<Address> addresse 
			= addressMapper.getAddressList(uid);
		for (Address address : addresse) {
			System.out.println(address);
		}
		
		ac.close();
	}
	
	@Test
	public void getAddressCountByUid() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-dao.xml");
		
		AddressMapper addressMapper 
			= ac.getBean(
				"addressMapper", 
				AddressMapper.class);
		
		Integer uid = 3;
		Integer count 
			= addressMapper.getAddressCountByUid(uid);
		System.out.println("count=" + count);
		
		ac.close();
	}
	
}
