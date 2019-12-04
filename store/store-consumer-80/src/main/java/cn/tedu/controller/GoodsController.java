package cn.tedu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cn.tedu.entity.Goods;
import cn.tedu.entity.ResponseResult;

@RestController
@RequestMapping("/goods")
public class GoodsController extends ControllerBase {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getlist")
	public ResponseResult<List<Goods>> getList(@RequestParam(name = "search",required = false,defaultValue =" ")String title,@RequestParam(name = "page",required = false,defaultValue = 1+"")Integer page){
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("title",title);
		param.put("page", page);
		return restTemplate.getForObject(getURL()+"/goods/getlist/{title}/{page}", ResponseResult.class,param);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/details/{id}")
	public ResponseResult<Goods> getGoods(@PathVariable("id")String id){
		return restTemplate.getForObject(getURL()+"/goods/details/{id}", ResponseResult.class,id);
	}
}
