package cn.tedu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.entity.Goods;
import cn.tedu.mapper.GoodsMapper;
import cn.tedu.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	public List<Goods> findByTitleList(String title, Integer page) {
		Integer line=(page-1)*20;
		return goodsMapper.findByTitleList(title, line);
	}

	@Override
	public Goods findById(String id) {
		return goodsMapper.selectByPrimaryKey(id);
	}
}
