package cn.tedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.entity.Cart;
import cn.tedu.entity.ResponseResult;
import cn.tedu.entity.vo.CartAndGoodsVO;
import cn.tedu.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController extends ControllerBase {
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/add")
	public ResponseResult<Void> addCart(@RequestBody Cart cart){
		System.err.println(cart.toString());
		cartService.addCatr(cart);
		return new ResponseResult<Void>(SUCCEES,"Ìí¼Ó³É¹¦");
	}
	
	@RequestMapping("/all/{uid}")
	public ResponseResult<List<Cart>> allCart(@PathVariable("uid")Integer uid){
		List<Cart> allCart=cartService.findByUid(uid);
		return new ResponseResult<List<Cart>>(SUCCEES,allCart);
	}
	
	@RequestMapping("/get_pitch")
	public ResponseResult<List<CartAndGoodsVO>> getPitchCart(@RequestParam("cids")Integer[] cids){
		List<CartAndGoodsVO> allCart=cartService.findBycids(cids);
		return new ResponseResult<List<CartAndGoodsVO>>(SUCCEES,allCart);
	}
}
