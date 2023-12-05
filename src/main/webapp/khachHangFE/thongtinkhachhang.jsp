<%@page import="java.sql.Date"%>
<%@page import="model.khachhang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Thông tin khách hàng</title>
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
	    
	    Object obj = session.getAttribute("khachHang");
	    khachhang kh = null;
	    if(obj!=null){
	    	kh = (khachhang)obj;
	    }
	    if(kh == null){
	%>
	    <div class="container">
	        <p>Chưa đăng nhập</p>
	    </div>
	<%
	    }else{
	    	String hoVaTen = kh.getTen();
	    	String gioiTinh = kh.getGioitinh();
	    	Date ngaySinh = kh.getTuoi();
	    	String diaChi = kh.getDiachi();
	    	String soDienThoai = kh.getSdt();
	    	String mail = kh.getEmail();
 	%>
 	
 	    <div class="container">
		<div class="text-center">
			<h1>THÔNG TIN KHÁCH HÀNG</h1>
		</div>
		<div class="red" id="baoLoi">
			
		</div>
		<form class="form" action="<%=url %>/KhachhangController" method="post">
		    <input type="hidden" value="thaydoithongtin" name="controller" id="thaydoithongtin"/>
			<div class="row">
				<div class="col-sm-6">
					
					<h3>Thông tin khách hàng</h3>
					<div class="mb-3">
						<label for="hoVaTen" class="form-label">Họ và tên</label> <input
							type="text" class="form-control" id="hoVaTen" name="hoVaTen" value="<%=hoVaTen%>">
					</div>
					<div class="mb-3">
						<label for="gioiTinh" class="form-label">Giới tính</label> <select
							class="form-control" id="gioiTinh" name="gioiTinh">
							<option></option>
							<option value="Nam" <%=(gioiTinh.equals("Nam"))?"selected='selected'":"" %>  >Nam</option>
							<option value="Nữ" <%=(gioiTinh.equals("Nữ"))?"selected='selected'":"" %> > Nữ</option>
							<option value="Khác" <%=(gioiTinh.equals("Khác"))?"selected='selected'":"" %> >Khác</option>
						</select>
					</div>
					<div class="mb-3">
						<label for="ngaySinh" class="form-label">Ngày sinh</label> <input
							type="date" class="form-control" id="ngaySinh" name="ngaySinh" value="<%=ngaySinh%>">
					</div>
				</div>
				<div class="col-sm-6">
					<h3>Địa chỉ</h3>
					<div class="mb-3">
						<label for="diaChiKhachHang" class="form-label">Địa chỉ
							khách hàng</label> <input type="text" class="form-control"
							id="diaChiKhachHang" name="diaChiKhachHang" value="<%=diaChi%>">
					</div>
					
					<div class="mb-3">
						<label for="dienThoai" class="form-label">Điện thoại</label> <input
							type="tel" class="form-control" id="dienThoai" name="dienThoai" value="<%=soDienThoai%>">
					</div>
					<div class="mb-3">
						<label for="email" class="form-label">Email</label> <input
							type="email" class="form-control" id="email" name="email"  value="<%=mail%>">
					</div>
					<hr />
					<div class="mb-3">
						<label for="dongY" class="form-label">Đồng ý thay đổi thông tin <span id="red">*</span>
						</label> <input type="checkbox" class="form-check-input"
							id="dongY" name="dongY" required="required" onchange="xuLyChonDongY()">
					</div>
					<input class="btn btn-primary form-control" type="submit"
						value="Cập nhật thông tin" name="submit" id="submit" style="visibility: hidden;" />
				</div>
			</div>
			<hr/>
			<div><p><span class="red"><%=baoLoi %></span></p></div>
		</form>
	</div>
	<%
	    }
	%>
	
	
	
</body>

<script>
	
	
	function xuLyChonDongY(){
		dongY = document.getElementById("dongY");
		if(dongY.checked==true){
			document.getElementById("submit").style.visibility="visible";
		} else {
			document.getElementById("submit").style.visibility="hidden";
		}
	}
</script>

</html>