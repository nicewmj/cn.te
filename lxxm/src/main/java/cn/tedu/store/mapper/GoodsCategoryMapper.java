package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.GoodsCategory;

public interface GoodsCategoryMapper {

	/**
	 * ������Ʒ����ĸ���id����ȡ��Ʒ������б�
	 * @param parentId ��Ʒ����ĸ���id
	 * @return ��Ʒ������б�
	 */
	List<GoodsCategory> getListByParentId(Long parentId);
	
}





