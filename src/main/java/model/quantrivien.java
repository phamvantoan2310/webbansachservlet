package model;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class quantrivien {
	@Id
	@GeneratedValue
	private Integer ma;
	private String ten;
	private String diachi;
	private String gioitinh;
	private Date ngaysinh;
	private String sdt;
	private String email;
	
	private String tendangnhap;
	private String matkhau;
	
	public quantrivien() {
		super();
	}

	public quantrivien(Integer ma, String ten, String diachi, String gioitinh, Date ngaysinh, String sdt, String email,
			String tendangnhap, String matkhau) {
		super();
		this.ma = ma;
		this.ten = ten;
		this.diachi = diachi;
		this.gioitinh = gioitinh;
		this.ngaysinh = ngaysinh;
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

	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
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
		return "quantrivien [ma=" + ma + ", ten=" + ten + ", diachi=" + diachi + ", gioitinh=" + gioitinh
				+ ", ngaysinh=" + ngaysinh + ", sdt=" + sdt + ", email=" + email + ", tendangnhap=" + tendangnhap
				+ ", matkhau=" + matkhau + "]";
	}
	
	
}
