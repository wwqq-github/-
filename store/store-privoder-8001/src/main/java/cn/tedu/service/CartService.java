package cn.tedu.service;

import java.util.List;

import cn.tedu.entity.Cart;
import cn.tedu.entity.vo.CartAndGoodsVO;

public interface CartService {

	/**
	 * 添加商品订单
	 * @param cart
	 */
	public void addCatr(Cart cart);
	
	/**
	 * 查询用户所有购物车数据
	 */
	public List<Cart> findByUid(Integer uid);
	
	/**
     * 根据购物车id查询购物车数据
     */
	public List<CartAndGoodsVO> findBycids(Integer[] cids);
}
