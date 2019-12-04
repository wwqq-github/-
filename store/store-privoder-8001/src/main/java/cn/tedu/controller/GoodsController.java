package cn.tedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.entity.Goods;
import cn.tedu.entity.ResponseResult;
import cn.tedu.service.GoodsService;

@RestController
@RequestMapping("/goods")
public class GoodsController extends ControllerBase {
	
	@Autowired
	private GoodsService goodService;
	@RequestMapping("/getlist/{title}/{page}")
	public ResponseResult<List<Goods>> getList(@PathVariable("title")String title,@PathVariable("page")Integer page){
		List<Goods> result=goodService.findByTitleList(title, page);
		return new ResponseResult<List<Goods>>(SUCCEES,result);
	}
	@RequestMapping("/details/{id}")
	public ResponseResult<Goods> getGoods(@PathVariable("id")String id){
		Goods result=goodService.findById(id);
		return new ResponseResult<Goods>(SUCCEES,result);
	}
	
}
