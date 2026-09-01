package com.example.transactionstarter.dto;

public class ApiResponse<T> {

	private String href;
	private T data;
	public String getHref() {
		return href;
	}
	
	public ApiResponse() {
	}
	
	public ApiResponse(String href, T data) {
		super();
		this.href = href;
		this.data = data;
	}

	public void setHref(String href) {
		this.href = href;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
