package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.GoodsCategory;

public interface IGoodsCategoryService {
	
	/**
	 * ������Ʒ����ĸ���id����ȡ��Ʒ������б�
	 * @param parentId ��Ʒ����ĸ���id
	 * @return ��Ʒ������б�
	 */
	List<GoodsCategory> getListByParentId(Long parentId);
}
