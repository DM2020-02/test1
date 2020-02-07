package com.jt.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jt.vo.SysResult;

@RestControllerAdvice
public class SysResultExceptionAOP {

	/**
	 * 统一返回数据 SysResult对象 status=201
	 */
	@ExceptionHandler(RuntimeException.class)
	public SysResult fail() {
		
		return SysResult.fail();
	}

}
