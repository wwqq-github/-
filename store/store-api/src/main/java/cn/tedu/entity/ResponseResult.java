package cn.tedu.entity;

import java.io.Serializable;

public class ResponseResult <T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4494585822287968057L;

	public Integer state;
	
	public String message;
	
	public T data;

	public ResponseResult() {
		super();
	}

	public ResponseResult(Integer state, String message) {
		super();
		this.state = state;
		this.message = message;
	}

	public ResponseResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}

	public Integer getstate() {
		return state;
	}

	public void setstate(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseResult [" + (state != null ? "state=" + state + ", " : "")
				+ (message != null ? "message=" + message + ", " : "") + (data != null ? "data=" + data.toString() : "") + "]";
	}
	
	
}
