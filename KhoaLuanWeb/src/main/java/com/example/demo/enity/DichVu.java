package com.example.demo.enity;


import javax.validation.constraints.NotNull;

public class DichVu {
	private long id;
	private String ghiChu;
	private double donGia;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public DichVu() {
		super();
		// TODO Auto-generated constructor stub
	}

}
