package cn.tedu.store.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.service.ex.InsertDataException;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private ICartService cartService;
	@Autowired
	private IAddressService addressService;

	public Integer createOrder(
			Integer uid, Integer addressId, Integer[] cartIds) {
		// ׼����������Ķ�����Ʒ����
		List<OrderItem> orderItems
			= new ArrayList<OrderItem>();
		// ׼������total_price
		Long totalPrice = 0L;
		// ����cartIds����һ��ȡ���ﳵ��Ϣ���õ�������Ʒ����������
		for (Integer cartId : cartIds) {
			// ��ȡ���ﳵ�е�����
			Cart cart = cartService.getCartById(cartId);
			// �ۼ���Ʒ�ļ۸�
			totalPrice += 
				cart.getGoodsPrice() * cart.getGoodsNum();
			// ׼��OrderItem���ݣ����������������
			OrderItem orderItem = new OrderItem();
			orderItem.setGoodsId(cart.getGoodsId());
			orderItem.setGoodsImage(cart.getGoodsImage());
			orderItem.setGoodsNum(cart.getGoodsNum());
			orderItem.setGoodsPrice(cart.getGoodsPrice());
			orderItem.setGoodsTitle(cart.getGoodsTitle());
			// ��OrderItem������ӵ�������
			orderItems.add(orderItem);
		}

		// �ջ������Ϣ��addressId��ѯ��õ�
		Address address
			= addressService.getAddressById(addressId);
		
		// status�̶�Ϊ1����ʾĬ�ϵġ�δ֧����
		Integer status = 1; // 1:δ֧��
		
		// create_timeȡ��ǰϵͳʱ��
		Date now = new Date();
		
		
		// ���붩������
		Order order = new Order();
		order.setUid(uid);
		order.setRecvName(address.getRecvName());
		order.setRecvPhone(address.getRecvPhone());
		order.setRecvAddress(
				address.getRecvDistrict() + " " 
						+ address.getRecvAddress());
		order.setStatus(status);
		order.setCreateTime(now);
		order.setPayTime(null);
		order.setTotalPrice(totalPrice);
		insertOrder(order);
		
		// ��ȡ�²���Ķ������ݵ�id
		Integer orderId = order.getId();

		// ���붩����Ʒ����
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrderId(orderId);
			insertOrderItem(orderItem);
		}
		
		// ����
		return orderId;
	}
	
	public Integer insertOrder(Order order) {
		Integer rows = orderMapper.insertOrder(order);
		if (rows == 1) {
			return rows;
		} else {
			throw new InsertDataException("������������ʧ�ܣ�");
		}
	}

	public Integer insertOrderItem(OrderItem orderItem) {
		Integer rows = orderMapper.insertOrderItem(orderItem);
		if (rows == 1) {
			return rows;
		} else {
			throw new InsertDataException("����������Ʒ����ʧ�ܣ�");
		}
	}

}
