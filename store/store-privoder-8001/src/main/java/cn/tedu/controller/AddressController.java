package cn.tedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.entity.Address;
import cn.tedu.entity.ResponseResult;
import cn.tedu.service.AddressService;

@RequestMapping("/address")
@RestController
public class AddressController extends ControllerBase{
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping(value="/add")
	public ResponseResult<Void> addAddress(@RequestBody  Address address){
		addressService.addAddress(address);
		return new ResponseResult<Void>(SUCCEES,"添加收获地址成功");
	}
	/**
	 * 查询用户收获地址
	 */
	@RequestMapping(value="/list/{uid}")
	public ResponseResult<List<Address>> findByUid(@PathVariable("uid") Integer uid){
		List<Address> result=addressService.findByUid(uid);
		return new ResponseResult<List<Address>>(SUCCEES,result);
	}
	
	/**
	 * 修改用户默认的收获地址
	 */
	@RequestMapping(value="/set_default/{uid}/{aid}")
	public ResponseResult<List<Address>> findByUid(@PathVariable("uid") Integer uid,@PathVariable("aid") Integer aid){
		addressService.changeDefault(uid, aid);
		return new ResponseResult<List<Address>>(SUCCEES,"修改成功");
	}
}
