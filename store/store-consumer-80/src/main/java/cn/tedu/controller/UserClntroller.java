package cn.tedu.controller;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.entity.ChangePassword;
import cn.tedu.entity.ResponseResult;
import cn.tedu.entity.User;

@RequestMapping("/user")
@RestController
public class UserClntroller extends ControllerBase{
	private final String URL="http://localhost:8001"; 
	private final String UPLOAD="static/upload";
	/**
	 * 确定上传文件的类型列表
	*/
	private static final List<String> upLoadContentType=new ArrayList<String>();

	static {
		upLoadContentType.add("image/png");
		upLoadContentType.add("image/jpeg");
		upLoadContentType.add("image/gif");
		upLoadContentType.add("image/bmp");
	}
	@Autowired
	private RestTemplate restTemplate;
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public ResponseResult<Void> addUser(User user) {
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<User> request=new HttpEntity<>(user,headers);
		return restTemplate.postForObject(URL+"/user/add", request, ResponseResult.class);
	}

	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ResponseResult<User> loginUser(User user,HttpSession session) {
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<User> request=new HttpEntity<>(user,headers);
		User result= restTemplate.postForObject(URL+"/user/login", request, User.class);
		if(result.getUid()==null) {
			return new ResponseResult<User>(FAILED,"登录失败，密码错误");
		}
		// 向Session中封装用户信息
		session.setAttribute("uid", result.getUid());
		session.setAttribute("username", result.getUsername());
		return new ResponseResult<User>(SUCCEES,result);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/change_password",method = RequestMethod.POST)
	public ResponseResult<Void> chengePassword(@RequestParam("old_password")String oldPassword,
			@RequestParam("new_password") String newPassword,HttpSession session) {
		Integer uid=getUidFromSession(session);
		ChangePassword changePassword=new ChangePassword();
		changePassword.setUid(uid);
		changePassword.setNewPassword(newPassword);
		changePassword.setOldPassword(oldPassword);
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<ChangePassword> request=new HttpEntity<>(changePassword,headers);
		return restTemplate.postForObject(URL+"/user/change_password", request, ResponseResult.class);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/info",method = RequestMethod.GET)
	public ResponseResult<Void> chengePassword(HttpSession session) {
		Integer uid=getUidFromSession(session);
		return restTemplate.getForObject(URL+"/user/info/{uid}",ResponseResult.class,uid);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/change_info",method = RequestMethod.POST)
	public ResponseResult<User> userInfo(User user,HttpSession session){
		Integer uid=getUidFromSession(session);
		user.setUid(uid);
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<User> request=new HttpEntity<>(user,headers);
		return restTemplate.postForObject(URL+"/user/change_info", request, ResponseResult.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/change_avatar",method = RequestMethod.POST)
	public ResponseResult<Void> changeAvatar(MultipartFile file,HttpSession session) {
		//获取项目路径
		String path=ClassUtils.getDefaultClassLoader().getResource("").getPath();
		if(file.isEmpty()){
			return new ResponseResult<Void>(FAILED,"文件为空,请重新上传");
		}
		if(file.getSize()>1l*1024*1024) {
			return new ResponseResult<Void>(FAILED,"文件过大,请重新上传");
		}
    	//文件类型
		String fileType=file.getContentType();
		// 检查文件类型
		if(!upLoadContentType.contains(fileType)) {
			// 类型不符(contains()为false)：抛出异常：FileContentTypeException
			return new ResponseResult<Void>(FAILED,"上传头像错误！不支持的文件类型");
		}
		Integer uid=getUidFromSession(session);
		File parent=new File(path+UPLOAD+"/"+uid+"/avarat");
		//判断文件存不存在，不存在则创建
		if(!parent.exists()) {
			parent.mkdirs();
		}
		String fileName=file.getOriginalFilename();
		//获取文件后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		//重新生成文件名
		fileName = System.nanoTime()+suffixName;
		//创建文件
		File fileImage=new File(path+"static/upload/"+uid+"/avarat",fileName);
		try {
			file.transferTo(fileImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> params=new HashMap<String, Object>();
		String avarat="http:localhost:80/upload/"+uid+"/avarat/"+fileName;
		params.put("uid",uid);
		params.put("avatar",avarat.replace("/", "*"));
		String url=URL+"/user/change_avatar/{uid}/{avatar}";
		System.out.println(URL+"/user/change_avatar/"+uid+"/"+avarat);
		return restTemplate.getForObject(url, ResponseResult.class, params);
	}


	
}
