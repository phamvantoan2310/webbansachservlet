package model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class tacgia {
	
	@Id
	@GeneratedValue
	private Integer ma;
	private String ten;
	private Date ngaysinh;
	
	@OneToMany(mappedBy = "tacGia")
	private List<sach> danhsachsach;
	
	public tacgia() {
		super();
	}
	
	public tacgia(Integer ma, String ten, Date ngaysinh, List<sach> danhsachsach) {
		super();
		this.ma = ma;
		this.ten = ten;
		this.ngaysinh = ngaysinh;
		this.danhsachsach = danhsachsach;
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
	public Date getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	
	public void themSach(sach s) {
		danhsachsach.add(s);
	}
	
	@Override
	public String toString() {
		return "tacgia [ma=" + ma + ", ten=" + ten + ", ngaysinh=" + ngaysinh + "]";
	}
	
	
}
