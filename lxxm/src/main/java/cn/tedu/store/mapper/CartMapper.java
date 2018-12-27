package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;

public interface CartMapper {

	/**
	 * ���ﳵ�����������
	 * 
	 * @param cart
	 *            ���ﳵ��Ϣ
	 * @return ��Ӱ�������
	 */
	Integer insert(Cart cart);

	/**
	 * �޸Ĺ��ﳵ��ĳ�û���ĳ��Ʒ������
	 * 
	 * @param id
	 *            ���ﳵ����id
	 * @param goodsNum
	 *            ��Ʒ��������
	 * @return ��Ӱ�������
	 */
	Integer changeGoodsNum(@Param("id") Integer id, @Param("goodsNum") Integer goodsNum);

	/**
	 * �����û�id����Ʒid����ȡ���ﳵ�е�����
	 * 
	 * @param uid
	 *            �û�id
	 * @param goodsId
	 *            ��Ʒid
	 * @return ƥ������ݣ����û��ƥ������ݣ��򷵻�null
	 */
	Cart getCartByUidAndGoodsId(@Param("uid") Integer uid, @Param("goodsId") Long goodsId);

	/**
	 * ��ȡĳ�û��Ĺ��ﳵ�����б�
	 * 
	 * @param uid
	 *            �û�id
	 * @return ���ﳵ�����б�
	 */
	List<Cart> getListByUid(Integer uid);
	
	/**
	 * ��������id��ȡ���ﳵ����
	 * @param id ����id
	 * @return ���ﳵ����
	 */
	Cart getCartById(Integer id);

}
