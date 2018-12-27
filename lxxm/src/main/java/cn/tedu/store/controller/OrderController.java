package cn.tedu.store.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ICartService;

@Controller
@RequestMapping("/order")
public class OrderController
	extends BaseController {
	
	@Autowired
	private IAddressService addressService;
	@Autowired
	private ICartService cartService;
	
	@RequestMapping("/info.do")
	public String showInfo(
		@RequestParam("cart_id") Integer[] cartIds,
		HttpSession session, ModelMap modelMap) {
		// ��ȡ��ǰ�û���uid
		Integer uid = getUidFromSession(session);

		// ��ȡ��ǰ�û��������ջ���ַ���б�
		// > IAddressService > List<Address> getAddressList(Integer uid)
		List<Address> addresses
			= addressService.getAddressList(uid);

		// ��ȡcartId������ÿ��id��Ӧ�Ĺ��ﳵ��Ϣ
		// ����List<Cart> carts����
		List<Cart> carts = new ArrayList<Cart>();
		// ����cartId����
		for (Integer cartId : cartIds) {
			// > ���������У�����id��ȡ���ﳵ��Ϣ
			// > ICartService > Cart getCartById(Integer id)
			Cart cart 
				= cartService.getCartById(cartId);
			// > ����ȡ����Cart������ӵ�carts������
			carts.add(cart);
		}

		// ���ջ���ַ�б��װ����ת��
		modelMap.addAttribute("addresses", addresses);
		// �����ﳵ�����б��װ����ת��
		modelMap.addAttribute("carts", carts);
		
		// ��������
		System.out.println("��OrderController > /order/info.do��");
		System.out.println("Request : " + Arrays.toString(cartIds));
		System.out.println("Address List : ");
		for (Address address : addresses) {
			System.out.println("\t" + address);
		}
		System.out.println("Cart List : ");
		for (Cart cart : carts) {
			System.out.println("\t" + cart);
		}
		System.out.println();
				
		// ִ��ת��
		return "order_info";
	}

}





