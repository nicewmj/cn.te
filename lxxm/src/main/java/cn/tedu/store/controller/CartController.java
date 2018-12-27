package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.ex.ServiceException;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {
	
	@Autowired
	private ICartService cartService;
	
	@RequestMapping("/list.do")
	public String showList(ModelMap modelMap,
			HttpSession session) {
		// ��ȡ��ǰ��¼���û���id
		Integer uid = getUidFromSession(session);
		
		// ��ȡ��ǰ�û��Ĺ��ﳵ����
		List<Cart> carts
			= cartService.getListByUid(uid);
		
		// ��װ���ݣ���ǰ�û��Ĺ��ﳵ����
		modelMap.addAttribute("carts", carts);
		
		// ִ��ת��
		return "cart_list";
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public ResponseResult<Void> handleAddToCart(
		@RequestParam("goods_id") Long goodsId,
		Integer num,
		HttpSession session) {
		// ����ResponseResult<Void>����ֵ
		ResponseResult<Void> rr;
		
		// ��session�л�ȡuid
		Integer uid = getUidFromSession(session);
		
		try {
			// ����ҵ���ġ���ӵ����ﳵ������
			cartService.addToCart(uid, goodsId, num);
			// �����ɹ�����������ֵ���󣬱��stateΪ�ɹ���������
			rr = new ResponseResult<Void>(
					ResponseResult.STATE_OK);
		} catch (ServiceException e) {
			// ����ʧ�ܣ���������ֵ���󣬱��stateΪʧ�ܣ���װmessage������Ϣ��������
			rr = new ResponseResult<Void>(e);
		}
		return rr;
	}
	
}








