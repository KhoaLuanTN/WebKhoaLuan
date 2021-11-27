package com.example.demo.enity;

import java.util.List;

public class Role {
	private long id;
	private String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TaiKhoan> dstaikhoan;

	public List<TaiKhoan> getDstaikhoan() {
		return dstaikhoan;
	}

	public void setDstaikhoan(List<TaiKhoan> dstaikhoan) {
		this.dstaikhoan = dstaikhoan;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
