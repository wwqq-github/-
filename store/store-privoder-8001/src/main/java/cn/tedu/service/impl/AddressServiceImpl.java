package cn.tedu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.entity.Address;
import cn.tedu.entity.User;
import cn.tedu.mapper.AddressMapper;
import cn.tedu.mapper.UserMapper;
import cn.tedu.service.AddressService;
import cn.tedu.service.exception.SQLException;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private UserMapper userMapper;
	@Override
	public void addAddress(Address address) {
		Address defaultAddress=addressMapper.findByDefault(address.getUid());
		address.setIsDefault(0);
		if(defaultAddress==null) {
			address.setIsDefault(1);
		}
		User userResult=userMapper.selectByPrimaryKey(address.getUid());
		if(userResult==null) {
			throw new SQLException("用户不存在");
		}
		address.setCreatedUser(userResult.getUsername());
		address.setModifiedUser(userResult.getUsername());
		Date now=new Date();
		address.setCreatedTime(now);
		address.setModifiedTime(now);
		Integer rows=addressMapper.insertSelective(address);
		if(rows!=1) {
			throw new SQLException("添加收获地址失败");
		}
	}
	@Override
	public List<Address> findByUid(Integer uid) {
		return addressMapper.findByUid(uid);
	}
	@Override
	@Transactional
	public void changeDefault(Integer uid, Integer aid) {
		addressMapper.setNoDefault(uid);
		addressMapper.setOneDefault(uid, aid);
	}

}
