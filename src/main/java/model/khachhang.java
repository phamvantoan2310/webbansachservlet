package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class khachhang {
	@Id
	@GeneratedValue
	private Integer ma;
	private String ten;
	private Date tuoi;
	private String diachi;
	private String gioitinh;
	private String sdt;
	private String email;
	
	@Column(name = "tendangnhap")
	private String tendangnhap;
	
	private String matkhau;
	public khachhang() {
		super();
	}
	public khachhang(Integer ma, String ten, Date tuoi, String diachi, String gioitinh, String sdt, String email,
			String tendangnhap, String matkhau) {
		super();
		this.ma = ma;
		this.ten = ten;
		this.tuoi = tuoi;
		this.diachi = diachi;
		this.gioitinh = gioitinh;
		this.sdt = sdt;
		this.email = email;
		this.tendangnhap = tendangnhap;
		this.matkhau = matkhau;
	}
	public Integer getMa() {
		return ma;
	}
	public void setMa(Integer ma) {
		this.ma = ma;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public Date getTuoi() {
		return tuoi;
	}
	public void setTuoi(Date tuoi) {
		this.tuoi = tuoi;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTendangnhap() {
		return tendangnhap;
	}
	public void setTendangnhap(String tendangnhap) {
		this.tendangnhap = tendangnhap;
	}
	public String getMatkhau() {
		return matkhau;
	}
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
	@Override
	public String toString() {
		return "khachhang [ma=" + ma + ", ten=" + ten + ", tuoi=" + tuoi + ", diachi=" + diachi + ", gioitinh="
				+ gioitinh + ", sdt=" + sdt + ", email=" + email + ", tendangnhap=" + tendangnhap + ", matkhau="
				+ matkhau + "]";
	}
	
	
	
}
