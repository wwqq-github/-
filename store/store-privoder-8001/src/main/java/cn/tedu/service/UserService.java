package cn.tedu.service;

import cn.tedu.entity.User;

/**
 * 用户类持久层
 */
public interface UserService {
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean addUser(User user);
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User userLogin(User user);
	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	public User changePassword(String oldPassword,String newPassword,Integer uid);
	
	/**
	 * 根据uid查询用户信息
	 */
	public User infoUser(Integer uid);
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public void changeUser(User user);
	/**
	 * 上传头像
	 * @param avatar
	 * @param uid
	 */
	public void changeAvatar(String avatar,Integer uid);
}
