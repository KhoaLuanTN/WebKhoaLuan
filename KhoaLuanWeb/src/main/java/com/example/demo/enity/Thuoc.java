package com.example.demo.enity;


public class Thuoc {
	private long id;
	private String tenThuoc;
	private double donGia;
	private String donVi;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTenThuoc() {
		return tenThuoc;
	}

	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}


	public Thuoc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Thuoc(String tenThuoc, double donGia, String donVi) {
		this.tenThuoc = tenThuoc;
		this.donGia = donGia;
		this.donVi = donVi;
	}
}
