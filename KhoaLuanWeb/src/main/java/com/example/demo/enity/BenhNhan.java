package com.example.demo.enity;

import java.util.Date;
import java.util.List;

public class BenhNhan {
    private long id;
    private String ten;
    private String diaChi;
    private String cmnd;
    private String email;
    private boolean gioiTinh;
    private Date ngaySinh;
    private String soDienThoai;
    private TaiKhoan taiKhoan;
    private List<PhieuKhambenh> dsphieukhambenh;

    private List<LichHen> dslichhen;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public List<PhieuKhambenh> getDsphieukhambenh() {
        return dsphieukhambenh;
    }

    public void setDsphieukhambenh(List<PhieuKhambenh> dsphieukhambenh) {
        this.dsphieukhambenh = dsphieukhambenh;
    }

    public List<LichHen> getDslichhen() {
        return dslichhen;
    }

    public void setDslichhen(List<LichHen> dslichhen) {
        this.dslichhen = dslichhen;
    }

    public BenhNhan() {
    }
}
