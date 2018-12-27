package cn.tedu.store.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.service.IGoodsCategoryService;
import cn.tedu.store.service.IGoodsService;

@Controller
@RequestMapping("/main")
public class MainController 
	extends BaseController {
	
	@Autowired
	private IGoodsCategoryService goodsCategoryService;
	@Autowired
	private IGoodsService goodsService;

	@RequestMapping("/index.do")
	public String showIndex(ModelMap modelMap) {
	    // ***** ������ر��� *****
		// ���Եķ����б�
	    Long[] parentIds = {162L, 171L, 186L};
	    // �������Եķ���id
	    Long hotGoodsCategoryId = 163L;
	    // ��Ҫ��ʾ��������Ʒ������
	    Integer hotGoodsCount = 3;

	    // ��ȡ���ݣ����Ե�3���¼������б�
	    List<List<GoodsCategory>> goodsCategoiesList
	        = new ArrayList<List<GoodsCategory>>();
	    for (int i = 0; i < parentIds.length; i++) {
	        List<GoodsCategory> goodsCategoies
	            = goodsCategoryService
	                .getListByParentId(parentIds[i]);
	        goodsCategoiesList.add(goodsCategoies);
	    }
		// ��ȡ���ݣ�������3̨����
	    List<Goods> goodsList
	    	= goodsService.getHotGoodsList(
	    		hotGoodsCategoryId, hotGoodsCount);

	    // ��װ��ҳ��ʾ��Ҫ������
	    modelMap.addAttribute(
	        "goodsCategoiesList", goodsCategoiesList);
	    modelMap.addAttribute("goodsList", goodsList);

	    // ִ��ת��
	    return "index"; // ת����index.jsp
	}

}











