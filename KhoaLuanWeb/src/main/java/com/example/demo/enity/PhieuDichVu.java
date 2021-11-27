package com.example.demo.enity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


public class PhieuDichVu {
	private long id;
	private DichVu dichVu;

	private PhieuKhambenh phieukhambenh;

	private String ghiChu;
	private String ketLuan;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date ngayTaoPhieu;
	private Float giaTienDV;

	public Float getGiaTienDV() {
		return giaTienDV;
	}

	public void setGiaTienDV(Float giaTienDV) {
		this.giaTienDV = giaTienDV;
	}

	public PhieuKhambenh getPhieukhambenh() {
		return phieukhambenh;
	}

	public void setPhieukhambenh(PhieuKhambenh phieukhambenh) {
		this.phieukhambenh = phieukhambenh;
	}

	public Date getNgayTaoPhieu() {
		return ngayTaoPhieu;
	}

	public void setNgayTaoPhieu(Date ngayTaoPhieu) {
		this.ngayTaoPhieu = ngayTaoPhieu;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public DichVu getDichVu() {
		return dichVu;
	}
	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}

	public String getKetLuan() {
		return ketLuan;
	}
	public void setKetLuan(String ketLuan) {
		this.ketLuan = ketLuan;
	}
	public PhieuDichVu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
}
