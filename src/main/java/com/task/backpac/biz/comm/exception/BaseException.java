/*
 * Copyright (c) 2014 jinotech Co., Ltd
 * All right reserved.
 */
package com.task.backpac.biz.comm.exception;

/**
 * @Title   : Exception 처리
 * @date    : 2021-04-02
 */
public class BaseException extends RuntimeException {
	public BaseException(String msg, Throwable t) {
		super(msg, t);
	}
	public BaseException(String msg) {
		super(msg);
	}
	public BaseException() {
		super();
	}
}
