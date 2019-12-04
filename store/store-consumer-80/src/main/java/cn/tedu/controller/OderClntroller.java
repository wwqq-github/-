package cn.tedu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cn.tedu.entity.ResponseResult;
import cn.tedu.entity.vo.OrderVO;

@RequestMapping("/order")
@RestController
public class OderClntroller extends ControllerBase{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/add")
	public ResponseResult<Void> addOrder(@RequestParam("cids")Integer cids[],
										 @RequestParam("price")Long price,
										 @RequestParam("address")String address,
										 HttpSession session){
		Integer uid=getUidFromSession(session);
		OrderVO result=new OrderVO();
		System.out.println(address);
		String[] param=address.split("-");
		result.setName(param[0]);
		result.setUid(uid);
		result.setCids(cids);
		result.setAddress(param[2]+param[3]);
		result.setPhone(param[4]);
		result.setPrice(price);
		return restTemplate.postForObject(getURL()+"/order/add", result,ResponseResult.class);
	}
}
