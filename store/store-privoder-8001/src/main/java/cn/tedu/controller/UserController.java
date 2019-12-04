package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.entity.ChangePassword;
import cn.tedu.entity.ResponseResult;
import cn.tedu.entity.User;
import cn.tedu.service.UserService;

/**
 * 用户类控制器层
 * @author zwq
 *
 */
@RestController
@RequestMapping("/user")
public class UserController extends ControllerBase{

	@Autowired
	private UserService userService;
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public ResponseResult<Void> addUser(@RequestBody User user) {
		boolean result = userService.addUser(user);
		if(!result) {
			System.out.println("UserController.addUser()");
		}
		return new ResponseResult<Void>(SUCCEES, "注册成功");
	}
	/**
	 * 登录
	 * @param changePassword
	 * @return
	 */
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public User loginUser(@RequestBody User user) {
		User result=userService.userLogin(user);
		return result;
	}
	/**
	 * 修改密码
	 * @param changePassword
	 * @return
	 */
	@RequestMapping(value="/change_password",method = RequestMethod.POST)
	public ResponseResult<Void> chengePassword(@RequestBody ChangePassword changePassword) {
		userService.changePassword(changePassword.getOldPassword(), changePassword.getNewPassword(), changePassword.getUid());
		return new ResponseResult<Void>(SUCCEES,"修改成功");
	}
	/**
	 * 初始化用户信息
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/info/{uid}",method = RequestMethod.GET)
	public ResponseResult<User> userInfo(@PathVariable("uid")Integer uid){
		System.out.println(uid);
		User user=userService.infoUser(uid);
		return new ResponseResult<User>(SUCCEES,user);
	}
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/change_info",method = RequestMethod.POST)
	public ResponseResult<User> userInfo(@RequestBody User user){
		System.out.println(user.toString());
		userService.changeUser(user);
		return new ResponseResult<User>(SUCCEES,"修改成功");
	}
	/**
	 * 上传头像
	 * @param uid
	 * @param avatar
	 * @return
	 */
	@RequestMapping(value="/change_avatar/{uid}/{avatar}",method = RequestMethod.GET)
	public ResponseResult<Void> changeAvatar(@PathVariable("uid")Integer uid,@PathVariable("avatar")String avatar) {
		System.err.println("avatar="+avatar);
		userService.changeAvatar(avatar.replace("*", "/"), uid);
		return new ResponseResult<Void>(SUCCEES,"上传成功");
	}
}
