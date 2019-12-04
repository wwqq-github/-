package cn.tedu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cn.tedu.entity.Cart;
import cn.tedu.entity.ResponseResult;
import cn.tedu.entity.vo.CartAndGoodsVO;

@RestController 
@RequestMapping("/cart")
public class CartController extends ControllerBase {
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/add")
	public ResponseResult<Void> addCart(@RequestParam("gid")Long gid,@RequestParam("num")Integer num,HttpSession session){
		Cart cart=new Cart();
		Integer uid=getUidFromSession(session);
		cart.setUid(uid);
		cart.setGid(gid);
		cart.setNum(num);
		return restTemplate.postForObject(getURL()+"/cart/add",cart, ResponseResult.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/all")
	public ResponseResult<List<Cart>> allCart(HttpSession session){
		Integer uid=getUidFromSession(session);
		return restTemplate.getForObject(getURL()+"/cart/all/{uid}", ResponseResult.class,uid);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/get_pitch")
	public ResponseResult<List<CartAndGoodsVO>> getPitchCart(@RequestParam("cids")Integer[] cids){	
		String url=getURL()+"/cart/get_pitch?";
		for(int i=0;i<cids.length;i++) {
			url=url+"cids="+cids[i]+"&";
			
		}
		url=url.substring(0,url.length() - 1);
		return restTemplate.getForObject(url, ResponseResult.class);
	}
}
