package com.hycloud.common.result;

public class CommonResult {
	String res;
	String msg;
	
	public CommonResult() {}
	
	public CommonResult(String res, String msg) {
		this.res = res;
		this.msg = msg;
	}
	
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "{\"res\":\"" + res + "\", \"msg\":\"" + msg + "\"}";
	}
	
}
