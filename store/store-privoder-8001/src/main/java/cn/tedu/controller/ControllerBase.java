package cn.tedu.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.tedu.entity.ResponseResult;

public class ControllerBase {
	public final Integer SUCCEES=200;
	
	public final Integer FAILED=500;
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseResult<Void> exception(RuntimeException e){
		return new ResponseResult<Void>(FAILED,e.getMessage());
	}
}
