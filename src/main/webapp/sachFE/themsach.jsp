<%@page import="model.quantrivien"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm Sách</title>
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

<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();

String baoLoi = request.getAttribute("baoLoi")+"";
baoLoi = (baoLoi.equals("null"))?"":baoLoi;
%>

<body>

<!-- header -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- end header -->
    
<%
    Object obj = session.getAttribute("qtv");
    quantrivien qtv = null;
    if(obj!=null){
    	qtv = (quantrivien)obj;
    }
    if(qtv==null){
%>   	
    <p>chưa đăng nhập</p>
<%
    }else{
    	
    
%>

        
    <p class="red"><%=baoLoi %></p>

	<div class="container">
		<form action="<%=url%>/sachController" method="post">
			<input type="hidden" name="controller" value="themsach"
				id="controller" />
			<h1 class="text-center">Nhập thông tin sách</h1>
			<div class="input-group mb-4">
				<span class="input-group-text" id="basic-addon1">Tên sách</span> <input
					type="text" class="form-control" placeholder="nhập tên sách"
					aria-label="Username" aria-describedby="basic-addon1" name="tenSach">
			</div>
			<div class="input-group mb-4">
				<span class="input-group-text" id="basic-addon1">Tác giả</span> <input
					type="text" class="form-control" placeholder="nhập tên tác giả"
					aria-label="Username" aria-describedby="basic-addon1" name="tacGia">
			</div>
			<div class="input-group mb-4">
				<span class="input-group-text" id="basic-addon1">Thể loại</span> <input
					type="text" class="form-control" placeholder="nhập tên thể loại"
					aria-label="Username" aria-describedby="basic-addon1" name="theLoai">
			</div>
			<div class="input-group mb-4">
				<span class="input-group-text" id="basic-addon1">Nhà xuất bản</span>
				<input type="text" class="form-control"
					placeholder="nhập tên nhà xuất bản" aria-label="Username"
					aria-describedby="basic-addon1" name="nhaXuatBan">
			</div>
			<div class="input-group mb-4">
				<span class="input-group-text" id="basic-addon1">Năm xuất bản</span>
				<input type="text" class="form-control" placeholder="nhập tên sách"
					aria-label="Username" aria-describedby="basic-addon1" name="namXuatBan">
			</div>
			<div class="input-group mb-4">
				<span class="input-group-text" id="basic-addon1">Giá</span> <input
					type="text" class="form-control" placeholder="nhập giá sách"
					aria-label="Username" aria-describedby="basic-addon1" name="gia">
			</div>
			
			
			<button type="submit" class="btn btn-primary">Thêm sách</button>
		</form>
	</div>
	
	<hr/>
	
	<div class="container">
		<form action="<%=url%>/tacGiaFE/themtacgia.jsp" method="post">
			<button type="submit" class="btn btn-primary">Thêm Tác Giả</button>
		</form>
	</div>
<%
    } 
%>	
	
	<!-- footer -->
	<%@ include file="footer.jsp" %>
	<!-- end footer -->

</body>
</html>