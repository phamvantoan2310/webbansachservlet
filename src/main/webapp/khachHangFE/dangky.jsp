<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Đăng ký</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
	integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
	integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
	crossorigin="anonymous"></script>
<style>
.red {
	color: red;
}
</style>
</head>
<body>



	<%String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()	;%>
	
	<%
	    String baoLoi = request.getAttribute("baoLoi")+"";
	    baoLoi = (baoLoi.equals("null"))?"":baoLoi;
	%>
	<div class="container">
		<div class="text-center">
			<h1>ĐĂNG KÝ TÀI KHOẢN</h1>
		</div>
		<div class="red" id="baoLoi">
			
		</div>
		<form class="form" action="<%=url %>/KhachhangController" method="post">
		    <input type="hidden" value="dangky" name="controller" id="dangky"/>
			<div class="row">
				<div class="col-sm-6">
					<h3>Tài khoản</h3>
					<div class="mb-3">
						<label for="tenDangNhap" class="form-label">Tên đăng nhập<span
							class="red">*</span></label> <input type="text" class="form-control"
							id="tenDangNhap" name="tenDangNhap" required="required" value="">
					</div>
					<div class="mb-3">
						<label for="matKhau" class="form-label">Mật khẩu<span
							class="red">*</span></label> <input type="password" class="form-control"
							id="matKhau" name="matKhau" required="required" onkeyup="kiemTraMatKhau()">
					</div>
					<div class="mb-3">
						<label for="matKhauNhapLai" class="form-label" >Nhập lại
							mật khẩu<span class="red">*</span> <span id="msg" class="red" ></span>
						</label> <input type="password" class="form-control" id="matKhauNhapLai"
							name="matKhauNhapLai" required="required" onkeyup="kiemTraMatKhau()">
					</div>
					<hr />
					<h3>Thông tin khách hàng</h3>
					<div class="mb-3">
						<label for="hoVaTen" class="form-label">Họ và tên</label> <input
							type="text" class="form-control" id="hoVaTen" name="hoVaTen" value="">
					</div>
					<div class="mb-3">
						<label for="gioiTinh" class="form-label">Giới tính</label> <select
							class="form-control" id="gioiTinh" name="gioiTinh">
							<option></option>
							<option value="Nam"  >Nam</option>
							<option value="Nữ" > Nữ</option>
							<option value="Khác"  >Khác</option>
						</select>
					</div>
					<div class="mb-3">
						<label for="ngaySinh" class="form-label">Ngày sinh</label> <input
							type="date" class="form-control" id="ngaySinh" name="ngaySinh" value="">
					</div>
				</div>
				<div class="col-sm-6">
					<h3>Địa chỉ</h3>
					<div class="mb-3">
						<label for="diaChiKhachHang" class="form-label">Địa chỉ
							khách hàng</label> <input type="text" class="form-control"
							id="diaChiKhachHang" name="diaChiKhachHang" value="">
					</div>
					
					<div class="mb-3">
						<label for="dienThoai" class="form-label">Điện thoại</label> <input
							type="tel" class="form-control" id="dienThoai" name="dienThoai" value="">
					</div>
					<div class="mb-3">
						<label for="email" class="form-label">Email</label> <input
							type="email" class="form-control" id="email" name="email"  value="">
					</div>
					<hr />
					<div class="mb-3">
						<label for="dongYDieuKhoan" class="form-label">Đồng ý với
							<a>điều khoản của công ty </a><span id="red">*</span>
						</label> <input type="checkbox" class="form-check-input"
							id="dongYDieuKhoan" name="dongYDieuKhoan" required="required" onchange="xuLyChonDongY()">
					</div>
					<div class="mb-3">
						<label for="dongYNhanMail" class="form-label">Đồng ý nhận
							email</label> <input type="checkbox" class="form-check-input"
							id="dongYNhanMail" name="dongYNhanMail">
					</div>
					<input class="btn btn-primary form-control" type="submit"
						value="Đăng Ký" name="submit" id="submit" style="visibility: hidden;" />
				</div>
			</div>
			<hr/>
			<div><p><span class="red"><%=baoLoi %></span></p></div>
		</form>
	</div>
	
	
</body>

<script>
	function kiemTraMatKhau(){
		matKhau = document.getElementById("matKhau").value;
		matKhauNhapLai = document.getElementById("matKhauNhapLai").value;
		if(matKhau!=matKhauNhapLai){
			document.getElementById("msg").innerHTML = "Mật khẩu không khớp!";
			return false;
		}else{
			document.getElementById("msg").innerHTML = "";
			return true;
		}
	}
	
	function xuLyChonDongY(){
		dongYDieuKhoan = document.getElementById("dongYDieuKhoan");
		if(dongYDieuKhoan.checked==true){
			document.getElementById("submit").style.visibility="visible";
		} else {
			document.getElementById("submit").style.visibility="hidden";
		}
	}
</script>

</html>