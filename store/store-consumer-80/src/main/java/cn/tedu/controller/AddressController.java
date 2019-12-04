package cn.tedu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cn.tedu.entity.Address;
import cn.tedu.entity.ResponseResult;

@RequestMapping("/address")
@RestController
public class AddressController extends ControllerBase{

	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/add")
	public ResponseResult<Void> addAddress(Address address,HttpSession session){
		address.setUid(getUidFromSession(session));
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<Address> request=new HttpEntity<>(address,headers);
		return restTemplate.postForObject(getURL()+"/address/add", request, ResponseResult.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ResponseResult<Void> findByUid(HttpSession session){
		Integer uid=getUidFromSession(session);
		return restTemplate.getForObject(getURL()+"/address/list/{uid}", ResponseResult.class,uid);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/{aid}/set_default")
	public ResponseResult<Void> findByUid(HttpSession session,@PathVariable("aid")Integer aid){
		Integer uid=getUidFromSession(session);
		Map<String,Integer> param=new HashMap<String,Integer>();
		param.put("aid", aid);
		param.put("uid", uid);
		return restTemplate.getForObject(getURL()+"/address/set_default/{uid}/{aid}", ResponseResult.class,param);
	}
}
