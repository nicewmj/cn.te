package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.service.IGoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {
	
	@Autowired
	private IGoodsService goodsService;
	
	@RequestMapping("/list.do")
	public String showGoodsList(
		@RequestParam("categoryId") Long categoryId, 
		Integer page, ModelMap modelMap) {
		// ����ҵ����ȡ����
		List<Goods> goodsList
			= goodsService
				.getListByCategoryId(categoryId, page);
		// ����ҵ����ȡ���ݵ����������������ҳ��
		Integer goodsCount
			= goodsService.getCountByCategoryId(categoryId);
		int maxPage = (int) Math.ceil(
				1.0 * goodsCount / IGoodsService.COUNT_PER_PAGE);
		
		// ��װ���ݣ���Ʒ�б���ת��
		modelMap.addAttribute("goodsList", goodsList);
		// ��װ���ݣ����ҳ������ת��
		modelMap.addAttribute("maxPage", maxPage);
		// ��װ���ݣ���ǰ�鿴����Ʒ�ķ���id����ת��
		modelMap.addAttribute("categoryId", categoryId);
		
		// ִ��ת��
		return "goods_list"; // goods_list.jsp 
	}
	
	@RequestMapping("/details.do")
	public String showDetails(
		@RequestParam("id") Long id,
		ModelMap modelMap) {
		// ��ȡ��Ʒ����
		Goods goods = goodsService.getGoodsById(id);
		
		// ��װ����Ʒ���ݣ���ת��
		modelMap.addAttribute("goods", goods);
		
		// ִ��ת��
		return "goods_details";
	}
	
}


