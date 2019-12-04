package cn.tedu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.entity.Order;
import cn.tedu.entity.OrderItem;
import cn.tedu.entity.vo.CartAndGoodsVO;
import cn.tedu.entity.vo.OrderVO;
import cn.tedu.mapper.CartMapper;
import cn.tedu.mapper.OrderItemMapper;
import cn.tedu.mapper.OrderMapper;
import cn.tedu.service.OrderService;
import cn.tedu.service.exception.SQLException;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderItemMapper orderItemMapper;
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Override
	@Transactional
	public Integer addOrder(OrderVO ordervo) {
		ordervo.setStatus(0);
		Date now=new Date();
		Order order=new Order();
		order.setUid(ordervo.getUid());
		order.setModifiedTime(now);
		order.setCreatedTime(now);
		order.setOrderTime(now);
		order.setAddress(ordervo.getAddress());
		order.setPhone(ordervo.getPhone());
		order.setPrice(ordervo.getPrice());
		order.setName(ordervo.getName());
		int oid=orderMapper.insert(order);
		List<CartAndGoodsVO> carts=cartMapper.findBycids(ordervo.getCids());
		List<OrderItem> orderItems=new ArrayList<OrderItem>();
		for(CartAndGoodsVO cart:carts) {
			OrderItem orderItem=new OrderItem();
			orderItem.setOid(oid);
			Date itemtime=new Date();
			orderItem.setCreatedTime(itemtime);
			orderItem.setModifiedTime(itemtime);
			orderItem.setGid(cart.getGid());
			orderItem.setNum(cart.getNum());
			orderItem.setPrice(cart.getPrice());
			orderItem.setImage(cart.getImage());
			orderItem.setTitle(cart.getTitle());
			orderItems.add(orderItem);
		}
		Integer rows=orderItemMapper.addList(orderItems);
		if(rows<=0) {
			throw new SQLException("Ìí¼Ó¶©µ¥Ê§°Ü");
		}
		return oid;
	}

}
