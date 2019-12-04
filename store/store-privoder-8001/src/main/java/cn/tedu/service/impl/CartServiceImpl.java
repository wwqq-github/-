package cn.tedu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.entity.Cart;
import cn.tedu.entity.vo.CartAndGoodsVO;
import cn.tedu.mapper.CartMapper;
import cn.tedu.service.CartService;
import cn.tedu.service.exception.SQLException;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public void addCatr(Cart cart) {
		Date date=new Date();
		cart.setModifiedTime(date);
		cart.setCreatedTime(date);
		Integer rows=cartMapper.insert(cart);
		if(rows!=1) {
			throw new SQLException("Ìí¼Ó¹ºÎï³µÊ§°Ü");
		}
	}

	@Override
	public List<Cart> findByUid(Integer uid) {
		return cartMapper.findByUid(uid);
	}

	@Override
	public List<CartAndGoodsVO> findBycids(Integer[] cids) {
		System.err.println(cids.length);
		return cartMapper.findBycids(cids);
	}

}
