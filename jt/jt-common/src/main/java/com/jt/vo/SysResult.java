package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysResult {

	private Integer status;
	private String message;
	private Object data;
	
	public static SysResult fail() {
		return new SysResult(201,"fail",null);
	}
	
	public static SysResult fail(String message) {
		return new SysResult(201,message,null);
	}
	
	public static SysResult success() {
		return new SysResult(200,"success",null);
	}
	public static SysResult success(String message,Object data) {
		return new SysResult(200,message,data);
	}
	public static SysResult success(Object data) {
		return new SysResult(200,"success",data);
	}
	
}
