package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Goods;

public interface GoodsMapper {

	/**
	 * ��ȡ������Ʒ�б�
	 * @param categoryId ��Ʒ����id
	 * @param count ��Ʒ������
	 * @return ָ��������ָ�������������Ʒ�ļ���
	 */
	List<Goods> getHotGoodsList(
			@Param("categoryId") Long categoryId, 
			@Param("count") Integer count);
	
	/**
	 * ���ݷ���id��ȡ��Ʒ�б�
	 * @param categoryId ����id
	 * @param offset ��ȡ����ʱ��ƫ����
	 * @param count ��ȡ���̿ڵ�����
	 * @return ��Ʒ�б�
	 */
	List<Goods> getListByCategoryId(
			@Param("categoryId") Long categoryId,
			@Param("offset") Integer offset,
			@Param("count") Integer count);
	
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



