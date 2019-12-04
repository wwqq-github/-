package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.entity.ResponseResult;
import cn.tedu.entity.vo.OrderVO;
import cn.tedu.service.OrderService;

@RequestMapping("/order")
@RestController
public class OderClntroller extends ControllerBase{
	
	@Autowired
	private OrderService orderService;
	@RequestMapping(value="/add")
	public ResponseResult<Integer> addOrder(@RequestBody OrderVO ordervo){
		System.out.println(ordervo.toString());
		Integer oid=orderService.addOrder(ordervo);
		return new ResponseResult<Integer>(SUCCEES,oid);
	}
}
