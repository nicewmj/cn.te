package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Goods;

public interface IGoodsService {
	
	/**
	 * ÿҳ��ʾ����������
	 */
	int COUNT_PER_PAGE = 20;

	/**
	 * ��ȡ������Ʒ�б�
	 * @param categoryId ��Ʒ����id
	 * @param count ��Ʒ������
	 * @return ָ��������ָ�������������Ʒ�ļ���
	 */
	List<Goods> getHotGoodsList(
			Long categoryId, Integer count);
	
	/**
	 * ���ݷ���id��ȡ��Ʒ�б�
	 * @param categoryId ����id
	 * @param page ҳ�룬����ȡ�ڼ�ҳ������
	 * @return ��Ʒ�б�
	 * @see #COUNT_PER_PAGE
	 */
	List<Goods> getListByCategoryId(
			Long categoryId, Integer page);
	
	/**
	 * ��ȡĳ�������µ���Ʒ������
	 * @param categoryId ����id
	 * @return ��Ʒ������
	 */
	Integer getCountByCategoryId(Long categoryId);
	
	/**
	 * ������Ʒid��ȡ��Ʒ��Ϣ
	 * @param id ��Ʒid
	 * @return ��Ʒ��Ϣ�����û��ƥ������ݣ��򷵻�null
	 */
	Goods getGoodsById(Long id);
	
}






