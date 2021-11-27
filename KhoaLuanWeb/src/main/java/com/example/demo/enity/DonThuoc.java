package com.example.demo.enity;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

public class DonThuoc {
	private long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayLapDon;
	private List<ChiTietDonThuoc> dsChiTietDonThuoc;
	private PhieuKhambenh phieukhambenh;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}


	public List<ChiTietDonThuoc> getDsChiTietDonThuoc() {
		return dsChiTietDonThuoc;
	}

	public void setDsChiTietDonThuoc(List<ChiTietDonThuoc> dsChiTietDonThuoc) {
		this.dsChiTietDonThuoc = dsChiTietDonThuoc;
	}

	public PhieuKhambenh getPhieukhambenh() {
		return phieukhambenh;
	}

	public void setPhieukhambenh(PhieuKhambenh phieukhambenh) {
		this.phieukhambenh = phieukhambenh;
	}

	public Date getNgayLapDon() {
		return ngayLapDon;
	}

	public void setNgayLapDon(Date ngayLapDon) {
		this.ngayLapDon = ngayLapDon;
	}

	public DonThuoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
