package cn.tedu.service;

import java.util.List;

import cn.tedu.entity.Address;

/**
 * 收获地址
 *
 */
public interface AddressService {
	/**
	 * 添加用户收获地址
	 * @param address
	 */
	public void addAddress(Address address);
	
	/**
	 * 查询用户收获地址
	 */
	public List<Address> findByUid(Integer uid);
	
	/**
	 * 修改默认收获地址
	 * @param uid
	 * @param aid
	 */
	public void changeDefault(Integer uid,Integer aid);
}
