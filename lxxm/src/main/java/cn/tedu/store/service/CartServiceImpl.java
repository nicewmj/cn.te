package cn.tedu.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ex.DataNotFoundException;
import cn.tedu.store.service.ex.IllegalParamException;
import cn.tedu.store.service.ex.InsertDataException;

@Service("cartService")
public class CartServiceImpl implements ICartService {
	
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private CartMapper cartMapper;

	public void addToCart(
			Integer uid, Long goodsId, Integer num) {
		// ��ѯ���ݱ����Ƿ��Ѿ��������ݣ�
		Cart cart = getCartByUidAndGoodsId(uid, goodsId);
	    // �жϷ���ֵ�Ƿ�Ϊnull
		if (cart == null) {
		    // Ϊnull�����û�û�н�����Ʒ��ӵ����ﳵ
		    // ֱ�ӵ���insert()����ִ������
			insert(uid, goodsId, num);
		} else {
			// ��null�����û��Ѿ�������Ʒ��ӵ����ﳵ
			// ����Ҫ������Ʒ������
			// �Ӳ�ѯ����л�ȡ����id��cart.getId()
			Integer id = cart.getId();
			// �Ӳ�ѯ����л�ȡԭ����������
	    	Integer n = cart.getGoodsNum();
	    	// �����µ�����Ӧ���Ƕ���goodsNum = num + n
	    	Integer goodsNum = n + num;
		    // �жϣ�������goodsNum > 0
	    	if (goodsNum > 0) {
	    		// �����޸�����
	    		changeGoodsNum(id, goodsNum);
	    	} else {
	    		// ����goodsNum <= 0�����׳��쳣IllegalParamException
				throw new IllegalParamException(
					"���������ﳵ�е���Ʒ�����޸�ΪС��1��ֵ��");
	    	}
		}
	}

	private void insert(
			Integer uid, Long goodsId, Integer num) {
		// ͨ��goodsId��ȡ��Ʒ��Ϣ��image, price, title
	    Goods goods = goodsService.getGoodsById(goodsId);
	    // ��װCart����
	    Cart cart = new Cart();
	    cart.setUid(uid);
	    cart.setGoodsId(goodsId);
	    cart.setGoodsImage(goods.getImage());
	    cart.setGoodsPrice(goods.getPrice());
	    cart.setGoodsTitle(goods.getTitle());
	    cart.setGoodsNum(num);
	    // ���ԣ���Cart�����з�װ��־��Ϣ
	    // ִ������
	    Integer rows = cartMapper.insert(cart);
	    // �ж����ӽ��
	    if (rows != 1) {
	        throw new InsertDataException(
	            "��ӹ��ﳵ��Ϣʧ�ܣ�" + cart);
	    }
	}

	public Integer changeGoodsNum(Integer id, Integer goodsNum) {
		Integer rows = cartMapper
				.changeGoodsNum(id, goodsNum);
		if (rows == 1) {
			return rows; // return 1;
		} else {
			throw new DataNotFoundException(
				"���Բ����Ĺ��ﳵ����(id=" + id +")�����ڣ�");
		}
	}

	public Cart getCartByUidAndGoodsId(Integer uid, Long goodsId) {
		return cartMapper.getCartByUidAndGoodsId(uid, goodsId);
	}

	public List<Cart> getListByUid(Integer uid) {
		return cartMapper.getListByUid(uid);
	}
	
	public Cart getCartById(Integer id) {
		return cartMapper.getCartById(id);
	}

}
