package com.task.backpac.biz.comm.exception;

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
