package cn.tedu.service;

import java.util.List;

import cn.tedu.entity.Goods;

public interface GoodsService {
	/**
	 * 模糊查询所有
	 * @param title
	 * @param page
	 * @return
	 */
	public List<Goods> findByTitleList(String title,Integer page);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Goods findById(String id);
}
