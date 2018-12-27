package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Cart;

public interface ICartService {

	/**
	 * ��ӵ����ﳵ
	 * @param uid ��ǰ��¼���û�id
	 * @param goodsId ��Ʒ��id
	 * @param num ��Ʒ������
	 * @throws IllegalParamException ��Ʒ��������
	 */
	void addToCart(Integer uid, Long goodsId, Integer num);
	
	/**
	 * �޸Ĺ��ﳵ��ĳ�û���ĳ��Ʒ������
	 * @param id ���ﳵ����id
	 * @param goodsNum ��Ʒ��������
	 * @return ��Ӱ�������
	 */
	Integer changeGoodsNum(Integer id, Integer goodsNum);
	
	/**
	 * �����û�id����Ʒid����ȡ���ﳵ�е�����
	 * @param uid �û�id
	 * @param goodsId ��Ʒid
	 * @return ƥ������ݣ����û��ƥ������ݣ��򷵻�null
	 */
	Cart getCartByUidAndGoodsId(Integer uid, Long goodsId);
	
	/**
	 * ��ȡĳ�û��Ĺ��ﳵ�����б�
	 * @param uid �û�id
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
