package com.hycloud.common.result;

public class ItemResult<T> extends CommonResult {
	T item;

	public ItemResult(String res, String msg) {
		this.res = res;
		this.msg = msg;
	}
	public ItemResult() {}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}
	
}
