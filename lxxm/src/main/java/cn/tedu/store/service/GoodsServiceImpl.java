package cn.tedu.store.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.GoodsMapper;

@Service("goodsService")
public class GoodsServiceImpl 
	implements IGoodsService {
	
	@Autowired
	private GoodsMapper goodsMapper;

	public List<Goods> getHotGoodsList(
			Long categoryId, Integer count) {
		return goodsMapper.getHotGoodsList(
				categoryId, count);
	}
	
	public List<Goods> getListByCategoryId(
			Long categoryId, Integer page) {
		// �ж�page����
		if (page == null || page < 1) {
			page = 1;
		}
		Integer goodsCount = getCountByCategoryId(
				categoryId);
		if (goodsCount == 0) {
			return new ArrayList<Goods>();
		}
		// int maxPage = goodsCount / COUNT_PER_PAGE;
		// if (goodsCount % COUNT_PER_PAGE != 0) {
		// maxPage++;
		// }
		int maxPage = (int) Math.ceil(
				1.0 * goodsCount / COUNT_PER_PAGE);
		if (page > maxPage) {
			page = maxPage;
		}
		// �����ȡ����ʱ��ƫ����
		Integer offset = (page - 1) * COUNT_PER_PAGE;
		// ���ó־ò�����ȡ����
		List<Goods> goodsList
			= goodsMapper.getListByCategoryId(
				categoryId, offset, COUNT_PER_PAGE);
		// ����
		return goodsList;
	}

	public Integer getCountByCategoryId(Long categoryId) {
		return goodsMapper.getCountByCategoryId(
				categoryId);
	}

	public Goods getGoodsById(Long id) {
		return goodsMapper.getGoodsById(id);
	}

}
