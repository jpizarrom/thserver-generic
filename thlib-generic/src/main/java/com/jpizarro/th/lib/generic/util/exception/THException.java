package com.jpizarro.th.lib.generic.util.exception;

public class THException {
	private String message;
	private int code;
	
	public THException(){
		super();
		// TODO Auto-generated constructor stub
	}
	
	public THException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
