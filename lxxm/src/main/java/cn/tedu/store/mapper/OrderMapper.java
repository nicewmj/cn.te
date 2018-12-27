package cn.tedu.store.mapper;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;

public interface OrderMapper {

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
