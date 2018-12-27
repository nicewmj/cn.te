package cn.tedu.store.service;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;

public interface IOrderService {
	
	/**
	 * ��������
	 * @param uid ��ǰ��¼���û�id
	 * @param addressId ѡ�����ջ���ַid
	 * @param cartIds ѡ���Ĺ��ﳵ�е�����id
	 * @return ������
	 */
	Integer createOrder(
		Integer uid, Integer addressId, Integer[] cartIds);
	
	/**
	 * ������������
	 * @param order ��������
	 * @return ��Ӱ�������
	 * @see #insert(OrderItem)
	 */
	Integer insertOrder(Order order);

	/**
	 * ����������Ʒ����
	 * @param order ������Ʒ����
	 * @return ��Ӱ�������
	 * @see #insert(Order)
	 */
	Integer insertOrderItem(OrderItem orderItem);
}
